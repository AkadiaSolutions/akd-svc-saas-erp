package br.akd.svc.akadia.proxy.asaas.webhooks.fiscal;

import br.akd.svc.akadia.proxy.asaas.webhooks.fiscal.mocks.AtualizacaoFiscalWebHookBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Webhook: AtualizacaoFiscal")
class AtualizacaoFiscalWebHookTest {
    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "AtualizacaoFiscalWebHook(event=INVOICE_AUTHORIZED, invoice=null)",
                AtualizacaoFiscalWebHookBuilder.builder().build().toString()
        );
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        AtualizacaoFiscalWebHook atualizacaoFiscalWebHook = new AtualizacaoFiscalWebHook(
                null,
                null
        );
        Assertions.assertEquals(
                "AtualizacaoFiscalWebHook(event=null, invoice=null)",
                atualizacaoFiscalWebHook.toString()
        );

    }
}
