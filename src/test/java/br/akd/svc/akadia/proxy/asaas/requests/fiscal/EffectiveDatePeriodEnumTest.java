package br.akd.svc.akadia.proxy.asaas.requests.fiscal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("eNUM: EffectiveDatePeriodEnum")
class EffectiveDatePeriodEnumTest {
    @Test
    @DisplayName("Deve testar atributos")
    void shouldTestGetters() {
        String atributosEmString =
                EffectiveDatePeriodEnum.ON_PAYMENT_CONFIRMATION.getCode() + " " +
                        EffectiveDatePeriodEnum.ON_PAYMENT_CONFIRMATION.getDesc();
        Assertions.assertEquals("0 Quando a cobrança for paga", atributosEmString);
    }
}
