package br.akd.svc.akadia.modules.web.models.entity.empresa.mocks;

import br.akd.svc.akadia.modules.web.models.entity.empresa.CriaEmpresaResponse;

public class CriaEmpresaResponseBuilder {

    CriaEmpresaResponseBuilder() {
    }

    CriaEmpresaResponse criaEmpresaResponse;

    public static CriaEmpresaResponseBuilder builder() {
        CriaEmpresaResponseBuilder builder = new CriaEmpresaResponseBuilder();
        builder.criaEmpresaResponse = new CriaEmpresaResponse();
        builder.criaEmpresaResponse.setIdClienteEmpresa(1L);
        builder.criaEmpresaResponse.setColaboradorCriado(null);
        return builder;
    }

    public CriaEmpresaResponse build() {
        return criaEmpresaResponse;
    }

}
