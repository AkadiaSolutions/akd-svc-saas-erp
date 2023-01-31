package br.akd.svc.akadia.models.enums.site;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusPlanoEnum {

    ATIVO (0, "Ativo"),
    INATIVO (1, "Inativo"),
    PERIODO_DE_TESTES (2, "Período de testes");

    private final int code;
    private final String desc;
}
