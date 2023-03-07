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
import br.akd.svc.akadia.proxy.asaas.requests.ClienteSistemaRequest;
import br.akd.svc.akadia.proxy.asaas.responses.ClienteSistemaResponse;
import br.akd.svc.akadia.proxy.asaas.responses.assinatura.AssinaturaResponse;
import br.akd.svc.akadia.repositories.site.impl.ClienteSistemaRepositoryImpl;
import br.akd.svc.akadia.services.exceptions.FeignConnectionException;
import br.akd.svc.akadia.services.exceptions.InvalidRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

@Slf4j
@Service
public class ClienteSistemaService {

    @Autowired
    ClienteSistemaRepositoryImpl clienteSistemaRepositoryImpl;

    @Autowired
    AssinaturaService assinaturaService;

    @Autowired
    AsaasProxy asaasProxy;

    public static final String TOKEN_ASAAS = "TOKEN_ASAAS";
    public static final String FALHA_COMUNICACAO_ASAAS =
            "Ocorreu uma falha na comunicação com a integradora de pagamentos: ";

    public void validaSeEmailJaExiste(ClienteSistemaDto clienteSistemaDto) {
        log.debug("Método de validação de e-mail acessado...");
        if (clienteSistemaRepositoryImpl.implementaBuscaPorEmail(clienteSistemaDto.getEmail()).isPresent()) {
            log.warn("O e-mail informado já existe: {}", clienteSistemaDto.getEmail());
            throw new InvalidRequestException("O e-mail informado já existe");
        }
    }

    public void validaSeCpfJaExiste(ClienteSistemaDto clienteSistemaDto) {
        log.debug("Método de validação de cpf acessado...");
        if (clienteSistemaRepositoryImpl.implementaBuscaPorCpf(clienteSistemaDto.getCpf()).isPresent()) {
            log.warn("O cpf informado já existe: {}", clienteSistemaDto.getCpf());
            throw new InvalidRequestException("O cpf informado já existe");
        }
    }

    public ClienteSistemaEntity cadastraNovoCliente(ClienteSistemaDto clienteSistemaDto) {

        log.debug("Método de serviço de cadastro de novo cliente acessado. Cliente recebido: {}", clienteSistemaDto);

        log.debug("Iniciando validações de dados...");
        validaSeEmailJaExiste(clienteSistemaDto);
        validaSeCpfJaExiste(clienteSistemaDto);

        log.debug("Iniciando construção do objeto ClienteSistemaEntity...");
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
                                .dataVencimento(LocalDate.now().plusDays(7L).toString())
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
        log.debug("Objeto ClienteSistemaEntity construído: {}", clienteSistema);

        log.debug("Iniciando acesso ao método de cadastro de cliente na integradora de pagamentos...");
        clienteSistema.setCodigoClienteAsaas(realizaCadastroClienteAsaas(clienteSistema).getId());

        log.debug("Iniciando acesso ao método de criação de assinatura na integradora de pagamentos...");
        AssinaturaResponse assinaturaResponse = assinaturaService.criaAssinaturaAsaas(clienteSistema);

        log.debug("Setando código de assinatura Asaas ao plano do cliente...");
        clienteSistema.getPlano().setCodigoAssinaturaAsaas(assinaturaResponse.getId());

        if (assinaturaResponse.getCreditCard() != null)
            clienteSistema.getCartao().setTokenCartao(assinaturaResponse.getCreditCard().getCreditCardToken());

        log.debug("Iniciando acesso ao método de implementação de persistência do cliente...");
        ClienteSistemaEntity clientePersistido = clienteSistemaRepositoryImpl.implementaPersistencia(clienteSistema);

        log.info("Criação do cliente realizada com sucesso");
        return clientePersistido;
    }

    public ClienteSistemaResponse realizaCadastroClienteAsaas(ClienteSistemaEntity clienteSistema) {

        log.debug("Método de cadastro de cliente na integradora de pagamentos acessado");

        log.debug("Iniciando construção do objeto ClienteSistemaRequest...");
        ClienteSistemaRequest clienteSistemaRequest = ClienteSistemaRequest.builder()
                .name(clienteSistema.getNome())
                .email(clienteSistema.getEmail())
                .phone(clienteSistema.getTelefone().getPrefixo() + clienteSistema.getTelefone().getNumero())
                .postalCode(clienteSistema.getEndereco() != null
                        ? clienteSistema.getEndereco().getCodigoPostal()
                        : null)
                .address(clienteSistema.getEndereco() != null
                        ? clienteSistema.getEndereco().getLogradouro()
                        : null)
                .addressNumber(clienteSistema.getEndereco() != null
                        ? clienteSistema.getEndereco().getNumero().toString()
                        : null)
                .province(clienteSistema.getEndereco() != null
                        ? clienteSistema.getEndereco().getBairro()
                        : null)
                .complement(clienteSistema.getEndereco() != null
                        ? clienteSistema.getEndereco().getComplemento()
                        : null)
                .mobilePhone(criaNumeroMobileComObjetoTelefone(clienteSistema.getTelefone()))
                .cpfCnpj(clienteSistema.getCpf())
                .build();
        log.debug("Objeto ClienteSistemaRequest construído com sucesso: {}", clienteSistemaRequest);

        ResponseEntity<ClienteSistemaResponse> cadastraClienteAsaas;

        try {
            cadastraClienteAsaas = asaasProxy.cadastraNovoCliente(clienteSistemaRequest, System.getenv(TOKEN_ASAAS));
            log.info("Cliente cadastrado na integradora de pagamentos com sucesso");
            log.debug("Cliente ASAAS: {}", cadastraClienteAsaas.getBody());
        } catch (Exception e) {
            log.error("Houve uma falha de comunicação com a integradora de pagamentos: {}", e.getMessage());
            throw new FeignConnectionException(FALHA_COMUNICACAO_ASAAS + e.getMessage());
        }

        if (cadastraClienteAsaas.getStatusCodeValue() != 200) {
            log.error("Ocorreu um erro no processo de criação do cliente na integradora de pagamentos: {}",
                    cadastraClienteAsaas.getBody());
            throw new InvalidRequestException("Ocorreu um erro no processo de criação do cliente: "
                    + cadastraClienteAsaas.getBody());
        }

        log.debug("Retornando cliente cadastrado na integradora de pagamentos...");
        return cadastraClienteAsaas.getBody();
    }

