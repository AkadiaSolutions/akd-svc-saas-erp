package br.akd.svc.akadia.models.enums.bckoff;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Enum: StatusAtividade")
class StatusAtividadeEnumTest {
    @Test
    @DisplayName("Deve testar atributos")
    void shouldTestGetters() {
        String atributosEmString =
                StatusAtividadeEnum.ATIVO.getCode() + " " +
                        StatusAtividadeEnum.ATIVO.getDesc();
        Assertions.assertEquals("0 Ativo", atributosEmString);
    }
}
