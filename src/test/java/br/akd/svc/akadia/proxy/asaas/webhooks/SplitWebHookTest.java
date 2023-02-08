package br.akd.svc.akadia.proxy.asaas.webhooks;

import br.akd.svc.akadia.proxy.asaas.webhooks.mocks.SplitWebHookBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Webhook: Split")
class SplitWebHookTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "SplitWebHook(walletId=null, fixedValue=0.0, status=null, refusalReason=null)",
                SplitWebHookBuilder.builder().build().toString()
        );
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        SplitWebHook splitWebHook = new SplitWebHook(
                null,
                0.0,
                null,
                null
        );
        Assertions.assertEquals(
                "SplitWebHook(walletId=null, fixedValue=0.0, status=null, refusalReason=null)",
                splitWebHook.toString()
        );

    }

}
