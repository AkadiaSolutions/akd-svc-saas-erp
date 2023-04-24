package br.akd.svc.akadia.models.entities.sistema.colaboradores;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_exclusao_colaborador")
public class ExclusaoColaboradorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dataExclusao;

    private String horaExclusao;

    private Boolean excluido;

    @ManyToOne
    @JoinColumn(name = "id_responsavel")
    private ColaboradorEntity responsavelExclusao;
}
