package br.akd.svc.akadia.proxy.asaas.responses.assinatura.atualiza;

import br.akd.svc.akadia.modules.web.proxy.asaas.responses.assinatura.atualiza.AtualizaAssinaturaSplitResponse;
import br.akd.svc.akadia.proxy.asaas.responses.assinatura.atualiza.mocks.AtualizaAssinaturaSplitResponseBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Response: AtualizaAssinaturaSplit")
class AtualizaAssinaturaSplitResponseTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "AtualizaAssinaturaSplitResponse(walletId=null, fixedValue=0.0, percentualValue=0.0, " +
                        "status=null, refusalReason=null)",
                AtualizaAssinaturaSplitResponseBuilder.builder().build().toString()
        );

    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        AtualizaAssinaturaSplitResponse atualizaAssinaturaSplitResponse = new AtualizaAssinaturaSplitResponse(
                null,
                0.0,
                0.0,
                null,
                null
        );
        Assertions.assertEquals(
                "AtualizaAssinaturaSplitResponse(walletId=null, fixedValue=0.0, percentualValue=0.0, " +
                        "status=null, refusalReason=null)",
                atualizaAssinaturaSplitResponse.toString()
        );

    }

}
