package br.akd.svc.akadia.modules.web.models.enums.fiscal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RegimeTributarioEnum {

    SIMPLES_NACIONAL (0, "Simples nacional"),
    SIMPLES_NACIONAL_EXCESSO_SUBLIME(1, "Simples nacional - Excesso de sublimite de receita bruta"),
    REGIME_NORMAL(2, "Regime normal");

    private final int code;
    private final String desc;

}
