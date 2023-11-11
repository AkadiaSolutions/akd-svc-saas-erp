package br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.mocks;

import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.dto.response.ExclusaoColaboradorResponse;

import java.time.LocalDate;
import java.time.LocalTime;

public class ExclusaoColaboradorResponseBuilder {
    ExclusaoColaboradorResponseBuilder() {
    }

    ExclusaoColaboradorResponse exclusaoColaboradorResponse;

    public static ExclusaoColaboradorResponseBuilder builder() {
        ExclusaoColaboradorResponseBuilder builder = new ExclusaoColaboradorResponseBuilder();
        builder.exclusaoColaboradorResponse = new ExclusaoColaboradorResponse();
        builder.exclusaoColaboradorResponse.setDataExclusao(LocalDate.of(2023, 2, 13).toString());
        builder.exclusaoColaboradorResponse.setHoraExclusao(LocalTime.of(14, 0, 0).toString());
        builder.exclusaoColaboradorResponse.setExcluido(true);
        return builder;
    }

    public ExclusaoColaboradorResponse build() {
        return exclusaoColaboradorResponse;
    }
}
