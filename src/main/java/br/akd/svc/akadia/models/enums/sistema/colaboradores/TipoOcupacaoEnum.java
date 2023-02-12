package br.akd.svc.akadia.models.enums.sistema.colaboradores;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoOcupacaoEnum {

    TECNICO_INTERNO(0, "Ativo"),
    TECNICO_EXTERNO(1, "Afastado"),
    ATENDENTE(2, "Férias"),
    GERENTE(3, "Dispensado"),
    DIRETOR(4, "Excluído"),
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
