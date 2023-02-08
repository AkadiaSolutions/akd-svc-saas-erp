package br.akd.svc.akadia.services.site;

import br.akd.svc.akadia.models.entities.site.ClienteSistemaEntity;
import br.akd.svc.akadia.models.entities.site.PagamentoSistemaEntity;
import br.akd.svc.akadia.models.enums.site.FormaPagamentoSistemaEnum;
import br.akd.svc.akadia.models.enums.site.StatusPagamentoSistemaEnum;
import br.akd.svc.akadia.models.enums.site.StatusPlanoEnum;
import br.akd.svc.akadia.proxy.asaas.webhooks.AtualizacaoCobrancaWebHook;
import br.akd.svc.akadia.proxy.asaas.webhooks.BillingTypeEnum;
import br.akd.svc.akadia.repositories.site.impl.ClienteSistemaRepositoryImpl;
import br.akd.svc.akadia.repositories.site.impl.PagamentoSistemaRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class PagamentoSistemaService {

    @Autowired
    ClienteSistemaRepositoryImpl clienteSistemaRepositoryImpl;

    @Autowired
    ClienteSistemaService clienteSistemaService;

    @Autowired
    PagamentoSistemaRepositoryImpl pagamentoSistemaRepositoryImpl;

    public void realizaTratamentoWebhook(AtualizacaoCobrancaWebHook atualizacaoCobrancaWebHook) {

        ClienteSistemaEntity clienteSistema = clienteSistemaRepositoryImpl
                .implementaBuscaPorCodigoClienteAsaas(atualizacaoCobrancaWebHook.getPayment().getCustomer());

        switch (atualizacaoCobrancaWebHook.getEvent()) {
            case PAYMENT_CREATED:
                realizaCriacaoDeNovoPagamento(atualizacaoCobrancaWebHook, clienteSistema);
                break;
            case PAYMENT_RECEIVED:
            case PAYMENT_CONFIRMED:
                realizaAtualizacaoDePagamentoRealizado(atualizacaoCobrancaWebHook, clienteSistema);
                break;
            case PAYMENT_UPDATED:
                realizaAtualizacaoDePagamentoAlterado(atualizacaoCobrancaWebHook, clienteSistema);
                break;
            case PAYMENT_OVERDUE:
                realizaAtualizacaoDePlanoParaPagamentoVencido(clienteSistema);
                break;
            case PAYMENT_DELETED:
            case PAYMENT_ANTICIPATED:
            case PAYMENT_AWAITING_RISK_ANALYSIS:
            case PAYMENT_APPROVED_BY_RISK_ANALYSIS:
            case PAYMENT_REPROVED_BY_RISK_ANALYSIS:
            case PAYMENT_RESTORED:
            case PAYMENT_REFUNDED:
            case PAYMENT_RECEIVED_IN_CASH_UNDONE:
            case PAYMENT_CHARGEBACK_REQUESTED:
            case PAYMENT_CHARGEBACK_DISPUTE:
            case PAYMENT_AWAITING_CHARGEBACK_REVERSAL:
            case PAYMENT_DUNNING_RECEIVED:
            case PAYMENT_DUNNING_REQUESTED:
            case PAYMENT_BANK_SLIP_VIEWED:
            case PAYMENT_CHECKOUT_VIEWED:
                break;
        }
    }

    public void realizaCriacaoDeNovoPagamento(AtualizacaoCobrancaWebHook atualizacaoCobrancaWebHook,
                                              ClienteSistemaEntity clienteSistema) {
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

        clienteSistema.getPagamentos().add(pagamentoSistemaEntity);
        clienteSistemaRepositoryImpl.implementaPersistencia(clienteSistema);
    }

    public void realizaAtualizacaoDePagamentoRealizado(AtualizacaoCobrancaWebHook atualizacaoCobrancaWebHook,
                                                       ClienteSistemaEntity clienteSistema) {

        PagamentoSistemaEntity pagamentoSistemaEntity = pagamentoSistemaRepositoryImpl
                .implementaBuscaPorCodigoPagamentoAsaas(atualizacaoCobrancaWebHook.getPayment().getId());

        clienteSistema.getPagamentos().remove(pagamentoSistemaEntity);

        clienteSistema.getPlano().setDataVencimento(clienteSistemaService
                .consultaAssinaturaAsaas(atualizacaoCobrancaWebHook.getPayment().getSubscription()).getNextDueDate());

        clienteSistema.getPlano().setStatusPlanoEnum(StatusPlanoEnum.ATIVO);

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

        clienteSistema.getPagamentos().add(pagamentoSistemaEntity);
        clienteSistemaRepositoryImpl.implementaPersistencia(clienteSistema);
    }

    public void realizaAtualizacaoDePlanoParaPagamentoVencido(ClienteSistemaEntity clienteSistema) {
        clienteSistema.getPlano().setStatusPlanoEnum(StatusPlanoEnum.INATIVO);

        if (LocalDate.now().compareTo(LocalDate.parse(clienteSistema.getPlano().getDataVencimento())) >= 5)
            clienteSistemaService.cancelaAssinatura(clienteSistema.getId());

        clienteSistemaRepositoryImpl.implementaPersistencia(clienteSistema);
    }

    public void realizaAtualizacaoDePagamentoAlterado(AtualizacaoCobrancaWebHook atualizacaoCobrancaWebHook,
                                                      ClienteSistemaEntity clienteSistema) {
        PagamentoSistemaEntity pagamentoSistemaEntity = pagamentoSistemaRepositoryImpl
                .implementaBuscaPorCodigoPagamentoAsaas(atualizacaoCobrancaWebHook.getPayment().getId());

        clienteSistema.getPagamentos().remove(pagamentoSistemaEntity);

        pagamentoSistemaEntity.setDescricao(atualizacaoCobrancaWebHook.getPayment().getDescription());
        pagamentoSistemaEntity.setFormaPagamentoSistemaEnum(FormaPagamentoSistemaEnum.valueOf(atualizacaoCobrancaWebHook
                .getPayment().getBillingType().getFormaPagamentoResumida()));
        pagamentoSistemaEntity.setCartao(atualizacaoCobrancaWebHook.getPayment().getBillingType()
                .equals(BillingTypeEnum.CREDIT_CARD) && clienteSistema.getCartao() != null
                ? clienteSistema.getCartao()
                : null);

        pagamentoSistemaEntity.setClienteSistema(clienteSistema);

        clienteSistema.getPagamentos().add(pagamentoSistemaEntity);

        clienteSistemaRepositoryImpl.implementaPersistencia(clienteSistema);
    }

}
