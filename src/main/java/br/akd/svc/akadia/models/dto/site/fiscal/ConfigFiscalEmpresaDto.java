package br.akd.svc.akadia.models.dto.site.fiscal;

import br.akd.svc.akadia.models.dto.site.EmpresaDto;
import br.akd.svc.akadia.models.enums.site.fiscal.OrientacaoDanfeEnum;
import br.akd.svc.akadia.models.enums.site.fiscal.RegimeTributarioEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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
    private EmpresaDto empresa;
    private NfeConfigDto nfeConfig;
    private NfceConfigDto nfceConfig;
    private NfseConfigDto nfseConfig;
}
