package br.akd.svc.akadia.proxy.asaas.webhooks.mocks;

import br.akd.svc.akadia.proxy.asaas.webhooks.AtualizacaoCobrancaWebHook;
import br.akd.svc.akadia.proxy.asaas.webhooks.enums.EventEnum;

public class ChargeBackWebHookBuilder {

    ChargeBackWebHookBuilder() {
    }

    AtualizacaoCobrancaWebHook atualizacaoCobrancaWebHook;

    public static ChargeBackWebHookBuilder builder() {
        ChargeBackWebHookBuilder builder = new ChargeBackWebHookBuilder();
        builder.atualizacaoCobrancaWebHook = new AtualizacaoCobrancaWebHook();
        builder.atualizacaoCobrancaWebHook.setEvent(EventEnum.PAYMENT_CONFIRMED);
        builder.atualizacaoCobrancaWebHook.setPayment(null);
        return builder;
    }

    public AtualizacaoCobrancaWebHook build() {
        return atualizacaoCobrancaWebHook;
    }


}
