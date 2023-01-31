package br.akd.svc.akadia.models.entities.site.fiscal;

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
@Table(name = "tb_nfse_config")
public class NfseConfigEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long proximoNumeroProducao;
    private Long proximoNumeroHomologacao;
    private Integer serieProducao;
    private Integer serieHomologacao;

    @OneToOne(targetEntity = ConfigFiscalEmpresaEntity.class)
    @JoinColumn(name = "id_cfg_fiscal")
    ConfigFiscalEmpresaEntity configFiscalEmpresa;
}