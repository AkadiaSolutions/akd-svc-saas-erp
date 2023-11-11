package br.akd.svc.akadia.models.dto.sistema.despesas.response.mock;

import br.akd.svc.akadia.modules.erp.despesas.models.dto.response.DespesaResponse;
import br.akd.svc.akadia.modules.erp.despesas.models.enums.StatusDespesaEnum;
import br.akd.svc.akadia.modules.erp.despesas.models.enums.TipoDespesaEnum;
import br.akd.svc.akadia.modules.erp.despesas.models.enums.TipoRecorrenciaDespesaEnum;

import java.time.LocalDate;
import java.time.LocalTime;

public class DespesaResponseBuilder {

    DespesaResponseBuilder() {
    }

    DespesaResponse despesaResponse;

    public static DespesaResponseBuilder builder() {
        DespesaResponseBuilder builder = new DespesaResponseBuilder();
        builder.despesaResponse = new DespesaResponse();
        builder.despesaResponse.setId(1L);
        builder.despesaResponse.setDataCadastro(LocalDate.of(2023, 8, 18).toString());
        builder.despesaResponse.setHoraCadastro(LocalTime.of(7, 55).toString());
        builder.despesaResponse.setDataPagamento(LocalDate.of(2023, 8, 18).toString());
        builder.despesaResponse.setDataAgendamento("Pago");
        builder.despesaResponse.setDescricao("Gasolina carro");
        builder.despesaResponse.setValor(100.0);
        builder.despesaResponse.setObservacao("Sem recorrências");
        builder.despesaResponse.setQtdRecorrencias(0);
        builder.despesaResponse.setStatusDespesa(StatusDespesaEnum.PAGO);
        builder.despesaResponse.setTipoDespesa(TipoDespesaEnum.VARIAVEL);
        builder.despesaResponse.setTipoRecorrencia(TipoRecorrenciaDespesaEnum.SEM_RECORRENCIA);
        return builder;
    }

    public DespesaResponse build() {
        return despesaResponse;
    }

}
