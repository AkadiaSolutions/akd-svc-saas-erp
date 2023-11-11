package br.akd.svc.akadia.models.dto.sistema.clientes.responses.mocks;

import br.akd.svc.akadia.modules.erp.clientes.models.dto.response.page.ClientePageResponse;

public class ClientePageResponseBuilder {
    ClientePageResponseBuilder() {
    }

    ClientePageResponse clientePageResponse;

    public static ClientePageResponseBuilder builder() {
        ClientePageResponseBuilder builder = new ClientePageResponseBuilder();
        builder.clientePageResponse = new ClientePageResponse();
        builder.clientePageResponse.setEmpty(true);
        builder.clientePageResponse.setFirst(true);
        builder.clientePageResponse.setLast(true);
        builder.clientePageResponse.setNumber(0);
        builder.clientePageResponse.setNumberOfElements(0);
        builder.clientePageResponse.setPageNumber(0);
        builder.clientePageResponse.setPageSize(0);
        builder.clientePageResponse.setPaged(true);
        builder.clientePageResponse.setUnpaged(false);
        builder.clientePageResponse.setSize(0);
        builder.clientePageResponse.setTotalElements(0L);
        builder.clientePageResponse.setTotalPages(0);
        return builder;
    }

    public ClientePageResponse build() {
        return this.clientePageResponse;
    }
}
