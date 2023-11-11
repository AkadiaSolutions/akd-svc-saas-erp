package br.akd.svc.akadia.proxy.asaas.responses;

import br.akd.svc.akadia.modules.web.proxy.asaas.responses.CreditCardResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Response: CreditCard")
class CreditCardResponseTest {

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        CreditCardResponse creditCardResponse = new CreditCardResponse(
                "5162306219378829",
                "MASTERCARD",
                "c127baad-5943-45dd-a85e-8bbe3fb5c01a"
        );
        Assertions.assertEquals(
                "CreditCardResponse(creditCardNumber=5162306219378829, creditCardBrand=MASTERCARD, creditCardToken=c127baad-5943-45dd-a85e-8bbe3fb5c01a)",
                creditCardResponse.toString()
        );

    }

}
