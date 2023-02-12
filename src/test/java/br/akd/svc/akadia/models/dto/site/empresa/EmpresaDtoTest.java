package br.akd.svc.akadia.models.dto.site.empresa;

import br.akd.svc.akadia.models.dto.site.empresa.mocks.EmpresaDtoBuilder;
import br.akd.svc.akadia.models.enums.site.SegmentoEmpresaEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

@SpringBootTest
@DisplayName("DTO: Empresa")
class EmpresaDtoTest {
    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "EmpresaDto(id=1, dataCadastro=2023-02-03, horaCadastro=10:48, nome=Akadia Solutions, " +
                        "razaoSocial=AKADIA LTDA, cnpj=12345678000112, endpoint=akadiasolutions, " +
                        "email=akadia@gmail.com, nomeFantasia=Akadia Solutions, inscricaoEstadual=12345667787867, " +
                        "inscricaoMunicipal=12345667787867, nomeResponsavel=Gabriel Lagrota, " +
                        "cpfResponsavel=47153427821, logo=[], deletada=false, dadosEmpresaDeletada=null, " +
                        "segmentoEmpresaEnum=BATERIA_AUTOMOTIVA, telefone=null, configFiscalEmpresa=null, " +
                        "endereco=null, chamados=[])",
                EmpresaDtoBuilder.builder().build().toString()
        );

    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        EmpresaDto empresaDto = new EmpresaDto(
                1L,
                LocalDate.of(2023, 2, 3).toString(),
                LocalTime.of(15, 27).toString(),
                "Organizações Tabajara",
                "Tabajara LTDA",
                "12345678000150",
                "org-tabajara",
                "tabajara@gmail.com",
                "Organizações Tabajara",
                "821397182936178236",
                "821397182936178236",
                "Augustinho Carrara",
                "98756432140",
                new byte[]{},
                false,
                null,
                SegmentoEmpresaEnum.BATERIA_AUTOMOTIVA,
                null,
                null,
                null,
                new ArrayList<>()
        );

        Assertions.assertEquals(
                "EmpresaDto(id=1, dataCadastro=2023-02-03, horaCadastro=15:27, nome=Organizações Tabajara, " +
                        "razaoSocial=Tabajara LTDA, cnpj=12345678000150, endpoint=org-tabajara, " +
                        "email=tabajara@gmail.com, nomeFantasia=Organizações Tabajara, " +
                        "inscricaoEstadual=821397182936178236, inscricaoMunicipal=821397182936178236, " +
                        "nomeResponsavel=Augustinho Carrara, cpfResponsavel=98756432140, logo=[], deletada=false, " +
                        "dadosEmpresaDeletada=null, segmentoEmpresaEnum=BATERIA_AUTOMOTIVA, telefone=null, " +
                        "configFiscalEmpresa=null, endereco=null, chamados=[])",
                empresaDto.toString()
        );

    }
}
