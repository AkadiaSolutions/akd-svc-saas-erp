package br.akd.svc.akadia.models.entities.site;

import br.akd.svc.akadia.models.entities.site.mocks.PagamentoSistemaEntityBuilder;
import br.akd.svc.akadia.models.enums.site.FormaPagamentoSistemaEnum;
import br.akd.svc.akadia.models.enums.site.StatusPagamentoSistemaEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootTest
@DisplayName("Entity: PagamentoSistema")
class PagamentoSistemaEntityTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "PagamentoSistemaEntity(id=1, dataCadastro=2023-02-03, horaCadastro=10:57, " +
                        "codigoTransacao=129371283971, valor=650.0, vencimento=2023-02-03, formaPagamentoSistemaEnum=CREDIT_CARD, statusPagamentoSistemaEnum=APROVADO, cartao=null, clienteSistema=null)",
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
                12345678910L,
                650.0,
                LocalDate.of(2023, 2, 5).toString(),
                FormaPagamentoSistemaEnum.PIX,
                StatusPagamentoSistemaEnum.REPROVADO,
                null,
                null
        );

        Assertions.assertEquals(
                "PagamentoSistemaEntity(id=1, dataCadastro=2023-02-03, horaCadastro=15:50, " +
                        "codigoTransacao=12345678910, valor=650.0, vencimento=2023-02-05, formaPagamentoSistemaEnum=PIX, " +
                        "statusPagamentoSistemaEnum=REPROVADO, cartao=null, clienteSistema=null)",
                pagamentoSistemaEntity.toString()
        );

    }

}
