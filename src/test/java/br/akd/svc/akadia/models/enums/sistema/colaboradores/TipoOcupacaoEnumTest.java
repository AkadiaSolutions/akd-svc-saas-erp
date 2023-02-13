package br.akd.svc.akadia.models.enums.sistema.colaboradores;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Enum: TipoOcupacao")
class TipoOcupacaoEnumTest {
    @Test
    @DisplayName("Deve testar atributos")
    void shouldTestGetters() {
        String atributosEmString =
                TipoOcupacaoEnum.TECNICO.getCode() + " " +
                        TipoOcupacaoEnum.TECNICO.getDesc();
        Assertions.assertEquals("5 Técnico", atributosEmString);
    }
}
