package br.akd.svc.akadia.models.dto.global;

import br.akd.svc.akadia.models.enums.global.TipoTelefoneEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("DTO: Telefone")
class TelefoneDtoTest {
    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {

        TelefoneDto telefoneDto =
                new TelefoneDto(1L, "11", "979815415", TipoTelefoneEnum.MOVEL_WHATSAPP);

        Assertions.assertEquals(
                "TelefoneDto(id=1, prefixo=11, numero=979815415, tipoTelefoneEnum=MOVEL_WHATSAPP)",
                telefoneDto.toString()
        );

    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        TelefoneDto telefoneDto = TelefoneDto.builder()
                .id(1L)
                .prefixo("11")
                .numero("979815415")
                .tipoTelefoneEnum(TipoTelefoneEnum.MOVEL_WHATSAPP)
                .build();
        Assertions.assertEquals(
                "TelefoneDto(id=1, prefixo=11, numero=979815415, tipoTelefoneEnum=MOVEL_WHATSAPP)",
                telefoneDto.toString()
        );
    }
}
