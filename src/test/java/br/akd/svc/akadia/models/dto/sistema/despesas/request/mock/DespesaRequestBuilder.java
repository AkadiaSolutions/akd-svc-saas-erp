package br.akd.svc.akadia.models.dto.sistema.despesas.request.mock;

import br.akd.svc.akadia.modules.erp.despesas.models.dto.request.DespesaRequest;
import br.akd.svc.akadia.modules.erp.despesas.models.enums.StatusDespesaEnum;
import br.akd.svc.akadia.modules.erp.despesas.models.enums.TipoDespesaEnum;

import java.time.LocalDate;

public class DespesaRequestBuilder {

    DespesaRequestBuilder() {
    }

    DespesaRequest despesaRequest;

    public static DespesaRequestBuilder builder() {
        DespesaRequestBuilder builder = new DespesaRequestBuilder();
        builder.despesaRequest = new DespesaRequest();
        builder.despesaRequest.setDataPagamento(LocalDate.of(2023, 8, 18).toString());
        builder.despesaRequest.setDataAgendamento("Pago");
        builder.despesaRequest.setDescricao("Gasolina carro");
        builder.despesaRequest.setValor(100.0);
        builder.despesaRequest.setQtdRecorrencias(0);
        builder.despesaRequest.setStatusDespesa(StatusDespesaEnum.PAGO);
        builder.despesaRequest.setTipoDespesa(TipoDespesaEnum.VARIAVEL);
        return builder;
    }

    public DespesaRequestBuilder comRecorrencia() {
        despesaRequest.setQtdRecorrencias(1);
        return this;
    }

    public DespesaRequest build() {
        return despesaRequest;
    }

}
