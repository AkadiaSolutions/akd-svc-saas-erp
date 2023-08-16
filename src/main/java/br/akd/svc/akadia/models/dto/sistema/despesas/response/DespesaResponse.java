package br.akd.svc.akadia.models.dto.sistema.despesas.response;

import br.akd.svc.akadia.models.enums.sistema.despesas.StatusDespesaEnum;
import br.akd.svc.akadia.models.enums.sistema.despesas.TipoDespesaEnum;
import br.akd.svc.akadia.models.enums.sistema.despesas.TipoRecorrenciaDespesaEnum;
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
