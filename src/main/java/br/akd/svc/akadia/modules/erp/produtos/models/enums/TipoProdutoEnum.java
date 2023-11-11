package br.akd.svc.akadia.modules.erp.produtos.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoProdutoEnum {

    BATERIA (1, "Bateria"),
    SUCATA (2, "Sucata"),
    OUTRO (3, "Outro");

    private final int code;
    private final String desc;
}
