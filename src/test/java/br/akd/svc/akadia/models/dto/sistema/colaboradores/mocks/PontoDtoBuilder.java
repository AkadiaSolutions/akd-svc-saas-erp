package br.akd.svc.akadia.models.dto.sistema.colaboradores.mocks;

import br.akd.svc.akadia.models.dto.sistema.colaboradores.PontoDto;

import java.time.LocalTime;

public class PontoDtoBuilder {

    PontoDtoBuilder() {
    }

    PontoDto pontoDto;

    public static PontoDtoBuilder builder() {
        PontoDtoBuilder builder = new PontoDtoBuilder();
        builder.pontoDto = new PontoDto();
        builder.pontoDto.setId(1L);
        builder.pontoDto.setHoraEntrada(LocalTime.of(9, 0).toString());
        builder.pontoDto.setHoraSaidaAlmoco(LocalTime.of(12, 0).toString());
        builder.pontoDto.setHoraEntradaAlmoco(LocalTime.of(13, 0).toString());
        builder.pontoDto.setHoraSaida(LocalTime.of(18, 0).toString());
        return builder;
    }

    public PontoDto build() {
        return pontoDto;
    }

}
