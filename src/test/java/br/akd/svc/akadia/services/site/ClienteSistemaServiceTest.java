package br.akd.svc.akadia.services.site;

import br.akd.svc.akadia.models.dto.site.mocks.ClienteSistemaDtoBuilder;
import br.akd.svc.akadia.models.entities.site.mocks.ClienteSistemaEntityBuilder;
import br.akd.svc.akadia.modules.web.services.AssinaturaService;
import br.akd.svc.akadia.modules.web.services.ClienteSistemaService;
import br.akd.svc.akadia.modules.web.proxy.asaas.AsaasProxy;
import br.akd.svc.akadia.proxy.asaas.responses.assinatura.mocks.AssinaturaResponseBuilder;
import br.akd.svc.akadia.proxy.asaas.responses.mocks.ClienteSistemaResponseBuilder;
import br.akd.svc.akadia.modules.web.repository.impl.ClienteSistemaRepositoryImpl;
import br.akd.svc.akadia.exceptions.FeignConnectionException;
import br.akd.svc.akadia.exceptions.InvalidRequestException;
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

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@DisplayName("Service: ClienteSistema")
class ClienteSistemaServiceTest {

    @InjectMocks
    ClienteSistemaService clienteSistemaService;

    @Mock
    AssinaturaService assinaturaService;

    @Mock
    ClienteSistemaRepositoryImpl clienteSistemaRepositoryImpl;

    @Mock
    AsaasProxy asaasProxy;

    @Test
    @DisplayName("Deve testar validação de e-mail com sucesso")
    void deveTestarValidacaoDeEmailComSucesso() {
        when(clienteSistemaRepositoryImpl.implementaBuscaPorEmail(any()))
                .thenReturn(Optional.empty());
        clienteSistemaService.validaSeEmailJaExiste(ClienteSistemaDtoBuilder.builder().build());
    }

