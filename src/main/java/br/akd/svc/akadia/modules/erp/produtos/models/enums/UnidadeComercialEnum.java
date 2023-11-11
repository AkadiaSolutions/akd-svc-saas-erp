package br.akd.svc.akadia.modules.erp.produtos.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UnidadeComercialEnum {

    UN (1, "Unidade"),
    KG (2, "KG");

    private final int code;
    private final String desc;
}
