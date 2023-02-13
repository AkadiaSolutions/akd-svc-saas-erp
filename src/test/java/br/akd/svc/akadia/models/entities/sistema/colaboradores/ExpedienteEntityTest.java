package br.akd.svc.akadia.models.entities.sistema.colaboradores;

import br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks.ExpedienteEntityBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;

@SpringBootTest
@DisplayName("Entity: Expediente")
class ExpedienteEntityTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "ExpedienteEntity(id=1, horaEntrada=09:00, horaSaidaAlmoco=12:00, horaEntradaAlmoco=13:00, " +
                        "horaSaida=18:00)",
                ExpedienteEntityBuilder.builder().build().toString()
        );
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        ExpedienteEntity expedienteEntity = new ExpedienteEntity(
                1L,
                LocalTime.of(9, 0).toString(),
                LocalTime.of(12, 0).toString(),
                LocalTime.of(13, 0).toString(),
                LocalTime.of(18, 0).toString()
        );
        Assertions.assertEquals(
                "ExpedienteEntity(id=1, horaEntrada=09:00, horaSaidaAlmoco=12:00, horaEntradaAlmoco=13:00, " +
                        "horaSaida=18:00)",
                expedienteEntity.toString()
        );
    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        ExpedienteEntity expedienteEntity = ExpedienteEntity.builder()
                .id(1L)
                .horaEntrada(LocalTime.of(9, 0).toString())
                .horaSaidaAlmoco(LocalTime.of(12, 0).toString())
                .horaEntradaAlmoco(LocalTime.of(13, 0).toString())
                .horaSaida(LocalTime.of(18, 0).toString())
                .build();
        Assertions.assertEquals(
                "ExpedienteEntity(id=1, horaEntrada=09:00, horaSaidaAlmoco=12:00, horaEntradaAlmoco=13:00, " +
                        "horaSaida=18:00)",
                expedienteEntity.toString()
        );
    }

}
