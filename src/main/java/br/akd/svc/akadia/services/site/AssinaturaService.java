package br.akd.svc.akadia.services.site;

import br.akd.svc.akadia.models.dto.site.ClienteSistemaDto;
import br.akd.svc.akadia.models.entities.site.ClienteSistemaEntity;
import br.akd.svc.akadia.models.entities.site.PlanoEntity;
import br.akd.svc.akadia.models.enums.site.FormaPagamentoSistemaEnum;
import br.akd.svc.akadia.models.enums.site.StatusPlanoEnum;
import br.akd.svc.akadia.proxy.asaas.AsaasProxy;
import br.akd.svc.akadia.proxy.asaas.global.Taxes;
import br.akd.svc.akadia.proxy.asaas.requests.CreditCardHolderInfoRequest;
import br.akd.svc.akadia.proxy.asaas.requests.CreditCardRequest;
import br.akd.svc.akadia.proxy.asaas.requests.assinatura.AssinaturaRequest;
import br.akd.svc.akadia.proxy.asaas.requests.assinatura.AtualizaAssinaturaRequest;
import br.akd.svc.akadia.proxy.asaas.requests.fiscal.CriaConfigFiscalRequest;
import br.akd.svc.akadia.proxy.asaas.requests.fiscal.EffectiveDatePeriodEnum;
import br.akd.svc.akadia.proxy.asaas.responses.assinatura.AssinaturaResponse;
import br.akd.svc.akadia.proxy.asaas.responses.assinatura.atualiza.AtualizaAssinaturaResponse;
import br.akd.svc.akadia.proxy.asaas.responses.assinatura.cancela.CancelamentoAssinaturaResponse;
import br.akd.svc.akadia.proxy.asaas.responses.assinatura.consulta.ConsultaAssinaturaResponse;
import br.akd.svc.akadia.repositories.site.impl.ClienteSistemaRepositoryImpl;
import br.akd.svc.akadia.services.exceptions.FeignConnectionException;
import br.akd.svc.akadia.services.exceptions.InvalidRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

@Slf4j
@Service
public class AssinaturaService {

    @Autowired
    ClienteSistemaRepositoryImpl clienteSistemaRepositoryImpl;

    @Autowired
    ClienteSistemaService clienteSistemaService;

    @Autowired
    AsaasProxy asaasProxy;

