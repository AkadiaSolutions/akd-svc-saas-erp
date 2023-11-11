package br.akd.svc.akadia.proxy.asaas.webhooks.fiscal.enums;

import br.akd.svc.akadia.modules.web.proxy.asaas.webhooks.fiscal.enums.EventoFiscalEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
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
