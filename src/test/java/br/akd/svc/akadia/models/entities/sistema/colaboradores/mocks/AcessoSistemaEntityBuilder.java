package br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks;

import br.akd.svc.akadia.models.entities.sistema.colaboradores.AcessoSistemaEntity;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.PermissaoEnum;

import java.util.HashSet;

public class AcessoSistemaEntityBuilder {

    AcessoSistemaEntityBuilder() {
    }

    AcessoSistemaEntity acessoSistemaEntity;

    public static AcessoSistemaEntityBuilder builder() {
        AcessoSistemaEntityBuilder builder = new AcessoSistemaEntityBuilder();
        builder.acessoSistemaEntity = new AcessoSistemaEntity();
        builder.acessoSistemaEntity.setId(1L);
        builder.acessoSistemaEntity.setAcessoSistemaAtivo(true);
        builder.acessoSistemaEntity.setSenha("123");
        builder.acessoSistemaEntity.setSenhaCriptografada("1239jd89j1u9tbhg");
        builder.acessoSistemaEntity.setPermissaoEnum(PermissaoEnum.LEITURA_AVANCADA_ALTERACAO);
        builder.acessoSistemaEntity.setPrivilegios(new HashSet<>());
        return builder;
    }

    public AcessoSistemaEntity build() {
        return acessoSistemaEntity;
    }

}
