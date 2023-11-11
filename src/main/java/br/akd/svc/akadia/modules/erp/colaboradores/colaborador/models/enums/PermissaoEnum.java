package br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PermissaoEnum {
    LEITURA_BASICA(0, "Leitura básica", "LEITURA_BASICA"),
    LEITURA_AVANCADA(1, "Leitura avançada", "LEITURA_AVANCADA"),
    LEITURA_BASICA_ALTERACAO(2, "Leitura básica com alteração", "LEITURA_BASICA_ALTERACAO"),
    LEITURA_AVANCADA_ALTERACAO(3, "Leitura avançada com alteração", "LEITURA_AVANCADA_ALTERACAO");
    private final Integer code;
    private final String desc;
    public final String role;
}
