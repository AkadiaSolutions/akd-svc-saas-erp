package br.akd.svc.akadia.models.enums.site;

import br.akd.svc.akadia.modules.web.models.enums.BandeiraCartaoEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Enum: BandeiraCartao")
class BandeiraCartaoEnumTest {
    @Test
    @DisplayName("Deve testar atributos")
    void shouldTestGetters() {
        String atributosEmString =
                BandeiraCartaoEnum.MASTERCARD.getCode() + " " +
                        BandeiraCartaoEnum.MASTERCARD.getDesc();
        Assertions.assertEquals("0 Mastercard", atributosEmString);
    }
}
