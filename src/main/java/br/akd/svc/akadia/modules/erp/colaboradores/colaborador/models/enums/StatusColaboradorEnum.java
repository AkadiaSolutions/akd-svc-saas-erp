package br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusColaboradorEnum {

    ATIVO(0, "Ativo"),
    AFASTADO(1, "Afastado"),
    FERIAS(2, "Férias"),
    DISPENSADO(3, "Dispensado"),
    EXCLUIDO(4, "Excluído"),
    FREELANCER(5, "Freelancer");

    private final int code;
    private final String desc;
}