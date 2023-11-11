package br.akd.svc.akadia.proxy.asaas.webhooks.cobranca.enums;

import br.akd.svc.akadia.modules.web.proxy.asaas.webhooks.cobranca.enums.EventoCobrancaEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
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
