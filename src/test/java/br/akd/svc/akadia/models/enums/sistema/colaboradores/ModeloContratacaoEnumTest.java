package br.akd.svc.akadia.models.enums.sistema.colaboradores;

import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.enums.ModeloContratacaoEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
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
