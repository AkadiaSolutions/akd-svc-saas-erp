package br.akd.svc.akadia.models.dto.sistema.colaboradores.mocks;

import br.akd.svc.akadia.models.dto.sistema.colaboradores.AdvertenciaDto;

import java.time.LocalDate;
import java.time.LocalTime;

public class AdvertenciaDtoBuilder {

    AdvertenciaDtoBuilder() {
    }

    AdvertenciaDto advertenciaDto;

    public static AdvertenciaDtoBuilder builder() {
        AdvertenciaDtoBuilder builder = new AdvertenciaDtoBuilder();
        builder.advertenciaDto = new AdvertenciaDto();
        builder.advertenciaDto.setId(1L);
        builder.advertenciaDto.setDataCadastro(LocalDate.of(2023, 2, 13).toString());
        builder.advertenciaDto.setHoraCadastro(LocalTime.of(10, 44).toString());
        builder.advertenciaDto.setMotivo("Brigou na loja");
        builder.advertenciaDto.setDescricao("Cuspiu no cliente");
        builder.advertenciaDto.setAdvertenciaAssinada(new byte[]{});
        return builder;
    }

    public AdvertenciaDto build() {
        return advertenciaDto;
    }

}
