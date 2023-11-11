package br.akd.svc.akadia.proxy.asaas.requests.assinatura.mocks;

import br.akd.svc.akadia.modules.web.proxy.asaas.requests.assinatura.AssinaturaRequest;
import br.akd.svc.akadia.proxy.asaas.requests.mocks.CreditCardHolderInfoRequestBuilder;
import br.akd.svc.akadia.proxy.asaas.requests.mocks.CreditCardRequestBuilder;

import java.time.LocalDate;

public class AssinaturaRequestBuilder {

    AssinaturaRequestBuilder() {
    }

    AssinaturaRequest assinaturaRequest;

    public static AssinaturaRequestBuilder builder() {
        AssinaturaRequestBuilder builder = new AssinaturaRequestBuilder();
        builder.assinaturaRequest = new AssinaturaRequest();
        builder.assinaturaRequest.setCustomer("cus_000005118516");
        builder.assinaturaRequest.setBillingType("CREDIT_CARD");
        builder.assinaturaRequest.setNextDueDate(LocalDate.of(2023, 2, 6).toString());
        builder.assinaturaRequest.setValue(600.0);
        builder.assinaturaRequest.setCycle("MONTHLY");
        builder.assinaturaRequest.setDescription("Assinatura de plano PRO");
        builder.assinaturaRequest.setCreditCardToken("c127baad-5943-45dd-a85e-8bbe3fb5c01a");
        builder.assinaturaRequest.setCreditCard(CreditCardRequestBuilder.builder().build());
        builder.assinaturaRequest.setCreditCardHolderInfo(CreditCardHolderInfoRequestBuilder.builder().build());
        return builder;
    }

    public AssinaturaRequest build() {
        return assinaturaRequest;
    }
}
