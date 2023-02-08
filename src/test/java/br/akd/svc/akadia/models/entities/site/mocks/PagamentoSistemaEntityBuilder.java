package br.akd.svc.akadia.models.entities.site.mocks;

import br.akd.svc.akadia.models.entities.site.PagamentoSistemaEntity;
import br.akd.svc.akadia.models.enums.site.FormaPagamentoSistemaEnum;
import br.akd.svc.akadia.models.enums.site.StatusPagamentoSistemaEnum;

import java.time.LocalDate;
import java.time.LocalTime;

public class PagamentoSistemaEntityBuilder {

    PagamentoSistemaEntityBuilder(){}
    PagamentoSistemaEntity pagamentoSistemaEntity;

    public static PagamentoSistemaEntityBuilder builder() {
        PagamentoSistemaEntityBuilder builder = new PagamentoSistemaEntityBuilder();
        builder.pagamentoSistemaEntity = new PagamentoSistemaEntity();
        builder.pagamentoSistemaEntity.setId(1L);
        builder.pagamentoSistemaEntity.setDataCadastro(LocalDate.of(2023, 2, 3).toString());
        builder.pagamentoSistemaEntity.setHoraCadastro(LocalTime.of(10, 57).toString());
        builder.pagamentoSistemaEntity.setDataPagamento(LocalDate.of(2023, 2, 3).toString());
        builder.pagamentoSistemaEntity.setHoraPagamento(LocalTime.of(10, 57).toString());
        builder.pagamentoSistemaEntity.setCodigoPagamentoAsaas("pay_5498745402963061");
        builder.pagamentoSistemaEntity.setValor(650.0);
        builder.pagamentoSistemaEntity.setValorLiquido(640.0);
        builder.pagamentoSistemaEntity.setDescricao("Assinatura de plano Basic");
        builder.pagamentoSistemaEntity.setDataVencimento("2023-02-03");
        builder.pagamentoSistemaEntity.setFormaPagamentoSistemaEnum(FormaPagamentoSistemaEnum.CREDIT_CARD);
        builder.pagamentoSistemaEntity.setStatusPagamentoSistemaEnum(StatusPagamentoSistemaEnum.APROVADO);
        builder.pagamentoSistemaEntity.setCartao(null);
        builder.pagamentoSistemaEntity.setClienteSistema(null);
        return builder;
    }

    public PagamentoSistemaEntity build() {
        return pagamentoSistemaEntity;
    }

}
