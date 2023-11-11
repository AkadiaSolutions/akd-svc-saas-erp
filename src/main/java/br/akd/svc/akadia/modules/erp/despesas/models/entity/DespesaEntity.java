package br.akd.svc.akadia.modules.erp.despesas.models.entity;

import br.akd.svc.akadia.modules.global.entity.ExclusaoEntity;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.entity.ColaboradorEntity;
import br.akd.svc.akadia.modules.web.models.entity.empresa.EmpresaEntity;
import br.akd.svc.akadia.modules.erp.despesas.models.enums.StatusDespesaEnum;
import br.akd.svc.akadia.modules.erp.despesas.models.enums.TipoDespesaEnum;
import br.akd.svc.akadia.modules.erp.despesas.models.enums.TipoRecorrenciaDespesaEnum;
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
