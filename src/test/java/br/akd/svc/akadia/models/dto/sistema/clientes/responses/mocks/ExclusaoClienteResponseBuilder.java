package br.akd.svc.akadia.models.dto.sistema.clientes.responses.mocks;

import br.akd.svc.akadia.models.dto.sistema.clientes.responses.ExclusaoClienteResponse;

public class ExclusaoClienteResponseBuilder {
    ExclusaoClienteResponseBuilder() {
    }

    ExclusaoClienteResponse exclusaoClienteDto;

    public static ExclusaoClienteResponseBuilder builder() {
        ExclusaoClienteResponseBuilder builder = new ExclusaoClienteResponseBuilder();
        builder.exclusaoClienteDto = new ExclusaoClienteResponse();
        builder.exclusaoClienteDto.setExcluido(false);
        builder.exclusaoClienteDto.setDataExclusao(null);
        builder.exclusaoClienteDto.setHoraExclusao(null);
        return builder;
    }

    public ExclusaoClienteResponse build() {
        return this.exclusaoClienteDto;
    }

}
