package br.akd.svc.akadia.models.entities.site.empresa.fiscal;

import lombok.*;

import javax.persistence.*;

@Entity
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tb_nfe_config")
public class NfeConfigEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long proximoNumeroProducao;
    private Long proximoNumeroHomologacao;
    private Integer serieProducao;
    private Integer serieHomologacao;
}
