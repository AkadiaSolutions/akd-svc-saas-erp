package br.akd.svc.akadia.proxy.asaas.responses.assinatura;

import br.akd.svc.akadia.modules.web.proxy.asaas.responses.assinatura.FineResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Response: Fine")
class FineResponseTest {

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        FineResponse fineResponse = new FineResponse(
                0.0,
                "FIXED"
        );
        Assertions.assertEquals(
                "FineResponse(value=0.0, type=FIXED)",
                fineResponse.toString()
        );

    }

}