    @Test
    @DisplayName("Deve testar validação de e-mail com e-mail que já existe")
    void deveTestarValidacaoDeEmailComEmailQueJaExiste() {
        when(clienteSistemaRepositoryImpl.implementaBuscaPorEmail(any()))
                .thenReturn(Optional.of(ClienteSistemaEntityBuilder.builder().build()));
        try {
            clienteSistemaService.validaSeEmailJaExiste(ClienteSistemaDtoBuilder.builder().build());
            Assertions.fail();
        } catch (InvalidRequestException e) {
            Assertions.assertEquals("O e-mail informado já existe",
                    e.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar validação de cpf com sucesso")
    void deveTestarValidacaoDeCpfComSucesso() {
        when(clienteSistemaRepositoryImpl.implementaBuscaPorCpf(any()))
                .thenReturn(Optional.empty());
        clienteSistemaService.validaSeCpfJaExiste(ClienteSistemaDtoBuilder.builder().build());
    }

    @Test
    @DisplayName("Deve testar validação de cpf com cpf que já existe")
    void deveTestarValidacaoDeCpfComCpfQueJaExiste() {
        when(clienteSistemaRepositoryImpl.implementaBuscaPorCpf(any()))
                .thenReturn(Optional.of(ClienteSistemaEntityBuilder.builder().build()));
        try {
            clienteSistemaService.validaSeCpfJaExiste(ClienteSistemaDtoBuilder.builder().build());
            Assertions.fail();
        } catch (InvalidRequestException e) {
            Assertions.assertEquals("O cpf informado já existe",
                    e.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar o método de cadastro de um novo cliente")
    void deveTestarCadastroDeNovoCliente() {

        when(assinaturaService.criaAssinaturaAsaas(any()))
                .thenReturn(AssinaturaResponseBuilder.builder().build());

        when(asaasProxy.cadastraNovoCliente(
                any(),
                any()))
                .thenReturn(new ResponseEntity<>(ClienteSistemaResponseBuilder.builder().build(), HttpStatus.OK));

        clienteSistemaService.cadastraNovoCliente(
                ClienteSistemaDtoBuilder.builder()
                        .comTelefone()
                        .comEndereco()
                        .comPlanoComPagamentoNoCredito().build());

        Assertions.assertDoesNotThrow(() -> new Exception());
    }

    @Test
    @DisplayName("Deve testar o método de cadastro de um novo cliente no ASAAS")
    void deveTestarCadastroDeClienteAsaas() {

        when(asaasProxy.cadastraNovoCliente(
                any(),
                any()))
                .thenReturn(new ResponseEntity<>(ClienteSistemaResponseBuilder.builder().build(), HttpStatus.OK));

        Assertions.assertEquals(
                "ClienteSistemaResponse(object=customer, id=cus_000005105823, dateCreated=2023-02-05, " +
                        "name=Gabriel Lagrota, email=gabriellagrota23@gmail.com, company=null, phone=979815415, " +
                        "mobilePhone=979815415, address=Avenida Coronel Manuel Py, addressNumber=583, " +
                        "complement=Sem complemento, province=Lauzane Paulista, postalCode=02442090, " +
                        "cpfCnpj=47153427821, personType=FISICA, deleted=false, additionalEmails=null, " +
                        "externalReference=null, notificationDisabled=true, observations=Sem observações, " +
                        "municipalInscription=46683695908, stateInscription=646681195275, canDelete=true, " +
                        "cannotBeDeletedReason=null, canEdit=true, cannotEditReason=null, foreignCustomer=false, " +
                        "city=12565, state=SP, country=Brasil)",
                clienteSistemaService.realizaCadastroClienteAsaas(ClienteSistemaEntityBuilder.builder().comTelefone()
                        .comPlanoComPagamentoNoCredito().build()).toString());
    }

    @Test
    @DisplayName("Deve testar o método de cadastro de um novo cliente no ASAAS com exception de falha de comunicação")
    void deveTestarCadastroDeClienteAsaasComExceptionDeFalhaDeComunicacao() {
        when(asaasProxy.cadastraNovoCliente(any(), any()))
                .thenThrow(new FeignConnectionException("Teste de exception"));
        try {
            clienteSistemaService.realizaCadastroClienteAsaas(
                    ClienteSistemaEntityBuilder.builder().comTelefone().comPlanoComPagamentoNoCredito().build());
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertEquals("Ocorreu uma falha na comunicação com a integradora de pagamentos: Teste de exception",
                    e.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar o método de cadastro de um novo cliente no ASAAS com exception de status code 400")
    void deveTestarCadastroDeClienteAsaasComExceptionDeStatusCode400() {
        when(asaasProxy.cadastraNovoCliente(
                any(),
                any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
        try {
            clienteSistemaService.realizaCadastroClienteAsaas(
                    ClienteSistemaEntityBuilder.builder().comTelefone().comPlanoComPagamentoNoCredito().build());
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertEquals("Ocorreu um erro no processo de criação do cliente: null",
                    e.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar atualização de dados do cliente")
    void deveTestarAtualizacaoDeDadosCliente() {
        when(clienteSistemaRepositoryImpl.implementaBuscaPorId(
                any()))
                .thenReturn(ClienteSistemaEntityBuilder.builder().build());

        when(clienteSistemaRepositoryImpl.implementaPersistencia(
                any()))
                .thenReturn(ClienteSistemaEntityBuilder.builder().build());

        when(asaasProxy.atualizaDadosCliente(
                any(),
                any(),
                any()))
                .thenReturn(new ResponseEntity<>(ClienteSistemaResponseBuilder.builder().build(), HttpStatus.OK));

        clienteSistemaService.atualizaDadosCliente(1L,
                ClienteSistemaDtoBuilder.builder().comEndereco().comTelefone().comPlanoComPagamentoNoCredito().build());
    }

    @Test
    @DisplayName("Deve testar atualização de dados do cliente com exception de e-mail já existente")
    void deveTestarAtualizacaoDeDadosClienteComInvalidRequestException() {

        when(clienteSistemaRepositoryImpl.implementaBuscaPorId(
                any()))
                .thenReturn(ClienteSistemaEntityBuilder.builder().comOutroEmail().build());

        when(clienteSistemaRepositoryImpl.implementaBuscaPorEmail(
                any()))
                .thenReturn(Optional.of(ClienteSistemaEntityBuilder.builder().build()));

        try {
            clienteSistemaService.atualizaDadosCliente(1L,
                    ClienteSistemaDtoBuilder.builder().comEndereco().comTelefone().comPlanoComPagamentoNoCredito().build());
            Assertions.fail();
        } catch (Exception exception) {
            Assertions.assertEquals("O e-mail informado já existe",
                    exception.getMessage());
        }

    }

    @Test
    @DisplayName("Deve testar atualização de dados do cliente na integradora de pagamentos")
    void deveTestarAtualizacaoDeDadosClienteNaIntegradoraDePagamentos() {
        when(asaasProxy.atualizaDadosCliente(
                any(),
                any(),
                any()))
                .thenReturn(new ResponseEntity<>(ClienteSistemaResponseBuilder.builder().build(), HttpStatus.OK));

        clienteSistemaService.atualizaDadosClienteAsaas(ClienteSistemaEntityBuilder.builder().comTelefone().comEndereco().build());

        Assertions.assertDoesNotThrow(() -> new Exception());
    }

    @Test
    @DisplayName("Deve testar atualização de dados do cliente na integradora de pagamentos com falha de comunicação")
    void deveTestarAtualizacaoDeDadosClienteNaIntegradoraDePagamentosComFeignConnectionException() {
        when(asaasProxy.atualizaDadosCliente(any(), any(), any()))
                .thenThrow(new FeignConnectionException("Teste de exception"));
        try {
            clienteSistemaService.atualizaDadosClienteAsaas(
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
    @DisplayName("Deve testar o método de atualização de dados do cliente no ASAAS com exception de status code 400")
    void deveTestarAtualizacaoDeCadastroClienteAsaasComBadRequest() {
        when(asaasProxy.atualizaDadosCliente(
                any(),
                any(),
                any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
        try {
            clienteSistemaService.atualizaDadosClienteAsaas(
                    ClienteSistemaEntityBuilder.builder()
                            .comTelefone()
                            .comPlanoComPagamentoNoCredito()
                            .comEndereco()
                            .build());
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertEquals("Ocorreu um erro no processo de atualização dos dados cadastrais do cliente: null",
                    e.getMessage());
        }
    }

}
