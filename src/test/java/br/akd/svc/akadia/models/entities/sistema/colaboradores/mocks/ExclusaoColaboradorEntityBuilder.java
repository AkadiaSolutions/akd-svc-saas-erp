package br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks;

import br.akd.svc.akadia.models.entities.sistema.colaboradores.ExclusaoColaboradorEntity;

import java.time.LocalDate;
import java.time.LocalTime;

public class ExclusaoColaboradorEntityBuilder {
    ExclusaoColaboradorEntityBuilder() {
    }

    ExclusaoColaboradorEntity exclusaoColaboradorEntity;

    public static ExclusaoColaboradorEntityBuilder builder() {
        ExclusaoColaboradorEntityBuilder builder = new ExclusaoColaboradorEntityBuilder();
        builder.exclusaoColaboradorEntity = new ExclusaoColaboradorEntity();
        builder.exclusaoColaboradorEntity.setId(1L);
        builder.exclusaoColaboradorEntity.setDataExclusao(LocalDate.of(2023, 2, 13).toString());
        builder.exclusaoColaboradorEntity.setHoraExclusao(LocalTime.of(10, 44).toString());
        builder.exclusaoColaboradorEntity.setResponsavelExclusao(null);
        builder.exclusaoColaboradorEntity.setExcluido(true);
        return builder;
    }

    public ExclusaoColaboradorEntity build() {
        return exclusaoColaboradorEntity;
    }
}
