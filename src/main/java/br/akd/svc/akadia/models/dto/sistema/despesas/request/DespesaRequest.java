package br.akd.svc.akadia.models.dto.sistema.despesas.request;

import br.akd.svc.akadia.models.enums.sistema.despesas.StatusDespesaEnum;
import br.akd.svc.akadia.models.enums.sistema.despesas.TipoDespesaEnum;
import lombok.*;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DespesaRequest {
    private String dataPagamento;
    private String dataAgendamento;
    private String descricao;
    private Double valor;
    private Integer qtdRecorrencias;
    private StatusDespesaEnum statusDespesa;
    private TipoDespesaEnum tipoDespesa;
}
