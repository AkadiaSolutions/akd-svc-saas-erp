package br.akd.svc.akadia.models.dto.site;

import br.akd.svc.akadia.models.enums.site.FormaPagamentoSistemaEnum;
import br.akd.svc.akadia.models.enums.site.StatusPagamentoSistemaEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoSistemaDto {
    private Long id;
    private String dataCadastro;
    private String horaCadastro;
    private Long codigoTransacao;
    private Double valor;
    private String vencimento;
    private FormaPagamentoSistemaEnum formaPagamentoSistemaEnum;
    private StatusPagamentoSistemaEnum statusPagamentoSistemaEnum;
    private CartaoDto cartao;
    private ClienteSistemaDto clienteSistema;
}