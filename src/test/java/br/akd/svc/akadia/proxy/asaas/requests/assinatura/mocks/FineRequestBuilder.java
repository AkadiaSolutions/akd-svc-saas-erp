package br.akd.svc.akadia.proxy.asaas.requests.assinatura.mocks;

import br.akd.svc.akadia.modules.web.proxy.asaas.requests.assinatura.FineRequest;

public class FineRequestBuilder {

    FineRequestBuilder() {
    }

    FineRequest fineRequest;

    public static FineRequestBuilder builder() {
        FineRequestBuilder builder = new FineRequestBuilder();
        builder.fineRequest = new FineRequest();
        builder.fineRequest.setValue(0.0);
        return builder;
    }

    public FineRequest build() {
        return fineRequest;
    }

}
