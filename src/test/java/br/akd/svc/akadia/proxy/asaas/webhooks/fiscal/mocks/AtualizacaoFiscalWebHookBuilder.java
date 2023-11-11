package br.akd.svc.akadia.proxy.asaas.webhooks.fiscal.mocks;

import br.akd.svc.akadia.modules.web.proxy.asaas.webhooks.fiscal.AtualizacaoFiscalWebHook;
import br.akd.svc.akadia.modules.web.proxy.asaas.webhooks.fiscal.enums.EventoFiscalEnum;

public class AtualizacaoFiscalWebHookBuilder {

    AtualizacaoFiscalWebHookBuilder() {
    }

    AtualizacaoFiscalWebHook atualizacaoFiscalWebHook;

    public static AtualizacaoFiscalWebHookBuilder builder() {
        AtualizacaoFiscalWebHookBuilder builder = new AtualizacaoFiscalWebHookBuilder();
        builder.atualizacaoFiscalWebHook = new AtualizacaoFiscalWebHook();
        builder.atualizacaoFiscalWebHook.setEvent(EventoFiscalEnum.INVOICE_AUTHORIZED);
        builder.atualizacaoFiscalWebHook.setInvoice(null);
        return builder;
    }

    public AtualizacaoFiscalWebHook build() {
        return atualizacaoFiscalWebHook;
    }
}
