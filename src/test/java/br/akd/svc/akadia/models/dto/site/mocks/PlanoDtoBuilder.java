package br.akd.svc.akadia.models.dto.site.mocks;

import br.akd.svc.akadia.models.dto.site.PlanoDto;
import br.akd.svc.akadia.models.enums.site.FormaPagamentoSistemaEnum;
import br.akd.svc.akadia.models.enums.site.StatusPlanoEnum;
import br.akd.svc.akadia.models.enums.site.TipoPlanoEnum;

import java.time.LocalDate;
import java.time.LocalTime;

public class PlanoDtoBuilder {

    PlanoDtoBuilder() {}

    PlanoDto planoDto;

    public static PlanoDtoBuilder builder() {
        PlanoDtoBuilder builder = new PlanoDtoBuilder();
        builder.planoDto = new PlanoDto();
        builder.planoDto.setId(1L);
        builder.planoDto.setCodigoAssinaturaAsaas("sub_jaIvjZ8TMlXZ");
        builder.planoDto.setDataContratacao(LocalDate.of(2023, 2, 3).toString());
        builder.planoDto.setHoraContratacao(LocalTime.of(9, 58).toString());
        builder.planoDto.setTipoPlanoEnum(TipoPlanoEnum.BASIC);
        builder.planoDto.setStatusPlanoEnum(StatusPlanoEnum.ATIVO);
        builder.planoDto.setFormaPagamentoSistemaEnum(FormaPagamentoSistemaEnum.BOLETO);
        return builder;
    }

    public PlanoDtoBuilder pagamentoNoCredito() {
        this.planoDto.setFormaPagamentoSistemaEnum(FormaPagamentoSistemaEnum.CREDIT_CARD);
        return this;
    }

    public PlanoDto build() {
        return planoDto;
    }

}
