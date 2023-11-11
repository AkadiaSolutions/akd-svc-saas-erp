package br.akd.svc.akadia.proxy.asaas.requests.assinatura.mocks;

import br.akd.svc.akadia.modules.web.proxy.asaas.requests.assinatura.AtualizaAssinaturaRequest;

import java.time.LocalDate;

public class AtualizaAssinaturaRequestBuilder {

    AtualizaAssinaturaRequestBuilder() {
    }
    AtualizaAssinaturaRequest atualizaAssinaturaRequest;
    public static AtualizaAssinaturaRequestBuilder builder() {
        AtualizaAssinaturaRequestBuilder builder = new AtualizaAssinaturaRequestBuilder();
        builder.atualizaAssinaturaRequest = new AtualizaAssinaturaRequest();
        builder.atualizaAssinaturaRequest.setBillingType("BOLETO");
        builder.atualizaAssinaturaRequest.setValue(400.0);
        builder.atualizaAssinaturaRequest.setNextDueDate(LocalDate.of(2023, 2, 8).toString());
        builder.atualizaAssinaturaRequest.setDiscount(null);
        builder.atualizaAssinaturaRequest.setInterest(null);
        builder.atualizaAssinaturaRequest.setFine(null);
        builder.atualizaAssinaturaRequest.setCycle("MONTHLY");
        builder.atualizaAssinaturaRequest.setDescription("Assinatura de plano standart");
        builder.atualizaAssinaturaRequest.setUpdatePendingPayments("true");
        builder.atualizaAssinaturaRequest.setExternalReference(null);
        return builder;
    }

    public AtualizaAssinaturaRequest build() {
        return atualizaAssinaturaRequest;
    }

}
