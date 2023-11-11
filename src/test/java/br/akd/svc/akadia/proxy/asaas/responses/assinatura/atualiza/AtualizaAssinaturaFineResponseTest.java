package br.akd.svc.akadia.proxy.asaas.responses.assinatura.atualiza;

import br.akd.svc.akadia.modules.web.proxy.asaas.responses.assinatura.atualiza.AtualizaAssinaturaFineResponse;
import br.akd.svc.akadia.proxy.asaas.responses.assinatura.atualiza.mocks.AtualizaAssinaturaFineResponseBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Response: AtualizaAssinaturaFineResponse")
class AtualizaAssinaturaFineResponseTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "AtualizaAssinaturaFineResponse(value=0.0)",
                AtualizaAssinaturaFineResponseBuilder.builder().build().toString()
        );

    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        AtualizaAssinaturaFineResponse fineResponse = new AtualizaAssinaturaFineResponse(
                0.0
        );
        Assertions.assertEquals(
                "AtualizaAssinaturaFineResponse(value=0.0)",
                fineResponse.toString()
        );

    }

}
