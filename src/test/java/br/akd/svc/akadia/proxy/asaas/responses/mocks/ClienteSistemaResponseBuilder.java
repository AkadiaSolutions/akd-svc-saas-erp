package br.akd.svc.akadia.proxy.asaas.responses.mocks;

import br.akd.svc.akadia.modules.global.enums.EstadoEnum;
import br.akd.svc.akadia.modules.web.proxy.asaas.responses.ClienteSistemaResponse;

import java.time.LocalDate;

public class ClienteSistemaResponseBuilder {

    ClienteSistemaResponseBuilder(){}
    ClienteSistemaResponse clienteSistemaResponse;

    public static ClienteSistemaResponseBuilder builder() {
        ClienteSistemaResponseBuilder builder = new ClienteSistemaResponseBuilder();
        builder.clienteSistemaResponse = new ClienteSistemaResponse();
        builder.clienteSistemaResponse.setObject("customer");
        builder.clienteSistemaResponse.setId("cus_000005105823");
        builder.clienteSistemaResponse.setDateCreated(LocalDate.of(2023, 2, 5).toString());
        builder.clienteSistemaResponse.setName("Gabriel Lagrota");
        builder.clienteSistemaResponse.setEmail("gabriellagrota23@gmail.com");
        builder.clienteSistemaResponse.setCompany(null);
        builder.clienteSistemaResponse.setPhone("979815415");
        builder.clienteSistemaResponse.setMobilePhone("979815415");
        builder.clienteSistemaResponse.setAddress("Avenida Coronel Manuel Py");
        builder.clienteSistemaResponse.setAddressNumber("583");
        builder.clienteSistemaResponse.setComplement("Sem complemento");
        builder.clienteSistemaResponse.setProvince("Lauzane Paulista");
        builder.clienteSistemaResponse.setPostalCode("02442090");
        builder.clienteSistemaResponse.setCpfCnpj("47153427821");
        builder.clienteSistemaResponse.setPersonType("FISICA");
        builder.clienteSistemaResponse.setDeleted(false);
        builder.clienteSistemaResponse.setAdditionalEmails(null);
        builder.clienteSistemaResponse.setExternalReference(null);
        builder.clienteSistemaResponse.setNotificationDisabled(true);
        builder.clienteSistemaResponse.setObservations("Sem observações");
        builder.clienteSistemaResponse.setMunicipalInscription("46683695908");
        builder.clienteSistemaResponse.setStateInscription("646681195275");
        builder.clienteSistemaResponse.setCanDelete(true);
        builder.clienteSistemaResponse.setCannotBeDeletedReason(null);
        builder.clienteSistemaResponse.setCanEdit(true);
        builder.clienteSistemaResponse.setCannotEditReason(null);
        builder.clienteSistemaResponse.setForeignCustomer(false);
        builder.clienteSistemaResponse.setCity(12565);
        builder.clienteSistemaResponse.setState(EstadoEnum.SP.getPrefix());
        builder.clienteSistemaResponse.setCountry("Brasil");
        return builder;
    }

    public ClienteSistemaResponse build() {
        return clienteSistemaResponse;
    }

}
