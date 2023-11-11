package br.akd.svc.akadia.proxy.asaas.responses.assinatura.cancela;

import br.akd.svc.akadia.modules.web.proxy.asaas.responses.assinatura.cancela.CancelamentoAssinaturaResponse;
import br.akd.svc.akadia.proxy.asaas.responses.assinatura.cancela.mocks.CancelamentoAssinaturaResponseBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Response: CancelamentoAssinatura")
class CancelamentoAssinaturaResponseTest {
    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals("CancelamentoAssinaturaResponse(deleted=true, id=sub_i8U7a1UzweKt)",
                CancelamentoAssinaturaResponseBuilder.builder().build().toString()
        );

    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        CancelamentoAssinaturaResponse cancelamentoAssinaturaResponse = new CancelamentoAssinaturaResponse(
                true,
                "sub_i8U7a1UzweKt"
        );

        Assertions.assertEquals("CancelamentoAssinaturaResponse(deleted=true, id=sub_i8U7a1UzweKt)",
                cancelamentoAssinaturaResponse.toString()
        );
    }
}
