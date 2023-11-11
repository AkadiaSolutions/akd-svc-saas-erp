package br.akd.svc.akadia.proxy.asaas.responses.assinatura.atualiza.mocks;

import br.akd.svc.akadia.modules.web.proxy.asaas.responses.assinatura.atualiza.AtualizaAssinaturaDiscountResponse;

public class AtualizaAssinaturaDiscountResponseBuilder {

    AtualizaAssinaturaDiscountResponseBuilder(){}
    AtualizaAssinaturaDiscountResponse atualizaAssinaturaDiscountResponse;

    public static AtualizaAssinaturaDiscountResponseBuilder builder() {
        AtualizaAssinaturaDiscountResponseBuilder builder = new AtualizaAssinaturaDiscountResponseBuilder();
        builder.atualizaAssinaturaDiscountResponse = new AtualizaAssinaturaDiscountResponse();
        builder.atualizaAssinaturaDiscountResponse.setValue(0.0);
        builder.atualizaAssinaturaDiscountResponse.setDueDateLimitDays(0);
        builder.atualizaAssinaturaDiscountResponse.setType("PERCENTAGE");
        return builder;
    }

    public AtualizaAssinaturaDiscountResponse build() {
        return atualizaAssinaturaDiscountResponse;
    }

}
