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
@Table(name = "tb_nfce_config")
public class NfceConfigEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long proximoNumeroProducao;
    private Long proximoNumeroHomologacao;
    private Integer serieProducao;
    private Integer serieHomologacao;
    private String cscProducao;
    private String cscHomologacao;
    private Long idTokenProducao;
    private Long idTokenHomologacao;
}
