package br.akd.svc.akadia.models.dto.sistema.colaboradores.mocks;

import br.akd.svc.akadia.models.dto.sistema.colaboradores.AcessoSistemaDto;

class AcessoSistemaDtoBuilder {

    AcessoSistemaDtoBuilder() {
    }

    AcessoSistemaDto acessoSistemaDto;

    public static AcessoSistemaDtoBuilder builder() {
        AcessoSistemaDtoBuilder builder = new AcessoSistemaDtoBuilder();
        builder.acessoSistemaDto = new AcessoSistemaDto();
        builder.acessoSistemaDto.setId(1L);
        builder.acessoSistemaDto.setAcessoSistemaAtivo(true);
        builder.acessoSistemaDto.setNomeUsuario("admin");
        builder.acessoSistemaDto.setSenha("123");
        builder.acessoSistemaDto.setSenhaCriptografada("1239jd89j1u9tbhg");
        return builder;
    }

    public AcessoSistemaDto build() {
        return acessoSistemaDto;
    }

}
