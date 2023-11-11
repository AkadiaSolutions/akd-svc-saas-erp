package br.akd.svc.akadia.proxy.asaas.webhooks.cobranca;

import br.akd.svc.akadia.modules.web.proxy.asaas.webhooks.cobranca.AtualizacaoCobrancaWebHook;
import br.akd.svc.akadia.modules.web.proxy.asaas.webhooks.cobranca.enums.EventoCobrancaEnum;
import br.akd.svc.akadia.proxy.asaas.webhooks.cobranca.mocks.AtualizacaoCobrancaWebHookBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
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
                EventoCobrancaEnum.PAYMENT_CONFIRMED,
                null
        );
        Assertions.assertEquals(
                "AtualizacaoCobrancaWebHook(event=PAYMENT_CONFIRMED, payment=null)",
                atualizacaoCobrancaWebHook.toString()
        );

    }

}
