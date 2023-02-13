package br.akd.svc.akadia.models.entities.sistema.colaboradores;

import br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks.FeriasEntityBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootTest
@DisplayName("Entity: Ferias")
class FeriasEntityTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "FeriasEntity(id=1, dataCadastro=2023-02-13, horaCadastro=10:44, totalDias=30, " +
                        "dataInicio=2023-02-13, dataFim=2023-03-13)",
                FeriasEntityBuilder.builder().build().toString()
        );
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        FeriasEntity feriasEntity = new FeriasEntity(
                1L,
                LocalDate.of(2023, 2, 13).toString(),
                LocalTime.of(9, 0).toString(),
                30,
                LocalDate.of(2023, 2, 13).toString(),
                LocalDate.of(2023, 3, 13).toString()
        );
        Assertions.assertEquals(
                "FeriasEntity(id=1, dataCadastro=2023-02-13, horaCadastro=09:00, totalDias=30, " +
                        "dataInicio=2023-02-13, dataFim=2023-03-13)",
                feriasEntity.toString()
        );
    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        FeriasEntity feriasEntity = FeriasEntity.builder()
                .id(1L)
                .dataCadastro(LocalDate.of(2023, 2, 13).toString())
                .horaCadastro(LocalTime.of(9, 0).toString())
                .totalDias(30)
                .dataInicio(LocalDate.of(2023, 2, 13).toString())
                .dataFim(LocalDate.of(2023, 3, 13).toString())
                .build();
        Assertions.assertEquals(
                "FeriasEntity(id=1, dataCadastro=2023-02-13, horaCadastro=09:00, totalDias=30, " +
                        "dataInicio=2023-02-13, dataFim=2023-03-13)",
                feriasEntity.toString()
        );
    }

}
