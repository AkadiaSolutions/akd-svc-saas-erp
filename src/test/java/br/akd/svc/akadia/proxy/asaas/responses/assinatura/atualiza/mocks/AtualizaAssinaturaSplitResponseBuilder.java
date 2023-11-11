package br.akd.svc.akadia.proxy.asaas.responses.assinatura.atualiza.mocks;

import br.akd.svc.akadia.modules.web.proxy.asaas.responses.assinatura.atualiza.AtualizaAssinaturaSplitResponse;

public class AtualizaAssinaturaSplitResponseBuilder {

    AtualizaAssinaturaSplitResponseBuilder() {
    }

    AtualizaAssinaturaSplitResponse atualizaAssinaturaSplitResponse;

    public static AtualizaAssinaturaSplitResponseBuilder builder() {
        AtualizaAssinaturaSplitResponseBuilder builder = new AtualizaAssinaturaSplitResponseBuilder();
        builder.atualizaAssinaturaSplitResponse = new AtualizaAssinaturaSplitResponse();
        builder.atualizaAssinaturaSplitResponse.setWalletId(null);
        builder.atualizaAssinaturaSplitResponse.setFixedValue(0.0);
        builder.atualizaAssinaturaSplitResponse.setPercentualValue(0.0);
        builder.atualizaAssinaturaSplitResponse.setStatus(null);
        builder.atualizaAssinaturaSplitResponse.setRefusalReason(null);
        return builder;
    }

    public AtualizaAssinaturaSplitResponse build() {
        return atualizaAssinaturaSplitResponse;
    }

}
