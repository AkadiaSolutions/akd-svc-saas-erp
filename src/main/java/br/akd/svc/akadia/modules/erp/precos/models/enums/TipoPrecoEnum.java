package br.akd.svc.akadia.modules.erp.precos.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoPrecoEnum {
    VAREJO (1, "Varejo"),
    ATACADO (2, "Atacado");

    private final int code;
    private final String desc;
}
