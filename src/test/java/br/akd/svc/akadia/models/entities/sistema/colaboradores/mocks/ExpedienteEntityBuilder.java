package br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks;

import br.akd.svc.akadia.models.entities.sistema.colaboradores.ExpedienteEntity;

import java.time.LocalTime;

class ExpedienteEntityBuilder {

    ExpedienteEntityBuilder() {
    }

    ExpedienteEntity expedienteEntity;

    public static ExpedienteEntityBuilder builder() {
        ExpedienteEntityBuilder builder = new ExpedienteEntityBuilder();
        builder.expedienteEntity = new ExpedienteEntity();
        builder.expedienteEntity.setId(1L);
        builder.expedienteEntity.setHoraEntrada(LocalTime.of(9, 0).toString());
        builder.expedienteEntity.setHoraSaidaAlmoco(LocalTime.of(12, 0).toString());
        builder.expedienteEntity.setHoraEntradaAlmoco(LocalTime.of(13, 0).toString());
        builder.expedienteEntity.setHoraSaida(LocalTime.of(18, 0).toString());
        return builder;
    }

    public ExpedienteEntity build() {
        return expedienteEntity;
    }

}
