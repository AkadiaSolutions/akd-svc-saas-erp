package br.akd.svc.akadia.models.dto.sistema.despesas.request;

import br.akd.svc.akadia.models.dto.sistema.despesas.request.mock.DespesaRequestBuilder;
import br.akd.svc.akadia.modules.erp.despesas.models.enums.StatusDespesaEnum;
import br.akd.svc.akadia.modules.erp.despesas.models.enums.TipoDespesaEnum;
import br.akd.svc.akadia.modules.erp.despesas.models.dto.request.DespesaRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Request: Despesa")
class DespesaRequestTest {
    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "DespesaRequest(dataPagamento=2023-08-18, dataAgendamento=Pago, descricao=Gasolina carro, " +
                        "valor=100.0, qtdRecorrencias=0, statusDespesa=PAGO, tipoDespesa=VARIAVEL)",
                DespesaRequestBuilder.builder().build().toString()
        );

    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        DespesaRequest despesaRequest = new DespesaRequest(
                LocalDate.of(2023, 8, 18).toString(),
                null,
                "Gasolina carro",
                100.0,
                0,
                StatusDespesaEnum.PAGO,
                TipoDespesaEnum.VARIAVEL
        );
        Assertions.assertEquals(
                "DespesaRequest(dataPagamento=2023-08-18, dataAgendamento=null, descricao=Gasolina carro, " +
                        "valor=100.0, qtdRecorrencias=0, statusDespesa=PAGO, tipoDespesa=VARIAVEL)",
                despesaRequest.toString()
        );

    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        DespesaRequest despesaRequest = DespesaRequest.builder()
                .dataPagamento(LocalDate.of(2023, 8, 18).toString())
                .dataAgendamento(null)
                .descricao("Gasolina carro")
                .valor(100.0)
                .statusDespesa(StatusDespesaEnum.PAGO)
                .tipoDespesa(TipoDespesaEnum.VARIAVEL)
                .build();
        Assertions.assertEquals(
                "DespesaRequest(dataPagamento=2023-08-18, dataAgendamento=null, descricao=Gasolina carro, " +
                        "valor=100.0, qtdRecorrencias=null, statusDespesa=PAGO, tipoDespesa=VARIAVEL)",
                despesaRequest.toString()
        );
    }
}
