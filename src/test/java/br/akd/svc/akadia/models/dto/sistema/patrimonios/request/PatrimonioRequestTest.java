package br.akd.svc.akadia.models.dto.sistema.patrimonios.request;

import br.akd.svc.akadia.models.dto.sistema.patrimonios.request.mock.PatrimonioRequestBuilder;
import br.akd.svc.akadia.modules.erp.patrimonios.models.enums.TipoPatrimonioEnum;
import br.akd.svc.akadia.modules.erp.patrimonios.models.dto.request.PatrimonioRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Request: Patrimonio")
class PatrimonioRequestTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "PatrimonioRequest(dataEntrada=2023-08-21, descricao=Dinheiro, valor=100.0, tipoPatrimonio=ATIVO)",
                PatrimonioRequestBuilder.builder().build().toString()
        );

    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        PatrimonioRequest patrimonioRequest = new PatrimonioRequest(
                LocalDate.of(2023, 8, 18).toString(),
                "Dinheiro",
                100.0,
                TipoPatrimonioEnum.ATIVO
        );
        Assertions.assertEquals(
                "PatrimonioRequest(dataEntrada=2023-08-18, descricao=Dinheiro, valor=100.0, " +
                        "tipoPatrimonio=ATIVO)",
                patrimonioRequest.toString()
        );

    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        PatrimonioRequest patrimonioRequest = PatrimonioRequest.builder()
                .dataEntrada(LocalDate.of(2023, 8, 18).toString())
                .descricao("Dinheiro")
                .valor(100.0)
                .tipoPatrimonio(TipoPatrimonioEnum.ATIVO)
                .build();
        Assertions.assertEquals(
                "PatrimonioRequest(dataEntrada=2023-08-18, descricao=Dinheiro, valor=100.0, tipoPatrimonio=ATIVO)",
                patrimonioRequest.toString()
        );
    }

}
