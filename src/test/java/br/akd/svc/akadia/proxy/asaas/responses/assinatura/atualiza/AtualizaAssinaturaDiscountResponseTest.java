package br.akd.svc.akadia.proxy.asaas.responses.assinatura.atualiza;

import br.akd.svc.akadia.modules.web.proxy.asaas.responses.assinatura.atualiza.AtualizaAssinaturaDiscountResponse;
import br.akd.svc.akadia.proxy.asaas.responses.assinatura.atualiza.mocks.AtualizaAssinaturaDiscountResponseBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Response: AtualizaAssinaturaDiscount")
class AtualizaAssinaturaDiscountResponseTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "AtualizaAssinaturaDiscountResponse(value=0.0, dueDateLimitDays=0, type=PERCENTAGE)",
                AtualizaAssinaturaDiscountResponseBuilder.builder().build().toString()
        );

    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        AtualizaAssinaturaDiscountResponse atualizaAssinaturaDiscountResponse = new AtualizaAssinaturaDiscountResponse(
                0.0,
                0,
                "PERCENTAGE"

        );
        Assertions.assertEquals(
                "AtualizaAssinaturaDiscountResponse(value=0.0, dueDateLimitDays=0, type=PERCENTAGE)",
                atualizaAssinaturaDiscountResponse.toString()
        );

    }

}
