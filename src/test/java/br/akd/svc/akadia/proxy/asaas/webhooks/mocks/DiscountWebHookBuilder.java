package br.akd.svc.akadia.proxy.asaas.webhooks.mocks;

import br.akd.svc.akadia.proxy.asaas.webhooks.DiscountWebHook;

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
