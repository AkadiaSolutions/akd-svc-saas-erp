package br.akd.svc.akadia.models.entities.sistema.clientes.mocks;

import br.akd.svc.akadia.models.entities.sistema.clientes.ExclusaoClienteEntity;

import java.time.LocalDate;
import java.time.LocalTime;

public class ExclusaoClienteEntityBuilder {

    ExclusaoClienteEntityBuilder() {
    }

    ExclusaoClienteEntity exclusaoClienteEntity;

    public static ExclusaoClienteEntityBuilder builder() {
        ExclusaoClienteEntityBuilder builder = new ExclusaoClienteEntityBuilder();
        builder.exclusaoClienteEntity = new ExclusaoClienteEntity();
        builder.exclusaoClienteEntity.setId(1L);
        builder.exclusaoClienteEntity.setExcluido(false);
        builder.exclusaoClienteEntity.setDataExclusao(null);
        builder.exclusaoClienteEntity.setHoraExclusao(null);
        builder.exclusaoClienteEntity.setResponsavelExclusao(null);
        return builder;
    }

    public ExclusaoClienteEntityBuilder comExclusao() {
        exclusaoClienteEntity.setExcluido(true);
        exclusaoClienteEntity.setDataExclusao(LocalDate.of(2023, 3, 6).toString());
        exclusaoClienteEntity.setHoraExclusao(LocalTime.of(14, 29).toString());
        return this;
    }

    public ExclusaoClienteEntity build() {
        return this.exclusaoClienteEntity;
    }

}
