package br.akd.svc.akadia.models.dto.sistema.patrimonios.response.mock;

import br.akd.svc.akadia.modules.erp.patrimonios.models.dto.response.page.PatrimonioPageResponse;

public class PatrimonioPageResponseBuilder {
    PatrimonioPageResponseBuilder() {
    }

    PatrimonioPageResponse patrimonioPageResponse;

    public static PatrimonioPageResponseBuilder builder() {
        PatrimonioPageResponseBuilder builder = new PatrimonioPageResponseBuilder();
        builder.patrimonioPageResponse = new PatrimonioPageResponse();
        builder.patrimonioPageResponse.setEmpty(true);
        builder.patrimonioPageResponse.setFirst(true);
        builder.patrimonioPageResponse.setLast(true);
        builder.patrimonioPageResponse.setNumber(0);
        builder.patrimonioPageResponse.setNumberOfElements(0);
        builder.patrimonioPageResponse.setPageNumber(0);
        builder.patrimonioPageResponse.setPageSize(0);
        builder.patrimonioPageResponse.setPaged(true);
        builder.patrimonioPageResponse.setUnpaged(false);
        builder.patrimonioPageResponse.setSize(0);
        builder.patrimonioPageResponse.setTotalElements(0L);
        builder.patrimonioPageResponse.setTotalPages(0);
        return builder;
    }

    public PatrimonioPageResponse build() {
        return this.patrimonioPageResponse;
    }
}
