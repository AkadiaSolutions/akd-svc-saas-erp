package br.akd.svc.akadia.models.dto.site.empresa.fiscal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("DTO: NfseConfig")
class NfseConfigDtoTest {
    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        NfseConfigDto nfseConfigDto = new NfseConfigDto(
                1L,
                1L,
                1L,
                1,
                1
        );
        Assertions.assertEquals(
                "NfseConfigDto(id=1, proximoNumeroProducao=1, proximoNumeroHomologacao=1, serieProducao=1, serieHomologacao=1)",
                nfseConfigDto.toString()
        );
    }
}
