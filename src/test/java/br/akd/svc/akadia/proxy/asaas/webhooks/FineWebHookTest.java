package br.akd.svc.akadia.proxy.asaas.webhooks;

import br.akd.svc.akadia.proxy.asaas.webhooks.mocks.FineWebHookBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Webhook: Fine")
class FineWebHookTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "FineWebHook(value=0.0, type=PERCENTAGE)",
                FineWebHookBuilder.builder().build().toString()
        );
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        FineWebHook fineWebHook = new FineWebHook(
                0.0,
                "PERCENTAGE"
        );
        Assertions.assertEquals(
                "FineWebHook(value=0.0, type=PERCENTAGE)",
                fineWebHook.toString()
        );

    }

}
