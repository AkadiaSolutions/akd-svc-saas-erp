package br.akd.svc.akadia.proxy.asaas.webhooks;

import br.akd.svc.akadia.proxy.asaas.webhooks.mocks.ChargeBackWebHookBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Webhook: ChargeBack")
class ChargeBackWebHookTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "AtualizacaoCobrancaWebHook(event=PAYMENT_CONFIRMED, payment=null)",
                ChargeBackWebHookBuilder.builder().build().toString()
        );
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        ChargeBackWebHook chargeBackWebHook = new ChargeBackWebHook(
                null,
                null
        );
        Assertions.assertEquals(
                "ChargeBackWebHook(status=null, reason=null)",
                chargeBackWebHook.toString()
        );

    }

}
