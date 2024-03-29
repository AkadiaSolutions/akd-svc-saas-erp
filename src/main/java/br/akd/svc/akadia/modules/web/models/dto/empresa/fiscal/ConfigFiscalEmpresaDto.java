package br.akd.svc.akadia.modules.web.models.dto.empresa.fiscal;

import br.akd.svc.akadia.modules.web.models.enums.fiscal.OrientacaoDanfeEnum;
import br.akd.svc.akadia.modules.web.models.enums.fiscal.RegimeTributarioEnum;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ConfigFiscalEmpresaDto {
    private Long id;
    private Boolean discriminaImpostos;
    private Boolean habilitaNfe;
    private Boolean habilitaNfce;
    private Boolean habilitaNfse;
    private Boolean habilitaEnvioEmailDestinatario;
    private Boolean exibeReciboNaDanfe;
    private String cnpjContabilidade;
    private String senhaCertificadoDigital;
    private OrientacaoDanfeEnum orientacaoDanfeEnum;
    private RegimeTributarioEnum regimeTributarioEnum;
    private byte[] certificadoDigital;
    private NfeConfigDto nfeConfig;
    private NfceConfigDto nfceConfig;
    private NfseConfigDto nfseConfig;
}
