package br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.mocks;

import br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.acesso.AcessoSistemaResponse;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.PermissaoEnum;

import java.util.HashSet;

public class AcessoSistemaResponseBuilder {
    AcessoSistemaResponseBuilder() {
    }

    AcessoSistemaResponse acessoSistemaResponse;

    public static AcessoSistemaResponseBuilder builder() {
        AcessoSistemaResponseBuilder builder = new AcessoSistemaResponseBuilder();
        builder.acessoSistemaResponse = new AcessoSistemaResponse();
        builder.acessoSistemaResponse.setAcessoSistemaAtivo(true);
        builder.acessoSistemaResponse.setPermissaoEnum(PermissaoEnum.LEITURA_AVANCADA_ALTERACAO);
        builder.acessoSistemaResponse.setPrivilegios(new HashSet<>());

        return builder;
    }

    public AcessoSistemaResponse build() {
        return this.acessoSistemaResponse;
    }
}
