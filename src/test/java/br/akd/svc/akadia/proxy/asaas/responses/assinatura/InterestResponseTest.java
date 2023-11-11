package br.akd.svc.akadia.proxy.asaas.responses.assinatura;

import br.akd.svc.akadia.modules.web.proxy.asaas.responses.assinatura.InterestResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Response: Interest")
class InterestResponseTest {

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        InterestResponse interestResponse = new InterestResponse(
                0.0,
                "PERCENTAGE"
        );
        Assertions.assertEquals(
                "InterestResponse(value=0.0, type=PERCENTAGE)",
                interestResponse.toString()
        );

    }

}
