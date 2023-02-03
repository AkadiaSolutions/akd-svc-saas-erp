package br.akd.svc.akadia.models.entities.site.fiscal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Entity: NfeConfig")
class NfeConfigEntityTest {

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        NfeConfigEntity nfeConfigEntity = new NfeConfigEntity(
                1L,
                1L,
                1L,
                1,
                1
        );
        Assertions.assertEquals(
                "NfeConfigEntity(id=1, proximoNumeroProducao=1, proximoNumeroHomologacao=1, serieProducao=1, serieHomologacao=1)",
                nfeConfigEntity.toString()
        );
    }

}
