package br.akd.svc.akadia.services.site;

import br.akd.svc.akadia.models.dto.site.ClienteSistemaDto;
import br.akd.svc.akadia.models.entities.global.TelefoneEntity;
import br.akd.svc.akadia.models.entities.site.CartaoEntity;
import br.akd.svc.akadia.models.entities.site.ClienteSistemaEntity;
import br.akd.svc.akadia.models.entities.site.PlanoEntity;
import br.akd.svc.akadia.models.enums.site.FormaPagamentoSistemaEnum;
import br.akd.svc.akadia.models.enums.site.StatusPlanoEnum;
import br.akd.svc.akadia.proxy.asaas.AsaasProxy;
import br.akd.svc.akadia.proxy.asaas.requests.ClienteSistemaRequest;
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
                        .codigoClienteAsaas(null)
                        .cartao(clienteSistemaDto.getPlano().getFormaPagamentoSistemaEnum().equals(FormaPagamentoSistemaEnum.CARTAO_CREDITO)
                                ? CartaoEntity.builder()
                                .cvv(clienteSistemaDto.getCartao().getCvv())
                                .ativo(true)
                                .bandeiraCartaoEnum(clienteSistemaDto.getCartao().getBandeiraCartaoEnum())
                                .mesExpiracao(clienteSistemaDto.getCartao().getMesExpiracao())
                                .anoExpiracao(clienteSistemaDto.getCartao().getAnoExpiracao())
                                .numero(clienteSistemaDto.getCartao().getNumero())
                                .cpfCnpj(clienteSistemaDto.getCartao().getCpfCnpj())
                                .pagamentos(new ArrayList<>())
                                .tokenCartao(null)
                                .build()
                                : null)
                        .plano(PlanoEntity.builder()
                                .dataContratacao(LocalDate.now().toString())
                                .horaContratacao(LocalTime.now().toString())
                                .tipoPlanoEnum(clienteSistemaDto.getPlano().getTipoPlanoEnum())
                                .formaPagamentoSistemaEnum(clienteSistemaDto.getPlano().getFormaPagamentoSistemaEnum())
                                .statusPlanoEnum(StatusPlanoEnum.PERIODO_DE_TESTES)
                                .build())
                        .endereco(null)
                        .empresas(new ArrayList<>())
                        .pagamentos(new ArrayList<>())
                        .build();


        clienteSistema.setCodigoClienteAsaas(realizaCadastroClienteAsaas(clienteSistema).getId());
        return clienteSistemaRepositoryImpl.implementaPersistencia(clienteSistema);
    }

    private ClienteSistemaResponse realizaCadastroClienteAsaas(ClienteSistemaEntity clienteSistema) {

        ClienteSistemaRequest clienteSistemaRequest = ClienteSistemaRequest.builder()
                .name(clienteSistema.getNome())
                .email(clienteSistema.getEmail())
                .phone(clienteSistema.getTelefone().getPrefixo().toString() + clienteSistema.getTelefone().getNumero())
                .cpfCnpj(clienteSistema.getCpf())
                .build();

        ResponseEntity<?> cadastraClienteAsaas;

        try {
            cadastraClienteAsaas = asaasProxy.cadastraCliente(clienteSistemaRequest, System.getenv("TOKEN_ASAAS"));
        } catch (Exception e) {
            throw new FeignConnectionException("Ocorreu uma falha na comunicação com a integradora de pagamentos: " + e.getMessage());
        }

        if (cadastraClienteAsaas.getStatusCodeValue() != 200)
            throw new InvalidRequestException("Ocorreu um erro no processo de criação do cliente: "
                    + cadastraClienteAsaas.getBody());

        return objectMapper.convertValue(cadastraClienteAsaas.getBody(), ClienteSistemaResponse.class);
    }

    private void criaAssinaturaClienteAsaas(ClienteSistemaEntity clienteSistema) {

    }

}
