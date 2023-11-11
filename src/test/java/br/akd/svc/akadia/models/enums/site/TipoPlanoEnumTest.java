package br.akd.svc.akadia.models.enums.site;

import br.akd.svc.akadia.modules.web.models.enums.TipoPlanoEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Enum: TipoPlano")
class TipoPlanoEnumTest {
    @Test
    @DisplayName("Deve testar atributos")
    void shouldTestGetters() {
        String atributosEmString =
                TipoPlanoEnum.BASIC.getCode() + " " +
                        TipoPlanoEnum.BASIC.getDesc() + " " +
                        TipoPlanoEnum.BASIC.getValor() + " " +
                        TipoPlanoEnum.BASIC.getQtdLimiteEmpresasCadastradas();
        Assertions.assertEquals("0 Basic 250.0 1", atributosEmString);
    }
}
