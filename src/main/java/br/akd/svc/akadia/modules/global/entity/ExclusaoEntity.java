package br.akd.svc.akadia.modules.global.entity;

import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.entity.ColaboradorEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_exclusao")
public class ExclusaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dataExclusao;

    private String horaExclusao;

    @ManyToOne
    @JoinColumn(name = "id_responsavel")
    private ColaboradorEntity responsavelExclusao;
}
