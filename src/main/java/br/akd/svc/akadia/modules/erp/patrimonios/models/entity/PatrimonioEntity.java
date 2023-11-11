package br.akd.svc.akadia.modules.erp.patrimonios.models.entity;

import br.akd.svc.akadia.modules.global.entity.ExclusaoEntity;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.entity.ColaboradorEntity;
import br.akd.svc.akadia.modules.web.models.entity.empresa.EmpresaEntity;
import br.akd.svc.akadia.modules.erp.patrimonios.models.enums.TipoPatrimonioEnum;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_patrimonio")
public class PatrimonioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dataCadastro;
    private String horaCadastro;
    private String dataEntrada;
    private String descricao;
    private Double valor;

    @Enumerated(EnumType.STRING)
    private TipoPatrimonioEnum tipoPatrimonio;

    @OneToOne(targetEntity = ExclusaoEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private ExclusaoEntity exclusao;

    @ManyToOne(targetEntity = ColaboradorEntity.class)
    @JoinColumn(name = "id_colab_responsavel")
    private ColaboradorEntity colaboradorResponsavel;

    @ManyToOne(targetEntity = EmpresaEntity.class)
    @JoinColumn(name = "id_empresa")
    private EmpresaEntity empresa;
}
