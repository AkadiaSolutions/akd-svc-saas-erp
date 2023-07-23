package br.akd.svc.akadia.models.enums.site.fiscal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Enum: OrientacaoDanfe")
class OrientacaoDanfeEnumTest {
    @Test
    @DisplayName("Deve testar atributos")
    void shouldTestGetters() {
        String atributosEmString =
                OrientacaoDanfeEnum.PORTRAIT.getCode() + " " +
                        OrientacaoDanfeEnum.PORTRAIT.getDesc();
        Assertions.assertEquals("0 Retrato", atributosEmString);
    }
}
