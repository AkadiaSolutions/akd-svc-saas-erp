package br.akd.svc.akadia.proxy.asaas.webhooks.cobranca.mocks;

import br.akd.svc.akadia.modules.web.proxy.asaas.webhooks.cobranca.PagamentoWebHook;
import br.akd.svc.akadia.modules.web.proxy.asaas.webhooks.cobranca.enums.BillingTypeEnum;

import java.time.LocalDate;

public class PagamentoWebHookBuilder {

    PagamentoWebHookBuilder() {
    }

    PagamentoWebHook pagamentoWebHook;

    public static PagamentoWebHookBuilder builder() {
        PagamentoWebHookBuilder builder = new PagamentoWebHookBuilder();
        builder.pagamentoWebHook = new PagamentoWebHook();
        builder.pagamentoWebHook.setObject("payment");
        builder.pagamentoWebHook.setId("pay_5874803025986658");
        builder.pagamentoWebHook.setDateCreated(LocalDate.of(2023, 2, 8).toString());
        builder.pagamentoWebHook.setCustomer("cus_000005121360");
        builder.pagamentoWebHook.setSubscription("sub_AnftMQ3KknF8");
        builder.pagamentoWebHook.setInstallment(null);
        builder.pagamentoWebHook.setPaymentLink(null);
        builder.pagamentoWebHook.setPaymentDate(null);
        builder.pagamentoWebHook.setDueDate(LocalDate.of(2023, 2, 8).toString());
        builder.pagamentoWebHook.setValue(650.0);
        builder.pagamentoWebHook.setNetValue(640.0);
        builder.pagamentoWebHook.setOriginalValue(650.0);
        builder.pagamentoWebHook.setInterestValue(0.0);
        builder.pagamentoWebHook.setDescription("Assinatura de plano pro");
        builder.pagamentoWebHook.setExternalReference(null);
        builder.pagamentoWebHook.setBillingType(BillingTypeEnum.BOLETO);
        builder.pagamentoWebHook.setStatus("ACTIVE");
        builder.pagamentoWebHook.setPixTransaction(null);
        builder.pagamentoWebHook.setConfirmedDate(null);
        builder.pagamentoWebHook.setPaymentDate(null);
        builder.pagamentoWebHook.setClientPaymentDate(null);
        builder.pagamentoWebHook.setInstallmentNumber(null);
        builder.pagamentoWebHook.setCreditDate(null);
        builder.pagamentoWebHook.setEstimatedCreditDate(null);
        builder.pagamentoWebHook.setInvoiceUrl(null);
        builder.pagamentoWebHook.setBankSlipUrl(null);
        builder.pagamentoWebHook.setTransactionReceiptUrl(null);
        builder.pagamentoWebHook.setInvoiceNumber(null);
        builder.pagamentoWebHook.setDeleted("false");
        builder.pagamentoWebHook.setAntecipated("false");
        builder.pagamentoWebHook.setAntecipable("false");
        builder.pagamentoWebHook.setLastInvoiceViewedDate(null);
        builder.pagamentoWebHook.setLastBankSlipViewedDate(null);
        builder.pagamentoWebHook.setPostalService("false");
        builder.pagamentoWebHook.setCreditCard(null);
        builder.pagamentoWebHook.setDiscount(null);
        builder.pagamentoWebHook.setFine(null);
        builder.pagamentoWebHook.setInterest(null);
        builder.pagamentoWebHook.setSplit(null);
        builder.pagamentoWebHook.setChargeBack(null);
        builder.pagamentoWebHook.setRefunds(null);
        return builder;
    }

    public PagamentoWebHook build() {
        return pagamentoWebHook;
    }

}
