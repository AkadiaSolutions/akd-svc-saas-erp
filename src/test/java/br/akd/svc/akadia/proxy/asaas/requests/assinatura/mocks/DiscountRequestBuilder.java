package br.akd.svc.akadia.proxy.asaas.requests.assinatura.mocks;

import br.akd.svc.akadia.modules.web.proxy.asaas.requests.assinatura.DiscountRequest;

public class DiscountRequestBuilder {

    DiscountRequestBuilder(){}
    DiscountRequest discountRequest;

    public static DiscountRequestBuilder builder() {
        DiscountRequestBuilder builder = new DiscountRequestBuilder();
        builder.discountRequest = new DiscountRequest();
        builder.discountRequest.setValue(0.0);
        builder.discountRequest.setDueDateLimitDays(0);
        return builder;
    }

    public DiscountRequest build() {
        return discountRequest;
    }

}
