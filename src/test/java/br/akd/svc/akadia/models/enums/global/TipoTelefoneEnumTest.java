package br.akd.svc.akadia.models.enums.global;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Enum: TipoTelefone")
class TipoTelefoneEnumTest {
    @Test
    @DisplayName("Deve testar atributos")
    void shouldTestGetters() {
        String atributosEmString =
                TipoTelefoneEnum.FIXO.getCode() + " " +
                        TipoTelefoneEnum.FIXO.getDesc();
        Assertions.assertEquals("0 Fixo", atributosEmString);
    }
}
