package br.akd.svc.akadia.proxy.asaas.webhooks.mocks;

import br.akd.svc.akadia.proxy.asaas.webhooks.SplitWebHook;

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
