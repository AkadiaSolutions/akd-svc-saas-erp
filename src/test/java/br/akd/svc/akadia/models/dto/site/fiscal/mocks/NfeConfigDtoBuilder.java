package br.akd.svc.akadia.models.dto.site.fiscal.mocks;

import br.akd.svc.akadia.models.dto.site.fiscal.NfeConfigDto;

public class NfeConfigDtoBuilder {

    NfeConfigDtoBuilder() {
    }

    NfeConfigDto nfeConfigDto;

    public static NfeConfigDtoBuilder builder() {
        NfeConfigDtoBuilder builder = new NfeConfigDtoBuilder();
        builder.nfeConfigDto = new NfeConfigDto();
        builder.nfeConfigDto.setId(1L);
        builder.nfeConfigDto.setProximoNumeroProducao(1L);
        builder.nfeConfigDto.setProximoNumeroHomologacao(1L);
        builder.nfeConfigDto.setSerieProducao(1);
        builder.nfeConfigDto.setSerieHomologacao(1);
        return builder;
    }

    public NfeConfigDto build() {
        return nfeConfigDto;
    }

}
