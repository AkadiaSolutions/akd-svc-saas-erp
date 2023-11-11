package br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.mocks;

import br.akd.svc.akadia.modules.erp.colaboradores.advertencia.models.dto.response.AdvertenciaPageResponse;

import java.util.ArrayList;

public class AdvertenciaPageResponseBuilder {

    AdvertenciaPageResponseBuilder() {
    }

    AdvertenciaPageResponse advertenciaPageResponse;

    public static AdvertenciaPageResponseBuilder builder() {
        AdvertenciaPageResponseBuilder builder = new AdvertenciaPageResponseBuilder();
        builder.advertenciaPageResponse = new AdvertenciaPageResponse();
        builder.advertenciaPageResponse.setContent(new ArrayList<>());
        builder.advertenciaPageResponse.setEmpty(true);
        builder.advertenciaPageResponse.setFirst(true);
        builder.advertenciaPageResponse.setLast(true);
        builder.advertenciaPageResponse.setNumber(0);
        builder.advertenciaPageResponse.setNumberOfElements(0);
        builder.advertenciaPageResponse.setOffset(0L);
        builder.advertenciaPageResponse.setPageNumber(0);
        builder.advertenciaPageResponse.setPageSize(10);
        builder.advertenciaPageResponse.setPaged(true);
        builder.advertenciaPageResponse.setUnpaged(false);
        builder.advertenciaPageResponse.setSize(10);
        builder.advertenciaPageResponse.setTotalElements(0L);
        builder.advertenciaPageResponse.setTotalPages(0);
        return builder;
    }

    public AdvertenciaPageResponse build() {
        return this.advertenciaPageResponse;
    }

}
