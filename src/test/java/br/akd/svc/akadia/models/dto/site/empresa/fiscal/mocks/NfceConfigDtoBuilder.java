package br.akd.svc.akadia.models.dto.site.empresa.fiscal.mocks;

import br.akd.svc.akadia.models.dto.site.empresa.fiscal.NfceConfigDto;

public class NfceConfigDtoBuilder {

    NfceConfigDtoBuilder() {
    }

    NfceConfigDto nfceConfigDto;

    public static NfceConfigDtoBuilder builder() {
        NfceConfigDtoBuilder builder = new NfceConfigDtoBuilder();
        builder.nfceConfigDto = new NfceConfigDto();
        builder.nfceConfigDto.setId(1L);
        builder.nfceConfigDto.setProximoNumeroProducao(1L);
        builder.nfceConfigDto.setProximoNumeroHomologacao(1L);
        builder.nfceConfigDto.setSerieProducao(1);
        builder.nfceConfigDto.setSerieHomologacao(1);
        builder.nfceConfigDto.setCscProducao("1");
        builder.nfceConfigDto.setCscHomologacao("1");
        builder.nfceConfigDto.setIdTokenHomologacao(123456L);
        builder.nfceConfigDto.setIdTokenHomologacao(123456L);
        return builder;
    }

    public NfceConfigDto build() {
        return nfceConfigDto;
    }

}
