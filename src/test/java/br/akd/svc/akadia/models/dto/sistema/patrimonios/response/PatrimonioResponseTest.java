package br.akd.svc.akadia.models.dto.sistema.patrimonios.response;

import br.akd.svc.akadia.models.dto.sistema.patrimonios.response.mock.PatrimonioResponseBuilder;
import br.akd.svc.akadia.modules.erp.patrimonios.models.enums.TipoPatrimonioEnum;
import br.akd.svc.akadia.modules.erp.patrimonios.models.dto.response.PatrimonioResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Response: Patrimonio")
class PatrimonioResponseTest {
    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "PatrimonioResponse(id=1, dataCadastro=2023-08-21, horaCadastro=10:20, dataEntrada=2023-08-21, " +
                        "descricao=Dinheiro, valor=100.0, tipoPatrimonio=Ativo)",
                PatrimonioResponseBuilder.builder().build().toString()
        );

    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        PatrimonioResponse patrimonioResponse = new PatrimonioResponse(
                1L,
                LocalDate.of(2023, 8, 18).toString(),
                LocalTime.of(7, 55).toString(),
                LocalDate.of(2023, 8, 18).toString(),
                "Dinheiro",
                100.0,
                TipoPatrimonioEnum.ATIVO.getDesc()
        );
        Assertions.assertEquals(
                "PatrimonioResponse(id=1, dataCadastro=2023-08-18, horaCadastro=07:55, " +
                        "dataEntrada=2023-08-18, descricao=Dinheiro, valor=100.0, tipoPatrimonio=Ativo)",
                patrimonioResponse.toString()
        );

    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        PatrimonioResponse patrimonioResponse = PatrimonioResponse.builder()
                .id(1L)
                .dataCadastro(LocalDate.of(2023, 8, 18).toString())
                .horaCadastro(LocalTime.of(7, 55).toString())
                .dataEntrada(LocalDate.of(2023, 8, 18).toString())
                .descricao("Dinheiro")
                .valor(100.0)
                .tipoPatrimonio(TipoPatrimonioEnum.ATIVO.getDesc())
                .build();
        Assertions.assertEquals(
                "PatrimonioResponse(id=1, dataCadastro=2023-08-18, horaCadastro=07:55, dataEntrada=2023-08-18, " +
                        "descricao=Dinheiro, valor=100.0, tipoPatrimonio=Ativo)",
                patrimonioResponse.toString()
        );
    }
}
