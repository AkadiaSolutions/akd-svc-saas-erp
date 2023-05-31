package br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.mocks;

import br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.ColaboradorPageResponse;

import java.util.ArrayList;

public class ColaboradorPageResponseBuilder {
    ColaboradorPageResponseBuilder() {
    }

    ColaboradorPageResponse colaboradorPageResponse;

    public static ColaboradorPageResponseBuilder builder() {
        ColaboradorPageResponseBuilder builder = new ColaboradorPageResponseBuilder();
        builder.colaboradorPageResponse = new ColaboradorPageResponse();
        builder.colaboradorPageResponse.setContent(new ArrayList<>());
        builder.colaboradorPageResponse.setEmpty(true);
        builder.colaboradorPageResponse.setFirst(true);
        builder.colaboradorPageResponse.setLast(true);
        builder.colaboradorPageResponse.setNumber(0);
        builder.colaboradorPageResponse.setNumberOfElements(0);
        builder.colaboradorPageResponse.setOffset(0L);
        builder.colaboradorPageResponse.setPageNumber(0);
        builder.colaboradorPageResponse.setPageSize(10);
        builder.colaboradorPageResponse.setPaged(true);
        builder.colaboradorPageResponse.setUnpaged(false);
        builder.colaboradorPageResponse.setSize(10);
        builder.colaboradorPageResponse.setTotalElements(0L);
        builder.colaboradorPageResponse.setTotalPages(0);
        return builder;
    }

    public ColaboradorPageResponse build() {
        return this.colaboradorPageResponse;
    }
}
