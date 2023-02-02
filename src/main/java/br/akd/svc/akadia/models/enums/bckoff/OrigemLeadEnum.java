package br.akd.svc.akadia.models.enums.bckoff;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrigemLeadEnum {

    PRE_CADASTRO (0, "Pré-cadastro"),
    MANUAL (1, "Manual");

    private final int code;
    private final String desc;

}
