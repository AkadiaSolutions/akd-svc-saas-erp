package br.akd.svc.akadia.services.site;

import br.akd.svc.akadia.models.dto.site.ClienteSistemaDto;
import br.akd.svc.akadia.models.entities.global.EnderecoEntity;
import br.akd.svc.akadia.models.entities.global.TelefoneEntity;
import br.akd.svc.akadia.models.entities.site.CartaoEntity;
import br.akd.svc.akadia.models.entities.site.ClienteSistemaEntity;
import br.akd.svc.akadia.models.entities.site.PlanoEntity;
import br.akd.svc.akadia.models.enums.global.TipoTelefoneEnum;
import br.akd.svc.akadia.models.enums.site.FormaPagamentoSistemaEnum;
import br.akd.svc.akadia.models.enums.site.StatusPlanoEnum;
import br.akd.svc.akadia.proxy.asaas.AsaasProxy;
import br.akd.svc.akadia.proxy.asaas.requests.AssinaturaRequest;
import br.akd.svc.akadia.proxy.asaas.requests.ClienteSistemaRequest;
import br.akd.svc.akadia.proxy.asaas.requests.CreditCardHolderInfoRequest;
import br.akd.svc.akadia.proxy.asaas.requests.CreditCardRequest;
import br.akd.svc.akadia.proxy.asaas.responses.AssinaturaResponse;
import br.akd.svc.akadia.proxy.asaas.responses.ClienteSistemaResponse;
import br.akd.svc.akadia.repositories.site.impl.ClienteSistemaRepositoryImpl;
import br.akd.svc.akadia.services.global.exceptions.FeignConnectionException;
import br.akd.svc.akadia.services.global.exceptions.InvalidRequestException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

@Service
public class ClienteSistemaService {

    @Autowired
    ClienteSistemaRepositoryImpl clienteSistemaRepositoryImpl;

    @Autowired
    AsaasProxy asaasProxy;

    ObjectMapper objectMapper = new ObjectMapper();

    public void validaSeEmailJaExiste(ClienteSistemaDto clienteSistemaDto) {
        if (clienteSistemaRepositoryImpl.implementaBuscaPorEmail(clienteSistemaDto.getEmail()).isPresent())
            throw new InvalidRequestException("O e-mail informado já existe");
    }

    public void validaSeCpfJaExiste(ClienteSistemaDto clienteSistemaDto) {
        if (clienteSistemaRepositoryImpl.implementaBuscaPorCpf(clienteSistemaDto.getCpf()).isPresent())
            throw new InvalidRequestException("O cpf informado já existe");
    }

    public ClienteSistemaEntity cadastraNovoCliente(ClienteSistemaDto clienteSistemaDto) {

        ClienteSistemaEntity clienteSistema =
                ClienteSistemaEntity.builder()
                        .dataCadastro(LocalDate.now().toString())
                        .horaCadastro(LocalTime.now().toString())
                        .dataNascimento(clienteSistemaDto.getDataNascimento())
                        .email(clienteSistemaDto.getEmail())
                        .senha(clienteSistemaDto.getSenha())
                        .cpf(clienteSistemaDto.getCpf())
                        .nome(clienteSistemaDto.getNome())
                        .telefone(TelefoneEntity.builder()
                                .prefixo(clienteSistemaDto.getTelefone().getPrefixo())
                                .numero(clienteSistemaDto.getTelefone().getNumero())
                                .tipoTelefoneEnum(clienteSistemaDto.getTelefone().getTipoTelefoneEnum())
                                .build())
                        .saldo(0.00)
                        .cartao(clienteSistemaDto.getPlano().getFormaPagamentoSistemaEnum().equals(FormaPagamentoSistemaEnum.CREDIT_CARD)
                                ? CartaoEntity.builder()
                                .ccv(clienteSistemaDto.getCartao().getCcv())
                                .ativo(true)
                                .nomePortador(clienteSistemaDto.getCartao().getNomePortador())
                                .bandeiraCartaoEnum(clienteSistemaDto.getCartao().getBandeiraCartaoEnum())
                                .mesExpiracao(clienteSistemaDto.getCartao().getMesExpiracao())
                                .anoExpiracao(clienteSistemaDto.getCartao().getAnoExpiracao())
                                .numero(clienteSistemaDto.getCartao().getNumero())
                                .cpfCnpj(clienteSistemaDto.getCartao().getCpfCnpj())
                                .tokenCartao(null)
                                .build()
                                : null)
                        .plano(PlanoEntity.builder()
                                .dataContratacao(LocalDate.now().toString())
                                .horaContratacao(LocalTime.now().toString())
                                .tipoPlanoEnum(clienteSistemaDto.getPlano().getTipoPlanoEnum())
                                .formaPagamentoSistemaEnum(clienteSistemaDto.getPlano().getFormaPagamentoSistemaEnum())
                                .statusPlanoEnum(StatusPlanoEnum.PERIODO_DE_TESTES)
                                .codigoAssinaturaAsaas(null)
                                .build())
                        .endereco(clienteSistemaDto.getEndereco() != null
                                ? EnderecoEntity.builder()
                                .codigoPostal(clienteSistemaDto.getEndereco().getCodigoPostal())
                                .logradouro(clienteSistemaDto.getEndereco().getLogradouro())
                                .numero(clienteSistemaDto.getEndereco().getNumero())
                                .bairro(clienteSistemaDto.getEndereco().getBairro())
                                .cidade(clienteSistemaDto.getEndereco().getCidade())
                                .estadoEnum(clienteSistemaDto.getEndereco().getEstadoEnum())
                                .build()
                                : null)
                        .codigoClienteAsaas(null)
                        .empresas(new ArrayList<>())
                        .pagamentos(new ArrayList<>())
                        .build();

        clienteSistema.setCodigoClienteAsaas(realizaCadastroClienteAsaas(clienteSistema).getId());

        AssinaturaResponse assinaturaResponse = criaAssinaturaAsaas(clienteSistema);
        clienteSistema.getPlano().setCodigoAssinaturaAsaas(assinaturaResponse.getId());
        if (assinaturaResponse.getCreditCard() != null)
            clienteSistema.getCartao().setTokenCartao(assinaturaResponse.getCreditCard().getCreditCardToken());

        return clienteSistemaRepositoryImpl.implementaPersistencia(clienteSistema);
    }

