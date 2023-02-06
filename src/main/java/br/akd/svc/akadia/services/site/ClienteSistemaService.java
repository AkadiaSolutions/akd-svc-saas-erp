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
import br.akd.svc.akadia.services.exceptions.FeignConnectionException;
import br.akd.svc.akadia.services.exceptions.InvalidRequestException;
import br.akd.svc.akadia.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ClienteSistemaService {

    @Autowired
    ClienteSistemaRepositoryImpl clienteSistemaRepositoryImpl;

    @Autowired
    AsaasProxy asaasProxy;

    public static final String TOKEN_ASAAS = "TOKEN_ASAAS";
    public static final String FALHA_COMUNICACAO_ASAAS =
            "Ocorreu uma falha na comunicação com a integradora de pagamentos: ";

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
                                .complemento(clienteSistemaDto.getEndereco().getComplemento())
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

    public ClienteSistemaResponse realizaCadastroClienteAsaas(ClienteSistemaEntity clienteSistema) {

        ClienteSistemaRequest clienteSistemaRequest = ClienteSistemaRequest.builder()
                .name(clienteSistema.getNome())
                .email(clienteSistema.getEmail())
                .phone(clienteSistema.getTelefone().getPrefixo().toString() + clienteSistema.getTelefone().getNumero())
                .mobilePhone(criaNumeroMobileComObjetoTelefone(clienteSistema.getTelefone()))
                .cpfCnpj(clienteSistema.getCpf())
                .build();

        ResponseEntity<ClienteSistemaResponse> cadastraClienteAsaas;

        try {
            cadastraClienteAsaas = asaasProxy.cadastraNovoCliente(clienteSistemaRequest, System.getenv(TOKEN_ASAAS));
        } catch (Exception e) {
            throw new FeignConnectionException(FALHA_COMUNICACAO_ASAAS + e.getMessage());
        }

        if (cadastraClienteAsaas.getStatusCodeValue() != 200)
            throw new InvalidRequestException("Ocorreu um erro no processo de criação do cliente: "
                    + cadastraClienteAsaas.getBody());

        return cadastraClienteAsaas.getBody();
    }

    public AssinaturaResponse criaAssinaturaAsaas(ClienteSistemaEntity clienteSistema) {

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
                                        .addressComplement(clienteSistema.getEndereco().getComplemento())
                                        .build()
                                        : null
                        )
                        .build();

        ResponseEntity<AssinaturaResponse> cadastraAssinaturaAsaas;

        try {
            cadastraAssinaturaAsaas = asaasProxy.cadastraNovoPlano(assinaturaRequest, System.getenv(TOKEN_ASAAS));
        } catch (Exception e) {
            throw new FeignConnectionException(FALHA_COMUNICACAO_ASAAS + e.getMessage());
        }

        if (cadastraAssinaturaAsaas.getStatusCodeValue() != 200)
            throw new InvalidRequestException("Ocorreu um erro no processo de criação da assinatura: "
                    + cadastraAssinaturaAsaas.getBody());

        return cadastraAssinaturaAsaas.getBody();
    }

    private String criaNumeroMobileComObjetoTelefone(TelefoneEntity telefone) {
        String numeroMobile = null;
        if (!telefone.getTipoTelefoneEnum().equals(TipoTelefoneEnum.FIXO))
            numeroMobile = telefone.getPrefixo().toString() + telefone.getNumero().toString();
        return numeroMobile;
    }

    public ClienteSistemaEntity atualizaDadosCliente(Long id, ClienteSistemaDto clienteSistemaDto) {
        Optional<ClienteSistemaEntity> clienteOptional = clienteSistemaRepositoryImpl.implementaBuscaPorId(id);
        ClienteSistemaEntity clienteSistema;

        if (clienteOptional.isPresent()) clienteSistema = clienteOptional.get();
        else throw new ObjectNotFoundException("Nenhum cliente foi encontrado com o id informado");

        if (!clienteSistema.getEmail().equals(clienteSistemaDto.getEmail()))
            validaSeEmailJaExiste(clienteSistemaDto);

        ClienteSistemaEntity clienteAtualizado = ClienteSistemaEntity.builder()
                .id(clienteSistema.getId())
                .codigoClienteAsaas(clienteSistema.getCodigoClienteAsaas())
                .dataCadastro(clienteSistema.getDataCadastro())
                .horaCadastro(clienteSistema.getHoraCadastro())
                .dataNascimento(clienteSistema.getDataNascimento())
                .email(clienteSistemaDto.getEmail())
                .nome(clienteSistema.getNome())
                .senha(clienteSistemaDto.getSenha())
                .cpf(clienteSistema.getCpf())
                .saldo(clienteSistema.getSaldo())
                .plano(clienteSistema.getPlano())
                .telefone(TelefoneEntity.builder()
                        .tipoTelefoneEnum(clienteSistemaDto.getTelefone().getTipoTelefoneEnum())
                        .prefixo(clienteSistemaDto.getTelefone().getPrefixo())
                        .numero(clienteSistemaDto.getTelefone().getNumero())
                        .build())
                .endereco(EnderecoEntity.builder()
                        .logradouro(clienteSistemaDto.getEndereco().getLogradouro())
                        .numero(clienteSistemaDto.getEndereco().getNumero())
                        .bairro(clienteSistemaDto.getEndereco().getBairro())
                        .codigoPostal(clienteSistemaDto.getEndereco().getCodigoPostal())
                        .cidade(clienteSistemaDto.getEndereco().getCidade())
                        .estadoEnum(clienteSistemaDto.getEndereco().getEstadoEnum())
                        .build())
                .cartao(clienteSistema.getCartao())
                .pagamentos(clienteSistema.getPagamentos())
                .empresas(clienteSistema.getEmpresas())
                .build();

        clienteSistemaRepositoryImpl.implementaPersistencia(clienteAtualizado);
        atualizaDadosClienteAsaas(clienteAtualizado);

        return clienteAtualizado;
    }

    public void atualizaDadosClienteAsaas(ClienteSistemaEntity clienteAtualizado) {
        ClienteSistemaRequest clienteSistemaRequest = ClienteSistemaRequest.builder()
                .name(clienteAtualizado.getNome())
                .email(clienteAtualizado.getEmail())
                .phone(clienteAtualizado.getTelefone().getPrefixo().toString() + clienteAtualizado.getTelefone().getNumero())
                .mobilePhone(criaNumeroMobileComObjetoTelefone(clienteAtualizado.getTelefone()))
                .cpfCnpj(clienteAtualizado.getCpf())
                .postalCode(clienteAtualizado.getEndereco().getCodigoPostal())
                .address(clienteAtualizado.getEndereco().getLogradouro())
                .addressNumber(clienteAtualizado.getEndereco().getNumero().toString())
                .complement(clienteAtualizado.getEndereco().getComplemento())
                .province(clienteAtualizado.getEndereco().getBairro())
                .build();

        ResponseEntity<ClienteSistemaResponse> atualizaClienteAsaas;

        try {
            atualizaClienteAsaas = asaasProxy.atualizaDadosCliente(clienteAtualizado.getCodigoClienteAsaas(),
                    clienteSistemaRequest, System.getenv(TOKEN_ASAAS));
        } catch (Exception e) {
            throw new FeignConnectionException(FALHA_COMUNICACAO_ASAAS + e.getMessage());
        }

        if (atualizaClienteAsaas.getStatusCodeValue() != 200)
            throw new InvalidRequestException("Ocorreu um erro no processo atualização dos dados cadastrais do cliente: "
                    + atualizaClienteAsaas.getBody());
    }

}
