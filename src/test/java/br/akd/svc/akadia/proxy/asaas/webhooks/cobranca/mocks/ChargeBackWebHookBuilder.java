package br.akd.svc.akadia.proxy.asaas.webhooks.cobranca.mocks;

import br.akd.svc.akadia.modules.web.proxy.asaas.webhooks.cobranca.ChargeBackWebHook;

public class ChargeBackWebHookBuilder {

    ChargeBackWebHookBuilder() {
    }

    ChargeBackWebHook chargeBackWebHook;

    public static ChargeBackWebHookBuilder builder() {
        ChargeBackWebHookBuilder builder = new ChargeBackWebHookBuilder();
        builder.chargeBackWebHook = new ChargeBackWebHook();
        builder.chargeBackWebHook.setStatus(null);
        builder.chargeBackWebHook.setReason(null);
        return builder;
    }

    public ChargeBackWebHook build() {
        return chargeBackWebHook;
    }


}
