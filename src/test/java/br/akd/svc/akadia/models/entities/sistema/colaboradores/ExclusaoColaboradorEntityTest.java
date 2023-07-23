package br.akd.svc.akadia.models.entities.sistema.colaboradores;

import br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks.ExclusaoColaboradorEntityBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Entity: ExclusaoColaborador")
class ExclusaoColaboradorEntityTest {
    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "ExclusaoColaboradorEntity(id=1, dataExclusao=2023-02-13, horaExclusao=10:44, excluido=true, " +
                        "responsavelExclusao=null)",
                ExclusaoColaboradorEntityBuilder.builder().build().toString()
        );
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        ExclusaoColaboradorEntity exclusaoColaboradorEntity = new ExclusaoColaboradorEntity(
                1L,
                LocalDate.of(2023, 6, 1).toString(),
                LocalTime.of(9, 37, 0).toString(),
                false,
                null
        );
        Assertions.assertEquals(
                "ExclusaoColaboradorEntity(id=1, dataExclusao=2023-06-01, horaExclusao=09:37, excluido=false, " +
                        "responsavelExclusao=null)",
                exclusaoColaboradorEntity.toString()
        );
    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        ExclusaoColaboradorEntity exclusaoColaboradorEntity = ExclusaoColaboradorEntity.builder()
                .id(1L)
                .dataExclusao(LocalDate.of(2023, 6, 1).toString())
                .horaExclusao(LocalTime.of(9, 37, 0).toString())
                .excluido(false)
                .responsavelExclusao(null)
                .build();
        Assertions.assertEquals(
                "ExclusaoColaboradorEntity(id=1, dataExclusao=2023-06-01, horaExclusao=09:37, excluido=false, " +
                        "responsavelExclusao=null)",
                exclusaoColaboradorEntity.toString()
        );
    }
}
