package br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.mocks;

import br.akd.svc.akadia.modules.erp.colaboradores.acesso.models.dto.response.page.AcessoPageResponse;

import java.util.ArrayList;

public class AcessoPageResponseBuilder {

    AcessoPageResponseBuilder() {
    }

    AcessoPageResponse acessoPageResponse;

    public static AcessoPageResponseBuilder builder() {
        AcessoPageResponseBuilder builder = new AcessoPageResponseBuilder();
        builder.acessoPageResponse = new AcessoPageResponse();
        builder.acessoPageResponse.setContent(new ArrayList<>());
        builder.acessoPageResponse.setEmpty(true);
        builder.acessoPageResponse.setFirst(true);
        builder.acessoPageResponse.setLast(true);
        builder.acessoPageResponse.setNumber(0);
        builder.acessoPageResponse.setNumberOfElements(0);
        builder.acessoPageResponse.setOffset(0L);
        builder.acessoPageResponse.setPageNumber(0);
        builder.acessoPageResponse.setPageSize(10);
        builder.acessoPageResponse.setPaged(true);
        builder.acessoPageResponse.setUnpaged(false);
        builder.acessoPageResponse.setSize(10);
        builder.acessoPageResponse.setTotalElements(0L);
        builder.acessoPageResponse.setTotalPages(0);
        return builder;
    }

    public AcessoPageResponse build() {
        return this.acessoPageResponse;
    }

}
