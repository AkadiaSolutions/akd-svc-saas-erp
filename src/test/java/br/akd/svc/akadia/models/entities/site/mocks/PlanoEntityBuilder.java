package br.akd.svc.akadia.models.entities.site.mocks;

import br.akd.svc.akadia.models.entities.site.PlanoEntity;
import br.akd.svc.akadia.models.enums.site.FormaPagamentoSistemaEnum;
import br.akd.svc.akadia.models.enums.site.StatusPlanoEnum;
import br.akd.svc.akadia.models.enums.site.TipoPlanoEnum;

import java.time.LocalDate;
import java.time.LocalTime;

public class PlanoEntityBuilder {

    PlanoEntityBuilder() {}

    PlanoEntity planoEntity;

    public static PlanoEntityBuilder builder() {
        PlanoEntityBuilder builder = new PlanoEntityBuilder();
        builder.planoEntity = new PlanoEntity();
        builder.planoEntity.setId(1L);
        builder.planoEntity.setCodigoAssinaturaAsaas("sub_jaIvjZ8TMlXZ");
        builder.planoEntity.setDataContratacao(LocalDate.of(2023, 2, 3).toString());
        builder.planoEntity.setHoraContratacao(LocalTime.of(9, 58).toString());
        builder.planoEntity.setDataVencimento(LocalDate.of(2023, 2, 3).toString());
        builder.planoEntity.setTipoPlanoEnum(TipoPlanoEnum.BASIC);
        builder.planoEntity.setStatusPlanoEnum(StatusPlanoEnum.ATIVO);
        builder.planoEntity.setFormaPagamentoSistemaEnum(FormaPagamentoSistemaEnum.BOLETO);
        return builder;
    }

    public PlanoEntityBuilder planoVencido(Long diasVencido) {
        this.planoEntity.setDataVencimento(LocalDate.now().minusDays(diasVencido).toString());
        return this;
    }

    public PlanoEntityBuilder pagamentoNoCredito() {
        this.planoEntity.setFormaPagamentoSistemaEnum(FormaPagamentoSistemaEnum.CREDIT_CARD);
        return this;
    }

    public PlanoEntity build() {
        return planoEntity;
    }

}
