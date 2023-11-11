package br.akd.svc.akadia.modules.web.models.entity.empresa.fiscal.mocks;

import br.akd.svc.akadia.modules.web.models.entity.empresa.fiscal.NfceConfigEntity;

public class NfceConfigEntityBuilder {

    NfceConfigEntityBuilder(){}

    NfceConfigEntity nfceConfigEntity;

    public static NfceConfigEntityBuilder builder() {
        NfceConfigEntityBuilder builder = new NfceConfigEntityBuilder();
        builder.nfceConfigEntity = new NfceConfigEntity();
        builder.nfceConfigEntity.setId(1L);
        builder.nfceConfigEntity.setProximoNumeroProducao(1L);
        builder.nfceConfigEntity.setProximoNumeroHomologacao(1L);
        builder.nfceConfigEntity.setSerieProducao(1);
        builder.nfceConfigEntity.setSerieHomologacao(1);
        builder.nfceConfigEntity.setCscProducao("1");
        builder.nfceConfigEntity.setCscHomologacao("1");
        builder.nfceConfigEntity.setIdTokenHomologacao(123456L);
        builder.nfceConfigEntity.setIdTokenHomologacao(123456L);
        return builder;
    }

    public NfceConfigEntity build() {
        return nfceConfigEntity;
    }

}
