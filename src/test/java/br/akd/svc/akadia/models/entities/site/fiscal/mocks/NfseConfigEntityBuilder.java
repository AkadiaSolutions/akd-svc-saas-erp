package br.akd.svc.akadia.models.entities.site.fiscal.mocks;

import br.akd.svc.akadia.models.entities.site.fiscal.NfseConfigEntity;

public class NfseConfigEntityBuilder {

    NfseConfigEntityBuilder() {
    }

    NfseConfigEntity nfseConfigEntity;

    public static NfseConfigEntityBuilder builder() {
        NfseConfigEntityBuilder builder = new NfseConfigEntityBuilder();
        builder.nfseConfigEntity = new NfseConfigEntity();
        builder.nfseConfigEntity.setId(1L);
        builder.nfseConfigEntity.setProximoNumeroProducao(1L);
        builder.nfseConfigEntity.setProximoNumeroHomologacao(1L);
        builder.nfseConfigEntity.setSerieProducao(1);
        builder.nfseConfigEntity.setSerieHomologacao(1);
        return builder;
    }

    public NfseConfigEntity build() {
        return nfseConfigEntity;
    }

}
