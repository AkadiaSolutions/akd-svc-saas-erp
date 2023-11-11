package br.akd.svc.akadia.proxy.asaas.responses.assinatura;

import br.akd.svc.akadia.modules.web.proxy.asaas.responses.assinatura.AssinaturaResponse;
import br.akd.svc.akadia.proxy.asaas.responses.assinatura.mocks.AssinaturaResponseBuilder;
import br.akd.svc.akadia.proxy.asaas.responses.mocks.CreditCardResponseBuilder;
import br.akd.svc.akadia.proxy.asaas.responses.assinatura.mocks.FineResponseBuilder;
import br.akd.svc.akadia.proxy.asaas.responses.assinatura.mocks.InterestResponseBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Response: Assinatura")
class AssinaturaResponseTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "AssinaturaResponse(object=subscription, id=sub_C2rGa18j8cT4, dateCreated=2023-02-06, customer=cus_000005118516, paymentLink=null, value=600.0, nextDueDate=2023-02-13, cycle=MONTHLY, description=Assinatura de plano PRO, billingType=CREDIT_CARD, deleted=false, status=ACTIVE, externalReference=null, sendPaymentByPostalService=null, creditCard=null, fine=FineResponse(value=0.0, type=FIXED), interest=InterestResponse(value=0.0, type=PERCENTAGE), split=null)",
                AssinaturaResponseBuilder.builder().build().toString()
        );
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        AssinaturaResponse assinaturaResponse = new AssinaturaResponse(
                "subscription",
                "sub_C2rGa18j8cT4",
                LocalDate.of(2023, 2, 6).toString(),
                "cus_000005118516",
                null,
                600.0,
                LocalDate.of(2023, 2, 6).plusDays(7).toString(),
                "MONTHLY",
                "Assinatura de plano PRO",
                "CREDIT_CARD",
                false,
                "ACTIVE",
                null,
                true,
                CreditCardResponseBuilder.builder().build(),
                FineResponseBuilder.builder().build(),
                InterestResponseBuilder.builder().build(),
                null
        );
        Assertions.assertEquals(
                "AssinaturaResponse(object=subscription, id=sub_C2rGa18j8cT4, dateCreated=2023-02-06, customer=cus_000005118516, paymentLink=null, value=600.0, nextDueDate=2023-02-13, cycle=MONTHLY, description=Assinatura de plano PRO, billingType=CREDIT_CARD, deleted=false, status=ACTIVE, externalReference=null, sendPaymentByPostalService=true, creditCard=CreditCardResponse(creditCardNumber=5162306219378829, creditCardBrand=MASTERCARD, creditCardToken=c127baad-5943-45dd-a85e-8bbe3fb5c01a), fine=FineResponse(value=0.0, type=FIXED), interest=InterestResponse(value=0.0, type=PERCENTAGE), split=null)",
                assinaturaResponse.toString()
        );

    }
}
