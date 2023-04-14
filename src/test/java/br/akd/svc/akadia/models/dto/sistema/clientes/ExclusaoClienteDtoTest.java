package br.akd.svc.akadia.models.dto.sistema.clientes;

import br.akd.svc.akadia.models.dto.sistema.clientes.mocks.ExclusaoClienteDtoBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("DTO: ExclusaoCliente")
class ExclusaoClienteDtoTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "ExclusaoClienteDto(id=1, dataExclusao=2023-03-06, horaExclusao=14:36, excluido=false, " +
                        "responsavelExclusao=null)", ExclusaoClienteDtoBuilder.builder().build().toString()
        );

    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {

        ExclusaoClienteDto exclusaoClienteDto = new ExclusaoClienteDto(
                1L, null, null, null, null);

        Assertions.assertEquals(
                "ExclusaoClienteDto(id=1, dataExclusao=null, horaExclusao=null, excluido=null, " +
                        "responsavelExclusao=null)", exclusaoClienteDto.toString()
        );

    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {

        ExclusaoClienteDto exclusaoClienteDto = ExclusaoClienteDto.builder()
                .id(1L)
                .dataExclusao(null)
                .horaExclusao(null)
                .excluido(null)
                .responsavelExclusao(null)
                .build();

        Assertions.assertEquals(
                "ExclusaoClienteDto(id=1, dataExclusao=null, horaExclusao=null, excluido=null, " +
                        "responsavelExclusao=null)", exclusaoClienteDto.toString()
        );
    }

}
