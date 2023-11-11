package br.akd.svc.akadia.proxy.asaas.webhooks.cobranca;

import br.akd.svc.akadia.modules.web.proxy.asaas.webhooks.cobranca.PagamentoWebHook;
import br.akd.svc.akadia.modules.web.proxy.asaas.webhooks.cobranca.enums.BillingTypeEnum;
import br.akd.svc.akadia.proxy.asaas.webhooks.cobranca.mocks.PagamentoWebHookBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Webhook: Pagamento")
class PagamentoWebHookTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "PagamentoWebHook(object=payment, id=pay_5874803025986658, dateCreated=2023-02-08, " +
                        "customer=cus_000005121360, subscription=sub_AnftMQ3KknF8, installment=null, " +
                        "paymentLink=null, dueDate=2023-02-08, originalDueDate=null, value=650.0, netValue=640.0, " +
                        "originalValue=650.0, interestValue=0.0, description=Assinatura de plano pro, " +
                        "externalReference=null, billingType=BOLETO, status=ACTIVE, pixTransaction=null, " +
                        "confirmedDate=null, paymentDate=null, clientPaymentDate=null, installmentNumber=null, " +
                        "creditDate=null, estimatedCreditDate=null, invoiceUrl=null, bankSlipUrl=null, " +
                        "transactionReceiptUrl=null, invoiceNumber=null, deleted=false, antecipated=false, " +
                        "antecipable=false, lastInvoiceViewedDate=null, lastBankSlipViewedDate=null, " +
                        "postalService=false, creditCard=null, discount=null, fine=null, interest=null, split=null, " +
                        "chargeBack=null, refunds=null)",
                PagamentoWebHookBuilder.builder().build().toString()
        );
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        PagamentoWebHook pagamentoWebHook = new PagamentoWebHook(
                "payment",
                "pay_5874803025986658",
                LocalDate.of(2023, 2, 8).toString(),
                "cus_000005121360",
                "sub_AnftMQ3KknF8",
                null,
                null,
                LocalDate.of(2023, 2, 8).toString(),
                LocalDate.of(2023, 2, 8).toString(),
                650.0,
                640.0,
                650.0,
                0.0,
                null,
                null,
                BillingTypeEnum.BOLETO,
                "ACTIVE",
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
                "false",
                "false",
                "false",
                null,
                null,
                "false",
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
                "PagamentoWebHook(object=payment, id=pay_5874803025986658, dateCreated=2023-02-08, " +
                        "customer=cus_000005121360, subscription=sub_AnftMQ3KknF8, installment=null, " +
                        "paymentLink=null, dueDate=2023-02-08, originalDueDate=2023-02-08, value=650.0, " +
                        "netValue=640.0, originalValue=650.0, interestValue=0.0, description=null, " +
                        "externalReference=null, billingType=BOLETO, status=ACTIVE, pixTransaction=null, " +
                        "confirmedDate=null, paymentDate=null, clientPaymentDate=null, installmentNumber=null, " +
                        "creditDate=null, estimatedCreditDate=null, invoiceUrl=null, bankSlipUrl=null, " +
                        "transactionReceiptUrl=null, invoiceNumber=false, deleted=false, antecipated=false, " +
                        "antecipable=null, lastInvoiceViewedDate=null, lastBankSlipViewedDate=false, " +
                        "postalService=null, creditCard=null, discount=null, fine=null, interest=null, split=null, " +
                        "chargeBack=null, refunds=null)",
                pagamentoWebHook.toString()
        );

    }

}
