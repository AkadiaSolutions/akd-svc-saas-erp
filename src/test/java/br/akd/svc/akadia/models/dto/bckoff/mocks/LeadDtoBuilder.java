package br.akd.svc.akadia.models.dto.bckoff.mocks;

import br.akd.svc.akadia.modules.backoffice.models.dto.LeadDto;
import br.akd.svc.akadia.models.dto.global.mocks.TelefoneDtoBuilder;
import br.akd.svc.akadia.modules.backoffice.models.enums.OrigemLeadEnum;

public class LeadDtoBuilder {

    LeadDtoBuilder(){}

    LeadDto leadDto;

    public static LeadDtoBuilder builder() {
        LeadDtoBuilder builder = new LeadDtoBuilder();
        builder.leadDto = new LeadDto();
        builder.leadDto.setId(1L);
        builder.leadDto.setNome("Fulano da Silva");
        builder.leadDto.setEmail("fulano@gmail.com");
        builder.leadDto.setOrigemLeadEnum(OrigemLeadEnum.MANUAL);
        builder.leadDto.setTelefone(TelefoneDtoBuilder.builder().build());
        return builder;
    }

    public LeadDto build() {
        return leadDto;
    }

}
