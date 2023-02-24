package br.akd.svc.akadia.proxy.asaas.webhooks.cobranca.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
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
