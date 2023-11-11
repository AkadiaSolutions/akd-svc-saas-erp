package br.akd.svc.akadia.modules.erp.colaboradores.acao.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoAcaoEnum {

    CRIACAO(0, "Criação"),

    ALTERACAO(1, "Alteração"),

    REMOCAO(2, "Remoção"),
    REMOCAO_EM_MASSA(3, "Remoção em massa"),

    RELATORIO(4, "Relatório"),

    OUTRO(5, "Outro");

    private final int code;
    private final String desc;

}
