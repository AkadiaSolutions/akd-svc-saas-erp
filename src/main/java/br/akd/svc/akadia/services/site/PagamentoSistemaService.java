package br.akd.svc.akadia.services.site;

import br.akd.svc.akadia.models.entities.site.ClienteSistemaEntity;
import br.akd.svc.akadia.models.entities.site.PagamentoSistemaEntity;
import br.akd.svc.akadia.models.enums.site.FormaPagamentoSistemaEnum;
import br.akd.svc.akadia.models.enums.site.StatusPagamentoSistemaEnum;
import br.akd.svc.akadia.models.enums.site.StatusPlanoEnum;
import br.akd.svc.akadia.proxy.asaas.webhooks.cobranca.AtualizacaoCobrancaWebHook;
import br.akd.svc.akadia.proxy.asaas.webhooks.cobranca.enums.BillingTypeEnum;
import br.akd.svc.akadia.proxy.asaas.webhooks.fiscal.AtualizacaoFiscalWebHook;
import br.akd.svc.akadia.repositories.site.impl.ClienteSistemaRepositoryImpl;
import br.akd.svc.akadia.repositories.site.impl.PagamentoSistemaRepositoryImpl;
import br.akd.svc.akadia.services.exceptions.UnauthorizedAccessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

import static br.akd.svc.akadia.utils.Constantes.INICIANDO_IMPL_PERSISTENCIA_CLIENTE;
import static br.akd.svc.akadia.utils.Constantes.OBJETO_PAGAMENTO_CRIADO;

@Slf4j
@Service
public class PagamentoSistemaService {

    @Autowired
    ClienteSistemaRepositoryImpl clienteSistemaRepositoryImpl;

    @Autowired
    AssinaturaService assinaturaService;

    @Autowired
    PagamentoSistemaRepositoryImpl pagamentoSistemaRepositoryImpl;

    @Value("${TOKEN_WEBHOOK_ASAAS}")
    String tokenWebhook;

    public void realizaValidacaoToken(String token) {
        log.debug("Método de validação de token acessado");

        log.debug("Iniciando validação de token...");
        if (token == null || token.equals("")) {
            log.error("O token recebido é nulo ou vazio: {}", token);
            throw new UnauthorizedAccessException("Nenhum token de acesso foi recebido");
        }
        if (!token.equals(tokenWebhook)) {
            log.error("O token recebido não é compatível com o esperado: {}", token);
            throw new UnauthorizedAccessException("O token de acesso recebido está incorreto");
        }
    }

    public void realizaTratamentoWebhookCobranca(AtualizacaoCobrancaWebHook atualizacaoCobrancaWebHook) {

        log.debug("Método 'motor de distruição' de Webhook de atualização de cobrança acessado");

        ClienteSistemaEntity clienteSistema = clienteSistemaRepositoryImpl
                .implementaBuscaPorCodigoClienteAsaas(atualizacaoCobrancaWebHook.getPayment().getCustomer());

        switch (atualizacaoCobrancaWebHook.getEvent()) {
            case PAYMENT_CREATED:
                log.debug("Condicional para novo pagamento CRIADO acessada");
                realizaCriacaoDeNovoPagamento(atualizacaoCobrancaWebHook, clienteSistema);
                log.info("Cobrança CRIADA com sucesso");
                break;
            case PAYMENT_RECEIVED:
            case PAYMENT_CONFIRMED:
                log.debug("Condicional de pagamento CONFIRMADO acessada");
                realizaAtualizacaoDePagamentoRealizado(atualizacaoCobrancaWebHook, clienteSistema);
                log.info("Cobrança CONFIRMADA sucesso");
                break;
            case PAYMENT_UPDATED:
                log.debug("Condicional de pagamento ALTERADO acessada");
                realizaAtualizacaoDePagamentoAlterado(atualizacaoCobrancaWebHook, clienteSistema);
                log.info("Cobrança ALTERADA com sucesso");
                break;
            case PAYMENT_OVERDUE:
                log.debug("Condicional de pagamento VENCIDO acessada");
                realizaAtualizacaoDePlanoParaPagamentoVencido(clienteSistema);
                log.info("Atualização de plano para pagamento VENCIDO realizada com sucesso");
                break;
            case PAYMENT_DELETED:
            case PAYMENT_ANTICIPATED:
            case PAYMENT_AWAITING_RISK_ANALYSIS:
            case PAYMENT_APPROVED_BY_RISK_ANALYSIS:
            case PAYMENT_REPROVED_BY_RISK_ANALYSIS:
            case PAYMENT_RESTORED:
            case PAYMENT_REFUNDED: //TODO Implantar lógica para estorno
            case PAYMENT_RECEIVED_IN_CASH_UNDONE:
            case PAYMENT_CHARGEBACK_REQUESTED:
            case PAYMENT_CHARGEBACK_DISPUTE:
            case PAYMENT_AWAITING_CHARGEBACK_REVERSAL:
            case PAYMENT_DUNNING_RECEIVED:
            case PAYMENT_DUNNING_REQUESTED:
            case PAYMENT_BANK_SLIP_VIEWED:
            case PAYMENT_CHECKOUT_VIEWED: //TODO Implantar lógica para fatura visualizada
                break;
        }
    }

