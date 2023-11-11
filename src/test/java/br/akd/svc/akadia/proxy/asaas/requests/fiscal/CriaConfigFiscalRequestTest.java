package br.akd.svc.akadia.proxy.asaas.requests.fiscal;

import br.akd.svc.akadia.modules.web.proxy.asaas.requests.fiscal.CriaConfigFiscalRequest;
import br.akd.svc.akadia.modules.web.proxy.asaas.requests.fiscal.EffectiveDatePeriodEnum;
import br.akd.svc.akadia.proxy.asaas.requests.fiscal.mocks.CriaConfigFiscalRequestBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Request: CriaConfigFiscal")
class CriaConfigFiscalRequestTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "CriaConfigFiscalRequest(municipalServiceId=104, municipalServiceCode=1.05, " +
                        "municipalServiceName=Licenciamento ou cessão de direito de uso de programas de computação, " +
                        "inclusive distribuição., updatePayment=null, deductions=0, " +
                        "effectiveDatePeriod=ON_PAYMENT_CONFIRMATION, receivedOnly=true, daysBeforeDueDate=null, " +
                        "observations=Configuração fiscal realizada, taxes=null)",
                CriaConfigFiscalRequestBuilder.builder().build().toString()
        );
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        CriaConfigFiscalRequest criaConfigFiscalRequest = new CriaConfigFiscalRequest(
                "104",
                "1.05",
                "Licenciamento ou cessão de direito de uso de programas de computação, inclusive distribuição.",
                null,
                0,
                EffectiveDatePeriodEnum.ON_PAYMENT_CONFIRMATION,
                true,
                null,
                "Configuração fiscal realizada",
                null
        );
        Assertions.assertEquals(
                "CriaConfigFiscalRequest(municipalServiceId=104, municipalServiceCode=1.05, " +
                        "municipalServiceName=Licenciamento ou cessão de direito de uso de programas de computação, " +
                        "inclusive distribuição., updatePayment=null, deductions=0, " +
                        "effectiveDatePeriod=ON_PAYMENT_CONFIRMATION, receivedOnly=true, daysBeforeDueDate=null, " +
                        "observations=Configuração fiscal realizada, taxes=null)",
                criaConfigFiscalRequest.toString()
        );

    }

}
