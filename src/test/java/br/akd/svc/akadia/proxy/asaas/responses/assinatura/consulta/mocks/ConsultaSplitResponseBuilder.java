package br.akd.svc.akadia.proxy.asaas.responses.assinatura.consulta.mocks;

import br.akd.svc.akadia.modules.web.proxy.asaas.responses.assinatura.consulta.ConsultaSplitResponse;

public class ConsultaSplitResponseBuilder {

    ConsultaSplitResponseBuilder() {
    }

    ConsultaSplitResponse consultaSplitResponse;

    public static ConsultaSplitResponseBuilder builder() {
        ConsultaSplitResponseBuilder builder = new ConsultaSplitResponseBuilder();
        builder.consultaSplitResponse = new ConsultaSplitResponse();
        builder.consultaSplitResponse.setWalletId(null);
        builder.consultaSplitResponse.setFixedValue(0.0);
        builder.consultaSplitResponse.setPercentualValue(0);
        builder.consultaSplitResponse.setStatus(null);
        builder.consultaSplitResponse.setRefusalReason(null);
        return builder;
    }

    public ConsultaSplitResponse build() {
        return consultaSplitResponse;
    }


}
