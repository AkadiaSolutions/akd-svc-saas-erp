package br.akd.svc.akadia.proxy.asaas.responses.fiscal.mocks;

import br.akd.svc.akadia.modules.web.proxy.asaas.responses.fiscal.CriaConfigFiscalResponse;

public class CriaConfigFiscalResponseBuilder {
    CriaConfigFiscalResponseBuilder() {
    }

    CriaConfigFiscalResponse criaConfigFiscalResponse;

    public static CriaConfigFiscalResponseBuilder builder() {
        CriaConfigFiscalResponseBuilder builder = new CriaConfigFiscalResponseBuilder();
        builder.criaConfigFiscalResponse = new CriaConfigFiscalResponse();
        builder.criaConfigFiscalResponse.setMunicipalServiceId("104");
        builder.criaConfigFiscalResponse.setMunicipalServiceCode("1.05");
        builder.criaConfigFiscalResponse.setMunicipalServiceName("Licenciamento ou cessão de direito de uso de programas de computação, inclusive distribuição.");
        builder.criaConfigFiscalResponse.setDeductions(0);
        builder.criaConfigFiscalResponse.setInvoiceCreationPeriod(null);
        builder.criaConfigFiscalResponse.setDaysBeforeDueDate(null);
        builder.criaConfigFiscalResponse.setReceivedOnly(true);
        builder.criaConfigFiscalResponse.setObservations("Configuração fiscal realizada");
        builder.criaConfigFiscalResponse.setTaxes(null);
        return builder;
    }

    public CriaConfigFiscalResponse build() {
        return criaConfigFiscalResponse;
    }

}