package br.akd.svc.akadia.models.entities.sistema.colaboradores;

import br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks.PontoEntityBuilder;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.entity.PontoEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalTime;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Entity: Ponto")
class PontoEntityTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "PontoEntity(id=1, horaEntrada=09:00, horaSaidaAlmoco=12:00, horaEntradaAlmoco=13:00, " +
                        "horaSaida=18:00)",
                PontoEntityBuilder.builder().build().toString()
        );
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        PontoEntity pontoEntity = new PontoEntity(
                1L,
                LocalTime.of(9, 0).toString(),
                LocalTime.of(12, 0).toString(),
                LocalTime.of(13, 0).toString(),
                LocalTime.of(18, 0).toString()
        );
        Assertions.assertEquals(
                "PontoEntity(id=1, horaEntrada=09:00, horaSaidaAlmoco=12:00, horaEntradaAlmoco=13:00, " +
                        "horaSaida=18:00)",
                pontoEntity.toString()
        );
    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        PontoEntity pontoEntity = PontoEntity.builder()
                .id(1L)
                .horaEntrada(LocalTime.of(9, 0).toString())
                .horaSaidaAlmoco(LocalTime.of(12, 0).toString())
                .horaEntradaAlmoco(LocalTime.of(13, 0).toString())
                .horaSaida(LocalTime.of(18, 0).toString())
                .build();
        Assertions.assertEquals(
                "PontoEntity(id=1, horaEntrada=09:00, horaSaidaAlmoco=12:00, horaEntradaAlmoco=13:00, " +
                        "horaSaida=18:00)",
                pontoEntity.toString()
        );
    }

}
