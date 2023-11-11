package br.akd.svc.akadia.proxy.asaas.responses.fiscal;

import br.akd.svc.akadia.modules.web.proxy.asaas.responses.fiscal.CriaConfigFiscalResponse;
import br.akd.svc.akadia.proxy.asaas.responses.fiscal.mocks.CriaConfigFiscalResponseBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Response: CriaConfigFiscal")
class CriaConfigFiscalResponseTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "CriaConfigFiscalResponse(municipalServiceId=104, municipalServiceCode=1.05, " +
                        "municipalServiceName=Licenciamento ou cessão de direito de uso de programas de computação, " +
                        "inclusive distribuição., deductions=0, invoiceCreationPeriod=null, daysBeforeDueDate=null, " +
                        "receivedOnly=true, observations=Configuração fiscal realizada, taxes=null)",
                CriaConfigFiscalResponseBuilder.builder().build().toString()
        );
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        CriaConfigFiscalResponse criaConfigFiscalResponse = new CriaConfigFiscalResponse(
                "104",
                "1.05",
                "Licenciamento ou cessão de direito de uso de programas de computação, inclusive distribuição.",
                0,
                null,
                null,
                true,
                "Configuração fiscal realizada",
                null
        );
        Assertions.assertEquals(
                "CriaConfigFiscalResponse(municipalServiceId=104, municipalServiceCode=1.05, " +
                        "municipalServiceName=Licenciamento ou cessão de direito de uso de programas de computação, " +
                        "inclusive distribuição., deductions=0, invoiceCreationPeriod=null, daysBeforeDueDate=null, " +
                        "receivedOnly=true, observations=Configuração fiscal realizada, taxes=null)",
                criaConfigFiscalResponse.toString()
        );

    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        CriaConfigFiscalResponse criaConfigFiscalResponse = CriaConfigFiscalResponse.builder()
                .municipalServiceId("104")
                .municipalServiceCode("1.05")
                .municipalServiceName("Licenciamento ou cessão de direito de uso de programas de computação, inclusive distribuição.")
                .deductions(0)
                .invoiceCreationPeriod(null)
                .daysBeforeDueDate(null)
                .receivedOnly(true)
                .observations("Configuração fiscal realizada")
                .taxes(null)
                .build();

        Assertions.assertEquals(
                "CriaConfigFiscalResponse(municipalServiceId=104, municipalServiceCode=1.05, " +
                        "municipalServiceName=Licenciamento ou cessão de direito de uso de programas de computação, " +
                        "inclusive distribuição., deductions=0, invoiceCreationPeriod=null, daysBeforeDueDate=null, " +
                        "receivedOnly=true, observations=Configuração fiscal realizada, taxes=null)",
                criaConfigFiscalResponse.toString()
        );

    }

}