    private ClienteSistemaResponse realizaCadastroClienteAsaas(ClienteSistemaEntity clienteSistema) {

        ClienteSistemaRequest clienteSistemaRequest = ClienteSistemaRequest.builder()
                .name(clienteSistema.getNome())
                .email(clienteSistema.getEmail())
                .phone(clienteSistema.getTelefone().getPrefixo().toString() + clienteSistema.getTelefone().getNumero())
                .mobilePhone(criaNumeroMobileComObjetoTelefone(clienteSistema.getTelefone()))
                .cpfCnpj(clienteSistema.getCpf())
                .build();

        ResponseEntity<?> cadastraClienteAsaas;

        try {
            cadastraClienteAsaas = asaasProxy.cadastraNovoCliente(clienteSistemaRequest, System.getenv("TOKEN_ASAAS"));
        } catch (Exception e) {
            throw new FeignConnectionException("Ocorreu uma falha na comunicação com a integradora de pagamentos: " + e.getMessage());
        }

        if (cadastraClienteAsaas.getStatusCodeValue() != 200)
            throw new InvalidRequestException("Ocorreu um erro no processo de criação do cliente: "
                    + cadastraClienteAsaas.getBody());

        return objectMapper.convertValue(cadastraClienteAsaas.getBody(), ClienteSistemaResponse.class);
    }

    private AssinaturaResponse criaAssinaturaAsaas(ClienteSistemaEntity clienteSistema) {

        AssinaturaRequest assinaturaRequest =
                AssinaturaRequest.builder()
                        .customer(clienteSistema.getCodigoClienteAsaas())
                        .nextDueDate(LocalDate.now().plusDays(7L).toString())
                        .cycle("MONTHLY")
                        .value(clienteSistema.getPlano().getTipoPlanoEnum().getValor())
                        .billingType(clienteSistema.getPlano().getFormaPagamentoSistemaEnum().toString())
                        .description("Assinatura de plano " + clienteSistema.getPlano().getTipoPlanoEnum().getDesc())
                        .creditCard(
                                clienteSistema.getPlano().getFormaPagamentoSistemaEnum().equals(FormaPagamentoSistemaEnum.CREDIT_CARD)
                                        ? CreditCardRequest.builder()
                                        .holderName(clienteSistema.getCartao().getNomePortador())
                                        .number(clienteSistema.getCartao().getNumero().toString())
                                        .expiryMonth(clienteSistema.getCartao().getMesExpiracao().toString())
                                        .expiryYear(clienteSistema.getCartao().getAnoExpiracao().toString())
                                        .ccv(clienteSistema.getCartao().getCcv().toString())
                                        .build()
                                        : null
                        )
                        .creditCardHolderInfo(
                                clienteSistema.getPlano().getFormaPagamentoSistemaEnum().equals(FormaPagamentoSistemaEnum.CREDIT_CARD)
                                        ? CreditCardHolderInfoRequest.builder()
                                        .name(clienteSistema.getNome())
                                        .email(clienteSistema.getEmail())
                                        .cpfCnpj(clienteSistema.getCpf())
                                        .phone(clienteSistema.getTelefone().getPrefixo().toString()
                                                + clienteSistema.getTelefone().getNumero().toString())
                                        .mobilePhone(criaNumeroMobileComObjetoTelefone(clienteSistema.getTelefone()))
                                        .postalCode(clienteSistema.getEndereco().getCodigoPostal())
                                        .addressNumber(clienteSistema.getEndereco().getNumero().toString())
                                        .build()
                                        : null
                        )
                        .build();

        ResponseEntity<?> cadastraAssinaturaAsaas;

        try {
            cadastraAssinaturaAsaas = asaasProxy.cadastraNovoPlano(assinaturaRequest, System.getenv("TOKEN_ASAAS"));
        } catch (Exception e) {
            throw new FeignConnectionException("Ocorreu uma falha na comunicação com a integradora de pagamentos: " + e.getMessage());
        }

        if (cadastraAssinaturaAsaas.getStatusCodeValue() != 200)
            throw new InvalidRequestException("Ocorreu um erro no processo de criação da assinatura: "
                    + cadastraAssinaturaAsaas.getBody());

        return objectMapper.convertValue(cadastraAssinaturaAsaas.getBody(), AssinaturaResponse.class);
    }

    private String criaNumeroMobileComObjetoTelefone(TelefoneEntity telefone) {
        String numeroMobile = null;
        if (!telefone.getTipoTelefoneEnum().equals(TipoTelefoneEnum.FIXO))
            numeroMobile = telefone.getPrefixo().toString() + telefone.getNumero().toString();
        return numeroMobile;
    }

}
