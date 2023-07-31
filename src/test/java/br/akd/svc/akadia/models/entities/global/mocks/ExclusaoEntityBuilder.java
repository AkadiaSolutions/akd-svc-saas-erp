package br.akd.svc.akadia.models.entities.global.mocks;

import br.akd.svc.akadia.models.entities.global.ExclusaoEntity;

import java.time.LocalDate;
import java.time.LocalTime;

public class ExclusaoEntityBuilder {

    ExclusaoEntityBuilder() {
    }

    br.akd.svc.akadia.models.entities.global.ExclusaoEntity exclusaoEntity;

    public static ExclusaoEntityBuilder builder() {
        ExclusaoEntityBuilder builder = new ExclusaoEntityBuilder();
        builder.exclusaoEntity = new ExclusaoEntity();
        builder.exclusaoEntity.setId(1L);
        builder.exclusaoEntity.setDataExclusao(null);
        builder.exclusaoEntity.setHoraExclusao(null);
        builder.exclusaoEntity.setResponsavelExclusao(null);
        return builder;
    }

    public ExclusaoEntityBuilder semExclusao() {
        exclusaoEntity = null;
        return this;
    }

    public ExclusaoEntityBuilder comExclusao() {
        exclusaoEntity.setDataExclusao(LocalDate.of(2023, 3, 6).toString());
        exclusaoEntity.setHoraExclusao(LocalTime.of(14, 29).toString());
        return this;
    }

    public ExclusaoEntity build() {
        return this.exclusaoEntity;
    }

}
