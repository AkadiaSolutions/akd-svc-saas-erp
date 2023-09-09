package br.akd.svc.akadia.models.dto.sistema.produtos.request;

import br.akd.svc.akadia.models.entities.sistema.precos.PrecoEntity;
import br.akd.svc.akadia.models.enums.sistema.produto.TipoProdutoEnum;
import br.akd.svc.akadia.models.enums.sistema.produto.UnidadeComercialEnum;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoRequest {
    private String codigoInterno;
    private String sigla;
    private String marca;
    private String descricao;
    private Integer quantidadeMinima;
    private Integer quantidadeAtacado;
    private Integer codigoNcm;
    private Double pesoUnitario;
    private TipoProdutoEnum tipoProduto;
    private UnidadeComercialEnum unidadeComercial;
    private CategoriaProdutoRequest categoriaProduto;
    private List<PrecoEntity> precos = new ArrayList<>();
}
