package br.akd.svc.akadia.proxy.asaas.webhooks.cobranca;

import br.akd.svc.akadia.modules.web.proxy.asaas.webhooks.cobranca.CreditCardWebHook;
import br.akd.svc.akadia.proxy.asaas.webhooks.cobranca.mocks.CreditCardWebHookBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Webhook: CreditCard")
class CreditCardWebHookTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "CreditCardWebHook(creditCardNumber=5162306219378829, creditCardBrand=VISA, " +
                        "creditCardToken=1b9ad2a7-76ee-4a88-b6c2-9a0d308bac5e)",
                CreditCardWebHookBuilder.builder().build().toString()
        );
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        CreditCardWebHook creditCardWebHook = new CreditCardWebHook(
                "5162306219378829",
                "VISA",
                "18e6c8de-4a66-4d33-8966-d3fed6641cc3"
        );
        Assertions.assertEquals(
                "CreditCardWebHook(creditCardNumber=5162306219378829, creditCardBrand=VISA, " +
                        "creditCardToken=18e6c8de-4a66-4d33-8966-d3fed6641cc3)",
                creditCardWebHook.toString()
        );

    }

}
