package br.akd.svc.akadia.modules.erp.produtos.models.dto.response;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoResponse {
    private UUID id;
    private String dataCadastro;
    private String horaCadastro;
    private String codigoInterno;
    private String sigla;
    private String marca;
    private String descricao;
    private String categoria;
    private Integer quantidadeMinima;
    private Integer quantidadeAtacado;
    private Integer quantidade;
    private Integer codigoNcm;
    private Double pesoUnitario;
    private String tipoProduto;
    private String unidadeComercial;
    private String tipoPeso;
    private String nomeColaboradorResponsavel;
}
