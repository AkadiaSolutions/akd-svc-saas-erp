package br.akd.svc.akadia.models.entities.bckoff.mocks;

import br.akd.svc.akadia.modules.backoffice.models.entity.AnexoMensagemEntity;

public class AnexoMensagemEntityBuilder {

    AnexoMensagemEntityBuilder(){}
    AnexoMensagemEntity anexoMensagemEntity;

    public static AnexoMensagemEntityBuilder builder() {
        AnexoMensagemEntityBuilder builder = new AnexoMensagemEntityBuilder();
        builder.anexoMensagemEntity = new AnexoMensagemEntity();
        builder.anexoMensagemEntity.setId(1L);
        builder.anexoMensagemEntity.setNome("imagem.png");
        builder.anexoMensagemEntity.setDados(new byte[]{});
        builder.anexoMensagemEntity.setTipo("png");
        return builder;
    }

    public AnexoMensagemEntity build() {
        return anexoMensagemEntity;
    }

}
