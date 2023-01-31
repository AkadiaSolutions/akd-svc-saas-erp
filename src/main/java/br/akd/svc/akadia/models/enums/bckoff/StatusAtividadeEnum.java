package br.akd.svc.akadia.models.enums.bckoff;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusAtividadeEnum {

    ATIVO (0, "Ativo"),
    FERIAS (1, "Férias"),
    INATIVO (2, "Inativo");

    private final int code;
    private final String desc;
}
