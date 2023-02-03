package br.akd.svc.akadia.models.entities.site;

import br.akd.svc.akadia.models.entities.global.mocks.EnderecoEntityBuilder;
import br.akd.svc.akadia.models.entities.global.mocks.TelefoneEntityBuilder;
import br.akd.svc.akadia.models.entities.site.fiscal.mocks.ConfigFiscalEmpresaEntityBuilder;
import br.akd.svc.akadia.models.entities.site.mocks.ClienteSistemaEntityBuilder;
import br.akd.svc.akadia.models.entities.site.mocks.EmpresaEntityBuilder;
import br.akd.svc.akadia.models.enums.site.SegmentoEmpresaEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

@SpringBootTest
@DisplayName("Entity: Empresa")
class EmpresaEntityTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "EmpresaEntity(id=1, dataCadastro=2023-02-03, horaCadastro=10:48, nome=Akadia Solutions, razaoSocial=AKADIA LTDA, cnpj=12345678000112, email=akadia@gmail.com, nomeFantasia=Akadia Solutions, inscricaoEstadual=12345667787867, inscricaoMunicipal=12345667787867, nomeResponsavel=Gabriel Lagrota, cpfResponsavel=47153427821, logo=[], segmentoEmpresaEnum=BATERIA_AUTOMOTIVA, telefone=TelefoneEntity(id=1, prefixo=11, numero=979815415, tipoTelefoneEnum=MOVEL_WHATSAPP), clienteSistema=ClienteSistemaEntity(id=1, codigoClienteAsaas=cus_000005113026, dataCadastro=2023-02-03, horaCadastro=10:40, dataNascimento=2023-02-03, email=fulano@gmail.com, nome=Fulano, senha=123, cpf=12345678910, saldo=0.0, plano=PlanoEntity(id=1, codigoAssinaturaAsaas=sub_jaIvjZ8TMlXZ, dataContratacao=2023-02-03, horaContratacao=09:58, tipoPlanoEnum=BASIC, statusPlanoEnum=ATIVO, formaPagamentoSistemaEnum=BOLETO), telefone=TelefoneEntity(id=1, prefixo=11, numero=979815415, tipoTelefoneEnum=MOVEL_WHATSAPP), endereco=EnderecoEntity(id=1, logradouro=Avenida Coronel Manuel Py, numero=583, bairro=Lauzane Paulista, codigoPostal=02442-090, cidade=São Paulo, estadoEnum=SP), cartao=CartaoEntity(id=1, nomePortador=Gabriel, cpfCnpj=47153427821, numero=5162306219378829, ccv=318, mesExpiracao=8, anoExpiracao=2025, tokenCartao=null, ativo=true, bandeiraCartaoEnum=VISA)), configFiscalEmpresa=ConfigFiscalEmpresaEntity(id=1, discriminaImpostos=true, habilitaNfe=true, habilitaNfce=true, habilitaNfse=true, habilitaEnvioEmailDestinatario=true, exibeReciboNaDanfe=true, cnpjContabilidade=11111111000111, senhaCertificadoDigital=123456, orientacaoDanfeEnum=LANDSCAPE, regimeTributarioEnum=SIMPLES_NACIONAL, certificadoDigital=[], nfeConfig=NfeConfigEntity(id=1, proximoNumeroProducao=1, proximoNumeroHomologacao=1, serieProducao=1, serieHomologacao=1), nfceConfig=NfceConfigEntity(id=1, proximoNumeroProducao=1, proximoNumeroHomologacao=1, serieProducao=1, serieHomologacao=1, cscProducao=1, cscHomologacao=1, idTokenProducao=null, idTokenHomologacao=123456), nfseConfig=NfseConfigEntity(id=1, proximoNumeroProducao=1, proximoNumeroHomologacao=1, serieProducao=1, serieHomologacao=1)), endereco=EnderecoEntity(id=1, logradouro=Avenida Coronel Manuel Py, numero=583, bairro=Lauzane Paulista, codigoPostal=02442-090, cidade=São Paulo, estadoEnum=SP))",
                EmpresaEntityBuilder.builder().comChamado().build().toString()
        );

    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        EmpresaEntity empresaEntity = new EmpresaEntity(
                1L,
                LocalDate.of(2023, 2, 3).toString(),
                LocalTime.of(15, 27).toString(),
                "Organizações Tabajara",
                "Tabajara LTDA",
                "12345678000150",
                "tabajara@gmail.com",
                "Organizações Tabajara",
                "821397182936178236",
                "821397182936178236",
                "Augustinho Carrara",
                "98756432140",
                new byte[]{},
                SegmentoEmpresaEnum.BATERIA_AUTOMOTIVA,
                TelefoneEntityBuilder.builder().build(),
                ClienteSistemaEntityBuilder.builder().build(),
                ConfigFiscalEmpresaEntityBuilder.builder().build(),
                EnderecoEntityBuilder.builder().build(),
                new ArrayList<>()
        );

        Assertions.assertEquals(
                "EmpresaEntity(id=1, dataCadastro=2023-02-03, horaCadastro=15:27, nome=Organizações Tabajara, razaoSocial=Tabajara LTDA, cnpj=12345678000150, email=tabajara@gmail.com, nomeFantasia=Organizações Tabajara, inscricaoEstadual=821397182936178236, inscricaoMunicipal=821397182936178236, nomeResponsavel=Augustinho Carrara, cpfResponsavel=98756432140, logo=[], segmentoEmpresaEnum=BATERIA_AUTOMOTIVA, telefone=TelefoneEntity(id=1, prefixo=11, numero=979815415, tipoTelefoneEnum=MOVEL_WHATSAPP), clienteSistema=ClienteSistemaEntity(id=1, codigoClienteAsaas=cus_000005113026, dataCadastro=2023-02-03, horaCadastro=10:40, dataNascimento=2023-02-03, email=fulano@gmail.com, nome=Fulano, senha=123, cpf=12345678910, saldo=0.0, plano=PlanoEntity(id=1, codigoAssinaturaAsaas=sub_jaIvjZ8TMlXZ, dataContratacao=2023-02-03, horaContratacao=09:58, tipoPlanoEnum=BASIC, statusPlanoEnum=ATIVO, formaPagamentoSistemaEnum=BOLETO), telefone=TelefoneEntity(id=1, prefixo=11, numero=979815415, tipoTelefoneEnum=MOVEL_WHATSAPP), endereco=EnderecoEntity(id=1, logradouro=Avenida Coronel Manuel Py, numero=583, bairro=Lauzane Paulista, codigoPostal=02442-090, cidade=São Paulo, estadoEnum=SP), cartao=CartaoEntity(id=1, nomePortador=Gabriel, cpfCnpj=47153427821, numero=5162306219378829, ccv=318, mesExpiracao=8, anoExpiracao=2025, tokenCartao=null, ativo=true, bandeiraCartaoEnum=VISA)), configFiscalEmpresa=ConfigFiscalEmpresaEntity(id=1, discriminaImpostos=true, habilitaNfe=true, habilitaNfce=true, habilitaNfse=true, habilitaEnvioEmailDestinatario=true, exibeReciboNaDanfe=true, cnpjContabilidade=11111111000111, senhaCertificadoDigital=123456, orientacaoDanfeEnum=LANDSCAPE, regimeTributarioEnum=SIMPLES_NACIONAL, certificadoDigital=[], nfeConfig=NfeConfigEntity(id=1, proximoNumeroProducao=1, proximoNumeroHomologacao=1, serieProducao=1, serieHomologacao=1), nfceConfig=NfceConfigEntity(id=1, proximoNumeroProducao=1, proximoNumeroHomologacao=1, serieProducao=1, serieHomologacao=1, cscProducao=1, cscHomologacao=1, idTokenProducao=null, idTokenHomologacao=123456), nfseConfig=NfseConfigEntity(id=1, proximoNumeroProducao=1, proximoNumeroHomologacao=1, serieProducao=1, serieHomologacao=1)), endereco=EnderecoEntity(id=1, logradouro=Avenida Coronel Manuel Py, numero=583, bairro=Lauzane Paulista, codigoPostal=02442-090, cidade=São Paulo, estadoEnum=SP))",
                empresaEntity.toString()
        );

    }

}
