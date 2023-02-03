package br.akd.svc.akadia.models.dto.site.fiscal.mocks;

import br.akd.svc.akadia.models.dto.site.fiscal.ConfigFiscalEmpresaDto;
import br.akd.svc.akadia.models.enums.site.fiscal.OrientacaoDanfeEnum;
import br.akd.svc.akadia.models.enums.site.fiscal.RegimeTributarioEnum;

public class ConfigFiscalEmpresaDtoBuilder {

    ConfigFiscalEmpresaDtoBuilder(){}
    ConfigFiscalEmpresaDto configFiscalEmpresaDto;

    public static ConfigFiscalEmpresaDtoBuilder builder() {
        ConfigFiscalEmpresaDtoBuilder builder = new ConfigFiscalEmpresaDtoBuilder();
        builder.configFiscalEmpresaDto = new ConfigFiscalEmpresaDto();
        builder.configFiscalEmpresaDto.setId(1L);
        builder.configFiscalEmpresaDto.setDiscriminaImpostos(true);
        builder.configFiscalEmpresaDto.setHabilitaNfe(true);
        builder.configFiscalEmpresaDto.setHabilitaNfce(true);
        builder.configFiscalEmpresaDto.setHabilitaNfse(true);
        builder.configFiscalEmpresaDto.setHabilitaEnvioEmailDestinatario(true);
        builder.configFiscalEmpresaDto.setExibeReciboNaDanfe(true);
        builder.configFiscalEmpresaDto.setCnpjContabilidade("11111111000111");
        builder.configFiscalEmpresaDto.setSenhaCertificadoDigital("123456");
        builder.configFiscalEmpresaDto.setOrientacaoDanfeEnum(OrientacaoDanfeEnum.LANDSCAPE);
        builder.configFiscalEmpresaDto.setRegimeTributarioEnum(RegimeTributarioEnum.SIMPLES_NACIONAL);
        builder.configFiscalEmpresaDto.setCertificadoDigital(new byte[]{});
        builder.configFiscalEmpresaDto.setNfeConfig(NfeConfigDtoBuilder.builder().build());
        builder.configFiscalEmpresaDto.setNfceConfig(NfceConfigDtoBuilder.builder().build());
        builder.configFiscalEmpresaDto.setNfseConfig(NfseConfigDtoBuilder.builder().build());
        return builder;
    }

    public ConfigFiscalEmpresaDto build() {
        return configFiscalEmpresaDto;
    }

}
