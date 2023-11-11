package br.akd.svc.akadia.proxy.asaas.requests.fiscal.mocks;

import br.akd.svc.akadia.modules.web.proxy.asaas.requests.fiscal.CriaConfigFiscalRequest;
import br.akd.svc.akadia.modules.web.proxy.asaas.requests.fiscal.EffectiveDatePeriodEnum;

public class CriaConfigFiscalRequestBuilder {
    CriaConfigFiscalRequestBuilder() {
    }

    CriaConfigFiscalRequest criaConfigFiscalRequest;

    public static CriaConfigFiscalRequestBuilder builder() {
        CriaConfigFiscalRequestBuilder builder = new CriaConfigFiscalRequestBuilder();
        builder.criaConfigFiscalRequest = new CriaConfigFiscalRequest();
        builder.criaConfigFiscalRequest.setMunicipalServiceId("104");
        builder.criaConfigFiscalRequest.setMunicipalServiceCode("1.05");
        builder.criaConfigFiscalRequest.setMunicipalServiceName("Licenciamento ou cessão de direito de uso de programas de computação, inclusive distribuição.");
        builder.criaConfigFiscalRequest.setUpdatePayment(null);
        builder.criaConfigFiscalRequest.setDeductions(0);
        builder.criaConfigFiscalRequest.setEffectiveDatePeriod(EffectiveDatePeriodEnum.ON_PAYMENT_CONFIRMATION);
        builder.criaConfigFiscalRequest.setReceivedOnly(true);
        builder.criaConfigFiscalRequest.setDaysBeforeDueDate(null);
        builder.criaConfigFiscalRequest.setObservations("Configuração fiscal realizada");
        builder.criaConfigFiscalRequest.setTaxes(null);
        return builder;
    }

    public CriaConfigFiscalRequest build() {
        return criaConfigFiscalRequest;
    }

}