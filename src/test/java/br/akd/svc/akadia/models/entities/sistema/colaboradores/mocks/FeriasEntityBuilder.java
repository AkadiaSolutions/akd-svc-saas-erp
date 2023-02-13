package br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks;

import br.akd.svc.akadia.models.entities.sistema.colaboradores.FeriasEntity;

import java.time.LocalDate;
import java.time.LocalTime;

public class FeriasEntityBuilder {

    FeriasEntityBuilder() {
    }

    FeriasEntity feriasEntity;

    public static FeriasEntityBuilder builder() {
        FeriasEntityBuilder builder = new FeriasEntityBuilder();
        builder.feriasEntity = new FeriasEntity();
        builder.feriasEntity.setId(1L);
        builder.feriasEntity.setDataCadastro(LocalDate.of(2023, 2, 13).toString());
        builder.feriasEntity.setHoraCadastro(LocalTime.of(10, 44).toString());
        builder.feriasEntity.setTotalDias(30);
        builder.feriasEntity.setDataInicio(LocalDate.of(2023, 2, 13).toString());
        builder.feriasEntity.setDataFim(LocalDate.of(2023, 3, 13).toString());
        return builder;
    }

    public FeriasEntity build() {
        return feriasEntity;
    }

}