    public String criaNumeroMobileComObjetoTelefone(TelefoneEntity telefone) {
        log.debug("Método de criação de número mobile com telefone informado ({}) acessado", telefone);
        String numeroMobile = null;
        if (!telefone.getTipoTelefoneEnum().equals(TipoTelefoneEnum.FIXO))
            numeroMobile = telefone.getPrefixo() + telefone.getNumero();

        log.debug("Retornando numero mobile criado: {}", numeroMobile);
        return numeroMobile;
    }

    public ClienteSistemaEntity atualizaDadosCliente(Long id, ClienteSistemaDto clienteSistemaDto) {

        log.debug("Método de serviço de atualização de dados do cliente acessado. " +
                "Cliente recebido: {} | ID recebido: {}", clienteSistemaDto, id);

        log.debug("Iniciando acesso ao método de implementação de busca de cliente por id...");
        ClienteSistemaEntity clienteSistema = clienteSistemaRepositoryImpl.implementaBuscaPorId(id);

        if (!clienteSistema.getEmail().equals(clienteSistemaDto.getEmail()))
            validaSeEmailJaExiste(clienteSistemaDto);

        log.debug("Iniciando construção do objeto ClienteSistemaEntity...");
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
        log.debug("Objeto ClienteSistemaEntity construído com sucesso");

        log.debug("Iniciando acesso ao método de atualização dos dados do cliente na integradora de pagamentos...");
        atualizaDadosClienteAsaas(clienteAtualizado);

        log.debug("Iniciando acesso ao método de implementação de persistência do cliente...");
        ClienteSistemaEntity clientePosPersistencia = clienteSistemaRepositoryImpl.implementaPersistencia(clienteAtualizado);

        log.info("Atualização do cliente realizada com sucesso");
        return clientePosPersistencia;
    }

    public void atualizaDadosClienteAsaas(ClienteSistemaEntity clienteAtualizado) {

        log.debug("Método de atualização dos dados do cliente na integradora de pagamentos acessado. Cliente recebido: {}",
                clienteAtualizado);

        log.debug("Iniciando construção do objeto ClienteSistemaRequest...");
        ClienteSistemaRequest clienteSistemaRequest = ClienteSistemaRequest.builder()
                .name(clienteAtualizado.getNome())
                .email(clienteAtualizado.getEmail())
                .phone(clienteAtualizado.getTelefone().getPrefixo() + clienteAtualizado.getTelefone().getNumero())
                .mobilePhone(criaNumeroMobileComObjetoTelefone(clienteAtualizado.getTelefone()))
                .cpfCnpj(clienteAtualizado.getCpf())
                .postalCode(clienteAtualizado.getEndereco().getCodigoPostal())
                .address(clienteAtualizado.getEndereco().getLogradouro())
                .addressNumber(clienteAtualizado.getEndereco().getNumero().toString())
                .complement(clienteAtualizado.getEndereco().getComplemento())
                .province(clienteAtualizado.getEndereco().getBairro())
                .build();
        log.debug("Objeto ClienteSistemaRequest construído com sucesso: {}", clienteSistemaRequest);

        ResponseEntity<ClienteSistemaResponse> atualizaClienteAsaas;

        try {
            atualizaClienteAsaas = asaasProxy.atualizaDadosCliente(clienteAtualizado.getCodigoClienteAsaas(),
                    clienteSistemaRequest, System.getenv(TOKEN_ASAAS));
            log.info("Dados do cliente atualizados na integradora de pagamentos com sucesso");
            log.debug("Cliente ASAAS: {}", atualizaClienteAsaas.getBody());
        } catch (Exception e) {
            log.error(FALHA_COMUNICACAO_ASAAS + e.getMessage());
            throw new FeignConnectionException(FALHA_COMUNICACAO_ASAAS + e.getMessage());
        }

        if (atualizaClienteAsaas.getStatusCodeValue() != 200) {
            log.error("Ocorreu um erro no processo de atualização dos dados cadastrais do cliente na integradora de pagamentos: {}",
                    atualizaClienteAsaas.getBody());
            throw new InvalidRequestException("Ocorreu um erro no processo de atualização dos dados cadastrais do cliente: "
                    + atualizaClienteAsaas.getBody());
        }
    }

}
