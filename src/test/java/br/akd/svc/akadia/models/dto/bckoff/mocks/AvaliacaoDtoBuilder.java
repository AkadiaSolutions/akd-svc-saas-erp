package br.akd.svc.akadia.models.dto.bckoff.mocks;

import br.akd.svc.akadia.models.dto.bckoff.AvaliacaoDto;

public class AvaliacaoDtoBuilder {

    AvaliacaoDtoBuilder(){}

    AvaliacaoDto avaliacaoDto;

    public static AvaliacaoDtoBuilder builder() {
        AvaliacaoDtoBuilder builder = new AvaliacaoDtoBuilder();
        builder.avaliacaoDto = new AvaliacaoDto();
        builder.avaliacaoDto.setId(1L);
        builder.avaliacaoDto.setDescricao("Atendimento ótimo!");
        builder.avaliacaoDto.setNota(10);
        return builder;
    }

    public AvaliacaoDto build() {
        return avaliacaoDto;
    }
}
