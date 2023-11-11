package br.akd.svc.akadia.models.dto.site.mocks;

import br.akd.svc.akadia.modules.web.models.dto.PagamentoSistemaDto;
import br.akd.svc.akadia.modules.web.models.enums.FormaPagamentoSistemaEnum;
import br.akd.svc.akadia.modules.web.models.enums.StatusPagamentoSistemaEnum;

import java.time.LocalDate;
import java.time.LocalTime;

public class PagamentoSistemaDtoBuilder {

    PagamentoSistemaDtoBuilder(){}
    PagamentoSistemaDto pagamentoSistemaDto;

    public static PagamentoSistemaDtoBuilder builder() {
        PagamentoSistemaDtoBuilder builder = new PagamentoSistemaDtoBuilder();
        builder.pagamentoSistemaDto = new PagamentoSistemaDto();
        builder.pagamentoSistemaDto.setId(1L);
        builder.pagamentoSistemaDto.setDataCadastro(LocalDate.of(2023, 2, 3).toString());
        builder.pagamentoSistemaDto.setHoraCadastro(LocalTime.of(10, 57).toString());
        builder.pagamentoSistemaDto.setDataPagamento(LocalDate.of(2023, 2, 3).toString());
        builder.pagamentoSistemaDto.setHoraPagamento(LocalTime.of(10, 57).toString());
        builder.pagamentoSistemaDto.setCodigoPagamentoAsaas("pay_5498745402963061");
        builder.pagamentoSistemaDto.setValor(650.0);
        builder.pagamentoSistemaDto.setValorLiquido(640.0);
        builder.pagamentoSistemaDto.setDescricao("Assinatura de plano Basic");
        builder.pagamentoSistemaDto.setDataVencimento("2023-02-03");
        builder.pagamentoSistemaDto.setFormaPagamentoSistemaEnum(FormaPagamentoSistemaEnum.CREDIT_CARD);
        builder.pagamentoSistemaDto.setStatusPagamentoSistemaEnum(StatusPagamentoSistemaEnum.APROVADO);
        builder.pagamentoSistemaDto.setCartao(null);
        builder.pagamentoSistemaDto.setClienteSistema(null);
        return builder;
    }

    public PagamentoSistemaDto build() {
        return pagamentoSistemaDto;
    }

}
