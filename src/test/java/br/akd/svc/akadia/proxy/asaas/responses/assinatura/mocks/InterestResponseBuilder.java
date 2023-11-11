package br.akd.svc.akadia.proxy.asaas.responses.assinatura.mocks;

import br.akd.svc.akadia.modules.web.proxy.asaas.responses.assinatura.InterestResponse;

public class InterestResponseBuilder {

    InterestResponseBuilder(){}
    InterestResponse interestResponse;

    public static InterestResponseBuilder builder() {
        InterestResponseBuilder builder = new InterestResponseBuilder();
        builder.interestResponse = new InterestResponse();
        builder.interestResponse.setValue(0.0);
        builder.interestResponse.setType("PERCENTAGE");
        return builder;
    }

    public InterestResponse build() {
        return interestResponse;
    }

}
