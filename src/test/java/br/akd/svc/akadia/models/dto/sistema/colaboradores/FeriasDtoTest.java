package br.akd.svc.akadia.models.dto.sistema.colaboradores;

import br.akd.svc.akadia.models.dto.sistema.colaboradores.mocks.FeriasDtoBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootTest
@DisplayName("DTO: Ferias")
class FeriasDtoTest {
    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "FeriasDto(id=1, dataCadastro=2023-02-13, horaCadastro=10:44, totalDias=30, " +
                        "dataInicio=2023-02-13, dataFim=2023-03-13)",
                FeriasDtoBuilder.builder().build().toString()
        );
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        FeriasDto feriasDto = new FeriasDto(
                1L,
                LocalDate.of(2023, 2, 13).toString(),
                LocalTime.of(9, 0).toString(),
                30,
                LocalDate.of(2023, 2, 13).toString(),
                LocalDate.of(2023, 3, 13).toString()
        );
        Assertions.assertEquals(
                "FeriasDto(id=1, dataCadastro=2023-02-13, horaCadastro=09:00, totalDias=30, " +
                        "dataInicio=2023-02-13, dataFim=2023-03-13)",
                feriasDto.toString()
        );
    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        FeriasDto feriasDto = FeriasDto.builder()
                .id(1L)
                .dataCadastro(LocalDate.of(2023, 2, 13).toString())
                .horaCadastro(LocalTime.of(9, 0).toString())
                .totalDias(30)
                .dataInicio(LocalDate.of(2023, 2, 13).toString())
                .dataFim(LocalDate.of(2023, 3, 13).toString())
                .build();
        Assertions.assertEquals(
                "FeriasDto(id=1, dataCadastro=2023-02-13, horaCadastro=09:00, totalDias=30, " +
                        "dataInicio=2023-02-13, dataFim=2023-03-13)",
                feriasDto.toString()
        );
    }
}
