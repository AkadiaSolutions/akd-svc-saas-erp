package br.akd.svc.akadia.models.dto.site;

import br.akd.svc.akadia.models.enums.site.FormaPagamentoSistemaEnum;
import br.akd.svc.akadia.models.enums.site.StatusPagamentoSistemaEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoSistemaDto {
    private Long id;
    private String dataCadastro;
    private String horaCadastro;
    private String dataPagamento;
    private String horaPagamento;
    private String codigoPagamentoAsaas;
    private Double valor;
    private Double valorLiquido;
    private String descricao;
    private String dataVencimento;
    private FormaPagamentoSistemaEnum formaPagamentoSistemaEnum;
    private StatusPagamentoSistemaEnum statusPagamentoSistemaEnum;
    private CartaoDto cartao;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ClienteSistemaDto clienteSistema;
}