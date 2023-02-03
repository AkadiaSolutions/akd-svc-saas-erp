package br.akd.svc.akadia.models.entities.site.fiscal.mocks;

import br.akd.svc.akadia.models.entities.site.fiscal.ConfigFiscalEmpresaEntity;
import br.akd.svc.akadia.models.enums.site.fiscal.OrientacaoDanfeEnum;
import br.akd.svc.akadia.models.enums.site.fiscal.RegimeTributarioEnum;

public class ConfigFiscalEmpresaEntityBuilder {

    ConfigFiscalEmpresaEntityBuilder(){}
    ConfigFiscalEmpresaEntity configFiscalEmpresaEntity;

    public static ConfigFiscalEmpresaEntityBuilder builder() {
        ConfigFiscalEmpresaEntityBuilder builder = new ConfigFiscalEmpresaEntityBuilder();
        builder.configFiscalEmpresaEntity = new ConfigFiscalEmpresaEntity();
        builder.configFiscalEmpresaEntity.setId(1L);
        builder.configFiscalEmpresaEntity.setDiscriminaImpostos(true);
        builder.configFiscalEmpresaEntity.setHabilitaNfe(true);
        builder.configFiscalEmpresaEntity.setHabilitaNfce(true);
        builder.configFiscalEmpresaEntity.setHabilitaNfse(true);
        builder.configFiscalEmpresaEntity.setHabilitaEnvioEmailDestinatario(true);
        builder.configFiscalEmpresaEntity.setExibeReciboNaDanfe(true);
        builder.configFiscalEmpresaEntity.setCnpjContabilidade("11111111000111");
        builder.configFiscalEmpresaEntity.setSenhaCertificadoDigital("123456");
        builder.configFiscalEmpresaEntity.setOrientacaoDanfeEnum(OrientacaoDanfeEnum.LANDSCAPE);
        builder.configFiscalEmpresaEntity.setRegimeTributarioEnum(RegimeTributarioEnum.SIMPLES_NACIONAL);
        builder.configFiscalEmpresaEntity.setCertificadoDigital(new byte[]{});
        builder.configFiscalEmpresaEntity.setNfeConfig(NfeConfigEntityBuilder.builder().build());
        builder.configFiscalEmpresaEntity.setNfceConfig(NfceConfigEntityBuilder.builder().build());
        builder.configFiscalEmpresaEntity.setNfseConfig(NfseConfigEntityBuilder.builder().build());
        return builder;
    }

    public ConfigFiscalEmpresaEntity build() {
        return configFiscalEmpresaEntity;
    }

}
