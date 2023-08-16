package br.akd.svc.akadia.models.entities.sistema.despesas;

import br.akd.svc.akadia.models.entities.global.ExclusaoEntity;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.ColaboradorEntity;
import br.akd.svc.akadia.models.entities.site.empresa.EmpresaEntity;
import br.akd.svc.akadia.models.enums.sistema.despesas.StatusDespesaEnum;
import br.akd.svc.akadia.models.enums.sistema.despesas.TipoDespesaEnum;
import br.akd.svc.akadia.models.enums.sistema.despesas.TipoRecorrenciaDespesaEnum;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_despesa")
public class DespesaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dataCadastro;
    private String horaCadastro;
    private String dataPagamento;
    private String dataAgendamento;
    private String descricao;
    private Double valor;
    private String observacao;

    @Enumerated(EnumType.STRING)

    private TipoRecorrenciaDespesaEnum tipoRecorrencia;

    @Enumerated(EnumType.STRING)
    private StatusDespesaEnum statusDespesa;

    @Enumerated(EnumType.STRING)
    private TipoDespesaEnum tipoDespesa;

    @OneToOne(targetEntity = ExclusaoEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private ExclusaoEntity exclusao;

    @ManyToOne(targetEntity = ColaboradorEntity.class)
    @JoinColumn(name = "id_colab_responsavel")
    private ColaboradorEntity colaboradorResponsavel;

    @ManyToOne(targetEntity = EmpresaEntity.class)
    @JoinColumn(name = "id_empresa")
    private EmpresaEntity empresa;

    @ToString.Exclude
    @Builder.Default
    @OneToMany(targetEntity = DespesaEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<DespesaEntity> recorrencias = new ArrayList<>();
}
