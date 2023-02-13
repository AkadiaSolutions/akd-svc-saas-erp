package br.akd.svc.akadia.models.dto.sistema.colaboradores.mocks;

import br.akd.svc.akadia.models.dto.sistema.colaboradores.FeriasDto;

import java.time.LocalDate;
import java.time.LocalTime;

public class FeriasDtoBuilder {

    FeriasDtoBuilder() {
    }

    FeriasDto feriasDto;

    public static FeriasDtoBuilder builder() {
        FeriasDtoBuilder builder = new FeriasDtoBuilder();
        builder.feriasDto = new FeriasDto();
        builder.feriasDto.setId(1L);
        builder.feriasDto.setDataCadastro(LocalDate.of(2023, 2, 13).toString());
        builder.feriasDto.setHoraCadastro(LocalTime.of(10, 44).toString());
        builder.feriasDto.setTotalDias(30);
        builder.feriasDto.setDataInicio(LocalDate.of(2023, 2, 13).toString());
        builder.feriasDto.setDataFim(LocalDate.of(2023, 3, 13).toString());
        return builder;
    }

    public FeriasDto build() {
        return feriasDto;
    }

}
