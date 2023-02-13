package br.akd.svc.akadia.models.dto.sistema.colaboradores;

import br.akd.svc.akadia.models.dto.sistema.colaboradores.mocks.ConfiguracaoPerfilDtoBuilder;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.TemaTelaEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootTest
@DisplayName("DTO: ConfiguracaoPerfil")
class ConfiguracaoPerfilDtoTest {
    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "ConfiguracaoPerfilDto(id=1, dataUltimaAtualizacao=2023-02-13, horaUltimaAtualizacao=10:44, " +
                        "temaTelaEnum=TELA_ESCURA)",
                ConfiguracaoPerfilDtoBuilder.builder().build().toString()
        );
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        ConfiguracaoPerfilDto configuracaoPerfilDto = new ConfiguracaoPerfilDto(
                1L,
                LocalDate.of(2023, 2, 13).toString(),
                LocalTime.of(11, 17).toString(),
                TemaTelaEnum.TELA_CLARA
        );
        Assertions.assertEquals(
                "ConfiguracaoPerfilDto(id=1, dataUltimaAtualizacao=2023-02-13, horaUltimaAtualizacao=11:17, " +
                        "temaTelaEnum=TELA_CLARA)",
                configuracaoPerfilDto.toString()
        );
    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        ConfiguracaoPerfilDto configuracaoPerfilDto = ConfiguracaoPerfilDto.builder()
                .id(1L)
                .dataUltimaAtualizacao(LocalDate.of(2023, 2, 13).toString())
                .horaUltimaAtualizacao(LocalTime.of(11, 18).toString())
                .temaTelaEnum(TemaTelaEnum.TELA_ESCURA)
                .build();
        Assertions.assertEquals(
                "ConfiguracaoPerfilDto(id=1, dataUltimaAtualizacao=2023-02-13, horaUltimaAtualizacao=11:18, " +
                        "temaTelaEnum=TELA_ESCURA)",
                configuracaoPerfilDto.toString()
        );
    }
}
