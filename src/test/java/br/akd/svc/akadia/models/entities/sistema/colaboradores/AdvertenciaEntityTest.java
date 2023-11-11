package br.akd.svc.akadia.models.entities.sistema.colaboradores;

import br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks.AdvertenciaEntityBuilder;
import br.akd.svc.akadia.modules.erp.colaboradores.advertencia.models.enums.StatusAdvertenciaEnum;
import br.akd.svc.akadia.modules.erp.colaboradores.advertencia.models.entity.AdvertenciaEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Entity: Advertencia")
class AdvertenciaEntityTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "AdvertenciaEntity(id=1, dataCadastro=2023-02-13, horaCadastro=10:44, motivo=Brigou na loja, " +
                        "descricao=Cuspiu no cliente, statusAdvertenciaEnum=ASSINADA, advertenciaAssinada=null)",
                AdvertenciaEntityBuilder.builder().build().toString()
        );
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        AdvertenciaEntity advertenciaEntity = new AdvertenciaEntity(
                1L,
                LocalDate.of(2023, 2, 13).toString(),
                LocalTime.of(11, 17).toString(),
                "Falta todo dia",
                "Fica faltando no serviço toda hora",
                StatusAdvertenciaEnum.ASSINADA,
                null
        );
        Assertions.assertEquals(
                "AdvertenciaEntity(id=1, dataCadastro=2023-02-13, horaCadastro=11:17, motivo=Falta todo dia, " +
                        "descricao=Fica faltando no serviço toda hora, statusAdvertenciaEnum=ASSINADA, advertenciaAssinada=null)",
                advertenciaEntity.toString()
        );
    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        AdvertenciaEntity advertenciaEntity = AdvertenciaEntity.builder()
                .id(1L)
                .dataCadastro(LocalDate.of(2023, 2, 13).toString())
                .horaCadastro(LocalTime.of(11, 18).toString())
                .motivo("Falta todo dia")
                .descricao("Fica faltando no serviço toda hora")
                .statusAdvertenciaEnum(StatusAdvertenciaEnum.PENDENTE)
                .advertenciaAssinada(null)
                .build();
        Assertions.assertEquals(
                "AdvertenciaEntity(id=1, dataCadastro=2023-02-13, horaCadastro=11:18, motivo=Falta todo dia, " +
                        "descricao=Fica faltando no serviço toda hora, statusAdvertenciaEnum=PENDENTE, advertenciaAssinada=null)",
                advertenciaEntity.toString()
        );
    }

}
