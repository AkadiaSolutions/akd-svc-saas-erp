package br.akd.svc.akadia.models.enums.global;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Enum: Estado")
class EstadoEnumTest {
    @Test
    @DisplayName("Deve testar atributos")
    void shouldTestGetters() {
        String atributosEmString =
                EstadoEnum.AC.getCode() + " " +
                        EstadoEnum.AC.getDesc() + " " +
                        EstadoEnum.AC.getPrefix();
        Assertions.assertEquals("1 Acre AC", atributosEmString);
    }
}
