package br.akd.svc.akadia.models.dto.sistema.despesas.response.mock;

import br.akd.svc.akadia.modules.erp.despesas.models.dto.response.page.DespesaPageResponse;

public class DespesaPageResponseBuilder {
    DespesaPageResponseBuilder() {
    }

    DespesaPageResponse despesaPageResponse;

    public static DespesaPageResponseBuilder builder() {
        DespesaPageResponseBuilder builder = new DespesaPageResponseBuilder();
        builder.despesaPageResponse = new DespesaPageResponse();
        builder.despesaPageResponse.setEmpty(true);
        builder.despesaPageResponse.setFirst(true);
        builder.despesaPageResponse.setLast(true);
        builder.despesaPageResponse.setNumber(0);
        builder.despesaPageResponse.setNumberOfElements(0);
        builder.despesaPageResponse.setPageNumber(0);
        builder.despesaPageResponse.setPageSize(0);
        builder.despesaPageResponse.setPaged(true);
        builder.despesaPageResponse.setUnpaged(false);
        builder.despesaPageResponse.setSize(0);
        builder.despesaPageResponse.setTotalElements(0L);
        builder.despesaPageResponse.setTotalPages(0);
        return builder;
    }

    public DespesaPageResponse build() {
        return this.despesaPageResponse;
    }
}