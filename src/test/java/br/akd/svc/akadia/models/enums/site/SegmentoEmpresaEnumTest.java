package br.akd.svc.akadia.models.enums.site;

import br.akd.svc.akadia.modules.web.models.enums.SegmentoEmpresaEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Enum: SegmentoEmpresa")
class SegmentoEmpresaEnumTest {
    @Test
    @DisplayName("Deve testar atributos")
    void shouldTestGetters() {
        String atributosEmString =
                SegmentoEmpresaEnum.BATERIA_AUTOMOTIVA.getCode() + " " +
                        SegmentoEmpresaEnum.BATERIA_AUTOMOTIVA.getDesc();
        Assertions.assertEquals("0 Baterias automotivas", atributosEmString);
    }
}
