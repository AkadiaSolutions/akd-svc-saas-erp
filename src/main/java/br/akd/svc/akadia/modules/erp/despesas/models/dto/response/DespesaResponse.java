package br.akd.svc.akadia.modules.erp.despesas.models.dto.response;

import br.akd.svc.akadia.modules.erp.despesas.models.enums.StatusDespesaEnum;
import br.akd.svc.akadia.modules.erp.despesas.models.enums.TipoDespesaEnum;
import br.akd.svc.akadia.modules.erp.despesas.models.enums.TipoRecorrenciaDespesaEnum;
import lombok.*;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DespesaResponse {
    private Long id;
    private String dataCadastro;
    private String horaCadastro;
    private String dataPagamento;
    private String dataAgendamento;
    private String descricao;
    private Double valor;
    private String observacao;
    private Integer qtdRecorrencias;
    private StatusDespesaEnum statusDespesa;
    private TipoDespesaEnum tipoDespesa;
    private TipoRecorrenciaDespesaEnum tipoRecorrencia;
}
