package br.akd.svc.akadia.models.entities.fiscal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
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

    @OneToOne(targetEntity = ConfigFiscalEmpresaEntity.class)
    @JoinColumn(name = "id_cfg_fiscal")
    ConfigFiscalEmpresaEntity configFiscalEmpresa;
}
