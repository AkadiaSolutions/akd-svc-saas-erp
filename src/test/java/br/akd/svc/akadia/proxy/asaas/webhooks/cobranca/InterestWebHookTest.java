package br.akd.svc.akadia.proxy.asaas.webhooks.cobranca;

import br.akd.svc.akadia.modules.web.proxy.asaas.webhooks.cobranca.InterestWebHook;
import br.akd.svc.akadia.proxy.asaas.webhooks.cobranca.mocks.InterestWebHookBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
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
