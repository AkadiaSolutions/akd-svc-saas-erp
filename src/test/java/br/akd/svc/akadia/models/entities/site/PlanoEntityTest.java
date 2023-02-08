package br.akd.svc.akadia.models.entities.site;

import br.akd.svc.akadia.models.entities.site.mocks.PlanoEntityBuilder;
import br.akd.svc.akadia.models.enums.site.FormaPagamentoSistemaEnum;
import br.akd.svc.akadia.models.enums.site.StatusPlanoEnum;
import br.akd.svc.akadia.models.enums.site.TipoPlanoEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootTest
@DisplayName("Entity: Plano")
class PlanoEntityTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "PlanoEntity(id=1, codigoAssinaturaAsaas=sub_jaIvjZ8TMlXZ, dataContratacao=2023-02-03, " +
                        "horaContratacao=09:58, dataVencimento=null, tipoPlanoEnum=BASIC, statusPlanoEnum=ATIVO, " +
                        "formaPagamentoSistemaEnum=BOLETO)",
                PlanoEntityBuilder.builder().build().toString()
        );
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        PlanoEntity planoEntity = new PlanoEntity(
                1L,
                "sub_jaIvjZ8TMlXZ",
                LocalDate.of(2023, 2, 3).toString(),
                LocalTime.of(10, 2).toString(),
                LocalDate.of(2023, 2, 3).toString(),
                TipoPlanoEnum.STANDART,
                StatusPlanoEnum.INATIVO,
                FormaPagamentoSistemaEnum.PIX
        );
        Assertions.assertEquals(
                "PlanoEntity(id=1, codigoAssinaturaAsaas=sub_jaIvjZ8TMlXZ, dataContratacao=2023-02-03, " +
                        "horaContratacao=10:02, dataVencimento=2023-02-03, tipoPlanoEnum=STANDART, " +
                        "statusPlanoEnum=INATIVO, formaPagamentoSistemaEnum=PIX)",
                planoEntity.toString()
        );
    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        PlanoEntity planoEntity = PlanoEntity.builder()
                .id(1L)
                .codigoAssinaturaAsaas("sub_jaIvjZ8TMlXZ")
                .dataContratacao(LocalDate.of(2023, 2, 3).toString())
                .horaContratacao(LocalTime.of(10, 3).toString())
                .tipoPlanoEnum(TipoPlanoEnum.PRO)
                .statusPlanoEnum(StatusPlanoEnum.PERIODO_DE_TESTES)
                .formaPagamentoSistemaEnum(FormaPagamentoSistemaEnum.CREDIT_CARD)
                .build();
        Assertions.assertEquals(
                "PlanoEntity(id=1, codigoAssinaturaAsaas=sub_jaIvjZ8TMlXZ, dataContratacao=2023-02-03, " +
                        "horaContratacao=10:03, dataVencimento=null, tipoPlanoEnum=PRO, " +
                        "statusPlanoEnum=PERIODO_DE_TESTES, formaPagamentoSistemaEnum=CREDIT_CARD)",
                planoEntity.toString()
        );
    }

}
