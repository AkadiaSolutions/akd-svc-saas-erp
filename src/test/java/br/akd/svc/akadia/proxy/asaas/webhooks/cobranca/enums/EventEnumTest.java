package br.akd.svc.akadia.proxy.asaas.webhooks.cobranca.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Enum: Event")
class EventEnumTest {
    @Test
    @DisplayName("Deve testar atributos")
    void shouldTestGetters() {
        String atributosEmString =
                EventoCobrancaEnum.PAYMENT_CREATED.getDesc();
        Assertions.assertEquals("Geração de nova cobrança", atributosEmString);
    }
}
