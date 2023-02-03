package br.akd.svc.akadia.models.dto.site;

import br.akd.svc.akadia.models.dto.global.mocks.EnderecoDtoBuilder;
import br.akd.svc.akadia.models.dto.global.mocks.TelefoneDtoBuilder;
import br.akd.svc.akadia.models.dto.site.mocks.CartaoDtoBuilder;
import br.akd.svc.akadia.models.dto.site.mocks.ClienteSistemaDtoBuilder;
import br.akd.svc.akadia.models.dto.site.mocks.PlanoDtoBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

@SpringBootTest
@DisplayName("DTO: ClienteSistema")
class ClienteSistemaDtoTest {
    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "ClienteSistemaDto(id=1, codigoClienteAsaas=cus_000005113026, dataCadastro=2023-02-03, horaCadastro=10:40, dataNascimento=2023-02-03, email=fulano@gmail.com, nome=Fulano, senha=123, cpf=12345678910, saldo=0.0, plano=PlanoDto(id=1, codigoAssinaturaAsaas=sub_jaIvjZ8TMlXZ, dataContratacao=2023-02-03, horaContratacao=09:58, tipoPlanoEnum=BASIC, statusPlanoEnum=ATIVO, formaPagamentoSistemaEnum=BOLETO), telefone=TelefoneDto(id=1, prefixo=11, numero=979815415, tipoTelefoneEnum=MOVEL_WHATSAPP), endereco=EnderecoDto(id=1, logradouro=Avenida Coronel Manuel Py, numero=583, bairro=Lauzane Paulista, codigoPostal=02442-090, cidade=São Paulo, estadoEnum=SP), cartao=CartaoDto(id=1, nomePortador=Gabriel, cpfCnpj=47153427821, numero=5162306219378829, ccv=318, mesExpiracao=8, anoExpiracao=2025, tokenCartao=null, ativo=true, bandeiraCartaoEnum=VISA), pagamentos=[PagamentoSistemaDto(id=1, dataCadastro=2023-02-03, horaCadastro=10:57, codigoTransacao=129371283971, valor=650.0, vencimento=2023-02-03, formaPagamentoSistemaEnum=CREDIT_CARD, statusPagamentoSistemaEnum=APROVADO, cartao=CartaoDto(id=1, nomePortador=Gabriel, cpfCnpj=47153427821, numero=5162306219378829, ccv=318, mesExpiracao=8, anoExpiracao=2025, tokenCartao=null, ativo=true, bandeiraCartaoEnum=VISA), clienteSistema=ClienteSistemaDto(id=1, codigoClienteAsaas=cus_000005113026, dataCadastro=2023-02-03, horaCadastro=10:40, dataNascimento=2023-02-03, email=fulano@gmail.com, nome=Fulano, senha=123, cpf=12345678910, saldo=0.0, plano=PlanoDto(id=1, codigoAssinaturaAsaas=sub_jaIvjZ8TMlXZ, dataContratacao=2023-02-03, horaContratacao=09:58, tipoPlanoEnum=BASIC, statusPlanoEnum=ATIVO, formaPagamentoSistemaEnum=BOLETO), telefone=TelefoneDto(id=1, prefixo=11, numero=979815415, tipoTelefoneEnum=MOVEL_WHATSAPP), endereco=EnderecoDto(id=1, logradouro=Avenida Coronel Manuel Py, numero=583, bairro=Lauzane Paulista, codigoPostal=02442-090, cidade=São Paulo, estadoEnum=SP), cartao=CartaoDto(id=1, nomePortador=Gabriel, cpfCnpj=47153427821, numero=5162306219378829, ccv=318, mesExpiracao=8, anoExpiracao=2025, tokenCartao=null, ativo=true, bandeiraCartaoEnum=VISA), pagamentos=[], empresas=[]))], empresas=[EmpresaDto(id=1, dataCadastro=2023-02-03, horaCadastro=10:48, nome=Akadia Solutions, razaoSocial=AKADIA LTDA, cnpj=12345678000112, email=akadia@gmail.com, nomeFantasia=Akadia Solutions, inscricaoEstadual=12345667787867, inscricaoMunicipal=12345667787867, nomeResponsavel=Gabriel Lagrota, cpfResponsavel=47153427821, logo=[], segmentoEmpresaEnum=BATERIA_AUTOMOTIVA, telefone=TelefoneDto(id=1, prefixo=11, numero=979815415, tipoTelefoneEnum=MOVEL_WHATSAPP), clienteSistema=ClienteSistemaDto(id=1, codigoClienteAsaas=cus_000005113026, dataCadastro=2023-02-03, horaCadastro=10:40, dataNascimento=2023-02-03, email=fulano@gmail.com, nome=Fulano, senha=123, cpf=12345678910, saldo=0.0, plano=PlanoDto(id=1, codigoAssinaturaAsaas=sub_jaIvjZ8TMlXZ, dataContratacao=2023-02-03, horaContratacao=09:58, tipoPlanoEnum=BASIC, statusPlanoEnum=ATIVO, formaPagamentoSistemaEnum=BOLETO), telefone=TelefoneDto(id=1, prefixo=11, numero=979815415, tipoTelefoneEnum=MOVEL_WHATSAPP), endereco=EnderecoDto(id=1, logradouro=Avenida Coronel Manuel Py, numero=583, bairro=Lauzane Paulista, codigoPostal=02442-090, cidade=São Paulo, estadoEnum=SP), cartao=CartaoDto(id=1, nomePortador=Gabriel, cpfCnpj=47153427821, numero=5162306219378829, ccv=318, mesExpiracao=8, anoExpiracao=2025, tokenCartao=null, ativo=true, bandeiraCartaoEnum=VISA), pagamentos=[], empresas=[]), configFiscalEmpresa=ConfigFiscalEmpresaDto(id=1, discriminaImpostos=true, habilitaNfe=true, habilitaNfce=true, habilitaNfse=true, habilitaEnvioEmailDestinatario=true, exibeReciboNaDanfe=true, cnpjContabilidade=11111111000111, senhaCertificadoDigital=123456, orientacaoDanfeEnum=LANDSCAPE, regimeTributarioEnum=SIMPLES_NACIONAL, certificadoDigital=[], nfeConfig=NfeConfigDto(id=1, proximoNumeroProducao=1, proximoNumeroHomologacao=1, serieProducao=1, serieHomologacao=1), nfceConfig=NfceConfigDto(id=1, proximoNumeroProducao=1, proximoNumeroHomologacao=1, serieProducao=1, serieHomologacao=1, cscProducao=1, cscHomologacao=1, idTokenProducao=null, idTokenHomologacao=123456), nfseConfig=NfseConfigDto(id=1, proximoNumeroProducao=1, proximoNumeroHomologacao=1, serieProducao=1, serieHomologacao=1)), endereco=EnderecoDto(id=1, logradouro=Avenida Coronel Manuel Py, numero=583, bairro=Lauzane Paulista, codigoPostal=02442-090, cidade=São Paulo, estadoEnum=SP), chamados=[])])",
                ClienteSistemaDtoBuilder.builder().comEmpresa().comPagamento().build().toString()
        );

    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        ClienteSistemaDto clienteSistemaDto = new ClienteSistemaDto(
                1L,
                "cus_000005113026",
                LocalDate.of(2023, 2, 3).toString(),
                LocalTime.of(10, 40).toString(),
                "2023-02-03",
                "fulano@gmail.com",
                "fulano",
                "123",
                "12345678910",
                0.00,
                PlanoDtoBuilder.builder().build(),
                TelefoneDtoBuilder.builder().build(),
                EnderecoDtoBuilder.builder().build(),
                CartaoDtoBuilder.builder().build(),
                new ArrayList<>(),
                new ArrayList<>()
        );
        Assertions.assertEquals(
                "ClienteSistemaDto(id=1, codigoClienteAsaas=cus_000005113026, dataCadastro=2023-02-03, horaCadastro=10:40, dataNascimento=2023-02-03, email=fulano@gmail.com, nome=fulano, senha=123, cpf=12345678910, saldo=0.0, plano=PlanoDto(id=1, codigoAssinaturaAsaas=sub_jaIvjZ8TMlXZ, dataContratacao=2023-02-03, horaContratacao=09:58, tipoPlanoEnum=BASIC, statusPlanoEnum=ATIVO, formaPagamentoSistemaEnum=BOLETO), telefone=TelefoneDto(id=1, prefixo=11, numero=979815415, tipoTelefoneEnum=MOVEL_WHATSAPP), endereco=EnderecoDto(id=1, logradouro=Avenida Coronel Manuel Py, numero=583, bairro=Lauzane Paulista, codigoPostal=02442-090, cidade=São Paulo, estadoEnum=SP), cartao=CartaoDto(id=1, nomePortador=Gabriel, cpfCnpj=47153427821, numero=5162306219378829, ccv=318, mesExpiracao=8, anoExpiracao=2025, tokenCartao=null, ativo=true, bandeiraCartaoEnum=VISA), pagamentos=[], empresas=[])",
                clienteSistemaDto.toString()
        );

    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        ClienteSistemaDto clienteSistemaDto = ClienteSistemaDto.builder()
                .id(1L)
                .codigoClienteAsaas("cus_000005113026")
                .dataCadastro(LocalDate.of(2023, 2, 3).toString())
                .horaCadastro(LocalTime.of(10, 40).toString())
                .dataNascimento("2023-02-03")
                .email("fulano@gmail.com")
                .nome("fulano")
                .senha("123")
                .cpf("12345678910")
                .saldo(0.00)
                .plano(PlanoDtoBuilder.builder().build())
                .telefone(TelefoneDtoBuilder.builder().build())
                .endereco(EnderecoDtoBuilder.builder().build())
                .cartao(CartaoDtoBuilder.builder().build())
                .pagamentos(new ArrayList<>())
                .empresas(new ArrayList<>())
                .build();

        Assertions.assertEquals(
                "ClienteSistemaDto(id=1, codigoClienteAsaas=cus_000005113026, dataCadastro=2023-02-03, horaCadastro=10:40, dataNascimento=2023-02-03, email=fulano@gmail.com, nome=fulano, senha=123, cpf=12345678910, saldo=0.0, plano=PlanoDto(id=1, codigoAssinaturaAsaas=sub_jaIvjZ8TMlXZ, dataContratacao=2023-02-03, horaContratacao=09:58, tipoPlanoEnum=BASIC, statusPlanoEnum=ATIVO, formaPagamentoSistemaEnum=BOLETO), telefone=TelefoneDto(id=1, prefixo=11, numero=979815415, tipoTelefoneEnum=MOVEL_WHATSAPP), endereco=EnderecoDto(id=1, logradouro=Avenida Coronel Manuel Py, numero=583, bairro=Lauzane Paulista, codigoPostal=02442-090, cidade=São Paulo, estadoEnum=SP), cartao=CartaoDto(id=1, nomePortador=Gabriel, cpfCnpj=47153427821, numero=5162306219378829, ccv=318, mesExpiracao=8, anoExpiracao=2025, tokenCartao=null, ativo=true, bandeiraCartaoEnum=VISA), pagamentos=[], empresas=[])",
                clienteSistemaDto.toString()
        );
    }
}
