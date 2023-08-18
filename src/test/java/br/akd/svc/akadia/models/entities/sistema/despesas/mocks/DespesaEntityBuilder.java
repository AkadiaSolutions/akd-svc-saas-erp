package br.akd.svc.akadia.models.entities.sistema.despesas.mocks;

import br.akd.svc.akadia.models.entities.sistema.despesas.DespesaEntity;
import br.akd.svc.akadia.models.enums.sistema.despesas.StatusDespesaEnum;
import br.akd.svc.akadia.models.enums.sistema.despesas.TipoDespesaEnum;
import br.akd.svc.akadia.models.enums.sistema.despesas.TipoRecorrenciaDespesaEnum;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class DespesaEntityBuilder {

    DespesaEntityBuilder() {
    }

    DespesaEntity despesaEntity;

    public static DespesaEntityBuilder builder() {
        DespesaEntityBuilder builder = new DespesaEntityBuilder();
        builder.despesaEntity = new DespesaEntity();
        builder.despesaEntity.setId(1L);
        builder.despesaEntity.setDataCadastro(LocalDate.of(2023, 8, 18).toString());
        builder.despesaEntity.setHoraCadastro(LocalTime.of(7, 55).toString());
        builder.despesaEntity.setDataPagamento(LocalDate.of(2023, 8, 18).toString());
        builder.despesaEntity.setDataAgendamento(null);
        builder.despesaEntity.setDescricao("Gasolina carro");
        builder.despesaEntity.setValor(100.0);
        builder.despesaEntity.setObservacao("Sem recorrências");
        builder.despesaEntity.setTipoRecorrencia(TipoRecorrenciaDespesaEnum.SEM_RECORRENCIA);
        builder.despesaEntity.setStatusDespesa(StatusDespesaEnum.PAGO);
        builder.despesaEntity.setTipoDespesa(TipoDespesaEnum.VARIAVEL);
        builder.despesaEntity.setExclusao(null);
        builder.despesaEntity.setColaboradorResponsavel(null);
        builder.despesaEntity.setEmpresa(null);
        builder.despesaEntity.setRecorrencias(new ArrayList<>());
        return builder;
    }

    public DespesaEntity build() {
        return despesaEntity;
    }

}
