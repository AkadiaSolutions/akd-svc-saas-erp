package br.akd.svc.akadia.models.entities.bckoff;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_avaliacao")
public class AvaliacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer nota;

    private String descricao;

    @OneToOne(targetEntity = ChamadoEntity.class)
    @JoinColumn(name = "id_chamado")
    private ChamadoEntity chamado;
}
