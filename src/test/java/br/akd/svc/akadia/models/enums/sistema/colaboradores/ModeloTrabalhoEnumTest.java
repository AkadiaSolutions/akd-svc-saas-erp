package br.akd.svc.akadia.models.enums.sistema.colaboradores;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Enum: ModeloTrabalhoEnum")
class ModeloTrabalhoEnumTest {
    @Test
    @DisplayName("Deve testar atributos")
    void shouldTestGetters() {
        String atributosEmString =
                ModeloTrabalhoEnum.PRESENCIAL.getCode() + " " +
                        ModeloTrabalhoEnum.PRESENCIAL.getDesc();
        Assertions.assertEquals("0 Presencial", atributosEmString);
    }
}
