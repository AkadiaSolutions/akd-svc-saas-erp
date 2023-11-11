package br.akd.svc.akadia.modules.erp.despesas.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusDespesaEnum {

    PAGO (0, "Pago"),
    PENDENTE (1, "Pendente");

    private final int code;
    private final String desc;
}
