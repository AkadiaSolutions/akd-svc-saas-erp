package br.akd.svc.akadia.proxy.asaas.webhooks.cobranca;

import br.akd.svc.akadia.modules.web.proxy.asaas.webhooks.cobranca.ChargeBackWebHook;
import br.akd.svc.akadia.proxy.asaas.webhooks.cobranca.mocks.ChargeBackWebHookBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Webhook: ChargeBack")
class ChargeBackWebHookTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "ChargeBackWebHook(status=null, reason=null)",
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
