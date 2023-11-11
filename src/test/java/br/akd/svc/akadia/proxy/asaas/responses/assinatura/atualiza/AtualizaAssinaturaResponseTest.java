package br.akd.svc.akadia.proxy.asaas.responses.assinatura.atualiza;

import br.akd.svc.akadia.modules.web.proxy.asaas.responses.assinatura.atualiza.AtualizaAssinaturaResponse;
import br.akd.svc.akadia.proxy.asaas.responses.assinatura.atualiza.mocks.AtualizaAssinaturaResponseBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Response: AtualizaAssinatura")
class AtualizaAssinaturaResponseTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "AtualizaAssinaturaResponse(id=sub_i8U7a1UzweKt, dateCreated=2023-02-08, " +
                        "customer=cus_000005121369, paymentLink=null, billingType=PIX, value=650.0, " +
                        "nextDueDate=2023-02-08, discount=null, interest=null, fine=null, cycle=MONTHLY, " +
                        "description=Assinatura de plano pro, endDate=null, maxPayments=null, status=ACTIVE, " +
                        "externalReference=null, split=null)",
                AtualizaAssinaturaResponseBuilder.builder().build().toString()
        );

    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        AtualizaAssinaturaResponse atualizaAssinaturaResponse = new AtualizaAssinaturaResponse(
                "sub_i8U7a1UzweKt",
                LocalDate.of(2023, 2, 8).toString(),
                "cus_000005121369",
                null,
                "PIX",
                650.0,
                LocalDate.of(2023, 2, 8).toString(),
                null,
                null,
                null,
                "MONTHLY",
                "Assinatura de plano pro",
                null,
                null,
                "ACTIVE",
                null,
                null
        );
        Assertions.assertEquals(
                "AtualizaAssinaturaResponse(id=sub_i8U7a1UzweKt, dateCreated=2023-02-08, " +
                        "customer=cus_000005121369, paymentLink=null, billingType=PIX, value=650.0, " +
                        "nextDueDate=2023-02-08, discount=null, interest=null, fine=null, cycle=MONTHLY, " +
                        "description=Assinatura de plano pro, endDate=null, maxPayments=null, status=ACTIVE, " +
                        "externalReference=null, split=null)",
                atualizaAssinaturaResponse.toString()
        );

    }

}