package br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ModeloTrabalhoEnum {

    PRESENCIAL(0, "Presencial"),
    HIBRIDO(1, "Híbrido"),
    HOME_OFFICE(2, "Home office");

    private final int code;
    private final String desc;
}
