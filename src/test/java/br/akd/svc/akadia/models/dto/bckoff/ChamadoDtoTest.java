package br.akd.svc.akadia.models.dto.bckoff;

import br.akd.svc.akadia.models.dto.bckoff.mocks.AvaliacaoDtoBuilder;
import br.akd.svc.akadia.models.dto.bckoff.mocks.ChamadoDtoBuilder;
import br.akd.svc.akadia.models.dto.bckoff.mocks.ColaboradorInternoDtoBuilder;
import br.akd.svc.akadia.models.dto.site.mocks.EmpresaDtoBuilder;
import br.akd.svc.akadia.models.enums.bckoff.CategoriaChamadoEnum;
import br.akd.svc.akadia.models.enums.bckoff.StatusChamadoEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

@SpringBootTest
@DisplayName("DTO: Chamado")
class ChamadoDtoTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "ChamadoDto(id=null, dataCriacao=2023-02-02, horaCriacao=23:31, ticket=9841243, descricao=Ajuda com login, dataBaixa=2023-02-03, horaBaixa=14:25, categoriaChamadoEnum=PROBLEMA_TECNICO, statusChamadoEnum=FINALIZADO, atendenteResponsavel=ColaboradorInternoDto(id=1, dataCadastro=2023-02-02, horaCadastro=17:10, nome=Gabriel Lagrota, email=gabriellagrota23@gmail.com, cpf=471.534.278-21, acessoSistemaLiberado=true, dataNascimento=1998-07-21, remuneracao=10000.0, tempoFerias=30, entradaEmpresa=01-01-2023, saidaEmpresa=null, cargoEnum=GESTAO, statusAtividadeEnum=ATIVO, telefone=TelefoneDto(id=1, prefixo=11, numero=979815415, tipoTelefoneEnum=MOVEL_WHATSAPP), parentescos=[ParentescoDto(id=1, nome=Heitor Gonçalves Lagrota, dataNascimento=2021-04-11, cpf=588.543.987-21, grauParentescoEnum=FILHO, telefone=TelefoneDto(id=1, prefixo=11, numero=979815415, tipoTelefoneEnum=MOVEL_WHATSAPP))], chamados=[ChamadoDto(id=null, dataCriacao=null, horaCriacao=null, ticket=null, descricao=null, dataBaixa=null, horaBaixa=null, categoriaChamadoEnum=null, statusChamadoEnum=null, atendenteResponsavel=null, empresa=null, avaliacao=null, mensagens=[])]), empresa=EmpresaDto(id=1, dataCadastro=2023-02-03, horaCadastro=10:48, nome=Akadia Solutions, razaoSocial=AKADIA LTDA, cnpj=12345678000112, email=akadia@gmail.com, nomeFantasia=Akadia Solutions, inscricaoEstadual=12345667787867, inscricaoMunicipal=12345667787867, nomeResponsavel=Gabriel Lagrota, cpfResponsavel=47153427821, logo=[], segmentoEmpresaEnum=BATERIA_AUTOMOTIVA, telefone=TelefoneDto(id=1, prefixo=11, numero=979815415, tipoTelefoneEnum=MOVEL_WHATSAPP), clienteSistema=ClienteSistemaDto(id=1, codigoClienteAsaas=cus_000005113026, dataCadastro=2023-02-03, horaCadastro=10:40, dataNascimento=2023-02-03, email=fulano@gmail.com, nome=Fulano, senha=123, cpf=12345678910, saldo=0.0, plano=PlanoDto(id=1, codigoAssinaturaAsaas=sub_jaIvjZ8TMlXZ, dataContratacao=2023-02-03, horaContratacao=09:58, tipoPlanoEnum=BASIC, statusPlanoEnum=ATIVO, formaPagamentoSistemaEnum=BOLETO), telefone=TelefoneDto(id=1, prefixo=11, numero=979815415, tipoTelefoneEnum=MOVEL_WHATSAPP), endereco=EnderecoDto(id=1, logradouro=Avenida Coronel Manuel Py, numero=583, bairro=Lauzane Paulista, codigoPostal=02442-090, cidade=São Paulo, estadoEnum=SP), cartao=CartaoDto(id=1, nomePortador=Gabriel, cpfCnpj=47153427821, numero=5162306219378829, ccv=318, mesExpiracao=8, anoExpiracao=2025, tokenCartao=null, ativo=true, bandeiraCartaoEnum=VISA), pagamentos=[], empresas=[]), configFiscalEmpresa=ConfigFiscalEmpresaDto(id=1, discriminaImpostos=true, habilitaNfe=true, habilitaNfce=true, habilitaNfse=true, habilitaEnvioEmailDestinatario=true, exibeReciboNaDanfe=true, cnpjContabilidade=11111111000111, senhaCertificadoDigital=123456, orientacaoDanfeEnum=LANDSCAPE, regimeTributarioEnum=SIMPLES_NACIONAL, certificadoDigital=[], nfeConfig=NfeConfigDto(id=1, proximoNumeroProducao=1, proximoNumeroHomologacao=1, serieProducao=1, serieHomologacao=1), nfceConfig=NfceConfigDto(id=1, proximoNumeroProducao=1, proximoNumeroHomologacao=1, serieProducao=1, serieHomologacao=1, cscProducao=1, cscHomologacao=1, idTokenProducao=null, idTokenHomologacao=123456), nfseConfig=NfseConfigDto(id=1, proximoNumeroProducao=1, proximoNumeroHomologacao=1, serieProducao=1, serieHomologacao=1)), endereco=EnderecoDto(id=1, logradouro=Avenida Coronel Manuel Py, numero=583, bairro=Lauzane Paulista, codigoPostal=02442-090, cidade=São Paulo, estadoEnum=SP), chamados=[]), avaliacao=AvaliacaoDto(id=1, nota=10, descricao=Atendimento ótimo!), mensagens=[MensagemDto(id=1, dataEnvio=2023-02-02, horaEnvio=23:24, visualizada=true, respondida=true, conteudo=Preciso de suporte com o meu login, caminhoMensagemEnum=CLIENTE_PARA_SUPORTE, anexos=[])])",
                ChamadoDtoBuilder.builder().comMensagens().build().toString());
    }

    @Test
    @DisplayName("Deve testar allArgsConstructor")
    void deveTestarAllArgsConstructor() {

        ChamadoDto chamadoDto = new ChamadoDto(
                1L,
                LocalDate.of(2023, 2, 3).toString(),
                LocalTime.of(15, 16).toString(),
                12345678L,
                "Preciso de ajuda com certificado digital",
                null,
                null,
                CategoriaChamadoEnum.DUVIDA_TECNICA,
                StatusChamadoEnum.EM_ATENDIMENTO,
                ColaboradorInternoDtoBuilder.builder().build(),
                EmpresaDtoBuilder.builder().build(),
                AvaliacaoDtoBuilder.builder().build(),
                new ArrayList<>()
        );

        Assertions.assertEquals(
                "ChamadoDto(id=1, dataCriacao=2023-02-03, horaCriacao=15:16, ticket=12345678, descricao=Preciso de ajuda com certificado digital, dataBaixa=null, horaBaixa=null, categoriaChamadoEnum=DUVIDA_TECNICA, statusChamadoEnum=EM_ATENDIMENTO, atendenteResponsavel=ColaboradorInternoDto(id=1, dataCadastro=2023-02-02, horaCadastro=17:10, nome=Gabriel Lagrota, email=gabriellagrota23@gmail.com, cpf=471.534.278-21, acessoSistemaLiberado=true, dataNascimento=1998-07-21, remuneracao=10000.0, tempoFerias=30, entradaEmpresa=01-01-2023, saidaEmpresa=null, cargoEnum=GESTAO, statusAtividadeEnum=ATIVO, telefone=TelefoneDto(id=1, prefixo=11, numero=979815415, tipoTelefoneEnum=MOVEL_WHATSAPP), parentescos=[], chamados=[]), empresa=EmpresaDto(id=1, dataCadastro=2023-02-03, horaCadastro=10:48, nome=Akadia Solutions, razaoSocial=AKADIA LTDA, cnpj=12345678000112, email=akadia@gmail.com, nomeFantasia=Akadia Solutions, inscricaoEstadual=12345667787867, inscricaoMunicipal=12345667787867, nomeResponsavel=Gabriel Lagrota, cpfResponsavel=47153427821, logo=[], segmentoEmpresaEnum=BATERIA_AUTOMOTIVA, telefone=TelefoneDto(id=1, prefixo=11, numero=979815415, tipoTelefoneEnum=MOVEL_WHATSAPP), clienteSistema=ClienteSistemaDto(id=1, codigoClienteAsaas=cus_000005113026, dataCadastro=2023-02-03, horaCadastro=10:40, dataNascimento=2023-02-03, email=fulano@gmail.com, nome=Fulano, senha=123, cpf=12345678910, saldo=0.0, plano=PlanoDto(id=1, codigoAssinaturaAsaas=sub_jaIvjZ8TMlXZ, dataContratacao=2023-02-03, horaContratacao=09:58, tipoPlanoEnum=BASIC, statusPlanoEnum=ATIVO, formaPagamentoSistemaEnum=BOLETO), telefone=TelefoneDto(id=1, prefixo=11, numero=979815415, tipoTelefoneEnum=MOVEL_WHATSAPP), endereco=EnderecoDto(id=1, logradouro=Avenida Coronel Manuel Py, numero=583, bairro=Lauzane Paulista, codigoPostal=02442-090, cidade=São Paulo, estadoEnum=SP), cartao=CartaoDto(id=1, nomePortador=Gabriel, cpfCnpj=47153427821, numero=5162306219378829, ccv=318, mesExpiracao=8, anoExpiracao=2025, tokenCartao=null, ativo=true, bandeiraCartaoEnum=VISA), pagamentos=[], empresas=[]), configFiscalEmpresa=ConfigFiscalEmpresaDto(id=1, discriminaImpostos=true, habilitaNfe=true, habilitaNfce=true, habilitaNfse=true, habilitaEnvioEmailDestinatario=true, exibeReciboNaDanfe=true, cnpjContabilidade=11111111000111, senhaCertificadoDigital=123456, orientacaoDanfeEnum=LANDSCAPE, regimeTributarioEnum=SIMPLES_NACIONAL, certificadoDigital=[], nfeConfig=NfeConfigDto(id=1, proximoNumeroProducao=1, proximoNumeroHomologacao=1, serieProducao=1, serieHomologacao=1), nfceConfig=NfceConfigDto(id=1, proximoNumeroProducao=1, proximoNumeroHomologacao=1, serieProducao=1, serieHomologacao=1, cscProducao=1, cscHomologacao=1, idTokenProducao=null, idTokenHomologacao=123456), nfseConfig=NfseConfigDto(id=1, proximoNumeroProducao=1, proximoNumeroHomologacao=1, serieProducao=1, serieHomologacao=1)), endereco=EnderecoDto(id=1, logradouro=Avenida Coronel Manuel Py, numero=583, bairro=Lauzane Paulista, codigoPostal=02442-090, cidade=São Paulo, estadoEnum=SP), chamados=[]), avaliacao=AvaliacaoDto(id=1, nota=10, descricao=Atendimento ótimo!), mensagens=[])",
                chamadoDto.toString());
    }

}
