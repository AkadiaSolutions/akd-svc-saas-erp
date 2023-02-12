package br.akd.svc.akadia.models.entities.site.empresa.fiscal.mocks;

import br.akd.svc.akadia.models.entities.site.empresa.fiscal.ConfigFiscalEmpresaEntity;
import br.akd.svc.akadia.models.enums.site.fiscal.OrientacaoDanfeEnum;
import br.akd.svc.akadia.models.enums.site.fiscal.RegimeTributarioEnum;

public class ConfigFiscalEmpresaEntityBuilder {

    ConfigFiscalEmpresaEntityBuilder() {
    }

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

    public ConfigFiscalEmpresaEntityBuilder comNfe() {
        this.configFiscalEmpresaEntity.setNfeConfig(NfeConfigEntityBuilder.builder().build());
        this.configFiscalEmpresaEntity.setHabilitaNfe(true);
        return this;
    }

    public ConfigFiscalEmpresaEntityBuilder comNfce() {
        this.configFiscalEmpresaEntity.setNfceConfig(NfceConfigEntityBuilder.builder().build());
        this.configFiscalEmpresaEntity.setHabilitaNfce(true);
        return this;
    }

    public ConfigFiscalEmpresaEntityBuilder comNfse() {
        this.configFiscalEmpresaEntity.setNfseConfig(NfseConfigEntityBuilder.builder().build());
        this.configFiscalEmpresaEntity.setHabilitaNfse(true);
        return this;
    }

    public ConfigFiscalEmpresaEntity build() {
        return configFiscalEmpresaEntity;
    }

}
