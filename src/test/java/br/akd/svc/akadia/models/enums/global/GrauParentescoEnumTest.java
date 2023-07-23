package br.akd.svc.akadia.models.enums.global;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Enum: GrauParentesco")
class GrauParentescoEnumTest {
    @Test
    @DisplayName("Deve testar atributos")
    void shouldTestGetters() {
        String atributosEmString =
                GrauParentescoEnum.PAI.getCode() + " " +
                        GrauParentescoEnum.PAI.getDesc();
        Assertions.assertEquals("0 Pai", atributosEmString);
    }
}
