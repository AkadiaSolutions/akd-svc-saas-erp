package br.akd.svc.akadia.models.dto.site.empresa.fiscal;

import br.akd.svc.akadia.models.enums.site.fiscal.OrientacaoDanfeEnum;
import br.akd.svc.akadia.models.enums.site.fiscal.RegimeTributarioEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("DTO: ConfigFiscalEmpresa")
class ConfigFiscalEmpresaDtoTest {
    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        ConfigFiscalEmpresaDto configFiscalEmpresaDto = new ConfigFiscalEmpresaDto(
                1L,
                true,
                false,
                false,
                false,
                true,
                true,
                "12123123000112",
                "123456",
                OrientacaoDanfeEnum.PORTRAIT,
                RegimeTributarioEnum.SIMPLES_NACIONAL_EXCESSO_SUBLIME,
                new byte[]{},
                null,
                null,
                null
        );
        Assertions.assertEquals(
                "ConfigFiscalEmpresaDto(id=1, discriminaImpostos=true, habilitaNfe=false, habilitaNfce=false, habilitaNfse=false, habilitaEnvioEmailDestinatario=true, exibeReciboNaDanfe=true, cnpjContabilidade=12123123000112, senhaCertificadoDigital=123456, orientacaoDanfeEnum=PORTRAIT, regimeTributarioEnum=SIMPLES_NACIONAL_EXCESSO_SUBLIME, certificadoDigital=[], nfeConfig=null, nfceConfig=null, nfseConfig=null)",
                configFiscalEmpresaDto.toString()
        );

    }
}
