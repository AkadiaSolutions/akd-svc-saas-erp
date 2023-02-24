package br.akd.svc.akadia.proxy.asaas.webhooks.fiscal.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Enum: EventoFiscal")
class EventoFiscalEnumTest {
    @Test
    @DisplayName("Deve testar atributos")
    void shouldTestGetters() {
        String atributosEmString =
                EventoFiscalEnum.INVOICE_CREATED.getCode() + " " +
                        EventoFiscalEnum.INVOICE_CREATED.getDesc();
        Assertions.assertEquals("0 Geração de nova nota fiscal", atributosEmString);
    }
}
