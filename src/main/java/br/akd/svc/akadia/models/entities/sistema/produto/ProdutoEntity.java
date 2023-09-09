package br.akd.svc.akadia.models.entities.sistema.produto;

import br.akd.svc.akadia.models.entities.sistema.colaboradores.ColaboradorEntity;
import br.akd.svc.akadia.models.entities.sistema.compras.CompraEntity;
import br.akd.svc.akadia.models.entities.sistema.precos.PrecoEntity;
import br.akd.svc.akadia.models.entities.site.empresa.EmpresaEntity;
import br.akd.svc.akadia.models.enums.sistema.produto.TipoProdutoEnum;
import br.akd.svc.akadia.models.enums.sistema.produto.UnidadeComercialEnum;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String dataCadastro;

    @Column(nullable = false)
    private String horaCadastro;

    @Column(nullable = false)
    private String codigoInterno;

    @Column(nullable = false)
    private String sigla;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Integer quantidadeMinima;

    @Column(nullable = false)
    private Integer quantidadeAtacado;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private Integer codigoNcm;

    private Double pesoUnitario;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoProdutoEnum tipoProduto;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UnidadeComercialEnum unidadeComercial;

    @ManyToOne(targetEntity = CategoriaProdutoEntity.class)
    @JoinColumn(name = "id_categoria_produto")
    private CategoriaProdutoEntity categoriaProduto;

    @ToString.Exclude
    @Builder.Default
    @Column(nullable = false)
    @OneToMany(targetEntity = CompraEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<PrecoEntity> precos = new ArrayList<>();

    @ToString.Exclude
    @Builder.Default
    @Column(nullable = false)
    @OneToMany(targetEntity = CompraEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<CompraEntity> historicoCompras = new ArrayList<>();

    @ManyToOne(targetEntity = ColaboradorEntity.class)
    @JoinColumn(name = "id_colab_responsavel")
    private ColaboradorEntity colaboradorResponsavel;

    @ManyToOne(targetEntity = EmpresaEntity.class)
    @JoinColumn(name = "id_empresa")
    private EmpresaEntity empresa;
}
