package br.akd.svc.akadia.proxy.asaas.webhooks.mocks;

import br.akd.svc.akadia.proxy.asaas.webhooks.ChargeBackWebHook;

public class AtualizacaoCobrancaWebHookBuilder {
    AtualizacaoCobrancaWebHookBuilder() {
    }

    ChargeBackWebHook chargeBackWebHook;

    public static AtualizacaoCobrancaWebHookBuilder builder() {
        AtualizacaoCobrancaWebHookBuilder builder = new AtualizacaoCobrancaWebHookBuilder();
        builder.chargeBackWebHook = new ChargeBackWebHook();
        builder.chargeBackWebHook.setStatus(null);
        builder.chargeBackWebHook.setReason(null);
        return builder;
    }

    public ChargeBackWebHook build() {
        return chargeBackWebHook;
    }

}
