package br.akd.svc.akadia.proxy.asaas.responses.assinatura.consulta;

import br.akd.svc.akadia.modules.web.proxy.asaas.responses.assinatura.consulta.ConsultaSplitResponse;
import br.akd.svc.akadia.proxy.asaas.responses.assinatura.consulta.mocks.ConsultaSplitResponseBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Response: ConsultaSplit")
class ConsultaSplitResponseTest {
    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals("ConsultaSplitResponse(walletId=null, fixedValue=0.0, percentualValue=0, " +
                        "status=null, refusalReason=null)",
                ConsultaSplitResponseBuilder.builder().build().toString()
        );

    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        ConsultaSplitResponse consultaSplitResponse = new ConsultaSplitResponse(
                null,
                0.0,
                0,
                null,
                null
        );

        Assertions.assertEquals("ConsultaSplitResponse(walletId=null, fixedValue=0.0, percentualValue=0, " +
                        "status=null, refusalReason=null)",
                consultaSplitResponse.toString()
        );
    }

}
