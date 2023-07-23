package br.akd.svc.akadia.models.enums.bckoff;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Enum: OrigemLead")
class OrigemLeadEnumTest {
    @Test
    @DisplayName("Deve testar atributos")
    void shouldTestGetters() {
        String atributosEmString =
                OrigemLeadEnum.PRE_CADASTRO.getCode() + " " +
                        OrigemLeadEnum.PRE_CADASTRO.getDesc();
        Assertions.assertEquals("0 Pré-cadastro", atributosEmString);
    }
}
