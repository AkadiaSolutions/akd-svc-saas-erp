package br.akd.svc.akadia.models.entities.bckoff;

import br.akd.svc.akadia.models.entities.bckoff.mocks.AvaliacaoEntityBuilder;
import br.akd.svc.akadia.models.entities.bckoff.mocks.ChamadoEntityBuilder;
import br.akd.svc.akadia.models.entities.bckoff.mocks.ColaboradorInternoEntityBuilder;
import br.akd.svc.akadia.models.entities.site.mocks.EmpresaEntityBuilder;
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
@DisplayName("Entity: Chamado")
class ChamadoEntityTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "ChamadoEntity(id=null, dataCriacao=2023-02-02, horaCriacao=23:31, ticket=9841243, descricao=Ajuda com login, dataBaixa=2023-02-03, horaBaixa=14:25, categoriaChamadoEnum=PROBLEMA_TECNICO, statusChamadoEnum=FINALIZADO, atendenteResponsavel=ColaboradorInternoEntity(id=1, dataCadastro=2023-02-02, horaCadastro=17:10, nome=Gabriel Lagrota, email=gabriellagrota23@gmail.com, cpf=471.534.278-21, acessoSistemaLiberado=true, dataNascimento=1998-07-21, remuneracao=10000.0, tempoFerias=30, entradaEmpresa=01-01-2023, saidaEmpresa=null, cargoEnum=GESTAO, statusAtividadeEnum=ATIVO, telefone=TelefoneEntity(id=1, prefixo=11, numero=979815415, tipoTelefoneEnum=MOVEL_WHATSAPP)), empresa=EmpresaEntity(id=1, dataCadastro=2023-02-03, horaCadastro=10:48, nome=Akadia Solutions, razaoSocial=AKADIA LTDA, cnpj=12345678000112, email=akadia@gmail.com, nomeFantasia=Akadia Solutions, inscricaoEstadual=12345667787867, inscricaoMunicipal=12345667787867, nomeResponsavel=Gabriel Lagrota, cpfResponsavel=47153427821, logo=[], segmentoEmpresaEnum=BATERIA_AUTOMOTIVA, telefone=TelefoneEntity(id=1, prefixo=11, numero=979815415, tipoTelefoneEnum=MOVEL_WHATSAPP), clienteSistema=ClienteSistemaEntity(id=1, codigoClienteAsaas=cus_000005113026, dataCadastro=2023-02-03, horaCadastro=10:40, dataNascimento=2023-02-03, email=fulano@gmail.com, nome=Fulano, senha=123, cpf=12345678910, saldo=0.0, plano=PlanoEntity(id=1, codigoAssinaturaAsaas=sub_jaIvjZ8TMlXZ, dataContratacao=2023-02-03, horaContratacao=09:58, tipoPlanoEnum=BASIC, statusPlanoEnum=ATIVO, formaPagamentoSistemaEnum=BOLETO), telefone=TelefoneEntity(id=1, prefixo=11, numero=979815415, tipoTelefoneEnum=MOVEL_WHATSAPP), endereco=EnderecoEntity(id=1, logradouro=Avenida Coronel Manuel Py, numero=583, bairro=Lauzane Paulista, codigoPostal=02442-090, cidade=São Paulo, estadoEnum=SP), cartao=CartaoEntity(id=1, nomePortador=Gabriel, cpfCnpj=47153427821, numero=5162306219378829, ccv=318, mesExpiracao=8, anoExpiracao=2025, tokenCartao=null, ativo=true, bandeiraCartaoEnum=VISA)), configFiscalEmpresa=ConfigFiscalEmpresaEntity(id=1, discriminaImpostos=true, habilitaNfe=true, habilitaNfce=true, habilitaNfse=true, habilitaEnvioEmailDestinatario=true, exibeReciboNaDanfe=true, cnpjContabilidade=11111111000111, senhaCertificadoDigital=123456, orientacaoDanfeEnum=LANDSCAPE, regimeTributarioEnum=SIMPLES_NACIONAL, certificadoDigital=[], nfeConfig=NfeConfigEntity(id=1, proximoNumeroProducao=1, proximoNumeroHomologacao=1, serieProducao=1, serieHomologacao=1), nfceConfig=NfceConfigEntity(id=1, proximoNumeroProducao=1, proximoNumeroHomologacao=1, serieProducao=1, serieHomologacao=1, cscProducao=1, cscHomologacao=1, idTokenProducao=null, idTokenHomologacao=123456), nfseConfig=NfseConfigEntity(id=1, proximoNumeroProducao=1, proximoNumeroHomologacao=1, serieProducao=1, serieHomologacao=1)), endereco=EnderecoEntity(id=1, logradouro=Avenida Coronel Manuel Py, numero=583, bairro=Lauzane Paulista, codigoPostal=02442-090, cidade=São Paulo, estadoEnum=SP)), avaliacao=AvaliacaoEntity(id=1, nota=10, descricao=Atendimento ótimo!))",
                ChamadoEntityBuilder.builder().comMensagens().build().toString());
    }

    @Test
    @DisplayName("Deve testar allArgsConstructor")
    void deveTestarAllArgsConstructor() {

        ChamadoEntity chamadoEntity = new ChamadoEntity(
                1L,
                LocalDate.of(2023, 2, 3).toString(),
                LocalTime.of(15, 16).toString(),
                12345678L,
                "Preciso de ajuda com certificado digital",
                null,
                null,
                CategoriaChamadoEnum.DUVIDA_TECNICA,
                StatusChamadoEnum.EM_ATENDIMENTO,
                ColaboradorInternoEntityBuilder.builder().build(),
                EmpresaEntityBuilder.builder().build(),
                AvaliacaoEntityBuilder.builder().build(),
                new ArrayList<>()
        );

        Assertions.assertEquals(
                "ChamadoEntity(id=1, dataCriacao=2023-02-03, horaCriacao=15:16, ticket=12345678, descricao=Preciso de ajuda com certificado digital, dataBaixa=null, horaBaixa=null, categoriaChamadoEnum=DUVIDA_TECNICA, statusChamadoEnum=EM_ATENDIMENTO, atendenteResponsavel=ColaboradorInternoEntity(id=1, dataCadastro=2023-02-02, horaCadastro=17:10, nome=Gabriel Lagrota, email=gabriellagrota23@gmail.com, cpf=471.534.278-21, acessoSistemaLiberado=true, dataNascimento=1998-07-21, remuneracao=10000.0, tempoFerias=30, entradaEmpresa=01-01-2023, saidaEmpresa=null, cargoEnum=GESTAO, statusAtividadeEnum=ATIVO, telefone=TelefoneEntity(id=1, prefixo=11, numero=979815415, tipoTelefoneEnum=MOVEL_WHATSAPP)), empresa=EmpresaEntity(id=1, dataCadastro=2023-02-03, horaCadastro=10:48, nome=Akadia Solutions, razaoSocial=AKADIA LTDA, cnpj=12345678000112, email=akadia@gmail.com, nomeFantasia=Akadia Solutions, inscricaoEstadual=12345667787867, inscricaoMunicipal=12345667787867, nomeResponsavel=Gabriel Lagrota, cpfResponsavel=47153427821, logo=[], segmentoEmpresaEnum=BATERIA_AUTOMOTIVA, telefone=TelefoneEntity(id=1, prefixo=11, numero=979815415, tipoTelefoneEnum=MOVEL_WHATSAPP), clienteSistema=ClienteSistemaEntity(id=1, codigoClienteAsaas=cus_000005113026, dataCadastro=2023-02-03, horaCadastro=10:40, dataNascimento=2023-02-03, email=fulano@gmail.com, nome=Fulano, senha=123, cpf=12345678910, saldo=0.0, plano=PlanoEntity(id=1, codigoAssinaturaAsaas=sub_jaIvjZ8TMlXZ, dataContratacao=2023-02-03, horaContratacao=09:58, tipoPlanoEnum=BASIC, statusPlanoEnum=ATIVO, formaPagamentoSistemaEnum=BOLETO), telefone=TelefoneEntity(id=1, prefixo=11, numero=979815415, tipoTelefoneEnum=MOVEL_WHATSAPP), endereco=EnderecoEntity(id=1, logradouro=Avenida Coronel Manuel Py, numero=583, bairro=Lauzane Paulista, codigoPostal=02442-090, cidade=São Paulo, estadoEnum=SP), cartao=CartaoEntity(id=1, nomePortador=Gabriel, cpfCnpj=47153427821, numero=5162306219378829, ccv=318, mesExpiracao=8, anoExpiracao=2025, tokenCartao=null, ativo=true, bandeiraCartaoEnum=VISA)), configFiscalEmpresa=ConfigFiscalEmpresaEntity(id=1, discriminaImpostos=true, habilitaNfe=true, habilitaNfce=true, habilitaNfse=true, habilitaEnvioEmailDestinatario=true, exibeReciboNaDanfe=true, cnpjContabilidade=11111111000111, senhaCertificadoDigital=123456, orientacaoDanfeEnum=LANDSCAPE, regimeTributarioEnum=SIMPLES_NACIONAL, certificadoDigital=[], nfeConfig=NfeConfigEntity(id=1, proximoNumeroProducao=1, proximoNumeroHomologacao=1, serieProducao=1, serieHomologacao=1), nfceConfig=NfceConfigEntity(id=1, proximoNumeroProducao=1, proximoNumeroHomologacao=1, serieProducao=1, serieHomologacao=1, cscProducao=1, cscHomologacao=1, idTokenProducao=null, idTokenHomologacao=123456), nfseConfig=NfseConfigEntity(id=1, proximoNumeroProducao=1, proximoNumeroHomologacao=1, serieProducao=1, serieHomologacao=1)), endereco=EnderecoEntity(id=1, logradouro=Avenida Coronel Manuel Py, numero=583, bairro=Lauzane Paulista, codigoPostal=02442-090, cidade=São Paulo, estadoEnum=SP)), avaliacao=AvaliacaoEntity(id=1, nota=10, descricao=Atendimento ótimo!))",
                chamadoEntity.toString());
    }

}
