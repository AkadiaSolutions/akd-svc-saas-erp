package br.akd.svc.akadia.services.site;

import br.akd.svc.akadia.models.dto.site.ClienteSistemaDto;
import br.akd.svc.akadia.models.entities.global.TelefoneEntity;
import br.akd.svc.akadia.models.entities.site.ClienteSistemaEntity;
import br.akd.svc.akadia.models.entities.site.PlanoEntity;
import br.akd.svc.akadia.models.enums.site.StatusPlanoEnum;
import br.akd.svc.akadia.proxy.asaas.AsaasProxy;
import br.akd.svc.akadia.proxy.asaas.requests.ClienteSistemaRequest;
import br.akd.svc.akadia.proxy.asaas.responses.ClienteSistemaResponse;
import br.akd.svc.akadia.repositories.site.impl.ClienteSistemaRepositoryImpl;
import br.akd.svc.akadia.services.global.exceptions.InvalidRequestException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

        ClienteSistemaEntity clienteSistema = ClienteSistemaEntity.builder()
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
                .cartoes(new ArrayList<>())
                .empresas(new ArrayList<>())
                .endereco(null)
                .pagamentos(new ArrayList<>())
                .build();

        PlanoEntity plano = PlanoEntity.builder()
                .dataContratacao(LocalDate.now().toString())
                .horaContratacao(LocalDate.now().toString())
                .tipoPlanoEnum(clienteSistemaDto.getPlano().getTipoPlanoEnum())
                .formaPagamentoSistemaEnum(clienteSistemaDto.getPlano().getFormaPagamentoSistemaEnum())
                .statusPlanoEnum(StatusPlanoEnum.PERIODO_DE_TESTES)
                .build();
        clienteSistema.setPlano(plano);

        ClienteSistemaRequest clienteSistemaRequest = ClienteSistemaRequest.builder()
                .name(clienteSistema.getNome())
                .email(clienteSistema.getEmail())
                .phone(clienteSistema.getTelefone().getPrefixo().toString() + clienteSistema.getTelefone().getNumero())
                .cpfCnpj(clienteSistema.getCpf())
                .build();

        Map<String, String> headers = new HashMap<>();
        headers.put("access_token", System.getenv("TOKEN_ASAAS"));

        System.err.println(headers);

        ResponseEntity<?> cadastraClienteAsaas =
                asaasProxy.cadastraCliente(clienteSistemaRequest, headers);

        ClienteSistemaResponse clienteSistemaResponse =
                objectMapper.convertValue(cadastraClienteAsaas.getBody(), ClienteSistemaResponse.class);

        clienteSistema.setCodigoClienteAsaas(clienteSistemaResponse.getId());

        System.err.println(cadastraClienteAsaas);
        System.err.println(clienteSistemaResponse);

        return clienteSistemaRepositoryImpl.implementaPersistencia(clienteSistema);

    }


}
