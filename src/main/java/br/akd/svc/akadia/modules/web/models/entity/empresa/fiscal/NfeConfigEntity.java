package br.akd.svc.akadia.modules.web.models.entity.empresa.fiscal;

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
