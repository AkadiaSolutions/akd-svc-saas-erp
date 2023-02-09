package br.akd.svc.akadia.proxy.asaas.webhooks;

import br.akd.svc.akadia.proxy.asaas.webhooks.mocks.DiscountWebHookBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Webhook: Discount")
class DiscountWebHookTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "DiscountWebHook(value=0.0, dueDateLimitDays=0, type=PERCENTAGE)",
                DiscountWebHookBuilder.builder().build().toString()
        );
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        DiscountWebHook discountWebHook = new DiscountWebHook(
                0.0,
                0,
                null
        );
        Assertions.assertEquals(
                "DiscountWebHook(value=0.0, dueDateLimitDays=0, type=null)",
                discountWebHook.toString()
        );

    }

}