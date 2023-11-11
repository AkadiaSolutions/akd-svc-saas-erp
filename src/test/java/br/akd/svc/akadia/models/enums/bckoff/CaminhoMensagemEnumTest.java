package br.akd.svc.akadia.models.enums.bckoff;

import br.akd.svc.akadia.modules.backoffice.models.enums.CaminhoMensagemEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Enum: CaminhoMensagem")
class CaminhoMensagemEnumTest {
    @Test
    @DisplayName("Deve testar atributos")
    void shouldTestGetters() {
        String atributosEmString =
                CaminhoMensagemEnum.SUPORTE_PARA_CLIENTE.getCode() + " " +
                        CaminhoMensagemEnum.SUPORTE_PARA_CLIENTE.getDesc();
        Assertions.assertEquals("0 Suporte para cliente", atributosEmString);
    }
}
