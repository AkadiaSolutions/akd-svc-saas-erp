package br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks;

import br.akd.svc.akadia.models.entities.sistema.colaboradores.AcessoSistemaEntity;

public class AcessoSistemaEntityBuilder {

    AcessoSistemaEntityBuilder() {
    }

    AcessoSistemaEntity acessoSistemaEntity;

    public static AcessoSistemaEntityBuilder builder() {
        AcessoSistemaEntityBuilder builder = new AcessoSistemaEntityBuilder();
        builder.acessoSistemaEntity = new AcessoSistemaEntity();
        builder.acessoSistemaEntity.setId(1L);
        builder.acessoSistemaEntity.setAcessoSistemaAtivo(true);
        builder.acessoSistemaEntity.setNomeUsuario("admin");
        builder.acessoSistemaEntity.setSenha("123");
        builder.acessoSistemaEntity.setSenhaCriptografada("1239jd89j1u9tbhg");
        return builder;
    }

    public AcessoSistemaEntity build() {
        return acessoSistemaEntity;
    }

}
