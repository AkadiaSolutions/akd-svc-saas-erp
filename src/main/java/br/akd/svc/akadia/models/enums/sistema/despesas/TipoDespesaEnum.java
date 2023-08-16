package br.akd.svc.akadia.models.enums.sistema.despesas;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoDespesaEnum {

    FIXO (0, "Fixo"),
    VARIAVEL (1, "Variável"),
    INVESTIMENTO (2, "Investimento");

    private final int code;
    private final String desc;
}
