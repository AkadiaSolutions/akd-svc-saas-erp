package br.akd.svc.akadia.modules.web.models.entity.empresa;

import br.akd.svc.akadia.modules.web.models.entity.empresa.mocks.CriaEmpresaResponseBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Response: CriaEmpresa")
class CriaEmpresaResponseTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "CriaEmpresaResponse(idClienteEmpresa=1, colaboradorCriado=null)",
                CriaEmpresaResponseBuilder.builder().build().toString()
        );
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        CriaEmpresaResponse criaEmpresaResponse = new CriaEmpresaResponse(
                1L,
                null
        );

        Assertions.assertEquals(
                "CriaEmpresaResponse(idClienteEmpresa=1, colaboradorCriado=null)",
                criaEmpresaResponse.toString()
        );

    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        CriaEmpresaResponse criaEmpresaResponse = CriaEmpresaResponse.builder()
                .idClienteEmpresa(1L)
                .colaboradorCriado(null)
                .build();
        Assertions.assertEquals(
                "CriaEmpresaResponse(idClienteEmpresa=1, colaboradorCriado=null)",
                criaEmpresaResponse.toString()
        );
    }

}
