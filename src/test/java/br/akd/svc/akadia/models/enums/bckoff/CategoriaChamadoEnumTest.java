package br.akd.svc.akadia.models.enums.bckoff;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Enum: CategoriaChamado")
class CategoriaChamadoEnumTest {
    @Test
    @DisplayName("Deve testar atributos")
    void shouldTestGetters() {
        String atributosEmString =
                CategoriaChamadoEnum.DUVIDA_TECNICA.getCode() + " " +
                        CategoriaChamadoEnum.DUVIDA_TECNICA.getDesc();
        Assertions.assertEquals("0 Dúvida técnica", atributosEmString);
    }
}
