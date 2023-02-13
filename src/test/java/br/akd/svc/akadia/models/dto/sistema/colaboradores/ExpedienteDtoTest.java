package br.akd.svc.akadia.models.dto.sistema.colaboradores;

import br.akd.svc.akadia.models.dto.sistema.colaboradores.mocks.ExpedienteDtoBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;

@SpringBootTest
@DisplayName("DTO: Expediente")
class ExpedienteDtoTest {
    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "ExpedienteDto(id=1, horaEntrada=09:00, horaSaidaAlmoco=12:00, horaEntradaAlmoco=13:00, " +
                        "horaSaida=18:00)",
                ExpedienteDtoBuilder.builder().build().toString()
        );
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        ExpedienteDto expedienteDto = new ExpedienteDto(
                1L,
                LocalTime.of(9, 0).toString(),
                LocalTime.of(12, 0).toString(),
                LocalTime.of(13, 0).toString(),
                LocalTime.of(18, 0).toString()
        );
        Assertions.assertEquals(
                "ExpedienteDto(id=1, horaEntrada=09:00, horaSaidaAlmoco=12:00, horaEntradaAlmoco=13:00, " +
                        "horaSaida=18:00)",
                expedienteDto.toString()
        );
    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        ExpedienteDto expedienteDto = ExpedienteDto.builder()
                .id(1L)
                .horaEntrada(LocalTime.of(9, 0).toString())
                .horaSaidaAlmoco(LocalTime.of(12, 0).toString())
                .horaEntradaAlmoco(LocalTime.of(13, 0).toString())
                .horaSaida(LocalTime.of(18, 0).toString())
                .build();
        Assertions.assertEquals(
                "ExpedienteDto(id=1, horaEntrada=09:00, horaSaidaAlmoco=12:00, horaEntradaAlmoco=13:00, " +
                        "horaSaida=18:00)",
                expedienteDto.toString()
        );
    }
}
