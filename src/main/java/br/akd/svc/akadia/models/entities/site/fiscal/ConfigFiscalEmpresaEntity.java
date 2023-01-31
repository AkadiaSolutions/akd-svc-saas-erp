package br.akd.svc.akadia.models.entities.site.fiscal;

import br.akd.svc.akadia.models.entities.site.EmpresaEntity;
import br.akd.svc.akadia.models.enums.site.fiscal.OrientacaoDanfeEnum;
import br.akd.svc.akadia.models.enums.site.fiscal.RegimeTributarioEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Table(name = "tb_cfg_fiscal")
public class ConfigFiscalEmpresaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean discriminaImpostos;

    private Boolean habilitaNfe;
    private Boolean habilitaNfce;
    private Boolean habilitaNfse;
    private Boolean habilitaEnvioEmailDestinatario;
    private Boolean exibeReciboNaDanfe;

    private String cnpjContabilidade;

    private String senhaCertificadoDigital;

    @Enumerated(EnumType.STRING)
    private OrientacaoDanfeEnum orientacaoDanfeEnum;

    @Enumerated(EnumType.STRING)
    private RegimeTributarioEnum regimeTributarioEnum;

    private byte[] certificadoDigital;

    @OneToOne(targetEntity = EmpresaEntity.class)
    @JoinColumn(name = "empresa_id")
    private EmpresaEntity empresa;

    @OneToOne(targetEntity = NfeConfigEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private NfeConfigEntity nfeConfig;

    @OneToOne(targetEntity = NfceConfigEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private NfceConfigEntity nfceConfig;

    @OneToOne(targetEntity = NfseConfigEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private NfseConfigEntity nfseConfig;
}
