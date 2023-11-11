package br.akd.svc.akadia.models.enums.sistema.colaboradores;

import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.enums.StatusColaboradorEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Enum: StatusColaborador")
class StatusColaboradorEnumTest {
    @Test
    @DisplayName("Deve testar atributos")
    void shouldTestGetters() {
        String atributosEmString =
                StatusColaboradorEnum.ATIVO.getCode() + " " +
                        StatusColaboradorEnum.ATIVO.getDesc();
        Assertions.assertEquals("0 Ativo", atributosEmString);
    }
}