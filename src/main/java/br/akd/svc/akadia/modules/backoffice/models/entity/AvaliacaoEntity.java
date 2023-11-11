package br.akd.svc.akadia.modules.backoffice.models.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@ToString
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
}
