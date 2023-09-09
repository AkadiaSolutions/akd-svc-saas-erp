package br.akd.svc.akadia.models.enums.sistema.produto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoProdutoEnum {

    BATERIA (0, "Bateria"),
    SUCATA (1, "Sucata"),
    OUTRO (2, "Outro");

    private final int code;
    private final String desc;
}
