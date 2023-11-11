package br.akd.svc.akadia.modules.global.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GrauParentescoEnum {

    PAI (0, "Pai"),
    MAE (1, "Mãe"),
    IRMAO (2, "Irmão"),
    IRMA (3, "Irmã"),
    FILHO (4, "Filho"),
    FILHA (5, "Filha");

    private final int code;
    private final String desc;

}
