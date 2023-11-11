package br.akd.svc.akadia.modules.web.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SegmentoEmpresaEnum {

    BATERIA_AUTOMOTIVA (0, "Baterias automotivas");

    private final int code;
    private final String desc;

}
