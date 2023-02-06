package br.akd.svc.akadia.proxy.asaas.requests.mocks;

import br.akd.svc.akadia.proxy.asaas.requests.ClienteSistemaRequest;

public class ClienteSistemaRequestBuilder {

    ClienteSistemaRequestBuilder() {
    }

    ClienteSistemaRequest clienteSistemaRequest;

    public static ClienteSistemaRequestBuilder builder() {
        ClienteSistemaRequestBuilder builder = new ClienteSistemaRequestBuilder();
        builder.clienteSistemaRequest = new ClienteSistemaRequest();
        builder.clienteSistemaRequest.setName("Gabriel");
        builder.clienteSistemaRequest.setEmail("gabriellagrota23@gmail.com");
        builder.clienteSistemaRequest.setPhone("11979815415");
        builder.clienteSistemaRequest.setMobilePhone("11979815415");
        builder.clienteSistemaRequest.setCpfCnpj("47153427821");
        builder.clienteSistemaRequest.setPostalCode("02442090");
        builder.clienteSistemaRequest.setAddress("Avenida Coronel Manuel Py");
        builder.clienteSistemaRequest.setAddressNumber("583");
        builder.clienteSistemaRequest.setComplement("Casa 4");
        builder.clienteSistemaRequest.setProvince("Lauzane Paulista");
        builder.clienteSistemaRequest.setExternalReference(null);
        builder.clienteSistemaRequest.setNotificationDisabled(true);
        builder.clienteSistemaRequest.setAdditionalEmails(null);
        builder.clienteSistemaRequest.setMunicipalInscription(null);
        builder.clienteSistemaRequest.setStateInscription(null);
        builder.clienteSistemaRequest.setObservations(null);
        return builder;
    }

    public ClienteSistemaRequest build() {
        return clienteSistemaRequest;
    }

}
