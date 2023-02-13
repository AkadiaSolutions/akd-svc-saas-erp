package br.akd.svc.akadia.services.site;

import br.akd.svc.akadia.models.dto.site.mocks.ClienteSistemaDtoBuilder;
import br.akd.svc.akadia.models.entities.site.mocks.ClienteSistemaEntityBuilder;
import br.akd.svc.akadia.proxy.asaas.AsaasProxy;
import br.akd.svc.akadia.proxy.asaas.responses.assinatura.atualiza.mocks.AtualizaAssinaturaResponseBuilder;
import br.akd.svc.akadia.proxy.asaas.responses.assinatura.cancela.mocks.CancelamentoAssinaturaResponseBuilder;
import br.akd.svc.akadia.proxy.asaas.responses.assinatura.consulta.mocks.ConsultaAssinaturaResponseBuilder;
import br.akd.svc.akadia.proxy.asaas.responses.assinatura.mocks.AssinaturaResponseBuilder;
import br.akd.svc.akadia.repositories.site.impl.ClienteSistemaRepositoryImpl;
import br.akd.svc.akadia.services.exceptions.FeignConnectionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@DisplayName("Service: Assinatura")
class AssinaturaServiceTest {

    @InjectMocks
    AssinaturaService assinaturaService;

    @Mock
    ClienteSistemaService clienteSistemaService;

    @Mock
    ClienteSistemaRepositoryImpl clienteSistemaRepositoryImpl;

    @Mock
    AsaasProxy asaasProxy;

    @Test
    @DisplayName("Deve testar o método de cadastro de uma nova assinatura no ASAAS")
    void deveTestarCadastroDeAssinaturaAsaas() {

        when(asaasProxy.cadastraNovaAssinatura(any(), any()))
                .thenReturn(new ResponseEntity<>(AssinaturaResponseBuilder.builder().build(), HttpStatus.OK));

        assinaturaService.criaAssinaturaAsaas(
                ClienteSistemaEntityBuilder.builder()
                        .comTelefone()
                        .comEndereco()
                        .comPlanoComPagamentoNoCredito()
                        .build());
    }

