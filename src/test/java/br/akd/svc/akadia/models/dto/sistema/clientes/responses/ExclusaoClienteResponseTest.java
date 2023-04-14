package br.akd.svc.akadia.models.dto.sistema.clientes.responses;

import br.akd.svc.akadia.models.dto.sistema.clientes.responses.mocks.ExclusaoClienteResponseBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Response: ExclusaoCliente")
class ExclusaoClienteResponseTest {
    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "ExclusaoClienteResponse(dataExclusao=null, horaExclusao=null, excluido=false)",
                ExclusaoClienteResponseBuilder.builder().build().toString()
        );

    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {

        ExclusaoClienteResponse exclusaoClienteResponse = new ExclusaoClienteResponse(
                null, null, false);

        Assertions.assertEquals(
                "ExclusaoClienteResponse(dataExclusao=null, horaExclusao=null, excluido=false)",
                exclusaoClienteResponse.toString()
        );

    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        ExclusaoClienteResponse exclusaoClienteResponse = ExclusaoClienteResponse.builder()
                .dataExclusao(null)
                .horaExclusao(null)
                .excluido(false)
                .build();

        Assertions.assertEquals(
                "ExclusaoClienteResponse(dataExclusao=null, horaExclusao=null, excluido=false)",
                exclusaoClienteResponse.toString()
        );
    }
}
