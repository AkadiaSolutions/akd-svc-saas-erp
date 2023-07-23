package br.akd.svc.akadia.models.entities.sistema.clientes;

import br.akd.svc.akadia.models.entities.sistema.clientes.mocks.ExclusaoClienteEntityBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Entity: ExclusaoCliente")
class ExclusaoClienteEntityTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "ExclusaoClienteEntity(id=1, dataExclusao=null, horaExclusao=null, excluido=false, " +
                        "responsavelExclusao=null)", ExclusaoClienteEntityBuilder.builder().build().toString()
        );

    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {

        ExclusaoClienteEntity exclusaoClienteEntity = new ExclusaoClienteEntity(
                1L, null, null, null, null);

        Assertions.assertEquals(
                "ExclusaoClienteEntity(id=1, dataExclusao=null, horaExclusao=null, excluido=null, " +
                        "responsavelExclusao=null)", exclusaoClienteEntity.toString()
        );

    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {

        ExclusaoClienteEntity exclusaoClienteEntity = ExclusaoClienteEntity.builder()
                .id(1L)
                .dataExclusao(null)
                .horaExclusao(null)
                .excluido(null)
                .responsavelExclusao(null)
                .build();

        Assertions.assertEquals(
                "ExclusaoClienteEntity(id=1, dataExclusao=null, horaExclusao=null, excluido=null, " +
                        "responsavelExclusao=null)", exclusaoClienteEntity.toString()
        );
    }

}
