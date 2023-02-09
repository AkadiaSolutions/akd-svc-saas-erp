package br.akd.svc.akadia.proxy.asaas.webhooks;

import br.akd.svc.akadia.proxy.asaas.webhooks.enums.EventEnum;
import br.akd.svc.akadia.proxy.asaas.webhooks.mocks.AtualizacaoCobrancaWebHookBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Webhook: AtualizacaoCobranca")
class AtualizacaoCobrancaWebHookTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "AtualizacaoCobrancaWebHook(event=null, payment=null)",
                AtualizacaoCobrancaWebHookBuilder.builder().build().toString()
        );
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        AtualizacaoCobrancaWebHook atualizacaoCobrancaWebHook = new AtualizacaoCobrancaWebHook(
                EventEnum.PAYMENT_CONFIRMED,
                null
        );
        Assertions.assertEquals(
                "AtualizacaoCobrancaWebHook(event=PAYMENT_CONFIRMED, payment=null)",
                atualizacaoCobrancaWebHook.toString()
        );

    }

}