    @Test
    @DisplayName("Deve testar o método de cadastro de um novo plano de assinatura no ASAAS com exception de falha de comunicação")
    void deveTestarCadastroDePlanoDeAssinaturaAsaasComExceptionDeFalhaDeComunicacao() {
        when(asaasProxy.cadastraNovaAssinatura(any(), any()))
                .thenThrow(new FeignConnectionException("Teste de exception"));

        when(clienteSistemaService.criaNumeroMobileComObjetoTelefone(any()))
                .thenReturn("11979815415");

        try {
            assinaturaService.criaAssinaturaAsaas(
                    ClienteSistemaEntityBuilder.builder()
                            .comEndereco()
                            .comTelefone()
                            .comPlanoComPagamentoNoCredito()
                            .build());
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertEquals("Ocorreu uma falha na comunicação com a integradora de pagamentos: Teste de exception",
                    e.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar o método de cadastro de um novo plano de assinatura no ASAAS com exception de status code 400")
    void deveTestarCadastroDePlanoDeAssinaturaAsaasComBadRequest() {
        when(asaasProxy.cadastraNovaAssinatura(
                any(),
                any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
        try {
            assinaturaService.criaAssinaturaAsaas(
                    ClienteSistemaEntityBuilder.builder()
                            .comTelefone()
                            .comPlanoComPagamentoNoCredito()
                            .comEndereco()
                            .build());
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertEquals("Ocorreu um erro no processo de criação da assinatura: null",
                    e.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar o método de atualização de uma assinatura")
    void deveTestarAtualizacaoDeAssinatura() {

        when(clienteSistemaRepositoryImpl.implementaBuscaPorId(any()))
                .thenReturn(ClienteSistemaEntityBuilder.builder().comPlanoComPagamentoNoCredito().build());

        when(clienteSistemaRepositoryImpl.implementaPersistencia(any()))
                .thenReturn(ClienteSistemaEntityBuilder.builder().comPlanoComPagamentoNoCredito().build());

        when(asaasProxy.atualizaAssinatura(any(), any(), any()))
                .thenReturn(new ResponseEntity<>(AtualizaAssinaturaResponseBuilder.builder().build(), HttpStatus.OK));

        Assertions.assertEquals("ClienteSistemaEntity(id=1, codigoClienteAsaas=cus_000005113026, " +
                        "dataCadastro=2023-02-03, horaCadastro=10:40, dataNascimento=2023-02-03, " +
                        "email=fulano@gmail.com, nome=Fulano, senha=123, cpf=12345678910, saldo=0.0, " +
                        "plano=PlanoEntity(id=1, codigoAssinaturaAsaas=sub_jaIvjZ8TMlXZ, dataContratacao=2023-02-03, " +
                        "horaContratacao=09:58, dataVencimento=2023-02-03, tipoPlanoEnum=BASIC, statusPlanoEnum=ATIVO, " +
                        "formaPagamentoSistemaEnum=CREDIT_CARD), telefone=null, endereco=null, " +
                        "cartao=CartaoEntity(id=1, nomePortador=Gabriel, cpfCnpj=47153427821, " +
                        "numero=5162306219378829, ccv=318, mesExpiracao=8, anoExpiracao=2025, tokenCartao=null, " +
                        "ativo=true, bandeiraCartaoEnum=VISA))",
                assinaturaService.atualizaDadosAssinatura(1L, ClienteSistemaDtoBuilder.builder()
                        .comPlanoComPagamentoNoCredito().build()).toString());
    }

    @Test
    @DisplayName("Deve testar o método de atualização de uma assinatura Asaas")
    void atualizaDadosAssinaturaAsaas() {

        when(asaasProxy.atualizaAssinatura(any(), any(), any()))
                .thenReturn(new ResponseEntity<>(AtualizaAssinaturaResponseBuilder.builder().build(), HttpStatus.OK));

        assinaturaService.atualizaDadosAssinaturaAsaas(ClienteSistemaEntityBuilder.builder()
                .comPlanoComPagamentoNoCredito().build());

        Assertions.assertDoesNotThrow(() -> new Exception());
    }

    @Test
    @DisplayName("Deve testar atualização de assinatura do cliente na integradora de pagamentos com falha de comunicação")
    void deveTestarAtualizacaoDeDadosAssinaturaNaIntegradoraDePagamentosComFeignConnectionException() {
        when(asaasProxy.atualizaAssinatura(any(), any(), any()))
                .thenThrow(new FeignConnectionException("Teste de exception"));
        try {
            assinaturaService.atualizaDadosAssinaturaAsaas(
                    ClienteSistemaEntityBuilder.builder()
                            .comEndereco()
                            .comTelefone()
                            .comPlanoComPagamentoNoCredito()
                            .build());
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertEquals("Ocorreu uma falha na comunicação com a integradora de pagamentos: Teste de exception",
                    e.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar o método de atualização de assinatura do cliente no ASAAS com exception de status code 400")
    void deveTestarAtualizacaoDeAssinaturaAsaasComBadRequest() {
        when(asaasProxy.atualizaAssinatura(
                any(),
                any(),
                any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
        try {
            assinaturaService.atualizaDadosAssinaturaAsaas(
                    ClienteSistemaEntityBuilder.builder()
                            .comTelefone()
                            .comPlanoComPagamentoNoCredito()
                            .comEndereco()
                            .build());
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertEquals("Ocorreu um erro no processo de atualização de assinatura com a " +
                            "integradora: null",
                    e.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar o método de consulta de dados de uma assinatura Asaas")
    void consultaDadosAssinaturaAsaas() {

        when(asaasProxy.consultaAssinatura(any(), any()))
                .thenReturn(new ResponseEntity<>(ConsultaAssinaturaResponseBuilder.builder().build(), HttpStatus.OK));

        Assertions.assertEquals("ConsultaAssinaturaResponse(id=sub_i8U7a1UzweKt, dateCreated=2023-02-08, " +
                        "customer=cus_000005121369, paymentLink=null, billingType=PIX, value=650.0, nextDueDate=2023-02-08, " +
                        "cycle=MONTHLY, description=Assinatura de plano pro, endDate=null, maxPayments=null, status=ACTIVE, " +
                        "externalReference=null, split=[])",
                assinaturaService.consultaAssinaturaAsaas("consultaAssinaturaAsaas").toString());
    }

    @Test
    @DisplayName("Deve testar o método de consulta de dados de uma assinatura Asaas com falha de comunicação")
    void deveTestarConsultaDeDadosDeAssinaturaNaIntegradoraDePagamentosComFeignConnectionException() {
        when(asaasProxy.consultaAssinatura(any(), any()))
                .thenThrow(new FeignConnectionException("Teste de exception"));
        try {
            assinaturaService.consultaAssinaturaAsaas("sub_c28JMi6RKPvv");
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertEquals("Ocorreu uma falha na comunicação com a integradora de pagamentos: Teste de exception",
                    e.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar o método de consulta de dados de uma assinatura Asaas com exception de status code 400")
    void deveTestarConsultaDeDadosDeAssinaturaAsaasComBadRequest() {
        when(asaasProxy.consultaAssinatura(
                any(),
                any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
        try {
            assinaturaService.consultaAssinaturaAsaas("sub_c28JMi6RKPvv");
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertEquals("Ocorreu um erro no processo de consulta de assinatura com " +
                            "a integradora: null",
                    e.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar o método de cancelamento de uma assinatura")
    void deveTestarCancelamentoDeAssinatura() {

        when(clienteSistemaRepositoryImpl.implementaBuscaPorId(any()))
                .thenReturn(ClienteSistemaEntityBuilder.builder().comPlanoComPagamentoNoCredito().build());

        when(clienteSistemaRepositoryImpl.implementaPersistencia(any()))
                .thenReturn(ClienteSistemaEntityBuilder.builder().comPlanoComPagamentoNoCredito().build());

        when(asaasProxy.cancelaAssinatura(any(), any()))
                .thenReturn(new ResponseEntity<>(CancelamentoAssinaturaResponseBuilder.builder().build(), HttpStatus.OK));

        Assertions.assertEquals("ClienteSistemaEntity(id=1, codigoClienteAsaas=cus_000005113026, " +
                        "dataCadastro=2023-02-03, horaCadastro=10:40, dataNascimento=2023-02-03, " +
                        "email=fulano@gmail.com, nome=Fulano, senha=123, cpf=12345678910, saldo=0.0, " +
                        "plano=PlanoEntity(id=1, codigoAssinaturaAsaas=sub_jaIvjZ8TMlXZ, " +
                        "dataContratacao=2023-02-03, horaContratacao=09:58, dataVencimento=2023-02-03, " +
                        "tipoPlanoEnum=BASIC, statusPlanoEnum=ATIVO, formaPagamentoSistemaEnum=CREDIT_CARD), " +
                        "telefone=null, endereco=null, cartao=CartaoEntity(id=1, nomePortador=Gabriel, " +
                        "cpfCnpj=47153427821, numero=5162306219378829, ccv=318, mesExpiracao=8, anoExpiracao=2025, " +
                        "tokenCartao=null, ativo=true, bandeiraCartaoEnum=VISA))",
                assinaturaService.cancelaAssinatura(1L).toString());
    }

    @Test
    @DisplayName("Deve testar o método de cancelamento de uma assinatura Asaas")
    void deveTestesMetodoCancelamentoDeAssinaturaAsaas() {

        when(asaasProxy.cancelaAssinatura(any(), any()))
                .thenReturn(new ResponseEntity<>(CancelamentoAssinaturaResponseBuilder.builder().build(), HttpStatus.OK));

        assinaturaService.cancelaAssinaturaAsaas(ClienteSistemaEntityBuilder.builder()
                .comPlanoComPagamentoNoCredito().build());

        Assertions.assertDoesNotThrow(() -> new Exception());
    }

    @Test
    @DisplayName("Deve testar o método de cancelamento de assinatura Asaas com falha de comunicação")
    void deveTestarCancelamentoDeAssinaturaNaIntegradoraDePagamentosComFeignConnectionException() {
        when(asaasProxy.cancelaAssinatura(any(), any()))
                .thenThrow(new FeignConnectionException("Teste de exception"));
        try {
            assinaturaService.cancelaAssinaturaAsaas(ClienteSistemaEntityBuilder.builder().comPlanoComPagamentoNoCredito().build());
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertEquals("Ocorreu uma falha na comunicação com a integradora de pagamentos: Teste de exception",
                    e.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar o método de cancelamento de assinatura Asaas com exception de status code 400")
    void deveTestarCancelamentoDeAssinaturaAsaasComBadRequest() {
        when(asaasProxy.cancelaAssinatura(
                any(),
                any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
        try {
            assinaturaService.cancelaAssinaturaAsaas(ClienteSistemaEntityBuilder.builder().comPlanoComPagamentoNoCredito().build());
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertEquals("Ocorreu um erro no processo de cancelamento de assinatura com a " +
                            "integradora: null",
                    e.getMessage());
        }
    }

}
