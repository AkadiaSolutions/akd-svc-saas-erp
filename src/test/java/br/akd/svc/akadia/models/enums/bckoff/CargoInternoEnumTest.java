package br.akd.svc.akadia.models.enums.bckoff;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Enum: CargoInterno")
class CargoInternoEnumTest {
    @Test
    @DisplayName("Deve testar atributos")
    void shouldTestGetters() {
        String atributosEmString =
                CargoInternoEnum.SUPORTE.getCode() + " " +
                        CargoInternoEnum.SUPORTE.getDesc() + " " +
                        CargoInternoEnum.SUPORTE.getPrivilegio();
        Assertions.assertEquals("0 Suporte 1", atributosEmString);
    }
}
