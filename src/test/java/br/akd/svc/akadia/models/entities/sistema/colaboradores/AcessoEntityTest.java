package br.akd.svc.akadia.models.entities.sistema.colaboradores;

import br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks.AcessoEntityBuilder;
import br.akd.svc.akadia.modules.erp.colaboradores.acesso.models.entity.AcessoEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Entity: Acesso")
class AcessoEntityTest {
    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "AcessoEntity(id=1, dataCadastro=2023-02-13, horaCadastro=10:44)",
                AcessoEntityBuilder.builder().build().toString()
        );
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        AcessoEntity acessoEntity = new AcessoEntity(
                1L,
                LocalDate.of(2023, 6, 1).toString(),
                LocalTime.of(9, 37, 0).toString()
        );
        Assertions.assertEquals(
                "AcessoEntity(id=1, dataCadastro=2023-06-01, horaCadastro=09:37)",
                acessoEntity.toString()
        );
    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        AcessoEntity acessoEntity = AcessoEntity.builder()
                .id(1L)
                .dataCadastro(LocalDate.of(2023, 6, 1).toString())
                .horaCadastro(LocalTime.of(9, 37, 0).toString())
                .build();
        Assertions.assertEquals(
                "AcessoEntity(id=1, dataCadastro=2023-06-01, horaCadastro=09:37)",
                acessoEntity.toString()
        );
    }
}
