package br.akd.svc.akadia.models.dto.site;

import br.akd.svc.akadia.models.dto.site.mocks.PlanoDtoBuilder;
import br.akd.svc.akadia.modules.web.models.enums.FormaPagamentoSistemaEnum;
import br.akd.svc.akadia.modules.web.models.enums.StatusPlanoEnum;
import br.akd.svc.akadia.modules.web.models.enums.TipoPlanoEnum;
import br.akd.svc.akadia.modules.web.models.dto.PlanoDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("DTO: Plano")
class PlanoDtoTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "PlanoDto(id=1, codigoAssinaturaAsaas=sub_jaIvjZ8TMlXZ, dataContratacao=2023-02-03, " +
                        "horaContratacao=09:58, dataVencimento=null, tipoPlanoEnum=BASIC, statusPlanoEnum=ATIVO, " +
                        "formaPagamentoSistemaEnum=BOLETO)",
                PlanoDtoBuilder.builder().build().toString()
        );
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        PlanoDto planoDto = new PlanoDto(
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
                "PlanoDto(id=1, codigoAssinaturaAsaas=sub_jaIvjZ8TMlXZ, dataContratacao=2023-02-03, " +
                        "horaContratacao=10:02, dataVencimento=2023-02-03, tipoPlanoEnum=STANDART, " +
                        "statusPlanoEnum=INATIVO, formaPagamentoSistemaEnum=PIX)",
                planoDto.toString()
        );
    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        PlanoDto planoDto = PlanoDto.builder()
                .id(1L)
                .codigoAssinaturaAsaas("sub_jaIvjZ8TMlXZ")
                .dataContratacao(LocalDate.of(2023, 2, 3).toString())
                .horaContratacao(LocalTime.of(10, 3).toString())
                .tipoPlanoEnum(TipoPlanoEnum.PRO)
                .statusPlanoEnum(StatusPlanoEnum.PERIODO_DE_TESTES)
                .formaPagamentoSistemaEnum(FormaPagamentoSistemaEnum.CREDIT_CARD)
                .build();
        Assertions.assertEquals(
                "PlanoDto(id=1, codigoAssinaturaAsaas=sub_jaIvjZ8TMlXZ, dataContratacao=2023-02-03, " +
                        "horaContratacao=10:03, dataVencimento=null, tipoPlanoEnum=PRO, " +
                        "statusPlanoEnum=PERIODO_DE_TESTES, formaPagamentoSistemaEnum=CREDIT_CARD)",
                planoDto.toString()
        );
    }
}
