package br.akd.svc.akadia.models.entities.site.empresa.fiscal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Entity: NfseConfig")
class NfseConfigEntityTest {

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        NfseConfigEntity nfseConfigEntity = new NfseConfigEntity(
                1L,
                1L,
                1L,
                1,
                1
        );
        Assertions.assertEquals(
                "NfseConfigEntity(id=1, proximoNumeroProducao=1, proximoNumeroHomologacao=1, serieProducao=1, serieHomologacao=1)",
                nfseConfigEntity.toString()
        );
    }

}
