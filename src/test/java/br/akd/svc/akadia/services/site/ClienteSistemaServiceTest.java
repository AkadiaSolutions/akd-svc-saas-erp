package br.akd.svc.akadia.services.site;

import br.akd.svc.akadia.models.dto.site.mocks.ClienteSistemaDtoBuilder;
import br.akd.svc.akadia.models.entities.site.mocks.ClienteSistemaEntityBuilder;
import br.akd.svc.akadia.proxy.asaas.AsaasProxy;
import br.akd.svc.akadia.proxy.asaas.responses.AssinaturaResponse;
import br.akd.svc.akadia.proxy.asaas.responses.ClienteSistemaResponse;
import br.akd.svc.akadia.proxy.asaas.responses.mocks.AssinaturaResponseBuilder;
import br.akd.svc.akadia.proxy.asaas.responses.mocks.ClienteSistemaResponseBuilder;
import br.akd.svc.akadia.repositories.site.impl.ClienteSistemaRepositoryImpl;
import br.akd.svc.akadia.services.global.exceptions.FeignConnectionException;
import br.akd.svc.akadia.services.global.exceptions.InvalidRequestException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.var;
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
    ClienteSistemaRepositoryImpl clienteSistemaRepositoryImpl;

    @Mock
    ObjectMapper objectMapper;

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

        var responseEntityCadastraCliente = new ResponseEntity("{object=customer, id=cus_000005116731, " +
                "dateCreated=2023-02-05, name=Gabriel Henrique Lagrota, email=gabriellagrota23@gmail.com, company=null, " +
                "phone=11979815415, mobilePhone=11979815415, address=null, addressNumber=null, complement=null, " +
                "province=null, postalCode=null, cpfCnpj=47153427821, personType=FISICA, deleted=false, " +
                "additionalEmails=null, externalReference=null, notificationDisabled=false, observations=null, " +
                "municipalInscription=null, stateInscription=null, canDelete=true, cannotBeDeletedReason=null, " +
                "canEdit=true, cannotEditReason=null, foreignCustomer=false, city=null, state=null, country=Brasil}",
                HttpStatus.OK);

        var responseEntityAssinaPlano = new ResponseEntity("{object=subscription, id=sub_C2rGa18j8cT4, " +
                "dateCreated=2023-02-06, customer=cus_000005118516, paymentLink=null, value=250.0, " +
                "nextDueDate=2023-03-13, cycle=MONTHLY, description=Assinatura de plano Basic, billingType=CREDIT_CARD, " +
                "deleted=false, status=ACTIVE, externalReference=null, creditCard={creditCardNumber=8829, " +
                "creditCardBrand=MASTERCARD, creditCardToken=c127baad-5943-45dd-a85e-8bbe3fb5c01a}, " +
                "sendPaymentByPostalService=false, fine={value=0.0, type=FIXED}, interest={value=0.0, type=PERCENTAGE}, " +
                "split=null}", HttpStatus.OK);


        when(asaasProxy.cadastraNovoCliente(
                any(),
                any()))
                .thenReturn(responseEntityCadastraCliente);

        when(asaasProxy.cadastraNovoPlano(
                any(),
                any()))
                .thenReturn(responseEntityAssinaPlano);

        when(objectMapper.convertValue("{object=customer, id=cus_000005116731, dateCreated=2023-02-05, name=Gabriel " +
                "Henrique Lagrota, email=gabriellagrota23@gmail.com, company=null, phone=11979815415, " +
                "mobilePhone=11979815415, address=null, addressNumber=null, complement=null, province=null, " +
                "postalCode=null, cpfCnpj=47153427821, personType=FISICA, deleted=false, additionalEmails=null, " +
                "externalReference=null, notificationDisabled=false, observations=null, municipalInscription=null, " +
                "stateInscription=null, canDelete=true, cannotBeDeletedReason=null, canEdit=true, cannotEditReason=null, " +
                "foreignCustomer=false, city=null, state=null, country=Brasil}", ClienteSistemaResponse.class))
                .thenReturn(ClienteSistemaResponseBuilder.builder().build());

        when(objectMapper.convertValue("{object=subscription, id=sub_C2rGa18j8cT4, " +
                "dateCreated=2023-02-06, customer=cus_000005118516, paymentLink=null, value=250.0, " +
                "nextDueDate=2023-03-13, cycle=MONTHLY, description=Assinatura de plano Basic, billingType=CREDIT_CARD, " +
                "deleted=false, status=ACTIVE, externalReference=null, creditCard={creditCardNumber=8829, " +
                "creditCardBrand=MASTERCARD, creditCardToken=c127baad-5943-45dd-a85e-8bbe3fb5c01a}, " +
                "sendPaymentByPostalService=false, fine={value=0.0, type=FIXED}, interest={value=0.0, type=PERCENTAGE}, " +
                "split=null}", AssinaturaResponse.class))
                .thenReturn(AssinaturaResponseBuilder.builder().build());

        when(clienteSistemaService.realizaCadastroClienteAsaas(
                ClienteSistemaEntityBuilder.builder().build()))
                .thenReturn(ClienteSistemaResponseBuilder.builder().build());

        when(clienteSistemaService.criaAssinaturaAsaas(
                ClienteSistemaEntityBuilder.builder().build()))
                .thenReturn(AssinaturaResponseBuilder.builder().build());

        clienteSistemaService.cadastraNovoCliente(
                ClienteSistemaDtoBuilder.builder().comPlanoComPagamentoNoCredito().build());
    }

    @Test
    @DisplayName("Deve testar o método de cadastro de um novo cliente no ASAAS")
    void deveTestarCadastroDeClienteAsaas() {
        var responseEntityCadastraCliente = new ResponseEntity("{object=customer, id=cus_000005116731, " +
                "dateCreated=2023-02-05, name=Gabriel Henrique Lagrota, email=gabriellagrota23@gmail.com, company=null, " +
                "phone=11979815415, mobilePhone=11979815415, address=null, addressNumber=null, complement=null, " +
                "province=null, postalCode=null, cpfCnpj=47153427821, personType=FISICA, deleted=false, " +
                "additionalEmails=null, externalReference=null, notificationDisabled=false, observations=null, " +
                "municipalInscription=null, stateInscription=null, canDelete=true, cannotBeDeletedReason=null, " +
                "canEdit=true, cannotEditReason=null, foreignCustomer=false, city=null, state=null, country=Brasil}",
                HttpStatus.OK);

        when(asaasProxy.cadastraNovoCliente(any(), any())).thenReturn(responseEntityCadastraCliente);

        clienteSistemaService.realizaCadastroClienteAsaas(
                ClienteSistemaEntityBuilder.builder().comPlanoComPagamentoNoCredito().build());
    }

    @Test
    @DisplayName("Deve testar o método de cadastro de um novo cliente no ASAAS com exception de falha de comunicação")
    void deveTestarCadastroDeClienteAsaasComExceptionDeFalhaDeComunicacao() {
        when(asaasProxy.cadastraNovoCliente(any(), any()))
                .thenThrow(new FeignConnectionException("Teste de exception"));

        try {
            clienteSistemaService.realizaCadastroClienteAsaas(
                    ClienteSistemaEntityBuilder.builder().comPlanoComPagamentoNoCredito().build());
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertEquals("Ocorreu uma falha na comunicação com a integradora de pagamentos: Teste de exception",
                    e.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar o método de cadastro de um novo cliente no ASAAS com exception de status code 404")
    void deveTestarCadastroDeClienteAsaasComExceptionDeStatusCode404() {
        var responseEntityCadastraCliente = new ResponseEntity(HttpStatus.BAD_REQUEST);
        when(asaasProxy.cadastraNovoCliente(any(), any()))
                .thenReturn(responseEntityCadastraCliente);
        try {
            clienteSistemaService.realizaCadastroClienteAsaas(
                    ClienteSistemaEntityBuilder.builder().comPlanoComPagamentoNoCredito().build());
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertEquals("Ocorreu um erro no processo de criação do cliente: null",
                    e.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar o método de cadastro de uma nova assinatura no ASAAS")
    void deveTestarCadastroDeAssinaturaAsaas() {
        var responseEntityAssinaPlano = new ResponseEntity("{object=subscription, id=sub_C2rGa18j8cT4, " +
                "dateCreated=2023-02-06, customer=cus_000005118516, paymentLink=null, value=250.0, " +
                "nextDueDate=2023-03-13, cycle=MONTHLY, description=Assinatura de plano Basic, billingType=CREDIT_CARD, " +
                "deleted=false, status=ACTIVE, externalReference=null, creditCard={creditCardNumber=8829, " +
                "creditCardBrand=MASTERCARD, creditCardToken=c127baad-5943-45dd-a85e-8bbe3fb5c01a}, " +
                "sendPaymentByPostalService=false, fine={value=0.0, type=FIXED}, interest={value=0.0, type=PERCENTAGE}, " +
                "split=null}", HttpStatus.OK);

        when(asaasProxy.cadastraNovoPlano(any(), any())).thenReturn(responseEntityAssinaPlano);

        clienteSistemaService.criaAssinaturaAsaas(
                ClienteSistemaEntityBuilder.builder().comPlanoComPagamentoNoCredito().build());
    }

    @Test
    @DisplayName("Deve testar o método de cadastro de um novo plano de assinatura no ASAAS com exception de falha de comunicação")
    void deveTestarCadastroDePlanoDeAssinaturaAsaasComExceptionDeFalhaDeComunicacao() {
        when(asaasProxy.cadastraNovoPlano(any(), any()))
                .thenThrow(new FeignConnectionException("Teste de exception"));

        try {
            clienteSistemaService.criaAssinaturaAsaas(
                    ClienteSistemaEntityBuilder.builder().comPlanoComPagamentoNoCredito().build());
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertEquals("Ocorreu uma falha na comunicação com a integradora de pagamentos: Teste de exception",
                    e.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar o método de cadastro de um novo plano de assinatura no ASAAS com exception de status code 404")
    void deveTestarCadastroDePlanoDeAssinaturaAsaasComExceptionDeStatusCode404() {
        var responseEntityAssinaPlano = new ResponseEntity(HttpStatus.BAD_REQUEST);
        when(asaasProxy.cadastraNovoPlano(any(), any()))
                .thenReturn(responseEntityAssinaPlano);
        try {
            clienteSistemaService.criaAssinaturaAsaas(
                    ClienteSistemaEntityBuilder.builder().comPlanoComPagamentoNoCredito().build());
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertEquals("Ocorreu um erro no processo de criação da assinatura: null",
                    e.getMessage());
        }
    }

}
