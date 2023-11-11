package br.akd.svc.akadia.proxy.asaas.global;

import br.akd.svc.akadia.modules.web.proxy.asaas.global.Taxes;
import br.akd.svc.akadia.proxy.asaas.global.mocks.TaxesBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("ASAAS: Taxes")
class TaxesTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "Taxes(retainIss=true, iss=1, cofins=1, csll=1, inss=1, ir=1.0, pis=1.0)",
                TaxesBuilder.builder().build().toString()
        );
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        Taxes taxes = new Taxes(
                true,
                1,
                1,
                1,
                1,
                1.0,
                1.0
        );
        Assertions.assertEquals(
                "Taxes(retainIss=true, iss=1, cofins=1, csll=1, inss=1, ir=1.0, pis=1.0)",
                taxes.toString()
        );

    }

}
