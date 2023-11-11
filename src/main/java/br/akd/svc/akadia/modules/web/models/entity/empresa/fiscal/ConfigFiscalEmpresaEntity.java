package br.akd.svc.akadia.modules.web.models.entity.empresa.fiscal;

import br.akd.svc.akadia.modules.web.models.enums.fiscal.OrientacaoDanfeEnum;
import br.akd.svc.akadia.modules.web.models.enums.fiscal.RegimeTributarioEnum;
import lombok.*;

import javax.persistence.*;

@Entity
@ToString
@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToOne(targetEntity = NfeConfigEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private NfeConfigEntity nfeConfig;

    @OneToOne(targetEntity = NfceConfigEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private NfceConfigEntity nfceConfig;

    @OneToOne(targetEntity = NfseConfigEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private NfseConfigEntity nfseConfig;
}
