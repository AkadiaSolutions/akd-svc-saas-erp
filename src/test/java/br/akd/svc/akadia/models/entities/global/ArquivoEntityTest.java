package br.akd.svc.akadia.models.entities.global;

import br.akd.svc.akadia.models.entities.global.mocks.ArquivoEntityBuilder;
import br.akd.svc.akadia.models.enums.global.TipoArquivoEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Entity: ArquivoEntity")
class ArquivoEntityTest {
    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "ArquivoEntity(id=1, nome=arquivo.pdf, tamanho=1000, tipo=PDF, arquivo=[])",
                ArquivoEntityBuilder.builder().build().toString()
        );
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        ArquivoEntity arquivoEntity = new ArquivoEntity(
                1L,
                "arquivo.pdf",
                1000L,
                TipoArquivoEnum.PDF,
                new byte[0]
        );
        Assertions.assertEquals(
                "ArquivoEntity(id=1, nome=arquivo.pdf, tamanho=1000, tipo=PDF, arquivo=[])",
                arquivoEntity.toString()
        );
    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        ArquivoEntity arquivoEntity = ArquivoEntity.builder()
                .id(1L)
                .nome("arquivo.pdf")
                .tamanho(1000L)
                .tipo(TipoArquivoEnum.PDF)
                .arquivo(new byte[0])
                .build();

        Assertions.assertEquals(
                "ArquivoEntity(id=1, nome=arquivo.pdf, tamanho=1000, tipo=PDF, arquivo=[])",
                arquivoEntity.toString()
        );
    }
}
