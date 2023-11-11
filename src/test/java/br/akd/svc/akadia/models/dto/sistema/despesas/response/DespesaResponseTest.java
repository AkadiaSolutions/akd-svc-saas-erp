package br.akd.svc.akadia.models.dto.sistema.despesas.response;

import br.akd.svc.akadia.models.dto.sistema.despesas.response.mock.DespesaResponseBuilder;
import br.akd.svc.akadia.modules.erp.despesas.models.enums.StatusDespesaEnum;
import br.akd.svc.akadia.modules.erp.despesas.models.enums.TipoDespesaEnum;
import br.akd.svc.akadia.modules.erp.despesas.models.enums.TipoRecorrenciaDespesaEnum;
import br.akd.svc.akadia.modules.erp.despesas.models.dto.response.DespesaResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Response: Despesa")
class DespesaResponseTest {
    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "DespesaResponse(id=1, dataCadastro=2023-08-18, horaCadastro=07:55, dataPagamento=2023-08-18, " +
                        "dataAgendamento=Pago, descricao=Gasolina carro, valor=100.0, observacao=Sem recorrências, " +
                        "qtdRecorrencias=0, statusDespesa=PAGO, tipoDespesa=VARIAVEL, tipoRecorrencia=SEM_RECORRENCIA)",
                DespesaResponseBuilder.builder().build().toString()
        );

    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        DespesaResponse despesaResponse = new DespesaResponse(
                1L,
                LocalDate.of(2023, 8, 18).toString(),
                LocalTime.of(7, 55).toString(),
                LocalDate.of(2023, 8, 18).toString(),
                null,
                "Gasolina carro",
                100.0,
                "Sem recorrências",
                0,
                StatusDespesaEnum.PAGO,
                TipoDespesaEnum.VARIAVEL,
                TipoRecorrenciaDespesaEnum.SEM_RECORRENCIA
        );
        Assertions.assertEquals(
                "DespesaResponse(id=1, dataCadastro=2023-08-18, horaCadastro=07:55, dataPagamento=2023-08-18, " +
                        "dataAgendamento=null, descricao=Gasolina carro, valor=100.0, observacao=Sem recorrências, " +
                        "qtdRecorrencias=0, statusDespesa=PAGO, tipoDespesa=VARIAVEL, tipoRecorrencia=SEM_RECORRENCIA)",
                despesaResponse.toString()
        );

    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        DespesaResponse despesaResponse = DespesaResponse.builder()
                .id(1L)
                .dataCadastro(LocalDate.of(2023, 8, 18).toString())
                .horaCadastro(LocalTime.of(7, 55).toString())
                .dataPagamento(LocalDate.of(2023, 8, 18).toString())
                .dataAgendamento(null)
                .descricao("Gasolina carro")
                .valor(100.0)
                .observacao("Sem recorrências")
                .qtdRecorrencias(0)
                .tipoRecorrencia(TipoRecorrenciaDespesaEnum.SEM_RECORRENCIA)
                .statusDespesa(StatusDespesaEnum.PAGO)
                .tipoDespesa(TipoDespesaEnum.VARIAVEL)
                .build();
        Assertions.assertEquals(
                "DespesaResponse(id=1, dataCadastro=2023-08-18, horaCadastro=07:55, dataPagamento=2023-08-18, " +
                        "dataAgendamento=null, descricao=Gasolina carro, valor=100.0, observacao=Sem recorrências, " +
                        "qtdRecorrencias=0, statusDespesa=PAGO, tipoDespesa=VARIAVEL, tipoRecorrencia=SEM_RECORRENCIA)",
                despesaResponse.toString()
        );
    }
}
