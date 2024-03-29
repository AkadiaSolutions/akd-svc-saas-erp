package br.akd.svc.akadia.models.enums.site;

import br.akd.svc.akadia.modules.web.models.enums.StatusPagamentoSistemaEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Enum: StatusPagamentoSistema")
class StatusPagamentoSistemaEnumTest {
    @Test
    @DisplayName("Deve testar atributos")
    void shouldTestGetters() {
        String atributosEmString =
                StatusPagamentoSistemaEnum.APROVADO.getCode() + " " +
                        StatusPagamentoSistemaEnum.APROVADO.getDesc();
        Assertions.assertEquals("0 Aprovado", atributosEmString);
    }
}
