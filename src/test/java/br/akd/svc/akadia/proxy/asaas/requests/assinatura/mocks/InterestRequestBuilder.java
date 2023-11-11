package br.akd.svc.akadia.proxy.asaas.requests.assinatura.mocks;

import br.akd.svc.akadia.modules.web.proxy.asaas.requests.assinatura.InterestRequest;

public class InterestRequestBuilder {

    InterestRequestBuilder() {
    }

    InterestRequest interestRequest;

    public static InterestRequestBuilder builder() {
        InterestRequestBuilder builder = new InterestRequestBuilder();
        builder.interestRequest = new InterestRequest();
        builder.interestRequest.setValue(0.0);
        return builder;
    }

    public InterestRequest build() {
        return interestRequest;
    }

}
