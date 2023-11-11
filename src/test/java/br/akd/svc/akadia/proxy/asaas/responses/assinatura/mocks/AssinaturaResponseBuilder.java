package br.akd.svc.akadia.proxy.asaas.responses.assinatura.mocks;

import br.akd.svc.akadia.modules.web.proxy.asaas.responses.assinatura.AssinaturaResponse;
import br.akd.svc.akadia.proxy.asaas.responses.mocks.CreditCardResponseBuilder;

import java.time.LocalDate;

public class AssinaturaResponseBuilder {

    AssinaturaResponseBuilder(){}
    AssinaturaResponse assinaturaResponse;

    public static AssinaturaResponseBuilder builder() {
        AssinaturaResponseBuilder builder = new AssinaturaResponseBuilder();
        builder.assinaturaResponse = new AssinaturaResponse();
        builder.assinaturaResponse.setObject("subscription");
        builder.assinaturaResponse.setId("sub_C2rGa18j8cT4");
        builder.assinaturaResponse.setDateCreated(LocalDate.of(2023, 2, 6).toString());
        builder.assinaturaResponse.setCustomer("cus_000005118516");
        builder.assinaturaResponse.setPaymentLink(null);
        builder.assinaturaResponse.setValue(600.0);
        builder.assinaturaResponse.setNextDueDate(LocalDate.of(2023, 2, 6).plusDays(7).toString());
        builder.assinaturaResponse.setCycle("MONTHLY");
        builder.assinaturaResponse.setDescription("Assinatura de plano PRO");
        builder.assinaturaResponse.setBillingType("CREDIT_CARD");
        builder.assinaturaResponse.setDeleted(false);
        builder.assinaturaResponse.setStatus("ACTIVE");
        builder.assinaturaResponse.setExternalReference(null);
        builder.assinaturaResponse.setCreditCard(null);
        builder.assinaturaResponse.setFine(FineResponseBuilder.builder().build());
        builder.assinaturaResponse.setInterest(InterestResponseBuilder.builder().build());
        return builder;
    }

    public AssinaturaResponseBuilder comCreditCard() {
        this.assinaturaResponse.setCreditCard(CreditCardResponseBuilder.builder().build());
        return this;
    }

    public AssinaturaResponse build() {
        return assinaturaResponse;
    }
}