    public static final String TOKEN_ASAAS = "TOKEN_ASAAS";
    public static final String FALHA_COMUNICACAO_ASAAS =
            "Ocorreu uma falha na comunicação com a integradora de pagamentos: ";

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
                                        .mobilePhone(clienteSistemaService.criaNumeroMobileComObjetoTelefone(clienteSistema.getTelefone()))
                                        .postalCode(clienteSistema.getEndereco().getCodigoPostal())
                                        .addressNumber(clienteSistema.getEndereco().getNumero().toString())
                                        .addressComplement(clienteSistema.getEndereco().getComplemento())
                                        .build()
                                        : null
                        )
                        .build();

        ResponseEntity<AssinaturaResponse> cadastraAssinaturaAsaas;

        try {
            cadastraAssinaturaAsaas = asaasProxy.cadastraNovaAssinatura(assinaturaRequest, System.getenv(TOKEN_ASAAS));
        } catch (Exception e) {
            throw new FeignConnectionException(FALHA_COMUNICACAO_ASAAS + e.getMessage());
        }

        if (cadastraAssinaturaAsaas.getStatusCodeValue() != 200)
            throw new InvalidRequestException("Ocorreu um erro no processo de criação da assinatura: "
                    + cadastraAssinaturaAsaas.getBody());

        criaConfigFiscalAssinaturaAsaas(Objects.requireNonNull(cadastraAssinaturaAsaas.getBody()));
        return cadastraAssinaturaAsaas.getBody();
    }


    public ClienteSistemaEntity atualizaDadosAssinatura(Long idCliente, ClienteSistemaDto clienteSistemaDto) {

        ClienteSistemaEntity clienteSistema = clienteSistemaRepositoryImpl.implementaBuscaPorId(idCliente);

        PlanoEntity planoAtualizado = PlanoEntity.builder()
                .id(clienteSistema.getPlano().getId())
                .codigoAssinaturaAsaas(clienteSistema.getPlano().getCodigoAssinaturaAsaas())
                .dataContratacao(clienteSistema.getPlano().getDataContratacao())
                .horaContratacao(clienteSistema.getPlano().getHoraContratacao())
                .dataVencimento(clienteSistema.getPlano().getDataVencimento())
                .tipoPlanoEnum(clienteSistemaDto.getPlano().getTipoPlanoEnum())
                .statusPlanoEnum(clienteSistema.getPlano().getStatusPlanoEnum())
                .formaPagamentoSistemaEnum(clienteSistemaDto.getPlano().getFormaPagamentoSistemaEnum())
                .build();

        clienteSistema.setPlano(planoAtualizado);
        ClienteSistemaEntity clienteAtualizado = clienteSistemaRepositoryImpl.implementaPersistencia(clienteSistema);
        atualizaDadosAssinaturaAsaas(clienteAtualizado);
        return clienteAtualizado;
    }

    public void atualizaDadosAssinaturaAsaas(ClienteSistemaEntity clienteSistema) {
        AtualizaAssinaturaRequest atualizaAssinaturaRequest = AtualizaAssinaturaRequest.builder()
                .billingType(clienteSistema.getPlano().getFormaPagamentoSistemaEnum().toString())
                .value(clienteSistema.getPlano().getTipoPlanoEnum().getValor())
                .cycle("MONTHLY")
                .description("Assinatura do plano " + clienteSistema.getPlano().getTipoPlanoEnum().getDesc())
                .updatePendingPayments("true")
                .build();

        ResponseEntity<AtualizaAssinaturaResponse> atualizaAssinaturaResponse;
        try {
            atualizaAssinaturaResponse = asaasProxy.atualizaAssinatura(
                    clienteSistema.getPlano().getCodigoAssinaturaAsaas(), atualizaAssinaturaRequest, System.getenv(TOKEN_ASAAS));
        } catch (Exception e) {
            throw new FeignConnectionException(FALHA_COMUNICACAO_ASAAS + e.getMessage());
        }

        if (atualizaAssinaturaResponse.getStatusCodeValue() != 200)
            throw new InvalidRequestException("Ocorreu um erro no processo de atualização de assinatura com a integradora: "
                    + atualizaAssinaturaResponse.getBody());
    }

    public ConsultaAssinaturaResponse consultaAssinaturaAsaas(String id) {
        log.debug("Método de consulta de assinatura ASAAS por ID ({}) acessado", id);
        ResponseEntity<ConsultaAssinaturaResponse> assinaturaAsaas;
        try {
            assinaturaAsaas =
                    asaasProxy.consultaAssinatura(id, System.getenv(TOKEN_ASAAS));
            log.debug("Assinatura encontrada: {}", assinaturaAsaas);
        } catch (Exception e) {
            log.error("Houve uma falha de comunicação com a integradora de pagamentos: {}", e.getMessage());
            throw new FeignConnectionException(FALHA_COMUNICACAO_ASAAS + e.getMessage());
        }

        if (assinaturaAsaas.getStatusCodeValue() != 200) {
            log.error("Houve uma falha no processo de consulta da assinatura com a integradora de pagamentos: {}",
                    assinaturaAsaas.getBody());
            throw new InvalidRequestException("Ocorreu um erro no processo de consulta de assinatura com a integradora: "
                    + assinaturaAsaas.getBody());
        }

        log.debug("Retornando assinatura...");
        return assinaturaAsaas.getBody();
    }

    public ClienteSistemaEntity cancelaAssinatura(Long idCliente) {
        log.debug("Iniciando acesso ao método de busca de cliente por id...");
        ClienteSistemaEntity clienteSistema = clienteSistemaRepositoryImpl.implementaBuscaPorId(idCliente);

        PlanoEntity plano = clienteSistema.getPlano();

        log.debug("Verificando se data de vencimento do plano já passou...");
        if (LocalDate.parse(plano.getDataVencimento()).isBefore(LocalDate.now())) {
            log.debug("A data de vencimento do plano já passou. Setando status do plano do cliente como INATIVO...");
            plano.setStatusPlanoEnum(StatusPlanoEnum.INATIVO);
        }

        log.debug("Setando plano atualizado ao cliente...");
        clienteSistema.setPlano(plano);

        log.debug("Iniciando acesso ao método de cancelamento de assinatura na integradora de pagamentos...");
        cancelaAssinaturaAsaas(clienteSistema);
        return clienteSistemaRepositoryImpl.implementaPersistencia(clienteSistema);
    }

    public void cancelaAssinaturaAsaas(ClienteSistemaEntity clienteSistema) {
        log.debug("Método de cancelamento de assinatura acessado");
        ResponseEntity<CancelamentoAssinaturaResponse> cancelamentoAssinaturaAsaas;
        try {
            cancelamentoAssinaturaAsaas = asaasProxy
                    .cancelaAssinatura(clienteSistema.getPlano().getCodigoAssinaturaAsaas(), System.getenv(TOKEN_ASAAS));
            log.info("A assinatura do cliente foi cancelada com sucesso");
        } catch (Exception e) {
            log.error("Houve uma falha de comunicação com a integradora de pagamentos: {}", e.getMessage());
            throw new FeignConnectionException(FALHA_COMUNICACAO_ASAAS + e.getMessage());
        }

        if (cancelamentoAssinaturaAsaas.getStatusCodeValue() != 200) {
            log.error("Ocorreu um erro no processo de cancelamento de assinatura com a integradora: {}",
                    cancelamentoAssinaturaAsaas.getBody());
            throw new InvalidRequestException("Ocorreu um erro no processo de cancelamento de assinatura com a integradora: "
                    + cancelamentoAssinaturaAsaas.getBody());
        }
    }

    //TODO Verificar informações fiscais com contador
    public void criaConfigFiscalAssinaturaAsaas(AssinaturaResponse assinatura) {

        CriaConfigFiscalRequest criaConfigFiscalRequest = CriaConfigFiscalRequest.builder()
                .municipalServiceId("104")
                .municipalServiceCode("1.05")
                .municipalServiceName("Licenciamento ou cessão de direito de uso de programas de computação, inclusive distribuição.")
                .deductions(0)
                .effectiveDatePeriod(EffectiveDatePeriodEnum.ON_PAYMENT_CONFIRMATION)
                .receivedOnly(true)
                .observations(assinatura.getDescription())
                .taxes(Taxes.builder()
                        .retainIss(false)
                        .iss(3)
                        .cofins(0)
                        .csll(1)
                        .inss(0)
                        .ir(1.5)
                        .pis(0.65)
                        .build())
                .build();

        asaasProxy.criaConfiguracaoFiscalDaAssinatura(assinatura.getId(), criaConfigFiscalRequest, System.getenv(TOKEN_ASAAS));
    }

    @Scheduled(cron = "0 1 1 * * ?", zone = "America/Sao_Paulo")
    public void verificaDiariamenteSeExistemPlanosVencidosAtivos() {
        //TODO Revisar lógica
        clienteSistemaRepositoryImpl.implementaBuscaPorPlanosVencidosAtivos();
    }
}
