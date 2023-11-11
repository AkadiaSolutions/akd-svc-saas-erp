package br.akd.svc.akadia.modules.backoffice.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CategoriaChamadoEnum {

    DUVIDA_TECNICA (0, "Dúvida técnica"),
    DUVIDA_FINANCEIRA (1, "Dúvida financeira"),
    PROBLEMA_TECNICO (2, "Problema técnico"),
    PROBLEMA_FINANCEIRO (3, "Problema financeiro"),
    SUGESTOES (4, "Sugestões");

    private final int code;
    private final String desc;

}
