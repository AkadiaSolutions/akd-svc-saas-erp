package br.akd.svc.akadia.models.entities.sistema.colaboradores;

import br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks.AcaoEntityBuilder;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.ModulosEnum;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.TipoAcaoEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootTest
@DisplayName("Entity: AcaoEntity")
class AcaoEntityTest {
    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "AcaoEntity(id=1, idObjeto=1, dataCriacao=2023-02-13, horaCriacao=10:44, " +
                        "moduloEnum=COLABORADORES, tipoAcaoEnum=CRIACAO, observacao=observação)",
                AcaoEntityBuilder.builder().build().toString()
        );
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        AcaoEntity acaoEntity = new AcaoEntity(
                1L,
                1L,
                LocalDate.of(2023, 6, 1).toString(),
                LocalTime.of(9, 37, 0).toString(),
                ModulosEnum.COLABORADORES,
                TipoAcaoEnum.CRIACAO,
                "observação"
        );
        Assertions.assertEquals(
                "AcaoEntity(id=1, idObjeto=1, dataCriacao=2023-06-01, horaCriacao=09:37, " +
                        "moduloEnum=COLABORADORES, tipoAcaoEnum=CRIACAO, observacao=observação)",
                acaoEntity.toString()
        );
    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        AcaoEntity acaoEntity = AcaoEntity.builder()
                .id(1L)
                .dataCriacao(LocalDate.of(2023, 6, 1).toString())
                .horaCriacao(LocalTime.of(9, 37, 0).toString())
                .moduloEnum(ModulosEnum.COLABORADORES)
                .tipoAcaoEnum(TipoAcaoEnum.CRIACAO)
                .observacao("observação")
                .build();
        Assertions.assertEquals(
                "AcaoEntity(id=1, idObjeto=null, dataCriacao=2023-06-01, horaCriacao=09:37, " +
                        "moduloEnum=COLABORADORES, tipoAcaoEnum=CRIACAO, observacao=observação)",
                acaoEntity.toString()
        );
    }
}
