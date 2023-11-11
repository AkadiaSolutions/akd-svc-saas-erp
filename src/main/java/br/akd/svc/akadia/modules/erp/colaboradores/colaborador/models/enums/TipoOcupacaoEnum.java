package br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoOcupacaoEnum {

    TECNICO_INTERNO(0, "Técnico Interno"),
    TECNICO_EXTERNO(1, "Técnico Externo"),
    ATENDENTE(2, "Atendente"),
    GERENTE(3, "Gerente"),
    DIRETOR(4, "Diretor"),
    FINANCEIRO(5, "Financeiro"),
    CONTABIL(5, "Contábil"),
    TECNICO(5, "Técnico"),
    ADMINISTRATIVO(5, "Administrativo"),
    MARKETING(5, "Marketing"),
    TECNICO_TI(5, "Técnico de TI"),
    ADMINISTRADOR(5, "Administrador");

    private final int code;
    private final String desc;
}
