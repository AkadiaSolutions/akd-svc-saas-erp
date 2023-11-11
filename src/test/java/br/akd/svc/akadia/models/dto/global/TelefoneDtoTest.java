package br.akd.svc.akadia.models.dto.global;

import br.akd.svc.akadia.modules.global.enums.TipoTelefoneEnum;
import br.akd.svc.akadia.modules.global.dto.TelefoneDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("DTO: Telefone")
class TelefoneDtoTest {
    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {

        TelefoneDto telefoneDto =
                new TelefoneDto(1L, "11", "979815415", TipoTelefoneEnum.MOVEL_WHATSAPP);

        Assertions.assertEquals(
                "TelefoneDto(id=1, prefixo=11, numero=979815415, tipoTelefone=MOVEL_WHATSAPP)",
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
                .tipoTelefone(TipoTelefoneEnum.MOVEL_WHATSAPP)
                .build();
        Assertions.assertEquals(
                "TelefoneDto(id=1, prefixo=11, numero=979815415, tipoTelefone=MOVEL_WHATSAPP)",
                telefoneDto.toString()
        );
    }
}
