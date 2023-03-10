package br.akd.svc.akadia.models.enums.site.fiscal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Enum: RegimeTributario")
class RegimeTributarioEnumTest {
    @Test
    @DisplayName("Deve testar atributos")
    void shouldTestGetters() {
        String atributosEmString =
                RegimeTributarioEnum.SIMPLES_NACIONAL.getCode() + " " +
                        RegimeTributarioEnum.SIMPLES_NACIONAL.getDesc();
        Assertions.assertEquals("0 Simples nacional", atributosEmString);
    }
}
