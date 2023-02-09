package br.akd.svc.akadia.proxy.asaas.webhooks.mocks;

import br.akd.svc.akadia.proxy.asaas.webhooks.AtualizacaoCobrancaWebHook;
import br.akd.svc.akadia.proxy.asaas.webhooks.enums.EventEnum;

public class AtualizacaoCobrancaWebHookBuilder {
    AtualizacaoCobrancaWebHookBuilder() {
    }

    AtualizacaoCobrancaWebHook atualizacaoCobrancaWebHook;

    public static AtualizacaoCobrancaWebHookBuilder builder() {
        AtualizacaoCobrancaWebHookBuilder builder = new AtualizacaoCobrancaWebHookBuilder();
        builder.atualizacaoCobrancaWebHook = new AtualizacaoCobrancaWebHook();
        builder.atualizacaoCobrancaWebHook.setEvent(null);
        builder.atualizacaoCobrancaWebHook.setPayment(null);
        return builder;
    }

    public AtualizacaoCobrancaWebHookBuilder comEnumEvento(EventEnum evento) {
        atualizacaoCobrancaWebHook.setEvent(evento);
        return this;
    }

    public AtualizacaoCobrancaWebHookBuilder comPagamento() {
        atualizacaoCobrancaWebHook.setPayment(PagamentoWebHookBuilder.builder().build());
        return this;
    }

    public AtualizacaoCobrancaWebHook build() {
        return atualizacaoCobrancaWebHook;
    }

}
