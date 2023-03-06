package br.akd.svc.akadia.models.dto.sistema.clientes.mocks;

import br.akd.svc.akadia.models.dto.sistema.clientes.ExclusaoClienteDto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ExclusaoClienteDtoBuilder {

    ExclusaoClienteDtoBuilder() {
    }

    ExclusaoClienteDto exclusaoClienteDto;

    public static ExclusaoClienteDtoBuilder builder() {
        ExclusaoClienteDtoBuilder builder = new ExclusaoClienteDtoBuilder();
        builder.exclusaoClienteDto = new ExclusaoClienteDto();
        builder.exclusaoClienteDto.setId(1L);
        builder.exclusaoClienteDto.setExcluido(false);
        builder.exclusaoClienteDto.setDataExclusao(LocalDate.of(2023, 3, 6).toString());
        builder.exclusaoClienteDto.setHoraExclusao(LocalTime.of(14, 36).toString());
        builder.exclusaoClienteDto.setResponsavelExclusao(null);
        return builder;
    }

    public ExclusaoClienteDto build() {
        return this.exclusaoClienteDto;
    }

}
