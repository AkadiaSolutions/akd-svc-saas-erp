package br.akd.svc.akadia.models.dto.sistema.colaboradores.mocks;

import br.akd.svc.akadia.models.dto.sistema.colaboradores.ExpedienteDto;

import java.time.LocalTime;

public class ExpedienteDtoBuilder {

    ExpedienteDtoBuilder() {
    }

    ExpedienteDto expedienteDto;

    public static ExpedienteDtoBuilder builder() {
        ExpedienteDtoBuilder builder = new ExpedienteDtoBuilder();
        builder.expedienteDto = new ExpedienteDto();
        builder.expedienteDto.setId(1L);
        builder.expedienteDto.setHoraEntrada(LocalTime.of(9, 0).toString());
        builder.expedienteDto.setHoraSaidaAlmoco(LocalTime.of(12, 0).toString());
        builder.expedienteDto.setHoraEntradaAlmoco(LocalTime.of(13, 0).toString());
        builder.expedienteDto.setHoraSaida(LocalTime.of(18, 0).toString());
        return builder;
    }

    public ExpedienteDto build() {
        return expedienteDto;
    }

}
