package br.akd.svc.akadia.models.enums.site;

import br.akd.svc.akadia.modules.web.models.enums.FormaPagamentoSistemaEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Enum: FormaPagamento")
class FormaPagamentoSistemaEnumTest {
    @Test
    @DisplayName("Deve testar atributos")
    void shouldTestGetters() {
        String atributosEmString =
                FormaPagamentoSistemaEnum.BOLETO.getCode() + " " +
                        FormaPagamentoSistemaEnum.BOLETO.getDesc();
        Assertions.assertEquals("0 Boleto", atributosEmString);
    }
}
