package br.akd.svc.akadia.models.dto.bckoff;

import br.akd.svc.akadia.models.dto.bckoff.mocks.AvaliacaoDtoBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("DTO: Avaliacao")
class AvaliacaoDtoTest {
    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "AvaliacaoDto(id=1, nota=10, descricao=Atendimento ótimo!)",
                AvaliacaoDtoBuilder.builder().build().toString());
    }

    @Test
    @DisplayName("Deve testar allArgsConstructor")
    void deveTestarAllArgsConstructor() {

        AvaliacaoDto avaliacaoDto = new AvaliacaoDto(1L, 10, "Atendimento ótimo!");

        Assertions.assertEquals(
                "AvaliacaoDto(id=1, nota=10, descricao=Atendimento ótimo!)",
                avaliacaoDto.toString());
    }
}
