package br.akd.svc.akadia.models.entities.sistema.patrimonios;

import br.akd.svc.akadia.models.entities.sistema.patrimonios.mocks.PatrimonioEntityBuilder;
import br.akd.svc.akadia.modules.erp.patrimonios.models.enums.TipoPatrimonioEnum;
import br.akd.svc.akadia.modules.erp.patrimonios.models.entity.PatrimonioEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Entity: Patrimonio")
class PatrimonioEntityTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "PatrimonioEntity(id=1, dataCadastro=2023-08-21, horaCadastro=10:20, dataEntrada=2023-08-21, " +
                        "descricao=Dinheiro, valor=100.0, tipoPatrimonio=ATIVO, exclusao=null, " +
                        "colaboradorResponsavel=null, empresa=null)",
                PatrimonioEntityBuilder.builder().build().toString()
        );

    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        PatrimonioEntity patrimonioEntity = new PatrimonioEntity(
                1L,
                LocalDate.of(2023, 8, 18).toString(),
                LocalTime.of(7, 55).toString(),
                LocalDate.of(2023, 8, 18).toString(),
                "Dinheiro",
                100.0,
                TipoPatrimonioEnum.ATIVO,
                null,
                null,
                null
        );
        Assertions.assertEquals(
                "PatrimonioEntity(id=1, dataCadastro=2023-08-18, horaCadastro=07:55, dataEntrada=2023-08-18, " +
                        "descricao=Dinheiro, valor=100.0, tipoPatrimonio=ATIVO, exclusao=null, " +
                        "colaboradorResponsavel=null, empresa=null)",
                patrimonioEntity.toString()
        );

    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        PatrimonioEntity patrimonioEntity = PatrimonioEntity.builder()
                .id(1L)
                .dataCadastro(LocalDate.of(2023, 8, 18).toString())
                .horaCadastro(LocalTime.of(7, 55).toString())
                .dataEntrada(LocalDate.of(2023, 8, 18).toString())
                .descricao("Dinheiro")
                .valor(100.0)
                .tipoPatrimonio(TipoPatrimonioEnum.ATIVO)
                .exclusao(null)
                .colaboradorResponsavel(null)
                .empresa(null)
                .build();
        Assertions.assertEquals(
                "PatrimonioEntity(id=1, dataCadastro=2023-08-18, horaCadastro=07:55, dataEntrada=2023-08-18, " +
                        "descricao=Dinheiro, valor=100.0, tipoPatrimonio=ATIVO, exclusao=null, " +
                        "colaboradorResponsavel=null, empresa=null)",
                patrimonioEntity.toString()
        );
    }

}
