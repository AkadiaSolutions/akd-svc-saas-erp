package br.akd.svc.akadia.models.entities.bckoff;

import br.akd.svc.akadia.models.entities.site.empresa.EmpresaEntity;
import br.akd.svc.akadia.models.enums.bckoff.CategoriaChamadoEnum;
import br.akd.svc.akadia.models.enums.bckoff.StatusChamadoEnum;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_chamado")
public class ChamadoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dataCriacao;

    private String horaCriacao;

    private Long ticket;

    private String descricao;

    private String dataBaixa;

    private String horaBaixa;

    @Enumerated(EnumType.STRING)
    private CategoriaChamadoEnum categoriaChamadoEnum;

    @Enumerated(EnumType.STRING)
    private StatusChamadoEnum statusChamadoEnum;

    @ManyToOne(targetEntity = ColaboradorInternoEntity.class)
    @JoinColumn(name = "id_colab_interno")
    private ColaboradorInternoEntity atendenteResponsavel;

    @ManyToOne(targetEntity = EmpresaEntity.class)
    @JoinColumn(name = "id_empresa")
    private EmpresaEntity empresa;

    @OneToOne(targetEntity = AvaliacaoEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private AvaliacaoEntity avaliacao;

    @OneToMany(targetEntity = MensagemEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<MensagemEntity> mensagens = new ArrayList<>();

}
