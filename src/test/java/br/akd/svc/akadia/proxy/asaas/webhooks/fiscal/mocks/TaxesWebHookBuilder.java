package br.akd.svc.akadia.proxy.asaas.webhooks.fiscal.mocks;

import br.akd.svc.akadia.modules.web.proxy.asaas.webhooks.fiscal.TaxesWebHook;

public class TaxesWebHookBuilder {
    TaxesWebHookBuilder() {
    }

    TaxesWebHook taxesWebHook;

    public static TaxesWebHookBuilder builder() {
        TaxesWebHookBuilder builder = new TaxesWebHookBuilder();
        builder.taxesWebHook = new TaxesWebHook();
        builder.taxesWebHook.setRetainIss(true);
        builder.taxesWebHook.setIss(1);
        builder.taxesWebHook.setCofins(1);
        builder.taxesWebHook.setCsll(1);
        builder.taxesWebHook.setInss(1);
        builder.taxesWebHook.setIr(1.0);
        builder.taxesWebHook.setPis(1.0);
        return builder;
    }

    public TaxesWebHook build() {
        return taxesWebHook;
    }
}
