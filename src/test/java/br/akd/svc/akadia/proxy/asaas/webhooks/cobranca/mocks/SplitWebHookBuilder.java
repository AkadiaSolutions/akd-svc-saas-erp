package br.akd.svc.akadia.proxy.asaas.webhooks.cobranca.mocks;

import br.akd.svc.akadia.modules.web.proxy.asaas.webhooks.cobranca.SplitWebHook;

public class SplitWebHookBuilder {

    SplitWebHookBuilder() {
    }

    SplitWebHook splitWebHook;

    public static SplitWebHookBuilder builder() {
        SplitWebHookBuilder builder = new SplitWebHookBuilder();
        builder.splitWebHook = new SplitWebHook();
        builder.splitWebHook.setWalletId(null);
        builder.splitWebHook.setFixedValue(0.0);
        builder.splitWebHook.setStatus(null);
        builder.splitWebHook.setRefusalReason(null);
        return builder;
    }

    public SplitWebHook build() {
        return splitWebHook;
    }


}
