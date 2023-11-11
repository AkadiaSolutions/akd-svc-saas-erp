package br.akd.svc.akadia.proxy.asaas.webhooks.fiscal;

import br.akd.svc.akadia.modules.web.proxy.asaas.webhooks.fiscal.TaxesWebHook;
import br.akd.svc.akadia.proxy.asaas.webhooks.fiscal.mocks.TaxesWebHookBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Webhook: Taxes")
class TaxesWebHookTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "TaxesWebHook(retainIss=true, iss=1, cofins=1, csll=1, inss=1, ir=1.0, pis=1.0)",
                TaxesWebHookBuilder.builder().build().toString()
        );
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        TaxesWebHook taxesWebHook = new TaxesWebHook(
                true,
                1,
                1,
                1,
                1,
                1.0,
                1.0
        );
        Assertions.assertEquals(
                "TaxesWebHook(retainIss=true, iss=1, cofins=1, csll=1, inss=1, ir=1.0, pis=1.0)",
                taxesWebHook.toString()
        );

    }

}
