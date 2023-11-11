package br.akd.svc.akadia.proxy.asaas.webhooks.cobranca.mocks;

import br.akd.svc.akadia.modules.web.proxy.asaas.webhooks.cobranca.DiscountWebHook;

public class DiscountWebHookBuilder {
    DiscountWebHookBuilder() {
    }

    DiscountWebHook discountWebHook;

    public static DiscountWebHookBuilder builder() {
        DiscountWebHookBuilder builder = new DiscountWebHookBuilder();
        builder.discountWebHook = new DiscountWebHook();
        builder.discountWebHook.setValue(0.0);
        builder.discountWebHook.setDueDateLimitDays(0);
        builder.discountWebHook.setType("PERCENTAGE");
        return builder;
    }

    public DiscountWebHook build() {
        return discountWebHook;
    }

}
