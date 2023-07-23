package br.akd.svc.akadia.proxy.asaas.requests;

import br.akd.svc.akadia.proxy.asaas.requests.mocks.ClienteSistemaRequestBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Request: ClienteSistemaRequest")
class ClienteSistemaRequestTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "ClienteSistemaRequest(name=Gabriel, email=gabriellagrota23@gmail.com, phone=11979815415, " +
                        "mobilePhone=11979815415, cpfCnpj=47153427821, postalCode=02442090, " +
                        "address=Avenida Coronel Manuel Py, addressNumber=583, complement=Casa 4, " +
                        "province=Lauzane Paulista, externalReference=null, notificationDisabled=true, " +
                        "additionalEmails=null, municipalInscription=null, stateInscription=null, observations=null)",
                ClienteSistemaRequestBuilder.builder().build().toString()
        );

    }

}