    public void realizaCriacaoDeNovoPagamento(AtualizacaoCobrancaWebHook atualizacaoCobrancaWebHook,
                                              ClienteSistemaEntity clienteSistema) {
        log.debug("Iniciando construção do objeto PagamentoSistemaEntity com valores recebidos pelo ASAAS...");
        PagamentoSistemaEntity pagamentoSistemaEntity = PagamentoSistemaEntity.builder()
                .dataCadastro(LocalDate.now().toString())
                .horaCadastro(LocalDate.now().toString())
                .dataPagamento(null)
                .horaPagamento(null)
                .codigoPagamentoAsaas(atualizacaoCobrancaWebHook.getPayment().getId())
                .valor(atualizacaoCobrancaWebHook.getPayment().getValue())
                .valorLiquido(atualizacaoCobrancaWebHook.getPayment().getNetValue())
                .descricao(atualizacaoCobrancaWebHook.getPayment().getDescription())
                .dataVencimento(atualizacaoCobrancaWebHook.getPayment().getDueDate())
                .formaPagamentoSistemaEnum(FormaPagamentoSistemaEnum.valueOf(atualizacaoCobrancaWebHook
                        .getPayment().getBillingType().getFormaPagamentoResumida()))
                .statusPagamentoSistemaEnum(StatusPagamentoSistemaEnum.PENDENTE)
                .cartao(atualizacaoCobrancaWebHook.getPayment().getBillingType().equals(BillingTypeEnum.CREDIT_CARD)
                        ? clienteSistema.getCartao()
                        : null)
                .clienteSistema(clienteSistema)
                .build();
        log.debug(OBJETO_PAGAMENTO_CRIADO, pagamentoSistemaEntity);

        log.debug("Adicionando objeto pagamento criado à lista de pagamentos do cliente...");
        clienteSistema.getPagamentos().add(pagamentoSistemaEntity);

        log.debug("Iniciando acesso ao método de implementação de persistência do cliente para o persistir com sua lista " +
                "de pagamentos atualizada...");
        clienteSistemaRepositoryImpl.implementaPersistencia(clienteSistema);
    }

    public void realizaAtualizacaoDePagamentoRealizado(AtualizacaoCobrancaWebHook atualizacaoCobrancaWebHook,
                                                       ClienteSistemaEntity clienteSistema) {
        log.debug("Iniciando acesso ao método de implementação de busca de pagamento por código ASAAS...");
        PagamentoSistemaEntity pagamentoSistemaEntity = pagamentoSistemaRepositoryImpl
                .implementaBuscaPorCodigoPagamentoAsaas(atualizacaoCobrancaWebHook.getPayment().getId());

        log.debug("Removendo pagamento encontrado do objeto cliente: {}", pagamentoSistemaEntity);
        clienteSistema.getPagamentos().remove(pagamentoSistemaEntity);

        log.debug("Iniciando setagem da data de vencimento do plano do cliente para o próximo vencimento...");
        clienteSistema.getPlano().setDataVencimento(assinaturaService
                .consultaAssinaturaAsaas(atualizacaoCobrancaWebHook.getPayment().getSubscription()).getNextDueDate());

        log.debug("Setando plano do cliente como ATIVO...");
        clienteSistema.getPlano().setStatusPlanoEnum(StatusPlanoEnum.ATIVO);

        log.debug("Atualizando variáveis do objeto pagamento...");
        pagamentoSistemaEntity.setDataPagamento(LocalDate.now().toString());
        pagamentoSistemaEntity.setHoraPagamento(LocalTime.now().toString());
        pagamentoSistemaEntity.setValor(atualizacaoCobrancaWebHook.getPayment().getValue());
        pagamentoSistemaEntity.setValorLiquido(atualizacaoCobrancaWebHook.getPayment().getNetValue());
        pagamentoSistemaEntity.setFormaPagamentoSistemaEnum(FormaPagamentoSistemaEnum.valueOf(atualizacaoCobrancaWebHook
                .getPayment().getBillingType().getFormaPagamentoResumida()));
        pagamentoSistemaEntity.setCartao(atualizacaoCobrancaWebHook.getPayment().getBillingType()
                .equals(BillingTypeEnum.CREDIT_CARD) && clienteSistema.getCartao() != null
                ? clienteSistema.getCartao()
                : null);
        pagamentoSistemaEntity.setClienteSistema(clienteSistema);
        pagamentoSistemaEntity.setStatusPagamentoSistemaEnum(StatusPagamentoSistemaEnum.APROVADO);
        log.debug(OBJETO_PAGAMENTO_CRIADO, pagamentoSistemaEntity);

        log.debug("Setando pagamento à lista de pagamentos do cliente...");
        clienteSistema.getPagamentos().add(pagamentoSistemaEntity);

        log.debug(INICIANDO_IMPL_PERSISTENCIA_CLIENTE);
        clienteSistemaRepositoryImpl.implementaPersistencia(clienteSistema);
    }

