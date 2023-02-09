package br.akd.svc.akadia.services.site;

import br.akd.svc.akadia.models.dto.site.mocks.EmpresaDtoBuilder;
import br.akd.svc.akadia.models.entities.site.mocks.ClienteSistemaEntityBuilder;
import br.akd.svc.akadia.models.entities.site.mocks.EmpresaEntityBuilder;
import br.akd.svc.akadia.repositories.site.impl.ClienteSistemaRepositoryImpl;
import br.akd.svc.akadia.repositories.site.impl.EmpresaRepositoryImpl;
import br.akd.svc.akadia.services.exceptions.InvalidRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("Service: Empresa")
class EmpresaServiceTest {

    @InjectMocks
    EmpresaService empresaService;

    @Mock
    EmpresaRepositoryImpl empresaRepositoryImpl;

    @Mock
    ClienteSistemaRepositoryImpl clienteSistemaRepositoryImpl;

    @Test
    @DisplayName("Deve testar validação de razão social com sucesso")
    void deveTestarValidacaoDeRazaoSocialComSucesso() {
        when(empresaRepositoryImpl.implementaBuscaPorRazaoSocial(any()))
                .thenReturn(Optional.empty());
        empresaService.validaSeRazaoSocialJaExiste("KG DIMOPOULOS NOVA ERA LTDA");
        Assertions.assertDoesNotThrow(() -> new Exception());
    }

    @Test
    @DisplayName("Deve testar validação de endpoint com sucesso")
    void deveTestarValidacaoDeEndpointComSucesso() {
        when(empresaRepositoryImpl.implementaBuscaPorEndpoint(any()))
                .thenReturn(Optional.empty());
        empresaService.validaSeEndpointJaExiste("twobrothers");
        Assertions.assertDoesNotThrow(() -> new Exception());
    }

    @Test
    @DisplayName("Deve testar validação de cnpj com sucesso")
    void deveTestarValidacaoDeCnpjComSucesso() {
        when(empresaRepositoryImpl.implementaBuscaPorCnpj(any()))
                .thenReturn(Optional.empty());
        empresaService.validaSeCnpjJaExiste("16733160000180");
        Assertions.assertDoesNotThrow(() -> new Exception());
    }

    @Test
    @DisplayName("Deve testar validação de inscrição estadual com sucesso")
    void deveTestarValidacaoDeInscricaoEstadualomSucesso() {
        when(empresaRepositoryImpl.implementaBuscaPorInscricaoEstadual(any()))
                .thenReturn(Optional.empty());
        empresaService.validaSeInscricaoEstadualJaExiste("145574080114");
        Assertions.assertDoesNotThrow(() -> new Exception());
    }

    @Test
    @DisplayName("Deve testar validação de chave única para atualização de empresa")
    void deveTestarValidacaoDeChaveUnicaParaAtualizacaoDeEmpresa() {
        empresaService.validacaoDeChaveUnicaParaAtualizacaoDeEmpresa(
                EmpresaDtoBuilder.builder().build(),
                EmpresaEntityBuilder.builder().build());
        Assertions.assertDoesNotThrow(() -> new Exception());
    }

    @Test
    @DisplayName("Deve testar validação de inscrição municipal com sucesso")
    void deveTestarValidacaoDeInscricaoMunicipalComSucesso() {
        when(empresaRepositoryImpl.implementaBuscaPorInscricaoMunicipal(any()))
                .thenReturn(Optional.empty());
        empresaService.validaSeInscricaoMunicipalJaExiste("145574080114");
        Assertions.assertDoesNotThrow(() -> new Exception());
    }

