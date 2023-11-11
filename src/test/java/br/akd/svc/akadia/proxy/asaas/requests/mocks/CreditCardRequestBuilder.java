package br.akd.svc.akadia.proxy.asaas.requests.mocks;

import br.akd.svc.akadia.modules.web.proxy.asaas.requests.CreditCardRequest;

public class CreditCardRequestBuilder {

    CreditCardRequestBuilder(){}
    CreditCardRequest creditCardRequest;

    public static CreditCardRequestBuilder builder() {
        CreditCardRequestBuilder builder = new CreditCardRequestBuilder();
        builder.creditCardRequest = new CreditCardRequest();
        builder.creditCardRequest.setHolderName("Gabriel Lagrota");
        builder.creditCardRequest.setNumber("5162306219378829");
        builder.creditCardRequest.setExpiryMonth("1");
        builder.creditCardRequest.setExpiryYear("2024");
        builder.creditCardRequest.setCcv("318");
        return builder;
    }

    public CreditCardRequest build() {
        return creditCardRequest;
    }

}