    public void realizaAtualizacaoDePlanoParaPagamentoVencido(ClienteSistemaEntity clienteSistema) {
        log.debug("Setando plano do cliente como INATIVO...");
        clienteSistema.getPlano().setStatusPlanoEnum(StatusPlanoEnum.INATIVO);

        if (LocalDate.now().compareTo(LocalDate.parse(clienteSistema.getPlano().getDataVencimento())) >= 5) {
            log.debug("Condicional de plano vencido a 5 dias ou mais acessada: {}", clienteSistema.getPlano());
            log.debug("Iniciando acesso ao método de cancelamento de assinatura...");
            assinaturaService.cancelaAssinatura(clienteSistema.getId());
        }
        log.debug(INICIANDO_IMPL_PERSISTENCIA_CLIENTE);
        clienteSistemaRepositoryImpl.implementaPersistencia(clienteSistema);
    }

    public void realizaAtualizacaoDePagamentoAlterado(AtualizacaoCobrancaWebHook atualizacaoCobrancaWebHook,
                                                      ClienteSistemaEntity clienteSistema) {
        log.debug("Iniciando acesso ao método de implementação de busca de pagamento por código ASAAS...");
        PagamentoSistemaEntity pagamentoSistemaEntity = pagamentoSistemaRepositoryImpl
                .implementaBuscaPorCodigoPagamentoAsaas(atualizacaoCobrancaWebHook.getPayment().getId());

        log.debug("Removendo pagamento encontrado do objeto cliente: {}", pagamentoSistemaEntity);
        clienteSistema.getPagamentos().remove(pagamentoSistemaEntity);

        log.debug("Atualizando variáveis do objeto pagamento...");
        pagamentoSistemaEntity.setDescricao(atualizacaoCobrancaWebHook.getPayment().getDescription());
        pagamentoSistemaEntity.setFormaPagamentoSistemaEnum(FormaPagamentoSistemaEnum.valueOf(atualizacaoCobrancaWebHook
                .getPayment().getBillingType().getFormaPagamentoResumida()));
        pagamentoSistemaEntity.setCartao(atualizacaoCobrancaWebHook.getPayment().getBillingType()
                .equals(BillingTypeEnum.CREDIT_CARD) && clienteSistema.getCartao() != null
                ? clienteSistema.getCartao()
                : null);
        log.debug(OBJETO_PAGAMENTO_CRIADO, pagamentoSistemaEntity);

        log.debug("Setando cliente ao pagamento...");
        pagamentoSistemaEntity.setClienteSistema(clienteSistema);

        log.debug("Setando pagamento à lista de pagamentos do cliente...");
        clienteSistema.getPagamentos().add(pagamentoSistemaEntity);

        log.debug(INICIANDO_IMPL_PERSISTENCIA_CLIENTE);
        clienteSistemaRepositoryImpl.implementaPersistencia(clienteSistema);
    }

    public void realizaTratamentoWebhookFiscal(AtualizacaoFiscalWebHook atualizacaoFiscalWebHook) {
        log.debug("Atualização status fiscal recebida: {}", atualizacaoFiscalWebHook);
        //TODO Construir lógica (Necessário: CNPJ, certificado digital e consultoria com contador)
    }

}
