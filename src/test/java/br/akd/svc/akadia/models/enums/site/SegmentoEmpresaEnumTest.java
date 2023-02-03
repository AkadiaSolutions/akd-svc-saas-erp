package br.akd.svc.akadia.models.enums.site;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
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
