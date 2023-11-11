package br.akd.svc.akadia.proxy.asaas.webhooks.fiscal;

import br.akd.svc.akadia.modules.web.proxy.asaas.webhooks.fiscal.InvoiceWebHook;
import br.akd.svc.akadia.proxy.asaas.webhooks.fiscal.mocks.InvoiceWebHookBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Webhook: Invoice")
class InvoiceWebHookTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "InvoiceWebHook(invoice=null, id=null, status=null, customer=null, type=null, " +
                        "statusDescription=null, serviceDescription=null, pdfUrl=null, xmlUrl=null, rpsSerie=null, " +
                        "rpsNumber=null, number=null, validationCode=null, value=null, deductions=null, " +
                        "effectiveDate=null, observations=null, estimatedTaxesDescription=null, payment=null, " +
                        "installment=null, taxes=null, municipalServiceCode=null, municipalServiceName=null)",
                InvoiceWebHookBuilder.builder().build().toString()
        );
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        InvoiceWebHook invoiceWebHook = new InvoiceWebHook(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
        Assertions.assertEquals(
                "InvoiceWebHook(invoice=null, id=null, status=null, customer=null, type=null, " +
                        "statusDescription=null, serviceDescription=null, pdfUrl=null, xmlUrl=null, rpsSerie=null, " +
                        "rpsNumber=null, number=null, validationCode=null, value=null, deductions=null, " +
                        "effectiveDate=null, observations=null, estimatedTaxesDescription=null, payment=null, " +
                        "installment=null, taxes=null, municipalServiceCode=null, municipalServiceName=null)",
                invoiceWebHook.toString()
        );

    }

}
