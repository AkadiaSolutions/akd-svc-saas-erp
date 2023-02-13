package br.akd.svc.akadia.models.enums.sistema.colaboradores;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Enum: ModeloContratacao")
class ModeloContratacaoEnumTest {
    @Test
    @DisplayName("Deve testar atributos")
    void shouldTestGetters() {
        String atributosEmString =
                ModeloContratacaoEnum.CLT.getCode() + " " +
                        ModeloContratacaoEnum.CLT.getDesc();
        Assertions.assertEquals("0 CLT", atributosEmString);
    }
}
