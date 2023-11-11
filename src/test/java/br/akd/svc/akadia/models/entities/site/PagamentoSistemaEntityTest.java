package br.akd.svc.akadia.models.entities.site;

import br.akd.svc.akadia.models.entities.site.mocks.PagamentoSistemaEntityBuilder;
import br.akd.svc.akadia.modules.web.models.enums.FormaPagamentoSistemaEnum;
import br.akd.svc.akadia.modules.web.models.enums.StatusPagamentoSistemaEnum;
import br.akd.svc.akadia.modules.web.models.entity.PagamentoSistemaEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Entity: PagamentoSistema")
class PagamentoSistemaEntityTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "PagamentoSistemaEntity(id=1, dataCadastro=2023-02-03, horaCadastro=10:57, " +
                        "dataPagamento=2023-02-03, horaPagamento=10:57, codigoPagamentoAsaas=pay_5498745402963061, " +
                        "valor=650.0, valorLiquido=640.0, descricao=Assinatura de plano Basic, " +
                        "dataVencimento=2023-02-03, formaPagamentoSistemaEnum=CREDIT_CARD, " +
                        "statusPagamentoSistemaEnum=APROVADO, cartao=null, clienteSistema=null)",
                PagamentoSistemaEntityBuilder.builder().build().toString()
        );

    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        PagamentoSistemaEntity pagamentoSistemaEntity = new PagamentoSistemaEntity(
                1L,
                LocalDate.of(2023, 2, 3).toString(),
                LocalTime.of(15, 50).toString(),
                LocalDate.of(2023, 2, 3).toString(),
                LocalTime.of(15, 50).toString(),
                "pay_5874803025986658",
                650.0,
                640.0,
                "Assinatura de plano Pro",
                LocalDate.of(2023, 2, 5).toString(),
                FormaPagamentoSistemaEnum.PIX,
                StatusPagamentoSistemaEnum.REPROVADO,
                null,
                null
        );

        Assertions.assertEquals(
                "PagamentoSistemaEntity(id=1, dataCadastro=2023-02-03, horaCadastro=15:50, " +
                        "dataPagamento=2023-02-03, horaPagamento=15:50, codigoPagamentoAsaas=pay_5874803025986658, " +
                        "valor=650.0, valorLiquido=640.0, descricao=Assinatura de plano Pro, dataVencimento=2023-02-05, " +
                        "formaPagamentoSistemaEnum=PIX, statusPagamentoSistemaEnum=REPROVADO, cartao=null, " +
                        "clienteSistema=null)",
                pagamentoSistemaEntity.toString()
        );

    }

}
