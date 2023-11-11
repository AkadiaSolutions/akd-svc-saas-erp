package br.akd.svc.akadia.proxy.asaas.webhooks.cobranca.mocks;

import br.akd.svc.akadia.modules.web.proxy.asaas.webhooks.cobranca.CreditCardWebHook;

public class CreditCardWebHookBuilder {

    CreditCardWebHookBuilder() {
    }

    CreditCardWebHook creditCardWebHook;

    public static CreditCardWebHookBuilder builder() {
        CreditCardWebHookBuilder builder = new CreditCardWebHookBuilder();
        builder.creditCardWebHook = new CreditCardWebHook();
        builder.creditCardWebHook.setCreditCardNumber("5162306219378829");
        builder.creditCardWebHook.setCreditCardBrand("VISA");
        builder.creditCardWebHook.setCreditCardToken("1b9ad2a7-76ee-4a88-b6c2-9a0d308bac5e");
        return builder;
    }

    public CreditCardWebHook build() {
        return creditCardWebHook;
    }

}
