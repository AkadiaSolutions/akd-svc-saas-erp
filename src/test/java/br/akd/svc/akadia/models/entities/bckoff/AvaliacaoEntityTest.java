package br.akd.svc.akadia.models.entities.bckoff;

import br.akd.svc.akadia.models.entities.bckoff.mocks.AvaliacaoEntityBuilder;
import br.akd.svc.akadia.modules.backoffice.models.entity.AvaliacaoEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Entity: Avaliacao")
class AvaliacaoEntityTest {
    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "AvaliacaoEntity(id=1, nota=10, descricao=Atendimento ótimo!)",
                AvaliacaoEntityBuilder.builder().build().toString());
    }

    @Test
    @DisplayName("Deve testar allArgsConstructor")
    void deveTestarAllArgsConstructor() {

        AvaliacaoEntity avaliacaoEntity = new AvaliacaoEntity(1L, 10, "Atendimento ótimo!");

        Assertions.assertEquals(
                "AvaliacaoEntity(id=1, nota=10, descricao=Atendimento ótimo!)",
                avaliacaoEntity.toString());
    }
}
