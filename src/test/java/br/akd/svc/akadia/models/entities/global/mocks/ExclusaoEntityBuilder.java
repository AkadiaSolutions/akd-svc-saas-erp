package br.akd.svc.akadia.models.entities.global.mocks;

import br.akd.svc.akadia.models.entities.global.ExclusaoEntity;

import java.time.LocalDate;
import java.time.LocalTime;

public class ExclusaoEntityBuilder {

    ExclusaoEntityBuilder() {
    }

    br.akd.svc.akadia.models.entities.global.ExclusaoEntity ExclusaoEntity;

    public static ExclusaoEntityBuilder builder() {
        ExclusaoEntityBuilder builder = new ExclusaoEntityBuilder();
        builder.ExclusaoEntity = new ExclusaoEntity();
        builder.ExclusaoEntity.setId(1L);
        builder.ExclusaoEntity.setDataExclusao(null);
        builder.ExclusaoEntity.setHoraExclusao(null);
        builder.ExclusaoEntity.setResponsavelExclusao(null);
        return builder;
    }

    public ExclusaoEntityBuilder comExclusao() {
        ExclusaoEntity.setDataExclusao(LocalDate.of(2023, 3, 6).toString());
        ExclusaoEntity.setHoraExclusao(LocalTime.of(14, 29).toString());
        return this;
    }

    public ExclusaoEntity build() {
        return this.ExclusaoEntity;
    }

}
