package br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.mocks;

import br.akd.svc.akadia.modules.erp.colaboradores.acao.models.dto.response.page.AcaoPageResponse;

import java.util.ArrayList;

public class AcaoPageResponseBuilder {

    AcaoPageResponseBuilder() {
    }

    AcaoPageResponse acaoPageResponse;

    public static AcaoPageResponseBuilder builder() {
        AcaoPageResponseBuilder builder = new AcaoPageResponseBuilder();
        builder.acaoPageResponse = new AcaoPageResponse();
        builder.acaoPageResponse.setContent(new ArrayList<>());
        builder.acaoPageResponse.setEmpty(true);
        builder.acaoPageResponse.setFirst(true);
        builder.acaoPageResponse.setLast(true);
        builder.acaoPageResponse.setNumber(0);
        builder.acaoPageResponse.setNumberOfElements(0);
        builder.acaoPageResponse.setPageNumber(0);
        builder.acaoPageResponse.setPageSize(10);
        builder.acaoPageResponse.setPaged(true);
        builder.acaoPageResponse.setUnpaged(false);
        builder.acaoPageResponse.setSize(10);
        builder.acaoPageResponse.setTotalElements(0L);
        builder.acaoPageResponse.setTotalPages(0);
        return builder;
    }

    public AcaoPageResponse build() {
        return this.acaoPageResponse;
    }

}
