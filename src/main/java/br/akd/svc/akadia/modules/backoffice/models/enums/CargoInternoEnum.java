package br.akd.svc.akadia.modules.backoffice.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CargoInternoEnum {

    SUPORTE (0, "Suporte", 1),
    DESENVOLVEDOR (1, "Desenvolvedor", 2),
    MARKETING (2, "Marketing", 2),
    COMERCIAL (3, "Comercial", 2),
    GESTAO (4, "Gestão", 3);

    private final int code;
    private final String desc;
    private final int privilegio;
}
