package br.akd.svc.akadia.models.enums.site.fiscal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrientacaoDanfeEnum {

    PORTRAIT(0, "Retrado"),
    LANDSCAPE(1, "Paisagem");

    private final int code;
    private final String desc;
}
