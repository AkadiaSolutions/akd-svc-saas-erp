package br.akd.svc.akadia.modules.erp.produtos.models.entity;

import br.akd.svc.akadia.modules.erp.produtos.models.dto.request.ProdutoRequest;
import br.akd.svc.akadia.modules.global.entity.ExclusaoEntity;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.entity.ColaboradorEntity;
import br.akd.svc.akadia.modules.erp.compras.models.entity.CompraEntity;
import br.akd.svc.akadia.modules.erp.precos.models.entity.PrecoEntity;
import br.akd.svc.akadia.modules.web.models.entity.empresa.EmpresaEntity;
import br.akd.svc.akadia.modules.erp.produtos.models.enums.TipoPesoEnum;
import br.akd.svc.akadia.modules.erp.produtos.models.enums.TipoProdutoEnum;
import br.akd.svc.akadia.modules.erp.produtos.models.enums.UnidadeComercialEnum;
import br.akd.svc.akadia.modules.erp.produtos.models.entity.id.ProdutoId;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ProdutoId.class)
@Table(name = "tb_produto")
@org.hibernate.annotations.Table(appliesTo = "tb_produto", comment = "Tabela de produtos")
public class ProdutoEntity {

    @Id
    @Comment("[PK] - ID do produto")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(name = "produto_id", updatable = false, nullable = false, length = 36)
    private UUID id;

    @Id
    @ManyToOne(targetEntity = EmpresaEntity.class)
    @JoinColumn(name = "produto_empresa", updatable = false, nullable = false)
    @Comment("[PK] - Empresa ao qual o produto pertence")
    private EmpresaEntity empresa;

    @Column(name = "produto_datacadastro", nullable = false, length = 10)
    @Comment("Data em que o produto foi cadastrado (padrão yyyy-MM-dd)")
    private String dataCadastro;

    @Column(name = "produto_horacadastro", nullable = false, length = 18)
    @Comment("Hora em que o produto foi cadastrado (padrão HH:mm:ss.sssssssss)")
    private String horaCadastro;

    @Comment("Código interno de identificação do produto cadastrado pela empresa ao qual ele pertence")
    @Column(name = "produto_codigointerno", nullable = false, length = 10)
    private String codigoInterno;

    @Comment("Sigla de identificação do produto cadastrado pela empresa ao qual ele pertence")
    @Column(name = "produto_sigla", nullable = false, length = 8)
    private String sigla;

    @Comment("Marca do produto")
    @Column(name = "produto_marca", nullable = false, length = 30)
    private String marca;

    @Comment("Descrição e detalhamento do produto")
    @Column(name = "produto_descricao", nullable = false, length = 30)
    private String descricao;

    @Comment("Categoria do produto")
    @Column(name = "produto_categoria", nullable = false, length = 30)
    private String categoria;

    @Comment("Quantidade mínima permitida do produto em estoque")
    @Column(name = "produto_quantidademinima", nullable = false)
    private Integer quantidadeMinima;

    @Comment("Quantidade do produto em que, no ato da venda, definirá se o preço aplicado será de atacado")
    @Column(name = "produto_quantidadeatacado", nullable = false)
    private Integer quantidadeAtacado;

    @Comment("Quantidade atual do produto em estoque")
    @Column(name = "produto_quantidade", nullable = false)
    private Integer quantidade;

    @Comment("Código NCM do produto")
    @Column(name = "produto_codigoncm", nullable = false)
    private Integer codigoNcm;

    @Comment("Código NCM (Nomenclatura comum do Mercosul) do produto")
    @Column(name = "produto_pesounitario", nullable = false)
    private Double pesoUnitario;

    @Enumerated(EnumType.ORDINAL)
    @Comment("Tipo do produto em valores constantes: 1 - BATERIA (Bateria), 2 - SUCATA (Sucata), 3 - OUTRO (Outro)")
    @Column(name = "produto_tipoproduto", nullable = false)
    private TipoProdutoEnum tipoProduto;

    @Enumerated(EnumType.ORDINAL)
    @Comment("Unidade comercial do produto em valores constantes: 1 - UN (Unidade), 2 - KG (Kilo)")
    @Column(name = "produto_unidadecomercial", nullable = false)
    private UnidadeComercialEnum unidadeComercial;

    @Enumerated(EnumType.ORDINAL)
    @Comment("Unidade de medida para peso do produto: 1 - G (Grama), 2 - KG (Kilo)")
    @Column(name = "produto_tipopeso", nullable = false)
    private TipoPesoEnum tipoPeso;

    @Comment("Exclusão do produto. Deve ser nulo caso o produto não tenha sido excluído")
    @OneToOne(targetEntity = ExclusaoEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "produto_exclusao")
    private ExclusaoEntity exclusao;

    @ToString.Exclude
    @Builder.Default
    @Column(name = "produto_precos", nullable = false)
    @Comment("Todos os preços atribuídos ao produto")
    @OneToMany(targetEntity = PrecoEntity.class, orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PrecoEntity> precos = new ArrayList<>();

    @ToString.Exclude
    @Builder.Default
    @Column(name = "produto_compras", nullable = false)
    @Comment("Todas as compras realizadas referentes ao produto")
    @OneToMany(targetEntity = CompraEntity.class, orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CompraEntity> historicoCompras = new ArrayList<>();

    @Comment("Usuário responsável pelo cadastro do produto")
    @ManyToOne(targetEntity = ColaboradorEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_responsavel", nullable = false)
    @ToString.Exclude
    private ColaboradorEntity colaboradorResponsavel;

    @Transient
    public ProdutoEntity buildFromRequest(ProdutoRequest produtoRequest, ColaboradorEntity colaboradorLogado) {
        return ProdutoEntity.builder()
                .empresa(colaboradorLogado.getEmpresa())
                .dataCadastro(LocalDate.now().toString())
                .horaCadastro(LocalTime.now().toString())
                .codigoInterno(produtoRequest.getCodigoInterno())
                .sigla(produtoRequest.getSigla())
                .marca(produtoRequest.getMarca())
                .descricao(produtoRequest.getDescricao())
                .categoria(produtoRequest.getCategoria())
                .quantidadeMinima(produtoRequest.getQuantidadeMinima())
                .quantidadeAtacado(produtoRequest.getQuantidadeAtacado())
                .quantidade(0)
                .codigoNcm(produtoRequest.getCodigoNcm())
                .pesoUnitario(produtoRequest.getPesoUnitario())
                .tipoProduto(produtoRequest.getTipoProduto())
                .unidadeComercial(produtoRequest.getUnidadeComercial())
                .tipoPeso(produtoRequest.getTipoPeso())
                .precos(new ArrayList<>())
                .historicoCompras(new ArrayList<>())
                .colaboradorResponsavel(colaboradorLogado)
                .build();
    }
}
