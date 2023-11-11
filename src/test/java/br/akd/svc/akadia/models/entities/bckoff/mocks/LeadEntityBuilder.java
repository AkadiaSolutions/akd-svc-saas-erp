package br.akd.svc.akadia.models.entities.bckoff.mocks;

import br.akd.svc.akadia.modules.backoffice.models.entity.LeadEntity;
import br.akd.svc.akadia.models.entities.global.mocks.TelefoneEntityBuilder;
import br.akd.svc.akadia.modules.backoffice.models.enums.OrigemLeadEnum;

public class LeadEntityBuilder {

    LeadEntityBuilder(){}

    LeadEntity leadEntity;

    public static LeadEntityBuilder builder() {
        LeadEntityBuilder builder = new LeadEntityBuilder();
        builder.leadEntity = new LeadEntity();
        builder.leadEntity.setId(1L);
        builder.leadEntity.setNome("Fulano da Silva");
        builder.leadEntity.setEmail("fulano@gmail.com");
        builder.leadEntity.setOrigemLeadEnum(OrigemLeadEnum.MANUAL);
        builder.leadEntity.setTelefone(TelefoneEntityBuilder.builder().build());
        return builder;
    }

    public LeadEntity build() {
        return leadEntity;
    }

}
