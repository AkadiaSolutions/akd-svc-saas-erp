package br.akd.svc.akadia.proxy.asaas.requests.assinatura;

import br.akd.svc.akadia.proxy.asaas.requests.assinatura.mocks.AssinaturaRequestBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Request: AssinaturaRequest")
class AssinaturaRequestTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "AssinaturaRequest(customer=cus_000005118516, billingType=CREDIT_CARD, nextDueDate=2023-02-06, value=600.0, cycle=MONTHLY, description=Assinatura de plano PRO, creditCardToken=c127baad-5943-45dd-a85e-8bbe3fb5c01a, creditCard=CreditCardRequest(holderName=Gabriel Lagrota, number=5162306219378829, expiryMonth=1, expiryYear=2024, ccv=318), creditCardHolderInfo=CreditCardHolderInfoRequest(name=Gabriel Lagrota, email=gabriellagrota23@gmail.com, cpfCnpj=47153427821, postalCode=02442090, addressNumber=583, addressComplement=Casa 4, phone=11979815415, mobilePhone=11979815415))",
                AssinaturaRequestBuilder.builder().build().toString()
        );

    }

}
