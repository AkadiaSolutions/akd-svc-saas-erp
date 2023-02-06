package br.akd.svc.akadia.proxy.asaas.responses;

import br.akd.svc.akadia.models.enums.global.EstadoEnum;
import br.akd.svc.akadia.proxy.asaas.responses.mocks.ClienteSistemaResponseBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
@DisplayName("Response: ClienteSistema")
class ClienteSistemaResponseTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "ClienteSistemaResponse(object=customer, id=cus_000005105823, dateCreated=2023-02-05, name=Gabriel Lagrota, email=gabriellagrota23@gmail.com, company=null, phone=979815415, mobilePhone=979815415, address=Avenida Coronel Manuel Py, addressNumber=583, complement=Sem complemento, province=Lauzane Paulista, postalCode=02442090, cpfCnpj=47153427821, personType=FISICA, deleted=false, additionalEmails=null, externalReference=null, notificationDisabled=true, observations=Sem observações, municipalInscription=46683695908, stateInscription=646681195275, canDelete=true, cannotBeDeletedReason=null, canEdit=true, cannotEditReason=null, foreignCustomer=false, city=12565, state=SP, country=Brasil)",
                ClienteSistemaResponseBuilder.builder().build().toString()
        );
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        ClienteSistemaResponse clienteSistemaResponse = new ClienteSistemaResponse(
                "customer",
                "cus_000005105823",
                LocalDate.of(2023, 2, 5).toString(),
                "Gabriel Lagrota",
                "gabriellagrota23@gmail.com",
                null,
                "979815415",
                "979815415",
                "Avenida Coronel Manuel Py",
                "583",
                "Sem complemento",
                "Lauzane Paulista",
                "02442090",
                "47153427821",
                "FISICA",
                false,
                null,
                null,
                true,
                "Sem observações",
                "46683695908",
                "646681195275",
                true,
                null,
                true,
                null,
                false,
                12565,
                EstadoEnum.SP.getPrefix(),
                "Brasil"
        );
        Assertions.assertEquals(
                "ClienteSistemaResponse(object=customer, id=cus_000005105823, dateCreated=2023-02-05, name=Gabriel Lagrota, email=gabriellagrota23@gmail.com, company=null, phone=979815415, mobilePhone=979815415, address=Avenida Coronel Manuel Py, addressNumber=583, complement=Sem complemento, province=Lauzane Paulista, postalCode=02442090, cpfCnpj=47153427821, personType=FISICA, deleted=false, additionalEmails=null, externalReference=null, notificationDisabled=true, observations=Sem observações, municipalInscription=46683695908, stateInscription=646681195275, canDelete=true, cannotBeDeletedReason=null, canEdit=true, cannotEditReason=null, foreignCustomer=false, city=12565, state=SP, country=Brasil)",
                clienteSistemaResponse.toString()
        );

    }

}
