package br.akd.svc.akadia.proxy.asaas.requests.assinatura;

import br.akd.svc.akadia.modules.web.proxy.asaas.requests.assinatura.InterestRequest;
import br.akd.svc.akadia.proxy.asaas.requests.assinatura.mocks.InterestRequestBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Request: Interest")
class InterestRequestTest {
    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals("InterestRequest(value=0.0)", InterestRequestBuilder.builder().build().toString()
        );

    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        InterestRequest interestRequest = new InterestRequest(
                0.0
        );
        Assertions.assertEquals("InterestRequest(value=0.0)", interestRequest.toString()
        );

    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        InterestRequest interestRequest = InterestRequest.builder()
                .value(0.0)
                .build();
        Assertions.assertEquals("InterestRequest(value=0.0)", interestRequest.toString()
        );
    }
}
