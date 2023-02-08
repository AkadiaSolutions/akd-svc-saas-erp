package br.akd.svc.akadia.proxy.asaas.webhooks;

import br.akd.svc.akadia.proxy.asaas.webhooks.mocks.InterestWebHookBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Webhook: Interest")
class InterestWebHookTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "InterestWebHook(value=0.0, type=PERCENTAGE)",
                InterestWebHookBuilder.builder().build().toString()
        );
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        InterestWebHook interestWebHook = new InterestWebHook(
                0.0,
                "PERCENTAGE"
        );
        Assertions.assertEquals(
                "InterestWebHook(value=0.0, type=PERCENTAGE)",
                interestWebHook.toString()
        );

    }

}
