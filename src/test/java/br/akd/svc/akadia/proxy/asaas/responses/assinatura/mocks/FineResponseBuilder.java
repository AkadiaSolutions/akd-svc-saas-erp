package br.akd.svc.akadia.proxy.asaas.responses.assinatura.mocks;

import br.akd.svc.akadia.modules.web.proxy.asaas.responses.assinatura.FineResponse;

public class FineResponseBuilder {

    FineResponseBuilder(){}
    FineResponse fineResponse;

    public static FineResponseBuilder builder() {
        FineResponseBuilder builder = new FineResponseBuilder();
        builder.fineResponse = new FineResponse();
        builder.fineResponse.setValue(0.0);
        builder.fineResponse.setType("FIXED");
        return builder;
    }

    public FineResponse build() {
        return fineResponse;
    }

}
