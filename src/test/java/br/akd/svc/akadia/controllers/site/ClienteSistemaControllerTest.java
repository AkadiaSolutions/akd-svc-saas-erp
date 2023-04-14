package br.akd.svc.akadia.controllers.site;

import br.akd.svc.akadia.models.dto.site.mocks.ClienteSistemaDtoBuilder;
import br.akd.svc.akadia.models.entities.bckoff.mocks.LeadEntityBuilder;
import br.akd.svc.akadia.models.entities.site.mocks.ClienteSistemaEntityBuilder;
import br.akd.svc.akadia.repositories.site.impl.ClienteSistemaRepositoryImpl;
import br.akd.svc.akadia.services.bckoff.LeadService;
import br.akd.svc.akadia.services.site.AssinaturaService;
import br.akd.svc.akadia.services.site.ClienteSistemaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("Controller: ClienteSistema")
class ClienteSistemaControllerTest {

    @InjectMocks
    ClienteSistemaController clienteSistemaController;

    @Mock
    ClienteSistemaRepositoryImpl clienteSistemaRepositoryImpl;

    @Mock
    LeadService leadService;

    @Mock
    ClienteSistemaService clienteSistemaService;

    @Mock
    AssinaturaService assinaturaService;

    @Test
    @DisplayName("Deve testar método controlador de listagem de todos os clientes cadastrados")
    void deveTestarMetodoControladorDeRecebimentoDeListagemDeTodosOsClientesCadastrados() {
        when(clienteSistemaRepositoryImpl.buscaTodosClientes()).thenReturn(new ArrayList<>());
        Assertions.assertEquals("<200 OK OK,[],[]>",
                clienteSistemaController.listaTodosClientes().toString());
    }

    @Test
    @DisplayName("Deve testar método controlador de captação de leads pré-cadastro")
    void deveTestarMetodoControladorDeRecebimentoDeCaptacaoDeLeadsPreCadastro() {
        when(leadService.encaminhaLeadParaPersistencia(any()))
                .thenReturn(LeadEntityBuilder.builder().build());
        Assertions.assertEquals("<201 CREATED Created,LeadEntity(id=1, nome=Fulano da Silva, " +
                        "email=fulano@gmail.com, origemLeadEnum=MANUAL, telefone=TelefoneEntity(id=1, prefixo=11, " +
                        "numero=979815415, tipoTelefone=MOVEL_WHATSAPP)),[]>",
                clienteSistemaController.captaLeadsPreCadastro(ClienteSistemaDtoBuilder.builder().build()).toString());
    }

    @Test
    @DisplayName("Deve testar método controlador de verificação de já existência do CPF")
    void deveTestarMetodoControladorDeVerificacaoDeJaExistenciaDoCpf() {
        Assertions.assertEquals("<200 OK OK,[]>",
                clienteSistemaController.verificaSeCpfJaExiste(ClienteSistemaDtoBuilder.builder().build()).toString());
    }

    @Test
    @DisplayName("Deve testar método controlador de criação de novo cliente")
    void deveTestarMetodoControladorDeCriacaoDeNovoCliente() {
        when(clienteSistemaService.cadastraNovoCliente(any()))
                .thenReturn(ClienteSistemaEntityBuilder.builder().build());
        Assertions.assertEquals("<201 CREATED Created,ClienteSistemaEntity(id=1, " +
                        "codigoClienteAsaas=cus_000005113026, dataCadastro=2023-02-03, horaCadastro=10:40, " +
                        "dataNascimento=2023-02-03, email=fulano@gmail.com, nome=Fulano, senha=123, cpf=12345678910, " +
                        "saldo=0.0, plano=null, telefone=null, endereco=null, cartao=null),[]>",
                clienteSistemaController.criaNovoCliente(ClienteSistemaDtoBuilder.builder().build()).toString());
    }

    @Test
    @DisplayName("Deve testar método controlador de atualização de cliente")
    void deveTestarMetodoControladorDeAtualizacaoDeCliente() {
        when(clienteSistemaService.atualizaDadosCliente(any(), any()))
                .thenReturn(ClienteSistemaEntityBuilder.builder().build());
        Assertions.assertEquals("<200 OK OK,ClienteSistemaEntity(id=1, codigoClienteAsaas=cus_000005113026, " +
                        "dataCadastro=2023-02-03, horaCadastro=10:40, dataNascimento=2023-02-03, email=fulano@gmail.com, " +
                        "nome=Fulano, senha=123, cpf=12345678910, saldo=0.0, plano=null, telefone=null, endereco=null, " +
                        "cartao=null),[]>",
                clienteSistemaController.atualizaDadosCliente(1L, ClienteSistemaDtoBuilder.builder().build()).toString());
    }

    @Test
    @DisplayName("Deve testar método controlador de atualização de dados de assinatura do cliente")
    void deveTestarMetodoControladorDeAtualizacaoDeDadosDeAssinaturaDoCliente() {
        when(assinaturaService.atualizaDadosAssinatura(any(), any()))
                .thenReturn(ClienteSistemaEntityBuilder.builder().build());
        Assertions.assertEquals("<200 OK OK,ClienteSistemaEntity(id=1, codigoClienteAsaas=cus_000005113026, " +
                        "dataCadastro=2023-02-03, horaCadastro=10:40, dataNascimento=2023-02-03, email=fulano@gmail.com, " +
                        "nome=Fulano, senha=123, cpf=12345678910, saldo=0.0, plano=null, telefone=null, endereco=null, " +
                        "cartao=null),[]>",
                clienteSistemaController.atualizaDadosAssinaturaCliente(
                        1L, ClienteSistemaDtoBuilder.builder().build()).toString());
    }

    @Test
    @DisplayName("Deve testar método controlador de cancelamento de assinatura do cliente")
    void deveTestarMetodoControladorDeCancelamentoDeAssinaturaDoCliente() {
        when(assinaturaService.cancelaAssinatura(any()))
                .thenReturn(ClienteSistemaEntityBuilder.builder().build());
        Assertions.assertEquals("<200 OK OK,ClienteSistemaEntity(id=1, codigoClienteAsaas=cus_000005113026, " +
                        "dataCadastro=2023-02-03, horaCadastro=10:40, dataNascimento=2023-02-03, " +
                        "email=fulano@gmail.com, nome=Fulano, senha=123, cpf=12345678910, saldo=0.0, plano=null, " +
                        "telefone=null, endereco=null, cartao=null),[]>",
                clienteSistemaController.cancelaAssinaturaCliente(1L).toString());
    }

}
