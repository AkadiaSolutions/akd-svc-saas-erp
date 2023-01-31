package br.akd.svc.akadia.models.enums.site;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoPlanoEnum {

    BASIC (0, "Basic"),
    PRO (1, "Pro"),
    CUSTOM (2, "Custom");

    private final int code;
    private final String desc;

}
