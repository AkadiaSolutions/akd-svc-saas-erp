package br.akd.svc.akadia.models.enums.bckoff;

import br.akd.svc.akadia.modules.backoffice.models.enums.StatusChamadoEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Enum: StatusChamado")
class StatusChamadoEnumTest {
    @Test
    @DisplayName("Deve testar atributos")
    void shouldTestGetters() {
        String atributosEmString =
                StatusChamadoEnum.EM_ABERTO.getCode() + " " +
                        StatusChamadoEnum.EM_ABERTO.getDesc();
        Assertions.assertEquals("0 Em aberto", atributosEmString);
    }
}
