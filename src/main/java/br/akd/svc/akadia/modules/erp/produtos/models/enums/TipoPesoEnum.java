package br.akd.svc.akadia.modules.erp.produtos.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoPesoEnum {

    G (1, "Grama"),
    KG (2, "Kilo");

    private final int code;
    private final String desc;
}
