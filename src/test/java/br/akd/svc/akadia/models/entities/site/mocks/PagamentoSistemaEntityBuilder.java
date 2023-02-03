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
        builder.pagamentoSistemaEntity.setCodigoTransacao(129371283971L);
        builder.pagamentoSistemaEntity.setValor(650.0);
        builder.pagamentoSistemaEntity.setVencimento("2023-02-03");
        builder.pagamentoSistemaEntity.setFormaPagamentoSistemaEnum(FormaPagamentoSistemaEnum.CREDIT_CARD);
        builder.pagamentoSistemaEntity.setStatusPagamentoSistemaEnum(StatusPagamentoSistemaEnum.APROVADO);
        builder.pagamentoSistemaEntity.setCartao(CartaoEntityBuilder.builder().build());
        builder.pagamentoSistemaEntity.setClienteSistema(ClienteSistemaEntityBuilder.builder().build());
        return builder;
    }

    public PagamentoSistemaEntity build() {
        return pagamentoSistemaEntity;
    }

}
