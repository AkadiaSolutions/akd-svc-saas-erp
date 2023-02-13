package br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks;

import br.akd.svc.akadia.models.entities.sistema.colaboradores.PontoEntity;

import java.time.LocalTime;

public class PontoEntityBuilder {

    PontoEntityBuilder() {
    }

    PontoEntity pontoEntity;

    public static PontoEntityBuilder builder() {
        PontoEntityBuilder builder = new PontoEntityBuilder();
        builder.pontoEntity = new PontoEntity();
        builder.pontoEntity.setId(1L);
        builder.pontoEntity.setHoraEntrada(LocalTime.of(9, 0).toString());
        builder.pontoEntity.setHoraSaidaAlmoco(LocalTime.of(12, 0).toString());
        builder.pontoEntity.setHoraEntradaAlmoco(LocalTime.of(13, 0).toString());
        builder.pontoEntity.setHoraSaida(LocalTime.of(18, 0).toString());
        return builder;
    }

    public PontoEntity build() {
        return pontoEntity;
    }

}
