package br.akd.svc.akadia.models.entities.sistema.colaboradores;

import br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks.ConfiguracaoPerfilEntityBuilder;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.TemaTelaEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootTest
@DisplayName("Entity: ConfiguracaoPerfil")
class ConfiguracaoPerfilEntityTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "ConfiguracaoPerfilEntity(id=1, dataUltimaAtualizacao=2023-02-13, horaUltimaAtualizacao=10:44, " +
                        "temaTelaEnum=TELA_ESCURA)",
                ConfiguracaoPerfilEntityBuilder.builder().build().toString()
        );
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        ConfiguracaoPerfilEntity configuracaoPerfilEntity = new ConfiguracaoPerfilEntity(
                1L,
                LocalDate.of(2023, 2, 13).toString(),
                LocalTime.of(11, 17).toString(),
                TemaTelaEnum.TELA_CLARA
        );
        Assertions.assertEquals(
                "ConfiguracaoPerfilEntity(id=1, dataUltimaAtualizacao=2023-02-13, horaUltimaAtualizacao=11:17, " +
                        "temaTelaEnum=TELA_CLARA)",
                configuracaoPerfilEntity.toString()
        );
    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        ConfiguracaoPerfilEntity configuracaoPerfilEntity = ConfiguracaoPerfilEntity.builder()
                .id(1L)
                .dataUltimaAtualizacao(LocalDate.of(2023, 2, 13).toString())
                .horaUltimaAtualizacao(LocalTime.of(11, 18).toString())
                .temaTelaEnum(TemaTelaEnum.TELA_ESCURA)
                .build();
        Assertions.assertEquals(
                "ConfiguracaoPerfilEntity(id=1, dataUltimaAtualizacao=2023-02-13, horaUltimaAtualizacao=11:18, " +
                        "temaTelaEnum=TELA_ESCURA)",
                configuracaoPerfilEntity.toString()
        );
    }

}
