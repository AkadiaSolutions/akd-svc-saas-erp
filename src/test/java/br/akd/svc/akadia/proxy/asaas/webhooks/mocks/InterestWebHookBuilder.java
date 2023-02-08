package br.akd.svc.akadia.proxy.asaas.webhooks.mocks;

import br.akd.svc.akadia.proxy.asaas.webhooks.InterestWebHook;

public class InterestWebHookBuilder {

    InterestWebHookBuilder() {
    }

    InterestWebHook interestWebHook;

    public static InterestWebHookBuilder builder() {
        InterestWebHookBuilder builder = new InterestWebHookBuilder();
        builder.interestWebHook = new InterestWebHook();
        builder.interestWebHook.setValue(0.0);
        builder.interestWebHook.setType("PERCENTAGE");
        return builder;
    }

    public InterestWebHook build() {
        return interestWebHook;
    }

}
