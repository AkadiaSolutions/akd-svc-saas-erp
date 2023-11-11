package br.akd.svc.akadia.proxy.asaas.responses.mocks;

import br.akd.svc.akadia.modules.web.proxy.asaas.responses.CreditCardResponse;

public class CreditCardResponseBuilder {

    CreditCardResponseBuilder(){}
    CreditCardResponse creditCardResponse;

    public static CreditCardResponseBuilder builder() {
        CreditCardResponseBuilder builder = new CreditCardResponseBuilder();
        builder.creditCardResponse = new CreditCardResponse();
        builder.creditCardResponse.setCreditCardNumber("5162306219378829");
        builder.creditCardResponse.setCreditCardBrand("MASTERCARD");
        builder.creditCardResponse.setCreditCardToken("c127baad-5943-45dd-a85e-8bbe3fb5c01a");
        return builder;
    }

    public CreditCardResponse build() {
        return creditCardResponse;
    }
}
