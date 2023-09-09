package br.akd.svc.akadia.models.enums.sistema.produto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UnidadeComercialEnum {

    UN (0, "Unidade"),
    KG (1, "KG");

    private final int code;
    private final String desc;
}
