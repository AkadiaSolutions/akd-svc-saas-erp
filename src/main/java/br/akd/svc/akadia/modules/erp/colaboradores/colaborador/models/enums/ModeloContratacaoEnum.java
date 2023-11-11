package br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ModeloContratacaoEnum {

    CLT(0, "CLT"),
    PJ(1, "PJ"),
    FREELANCER(2, "Freelancer");

    private final int code;
    private final String desc;
}
