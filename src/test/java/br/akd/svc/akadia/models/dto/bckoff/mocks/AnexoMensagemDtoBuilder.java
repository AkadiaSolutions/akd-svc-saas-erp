package br.akd.svc.akadia.models.dto.bckoff.mocks;

import br.akd.svc.akadia.modules.backoffice.models.dto.AnexoMensagemDto;

public class AnexoMensagemDtoBuilder {

    AnexoMensagemDtoBuilder(){}
    AnexoMensagemDto anexoMensagemDto;

    public static AnexoMensagemDtoBuilder builder() {
        AnexoMensagemDtoBuilder builder = new AnexoMensagemDtoBuilder();
        builder.anexoMensagemDto = new AnexoMensagemDto();
        builder.anexoMensagemDto.setId(1L);
        builder.anexoMensagemDto.setNome("imagem.png");
        builder.anexoMensagemDto.setDados(new byte[]{});
        builder.anexoMensagemDto.setTipo("png");
        return builder;
    }

    public AnexoMensagemDto build() {
        return anexoMensagemDto;
    }

}
