package br.akd.svc.akadia.proxy.asaas.responses.assinatura.atualiza;

import br.akd.svc.akadia.modules.web.proxy.asaas.responses.assinatura.atualiza.AtualizaAssinaturaInterestResponse;
import br.akd.svc.akadia.proxy.asaas.responses.assinatura.atualiza.mocks.AtualizaAssinaturaInterestResponseBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Response: AtualizaAssinaturaInterest")
class AtualizaAssinaturaInterestResponseTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "AtualizaAssinaturaInterestResponse(interest=0.0)",
                AtualizaAssinaturaInterestResponseBuilder.builder().build().toString()
        );

    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        AtualizaAssinaturaInterestResponse atualizaAssinaturaFineResponse = new AtualizaAssinaturaInterestResponse(
                0.0
        );
        Assertions.assertEquals(
                "AtualizaAssinaturaInterestResponse(interest=0.0)",
                atualizaAssinaturaFineResponse.toString()
        );

    }

}
