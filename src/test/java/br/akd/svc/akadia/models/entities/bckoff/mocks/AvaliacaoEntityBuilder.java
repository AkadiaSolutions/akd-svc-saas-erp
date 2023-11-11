package br.akd.svc.akadia.models.entities.bckoff.mocks;

import br.akd.svc.akadia.modules.backoffice.models.entity.AvaliacaoEntity;

public class AvaliacaoEntityBuilder {

    AvaliacaoEntityBuilder(){}

    AvaliacaoEntity avaliacaoEntity;

    public static AvaliacaoEntityBuilder builder() {
        AvaliacaoEntityBuilder builder = new AvaliacaoEntityBuilder();
        builder.avaliacaoEntity = new AvaliacaoEntity();
        builder.avaliacaoEntity.setId(1L);
        builder.avaliacaoEntity.setDescricao("Atendimento ótimo!");
        builder.avaliacaoEntity.setNota(10);
        return builder;
    }

    public AvaliacaoEntity build() {
        return avaliacaoEntity;
    }
}
