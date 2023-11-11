package br.akd.svc.akadia.modules.erp.despesas.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoRecorrenciaDespesaEnum {

    SEM_RECORRENCIA(0, "Sem recorrência"),
    PRINCIPAL(1, "Principal"),
    HERDEIRA(2, "Herdeira");

    private final int code;
    private final String desc;

}
