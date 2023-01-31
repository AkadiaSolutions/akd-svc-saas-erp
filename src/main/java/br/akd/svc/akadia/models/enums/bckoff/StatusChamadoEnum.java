package br.akd.svc.akadia.models.enums.bckoff;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusChamadoEnum {

    EM_ABERTO (0, "Em aberto"),
    EM_ATENDIMENTO (1, "Em atendimento"),
    FINALIZADO (2, "Finalizado");

    private final int code;
    private final String desc;
}
