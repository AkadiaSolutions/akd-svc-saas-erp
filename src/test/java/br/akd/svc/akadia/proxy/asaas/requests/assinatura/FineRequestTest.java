package br.akd.svc.akadia.proxy.asaas.requests.assinatura;

import br.akd.svc.akadia.modules.web.proxy.asaas.requests.assinatura.FineRequest;
import br.akd.svc.akadia.proxy.asaas.requests.assinatura.mocks.FineRequestBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Request: Fine")
class FineRequestTest {
    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals("FineRequest(value=0.0)", FineRequestBuilder.builder().build().toString()
        );

    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        FineRequest fineRequest = new FineRequest(
                0.0
        );
        Assertions.assertEquals("FineRequest(value=0.0)",fineRequest.toString()
        );

    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        FineRequest fineRequest = FineRequest.builder()
                .value(0.0)
                .build();
        Assertions.assertEquals("FineRequest(value=0.0)", fineRequest.toString()
        );
    }
}
