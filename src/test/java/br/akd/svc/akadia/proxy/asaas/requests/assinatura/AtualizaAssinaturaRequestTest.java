package br.akd.svc.akadia.proxy.asaas.requests.assinatura;

import br.akd.svc.akadia.modules.web.proxy.asaas.requests.assinatura.AtualizaAssinaturaRequest;
import br.akd.svc.akadia.proxy.asaas.requests.assinatura.mocks.AtualizaAssinaturaRequestBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Request: AtualizaAssinatura")
class AtualizaAssinaturaRequestTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "AtualizaAssinaturaRequest(billingType=BOLETO, value=400.0, nextDueDate=2023-02-08, " +
                        "discount=null, interest=null, fine=null, cycle=MONTHLY, description=Assinatura de plano " +
                        "standart, updatePendingPayments=true, externalReference=null)",
                AtualizaAssinaturaRequestBuilder.builder().build().toString()
        );

    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        AtualizaAssinaturaRequest atualizaAssinaturaRequest = new AtualizaAssinaturaRequest(
                "BOLETO",
                400.0,
                LocalDate.of(2023, 2, 8).toString(),
                null,
                null,
                null,
                "MONTHLY",
                "Assinatura plano standart",
                "true",
                null
        );
        Assertions.assertEquals(
                "AtualizaAssinaturaRequest(billingType=BOLETO, value=400.0, nextDueDate=2023-02-08, " +
                        "discount=null, interest=null, fine=null, cycle=MONTHLY, description=Assinatura plano standart, " +
                        "updatePendingPayments=true, externalReference=null)",
                atualizaAssinaturaRequest.toString()
        );

    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        AtualizaAssinaturaRequest atualizaAssinaturaRequest = AtualizaAssinaturaRequest.builder()
                .billingType("BOLETO")
                .value(400.0)
                .nextDueDate(LocalDate.of(2023, 2, 8).toString())
                .discount(null)
                .interest(null)
                .fine(null)
                .cycle("MONTHLY")
                .description("Assinatura plano standart")
                .updatePendingPayments("true")
                .externalReference(null)
                .build();
        Assertions.assertEquals(
                "AtualizaAssinaturaRequest(billingType=BOLETO, value=400.0, nextDueDate=2023-02-08, " +
                        "discount=null, interest=null, fine=null, cycle=MONTHLY, description=Assinatura plano standart, " +
                        "updatePendingPayments=true, externalReference=null)",
                atualizaAssinaturaRequest.toString()
        );
    }

}
