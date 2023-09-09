package br.akd.svc.akadia.models.dto.sistema.produtos.response;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoResponse {
    private String dataCadastro;
    private String horaCadastro;
    private String codigoInterno;
    private String sigla;
    private String marca;
    private String descricao;
    private Integer quantidadeMinima;
    private Integer quantidadeAtacado;
    private Integer quantidade;
    private Integer codigoNcm;
    private Double pesoUnitario;
    private String tipoProduto;
    private String unidadeComercial;
    private String categoriaProduto;
    private String nomeColaboradorResponsavel;
}
