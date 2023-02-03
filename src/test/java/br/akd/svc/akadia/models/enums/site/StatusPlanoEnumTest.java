package br.akd.svc.akadia.models.enums.site;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Enum: StatusPlano")
class StatusPlanoEnumTest {
    @Test
    @DisplayName("Deve testar atributos")
    void shouldTestGetters() {
        String atributosEmString =
                StatusPlanoEnum.ATIVO.getCode() + " " +
                        StatusPlanoEnum.ATIVO.getDesc();
        Assertions.assertEquals("0 Ativo", atributosEmString);
    }
}
