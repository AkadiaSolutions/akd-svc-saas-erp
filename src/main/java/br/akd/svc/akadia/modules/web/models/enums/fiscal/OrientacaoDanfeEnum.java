package br.akd.svc.akadia.modules.web.models.enums.fiscal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrientacaoDanfeEnum {

    PORTRAIT(0, "Retrato"),
    LANDSCAPE(1, "Paisagem");

    private final int code;
    private final String desc;
}
