package br.akd.svc.akadia.proxy.asaas.webhooks.cobranca.mocks;

import br.akd.svc.akadia.modules.web.proxy.asaas.webhooks.cobranca.FineWebHook;

public class FineWebHookBuilder {

    FineWebHookBuilder() {
    }

    FineWebHook fineWebHook;

    public static FineWebHookBuilder builder() {
        FineWebHookBuilder builder = new FineWebHookBuilder();
        builder.fineWebHook = new FineWebHook();
        builder.fineWebHook.setValue(0.0);
        builder.fineWebHook.setType("PERCENTAGE");
        return builder;
    }

    public FineWebHook build() {
        return fineWebHook;
    }

}
