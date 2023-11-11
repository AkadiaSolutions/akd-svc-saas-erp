package br.akd.svc.akadia.proxy.asaas.webhooks.cobranca.mocks;

import br.akd.svc.akadia.modules.web.proxy.asaas.webhooks.cobranca.AtualizacaoCobrancaWebHook;
import br.akd.svc.akadia.modules.web.proxy.asaas.webhooks.cobranca.enums.EventoCobrancaEnum;

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

    public AtualizacaoCobrancaWebHookBuilder comEnumEvento(EventoCobrancaEnum evento) {
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
