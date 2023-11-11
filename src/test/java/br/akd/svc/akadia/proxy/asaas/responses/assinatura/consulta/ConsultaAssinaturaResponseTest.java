package br.akd.svc.akadia.proxy.asaas.responses.assinatura.consulta;

import br.akd.svc.akadia.modules.web.proxy.asaas.responses.assinatura.consulta.ConsultaAssinaturaResponse;
import br.akd.svc.akadia.proxy.asaas.responses.assinatura.consulta.mocks.ConsultaAssinaturaResponseBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Response: ConsultaAssinatura")
class ConsultaAssinaturaResponseTest {
    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals("ConsultaAssinaturaResponse(id=sub_i8U7a1UzweKt, dateCreated=2023-02-08, " +
                        "customer=cus_000005121369, paymentLink=null, billingType=PIX, value=650.0, " +
                        "nextDueDate=2023-02-08, cycle=MONTHLY, description=Assinatura de plano pro, endDate=null, " +
                        "maxPayments=null, status=ACTIVE, externalReference=null, split=[])",
                ConsultaAssinaturaResponseBuilder.builder().build().toString()
        );

    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        ConsultaAssinaturaResponse consultaAssinaturaResponse = new ConsultaAssinaturaResponse(
                "sub_i8U7a1UzweKt",
                LocalDate.of(2023, 2, 8).toString(),
                "cus_000005121369",
                null,
                "PIX",
                650.0,
                LocalDate.of(2023, 2, 8).toString(),
                "MONTHLY",
                "Assinatura de plano pro",
                null,
                null,
                "ACTIVE",
                null,
                new ArrayList<>()
        );

        Assertions.assertEquals("ConsultaAssinaturaResponse(id=sub_i8U7a1UzweKt, dateCreated=2023-02-08, " +
                        "customer=cus_000005121369, paymentLink=null, billingType=PIX, value=650.0, " +
                        "nextDueDate=2023-02-08, cycle=MONTHLY, description=Assinatura de plano pro, " +
                        "endDate=null, maxPayments=null, status=ACTIVE, externalReference=null, split=[])",
                consultaAssinaturaResponse.toString()
        );
    }
}
