package br.akd.svc.akadia.proxy.asaas.webhooks.cobranca.enums;

import br.akd.svc.akadia.modules.web.proxy.asaas.webhooks.cobranca.enums.BillingTypeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Enum: BillingType")
class BillingTypeEnumTest {
    @Test
    @DisplayName("Deve testar atributos")
    void shouldTestGetters() {
        String atributosEmString =
                BillingTypeEnum.PIX.getDesc() + " " +
                        BillingTypeEnum.PIX.getFormaPagamentoResumida();
        Assertions.assertEquals("Recebimento via Pix PIX", atributosEmString);
    }
}
