package br.akd.svc.akadia.models.enums.sistema.despesas;

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
