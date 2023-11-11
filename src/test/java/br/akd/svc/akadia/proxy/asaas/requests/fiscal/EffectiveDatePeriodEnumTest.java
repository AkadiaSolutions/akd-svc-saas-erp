package br.akd.svc.akadia.proxy.asaas.requests.fiscal;

import br.akd.svc.akadia.modules.web.proxy.asaas.requests.fiscal.EffectiveDatePeriodEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Enum: EffectiveDatePeriodEnum")
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
