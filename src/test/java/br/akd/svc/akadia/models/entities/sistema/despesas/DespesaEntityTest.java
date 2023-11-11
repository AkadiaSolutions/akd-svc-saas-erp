package br.akd.svc.akadia.models.entities.sistema.despesas;

import br.akd.svc.akadia.models.entities.sistema.despesas.mocks.DespesaEntityBuilder;
import br.akd.svc.akadia.modules.erp.despesas.models.enums.StatusDespesaEnum;
import br.akd.svc.akadia.modules.erp.despesas.models.enums.TipoDespesaEnum;
import br.akd.svc.akadia.modules.erp.despesas.models.enums.TipoRecorrenciaDespesaEnum;
import br.akd.svc.akadia.modules.erp.despesas.models.entity.DespesaEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Entity: Despesa")
class DespesaEntityTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "DespesaEntity(id=1, dataCadastro=2023-08-18, horaCadastro=07:55, dataPagamento=2023-08-18, " +
                        "dataAgendamento=Pago, descricao=Gasolina carro, valor=100.0, observacao=Sem recorrências, " +
                        "tipoRecorrencia=SEM_RECORRENCIA, statusDespesa=PAGO, tipoDespesa=VARIAVEL, exclusao=null, " +
                        "colaboradorResponsavel=null, empresa=null)",
                DespesaEntityBuilder.builder().build().toString()
        );

    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        DespesaEntity despesaEntity = new DespesaEntity(
                1L,
                LocalDate.of(2023, 8, 18).toString(),
                LocalTime.of(7, 55).toString(),
                LocalDate.of(2023, 8, 18).toString(),
                null,
                "Gasolina carro",
                100.0,
                "Sem recorrências",
                TipoRecorrenciaDespesaEnum.SEM_RECORRENCIA,
                StatusDespesaEnum.PAGO,
                TipoDespesaEnum.VARIAVEL,
                null,
                null,
                null,
                new ArrayList<>()
        );
        Assertions.assertEquals(
                "DespesaEntity(id=1, dataCadastro=2023-08-18, horaCadastro=07:55, dataPagamento=2023-08-18, " +
                        "dataAgendamento=null, descricao=Gasolina carro, valor=100.0, observacao=Sem recorrências, " +
                        "tipoRecorrencia=SEM_RECORRENCIA, statusDespesa=PAGO, tipoDespesa=VARIAVEL, exclusao=null, " +
                        "colaboradorResponsavel=null, empresa=null)",
                despesaEntity.toString()
        );

    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        DespesaEntity despesaEntity = DespesaEntity.builder()
                .id(1L)
                .dataCadastro(LocalDate.of(2023, 8, 18).toString())
                .horaCadastro(LocalTime.of(7, 55).toString())
                .dataPagamento(LocalDate.of(2023, 8, 18).toString())
                .dataAgendamento(LocalDate.of(2023, 8, 18).toString())
                .descricao("Gasolina carro")
                .valor(100.0)
                .observacao("Sem recorrências")
                .tipoRecorrencia(TipoRecorrenciaDespesaEnum.SEM_RECORRENCIA)
                .statusDespesa(StatusDespesaEnum.PAGO)
                .tipoDespesa(TipoDespesaEnum.VARIAVEL)
                .exclusao(null)
                .colaboradorResponsavel(null)
                .empresa(null)
                .recorrencias(new ArrayList<>())
                .build();
        Assertions.assertEquals(
                "DespesaEntity(id=1, dataCadastro=2023-08-18, horaCadastro=07:55, dataPagamento=2023-08-18, " +
                        "dataAgendamento=2023-08-18, descricao=Gasolina carro, valor=100.0, observacao=Sem recorrências, " +
                        "tipoRecorrencia=SEM_RECORRENCIA, statusDespesa=PAGO, tipoDespesa=VARIAVEL, exclusao=null, " +
                        "colaboradorResponsavel=null, empresa=null)",
                despesaEntity.toString()
        );
    }

}
