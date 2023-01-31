package br.akd.svc.akadia.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusPagamentoSistemaEnum {

    APROVADO (0, "Aprovado"),
    REPROVADO (1, "Reprovado"),
    PENDENTE (2, "Pendente");

    private final int code;
    private final String desc;

}