    @Test
    @DisplayName("Deve testar validação de endpoint com endpoint que já existe")
    void deveTestarValidacaoDeEndpointComEndpointQueJaExiste() {
        when(empresaRepositoryImpl.implementaBuscaPorEndpoint(any()))
                .thenReturn(Optional.of(EmpresaEntityBuilder.builder().build()));
        try {
            empresaService.validaSeEndpointJaExiste("twobrothers");
            Assertions.fail();
        } catch (InvalidRequestException e) {
            Assertions.assertEquals("O endpoint informado já existe",
                    e.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar validação de razão social que já existe")
    void deveTestarValidacaoDeRazaoSocialQueJaExiste() {
        when(empresaRepositoryImpl.implementaBuscaPorRazaoSocial(any()))
                .thenReturn(Optional.of(EmpresaEntityBuilder.builder().build()));
        try {
            empresaService.validaSeRazaoSocialJaExiste("KG DIMOPOULOS NOVA ERA LTDA");
            Assertions.fail();
        } catch (InvalidRequestException e) {
            Assertions.assertEquals("A razão social informada já existe",
                    e.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar validação de cnpj com cnpj que já existe")
    void deveTestarValidacaoDeCnpjComCnpjQueJaExiste() {
        when(empresaRepositoryImpl.implementaBuscaPorCnpj(any()))
                .thenReturn(Optional.of(EmpresaEntityBuilder.builder().build()));
        try {
            empresaService.validaSeCnpjJaExiste("16733160000180");
            Assertions.fail();
        } catch (InvalidRequestException e) {
            Assertions.assertEquals("O cnpj informado já existe",
                    e.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar criação de nova empresa")
    void deveTestarCriacaoDeNovaEmpresa() {

        when(clienteSistemaRepositoryImpl.implementaBuscaPorId(any()))
                .thenReturn(ClienteSistemaEntityBuilder.builder()
                        .comPlanoComPagamentoNoCredito()
                        .comTelefone()
                        .comEndereco()
                        .build());

        when(clienteSistemaRepositoryImpl.implementaPersistencia(any()))
                .thenReturn(ClienteSistemaEntityBuilder.builder()
                        .comPlanoComPagamentoNoCredito()
                        .comEndereco()
                        .comTelefone()
                        .comEmpresa()
                        .build());

        Assertions.assertEquals("ClienteSistemaEntity(id=1, codigoClienteAsaas=cus_000005113026, " +
                        "dataCadastro=2023-02-03, horaCadastro=10:40, dataNascimento=2023-02-03, email=fulano@gmail.com, " +
                        "nome=Fulano, senha=123, cpf=12345678910, saldo=0.0, plano=PlanoEntity(id=1, " +
                        "codigoAssinaturaAsaas=sub_jaIvjZ8TMlXZ, dataContratacao=2023-02-03, horaContratacao=09:58, " +
                        "dataVencimento=2023-02-03, tipoPlanoEnum=BASIC, statusPlanoEnum=ATIVO, " +
                        "formaPagamentoSistemaEnum=CREDIT_CARD), telefone=TelefoneEntity(id=1, prefixo=11, " +
                        "numero=979815415, tipoTelefoneEnum=MOVEL_WHATSAPP), endereco=EnderecoEntity(id=1, logradouro=Avenida " +
                        "Coronel Manuel Py, numero=583, bairro=Lauzane Paulista, codigoPostal=02442-090, cidade=São Paulo, " +
                        "complemento=Casa 4, estadoEnum=SP), cartao=CartaoEntity(id=1, nomePortador=Gabriel, " +
                        "cpfCnpj=47153427821, numero=5162306219378829, ccv=318, mesExpiracao=8, anoExpiracao=2025, " +
                        "tokenCartao=null, ativo=true, bandeiraCartaoEnum=VISA))",
                empresaService.criaNovaEmpresa(1L, EmpresaDtoBuilder.builder()
                        .comConfigFiscalComTodasNf()
                        .comEndereco()
                        .comTelefone()
                        .build()).toString());
    }

    @Test
    @DisplayName("Deve testar atualização de empresa")
    void deveTestarAtualizacaoDeEmpresa() {

        when(empresaRepositoryImpl.implementaBuscaPorId(any()))
                .thenReturn(EmpresaEntityBuilder.builder()
                        .comConfigFiscalComTodasNf()
                        .comEndereco()
                        .comTelefone().build());

        when(empresaRepositoryImpl.implementaPersistencia(any()))
                .thenReturn(EmpresaEntityBuilder.builder()
                        .comConfigFiscalComTodasNf()
                        .comEndereco()
                        .comTelefone().build());

        System.err.println(empresaService.atualizaEmpresa(1L, EmpresaDtoBuilder.builder()
                .comEndereco()
                .comConfigFiscalComTodasNf()
                .comTelefone()
                .comEndereco()
                .build()));
    }

}
