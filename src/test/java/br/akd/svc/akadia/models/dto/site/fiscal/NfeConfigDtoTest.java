package br.akd.svc.akadia.models.dto.site.fiscal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("DTO: NfeConfig")
class NfeConfigDtoTest {
    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        NfeConfigDto nfeConfigDto = new NfeConfigDto(
                1L,
                1L,
                1L,
                1,
                1
        );
        Assertions.assertEquals(
                "NfeConfigDto(id=1, proximoNumeroProducao=1, proximoNumeroHomologacao=1, serieProducao=1, serieHomologacao=1)",
                nfeConfigDto.toString()
        );
    }
}
