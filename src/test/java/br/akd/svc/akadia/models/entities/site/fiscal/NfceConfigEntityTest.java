package br.akd.svc.akadia.models.entities.site.fiscal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Entity: NfceConfigEntity")
class NfceConfigEntityTest {

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        NfceConfigEntity nfceConfigEntity = new NfceConfigEntity(
                1L,
                1L,
                1L,
                1,
                1,
                "1",
                "1",
                12345L,
                12345L
        );
        Assertions.assertEquals(
                "NfceConfigEntity(id=1, proximoNumeroProducao=1, proximoNumeroHomologacao=1, serieProducao=1, serieHomologacao=1, cscProducao=1, cscHomologacao=1, idTokenProducao=12345, idTokenHomologacao=12345)",
                nfceConfigEntity.toString()
        );

    }

}
