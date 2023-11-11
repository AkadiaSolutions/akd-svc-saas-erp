package br.akd.svc.akadia.proxy.asaas.responses.assinatura.cancela.mocks;

import br.akd.svc.akadia.modules.web.proxy.asaas.responses.assinatura.cancela.CancelamentoAssinaturaResponse;

public class CancelamentoAssinaturaResponseBuilder {

    CancelamentoAssinaturaResponseBuilder(){}
    CancelamentoAssinaturaResponse cancelamentoAssinaturaResponse;

    public static CancelamentoAssinaturaResponseBuilder builder() {
        CancelamentoAssinaturaResponseBuilder builder = new CancelamentoAssinaturaResponseBuilder();
        builder.cancelamentoAssinaturaResponse = new CancelamentoAssinaturaResponse();
        builder.cancelamentoAssinaturaResponse.setDeleted(true);
        builder.cancelamentoAssinaturaResponse.setId("sub_i8U7a1UzweKt");
        return builder;
    }

    public CancelamentoAssinaturaResponse build() {
        return cancelamentoAssinaturaResponse;
    }

}
