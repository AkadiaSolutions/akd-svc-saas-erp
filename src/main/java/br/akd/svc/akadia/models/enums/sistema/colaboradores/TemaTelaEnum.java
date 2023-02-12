package br.akd.svc.akadia.models.enums.sistema.colaboradores;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TemaTelaEnum {

    TELA_CLARA (0, "Tela clara"),
    TELA_ESCURA (1, "Tela escura");

    private final int code;
    private final String desc;

}
