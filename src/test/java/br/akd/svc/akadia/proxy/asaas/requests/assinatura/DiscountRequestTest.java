package br.akd.svc.akadia.proxy.asaas.requests.assinatura;

import br.akd.svc.akadia.proxy.asaas.requests.assinatura.mocks.DiscountRequestBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Request: Discount")
class DiscountRequestTest {
    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "DiscountRequest(value=0.0, dueDateLimitDays=0)",
                DiscountRequestBuilder.builder().build().toString()
        );

    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        DiscountRequest discountRequest = new DiscountRequest(
                0.0,
                0
        );
        Assertions.assertEquals(
                "DiscountRequest(value=0.0, dueDateLimitDays=0)",
                discountRequest.toString()
        );

    }
}
