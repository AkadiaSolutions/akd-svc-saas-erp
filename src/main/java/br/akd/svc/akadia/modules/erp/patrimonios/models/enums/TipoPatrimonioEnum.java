package br.akd.svc.akadia.modules.erp.patrimonios.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoPatrimonioEnum {

    ATIVO (0, "Ativo"),
    A_RECEBER (1, "A receber"),
    PASSIVO (2, "Passivo");

    private final int code;
    private final String desc;
}
