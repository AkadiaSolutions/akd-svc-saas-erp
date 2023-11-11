package br.akd.svc.akadia.modules.erp.patrimonios.models.dto.response;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PatrimonioResponse {
    private Long id;
    private String dataCadastro;
    private String horaCadastro;
    private String dataEntrada;
    private String descricao;
    private Double valor;
    private String tipoPatrimonio;
}
