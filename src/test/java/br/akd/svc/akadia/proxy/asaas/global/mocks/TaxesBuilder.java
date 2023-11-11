package br.akd.svc.akadia.proxy.asaas.global.mocks;

import br.akd.svc.akadia.modules.web.proxy.asaas.global.Taxes;

public class TaxesBuilder {
    TaxesBuilder() {
    }

    Taxes taxes;

    public static TaxesBuilder builder() {
        TaxesBuilder builder = new TaxesBuilder();
        builder.taxes = new Taxes();
        builder.taxes.setRetainIss(true);
        builder.taxes.setIss(1);
        builder.taxes.setCofins(1);
        builder.taxes.setCsll(1);
        builder.taxes.setInss(1);
        builder.taxes.setIr(1.0);
        builder.taxes.setPis(1.0);
        return builder;
    }

    public Taxes build() {
        return taxes;
    }

}

