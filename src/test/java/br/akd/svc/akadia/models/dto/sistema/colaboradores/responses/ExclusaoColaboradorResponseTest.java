package br.akd.svc.akadia.models.dto.sistema.colaboradores.responses;

import br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.mocks.ExclusaoColaboradorResponseBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Response: ExclusaoColaboradorResponse")
class ExclusaoColaboradorResponseTest {
    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "ExclusaoColaboradorResponse(dataExclusao=2023-02-13, horaExclusao=14:00, excluido=true)",
                ExclusaoColaboradorResponseBuilder.builder().build().toString()
        );

    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        ExclusaoColaboradorResponse exclusaoColaboradorResponse = new ExclusaoColaboradorResponse(
                LocalDate.of(2023, 1, 1).toString(),
                LocalTime.of(14, 1, 0).toString(),
                true
        );
        Assertions.assertEquals(
                "ExclusaoColaboradorResponse(dataExclusao=2023-01-01, horaExclusao=14:01, excluido=true)",
                exclusaoColaboradorResponse.toString()
        );

    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        ExclusaoColaboradorResponse exclusaoColaboradorResponse = ExclusaoColaboradorResponse.builder()
                .dataExclusao(LocalDate.of(2023, 1, 1).toString())
                .horaExclusao(LocalTime.of(14, 1, 0).toString())
                .excluido(true)
                .build();
        Assertions.assertEquals(
                "ExclusaoColaboradorResponse(dataExclusao=2023-01-01, horaExclusao=14:01, excluido=true)",
                exclusaoColaboradorResponse.toString()
        );
    }
}
