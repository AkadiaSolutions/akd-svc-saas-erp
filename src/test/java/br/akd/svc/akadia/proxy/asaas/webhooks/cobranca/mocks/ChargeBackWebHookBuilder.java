package br.akd.svc.akadia.proxy.asaas.webhooks.cobranca.mocks;

import br.akd.svc.akadia.proxy.asaas.webhooks.cobranca.AtualizacaoCobrancaWebHook;
import br.akd.svc.akadia.proxy.asaas.webhooks.cobranca.enums.EventoCobrancaEnum;

public class ChargeBackWebHookBuilder {

    ChargeBackWebHookBuilder() {
    }

    AtualizacaoCobrancaWebHook atualizacaoCobrancaWebHook;

    public static ChargeBackWebHookBuilder builder() {
        ChargeBackWebHookBuilder builder = new ChargeBackWebHookBuilder();
        builder.atualizacaoCobrancaWebHook = new AtualizacaoCobrancaWebHook();
        builder.atualizacaoCobrancaWebHook.setEvent(EventoCobrancaEnum.PAYMENT_CONFIRMED);
        builder.atualizacaoCobrancaWebHook.setPayment(null);
        return builder;
    }

    public AtualizacaoCobrancaWebHook build() {
        return atualizacaoCobrancaWebHook;
    }


}
