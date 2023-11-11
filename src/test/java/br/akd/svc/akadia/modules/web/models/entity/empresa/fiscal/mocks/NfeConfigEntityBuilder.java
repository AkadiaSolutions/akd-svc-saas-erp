package br.akd.svc.akadia.modules.web.models.entity.empresa.fiscal.mocks;

import br.akd.svc.akadia.modules.web.models.entity.empresa.fiscal.NfeConfigEntity;

public class NfeConfigEntityBuilder {

    NfeConfigEntityBuilder() {
    }

    NfeConfigEntity nfeConfigEntity;

    public static NfeConfigEntityBuilder builder() {
        NfeConfigEntityBuilder builder = new NfeConfigEntityBuilder();
        builder.nfeConfigEntity = new NfeConfigEntity();
        builder.nfeConfigEntity.setId(1L);
        builder.nfeConfigEntity.setProximoNumeroProducao(1L);
        builder.nfeConfigEntity.setProximoNumeroHomologacao(1L);
        builder.nfeConfigEntity.setSerieProducao(1);
        builder.nfeConfigEntity.setSerieHomologacao(1);
        return builder;
    }

    public NfeConfigEntity build() {
        return nfeConfigEntity;
    }

}
