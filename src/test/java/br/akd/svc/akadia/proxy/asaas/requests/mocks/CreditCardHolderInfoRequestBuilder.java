package br.akd.svc.akadia.proxy.asaas.requests.mocks;

import br.akd.svc.akadia.modules.web.proxy.asaas.requests.CreditCardHolderInfoRequest;

public class CreditCardHolderInfoRequestBuilder {

    CreditCardHolderInfoRequestBuilder(){}
    CreditCardHolderInfoRequest creditCardHolderInfoRequest;

    public static CreditCardHolderInfoRequestBuilder builder() {
        CreditCardHolderInfoRequestBuilder builder = new CreditCardHolderInfoRequestBuilder();
        builder.creditCardHolderInfoRequest = new CreditCardHolderInfoRequest();
        builder.creditCardHolderInfoRequest.setName("Gabriel Lagrota");
        builder.creditCardHolderInfoRequest.setEmail("gabriellagrota23@gmail.com");
        builder.creditCardHolderInfoRequest.setCpfCnpj("47153427821");
        builder.creditCardHolderInfoRequest.setPostalCode("02442090");
        builder.creditCardHolderInfoRequest.setAddressNumber("583");
        builder.creditCardHolderInfoRequest.setAddressComplement("Casa 4");
        builder.creditCardHolderInfoRequest.setPhone("11979815415");
        builder.creditCardHolderInfoRequest.setMobilePhone("11979815415");
        return builder;
    }

    public CreditCardHolderInfoRequest build() {
        return creditCardHolderInfoRequest;
    }

}
