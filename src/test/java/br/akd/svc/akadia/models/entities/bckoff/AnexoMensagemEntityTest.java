package br.akd.svc.akadia.models.entities.bckoff;

import br.akd.svc.akadia.models.entities.bckoff.mocks.AnexoMensagemEntityBuilder;
import br.akd.svc.akadia.modules.backoffice.models.entity.AnexoMensagemEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Entity: AnexoMensagem")
class AnexoMensagemEntityTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "AnexoMensagemEntity(id=1, dados=[], nome=imagem.png, tipo=png)",
                AnexoMensagemEntityBuilder.builder().build().toString());
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        AnexoMensagemEntity anexoMensagemEntity =
                new AnexoMensagemEntity(1L, new byte[]{}, "imagem.png", "png");
        Assertions.assertEquals(
                "AnexoMensagemEntity(id=1, dados=[], nome=imagem.png, tipo=png)",
                anexoMensagemEntity.toString());
    }

}
