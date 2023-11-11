package br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EscalaEnum {

    INDEFINIDA(0, "Escala indefinida"),
    SEG_A_SEX(1, "5x2"),
    SEG_A_SAB(2, "6x1"),
    DIA_SIM_DIA_NAO(3, "12x36");

    private final int code;
    private final String desc;
}
