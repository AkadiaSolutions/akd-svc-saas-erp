package br.akd.svc.akadia.models.dto.bckoff;

import br.akd.svc.akadia.models.dto.bckoff.mocks.AnexoMensagemDtoBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("DTO: AnexoMensagem")
class AnexoMensagemDtoTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "AnexoMensagemDto(id=1, dados=[], nome=imagem.png, tipo=png)",
                AnexoMensagemDtoBuilder.builder().build().toString());
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        AnexoMensagemDto anexoMensagemDto =
                new AnexoMensagemDto(1L, new byte[]{}, "imagem.png", "png");
        Assertions.assertEquals(
                "AnexoMensagemDto(id=1, dados=[], nome=imagem.png, tipo=png)",
                anexoMensagemDto.toString());
    }

}
