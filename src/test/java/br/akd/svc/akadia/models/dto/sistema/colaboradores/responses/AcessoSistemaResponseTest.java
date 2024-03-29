package br.akd.svc.akadia.models.dto.sistema.colaboradores.responses;

import br.akd.svc.akadia.modules.erp.colaboradores.acesso.models.dto.response.AcessoSistemaResponse;
import br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.mocks.AcessoSistemaResponseBuilder;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.enums.PermissaoEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashSet;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Response: AcessoSistemaResponse")
class AcessoSistemaResponseTest {
    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "AcessoSistemaResponse(acessoSistemaAtivo=true, permissaoEnum=LEITURA_AVANCADA_ALTERACAO)",
                AcessoSistemaResponseBuilder.builder().build().toString()
        );

    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        AcessoSistemaResponse acessoSistemaResponse = new AcessoSistemaResponse(
                true,
                PermissaoEnum.LEITURA_AVANCADA_ALTERACAO,
                new HashSet<>()
        );
        Assertions.assertEquals(
                "AcessoSistemaResponse(acessoSistemaAtivo=true, permissaoEnum=LEITURA_AVANCADA_ALTERACAO)",
                acessoSistemaResponse.toString()
        );

    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        AcessoSistemaResponse acessoSistemaResponse = AcessoSistemaResponse.builder()
                .acessoSistemaAtivo(true)
                .permissaoEnum(PermissaoEnum.LEITURA_BASICA)
                .privilegios(new HashSet<>())
                .build();
        Assertions.assertEquals(
                "AcessoSistemaResponse(acessoSistemaAtivo=true, permissaoEnum=LEITURA_BASICA)",
                acessoSistemaResponse.toString()
        );
    }
}
