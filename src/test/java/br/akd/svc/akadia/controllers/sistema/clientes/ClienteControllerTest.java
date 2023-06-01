package br.akd.svc.akadia.controllers.sistema.clientes;

import br.akd.svc.akadia.config.security.JWTUtil;
import br.akd.svc.akadia.models.dto.sistema.clientes.mocks.ClienteDtoBuilder;
import br.akd.svc.akadia.models.dto.sistema.clientes.responses.ClientePageResponse;
import br.akd.svc.akadia.models.dto.sistema.clientes.responses.ClienteResponse;
import br.akd.svc.akadia.models.dto.sistema.clientes.responses.mocks.ClientePageResponseBuilder;
import br.akd.svc.akadia.models.dto.sistema.clientes.responses.mocks.ClienteResponseBuilder;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks.ColaboradorEntityBuilder;
import br.akd.svc.akadia.services.sistema.clientes.ClienteRelatorioService;
import br.akd.svc.akadia.services.sistema.clientes.ClienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("Controller: Cliente")
class ClienteControllerTest {

    @InjectMocks
    ClienteController clienteController;

    @Mock
    ClienteService clienteService;

    @Mock
    ClienteRelatorioService relatorioService;

    @Mock
    JWTUtil jwtUtil;

    @Test
    @DisplayName("Deve testar método controlador de criação de novo cliente")
    void deveTestarMetodoControladorDeCriacaoDeNovoCliente() {

        when(clienteService.criaNovoCliente(any(), any())).thenReturn(ClienteResponse.builder().build());
        HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
        when(jwtUtil.obtemUsuarioAtivo(mockedRequest)).thenReturn(ColaboradorEntityBuilder.builder().comEmpresa().build());

        ResponseEntity<ClienteResponse> cliente =
                clienteController.criaNovoCliente(mockedRequest, ClienteDtoBuilder.builder().build());

        Assertions.assertEquals("<201 CREATED Created,ClienteResponse(id=null, dataCadastro=null, " +
                        "horaCadastro=null, dataNascimento=null, nome=null, cpfCnpj=null, inscricaoEstadual=null, " +
                        "email=null, statusCliente=null, tipoPessoa=null, qtdOrdensRealizadas=null, giroTotal=null, " +
                        "exclusaoCliente=null, endereco=null, telefone=null, nomeColaboradorResponsavel=null),[]>",
                cliente.toString());
    }

    @Test
    @DisplayName("Deve testar método controlador de atualização de cliente")
    void deveTestarMetodoControladorDeAtualizacaoDoCliente() {

        when(clienteService.atualizaCliente(any(), any(), any())).thenReturn(ClienteResponse.builder().build());
        HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
        when(jwtUtil.obtemUsuarioAtivo(mockedRequest)).thenReturn(ColaboradorEntityBuilder.builder().comEmpresa().build());

        ResponseEntity<ClienteResponse> cliente =
                clienteController.atualizaCliente(mockedRequest, ClienteDtoBuilder.builder().build(), 1L);

        Assertions.assertEquals("<200 OK OK,ClienteResponse(id=null, dataCadastro=null, horaCadastro=null, " +
                "dataNascimento=null, nome=null, cpfCnpj=null, inscricaoEstadual=null, email=null, " +
                "statusCliente=null, tipoPessoa=null, qtdOrdensRealizadas=null, giroTotal=null, exclusaoCliente=null, " +
                "endereco=null, telefone=null, nomeColaboradorResponsavel=null),[]>", cliente.toString());
    }

    @Test
    @DisplayName("Deve testar método de obtenção de cliente por id")
    void deveTestarMetodoDeVerificacaoDeObtencaoDeClientePorId() {
        HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
        when(jwtUtil.obtemUsuarioAtivo(mockedRequest)).thenReturn(ColaboradorEntityBuilder.builder().comEmpresa().build());

        ResponseEntity<?> cliente =
                clienteController.obtemClientePorId(1L, mockedRequest);

        Assertions.assertEquals("<200 OK OK,[]>", cliente.toString());
    }

    @Test
    @DisplayName("Deve testar método de verificação de duplicidade da Inscrição Estadual")
    void deveTestarMetodoDeVerificacaoDeDuplicidadeDaInscricaoEstadual() {

        HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
        when(jwtUtil.obtemUsuarioAtivo(mockedRequest)).thenReturn(ColaboradorEntityBuilder.builder().comEmpresa().build());

        ResponseEntity<?> cliente =
                clienteController.verificaDuplicidadeInscricaoEstadual(mockedRequest, "111111111111");

        Assertions.assertEquals("<200 OK OK,[]>", cliente.toString());
    }

    @Test
    @DisplayName("Deve testar método de verificação de duplicidade do CPF/CNPJ")
    void deveTestarMetodoDeVerificacaoDeDuplicidadeDoCpfCnpj() {

        HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
        when(jwtUtil.obtemUsuarioAtivo(mockedRequest)).thenReturn(ColaboradorEntityBuilder.builder().comEmpresa().build());

        ResponseEntity<?> cliente =
                clienteController.verificaDuplicidadeCpfCnpj(mockedRequest, "12345678910");

        Assertions.assertEquals("<200 OK OK,[]>", cliente.toString());
    }

    @Test
    @DisplayName("Deve testar método de obtenção de clientes paginados")
    void deveTestarMetodoObtencaoDeClientesPaginados() {
        when(clienteService.realizaBuscaPaginadaPorClientes(any(), any(), any()))
                .thenReturn(ClientePageResponseBuilder.builder().build());
        HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
        when(jwtUtil.obtemUsuarioAtivo(mockedRequest)).thenReturn(ColaboradorEntityBuilder.builder().comEmpresa().build());

        ResponseEntity<ClientePageResponse> cliente =
                clienteController.obtemClientesPaginados("busca", PageRequest.of(0, 20), mockedRequest);

        Assertions.assertEquals("<200 OK OK,ClientePageResponse(content=null, empty=true, first=true, " +
                "last=true, number=0, numberOfElements=0, pageNumber=0, pageSize=0, paged=true, unpaged=false, " +
                "size=0, totalElements=0, totalPages=0),[]>", cliente.toString());
    }

    @Test
    @DisplayName("Deve testar método controlador de remoção de cliente")
    void deveTestarMetodoControladorDeRemocaoDoCliente() {
        when(clienteService.removeCliente(any(), anyLong())).thenReturn(ClienteResponseBuilder.builder().build());
        HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
        when(jwtUtil.obtemUsuarioAtivo(mockedRequest)).thenReturn(ColaboradorEntityBuilder.builder().comEmpresa().build());

        ResponseEntity<ClienteResponse> cliente =
                clienteController.removeCliente(mockedRequest, 1L);

        Assertions.assertEquals("<200 OK OK,ClienteResponse(id=1, dataCadastro=2023-02-27, " +
                "horaCadastro=17:40, dataNascimento=1998-07-21, nome=Gabriel Lagrota, cpfCnpj=582.645.389-32, " +
                "inscricaoEstadual=145574080114, email=gabrielafonso@mail.com.br, statusCliente=COMUM, " +
                "tipoPessoa=FISICA, qtdOrdensRealizadas=0, giroTotal=0.0, exclusaoCliente=null, endereco=null, " +
                "telefone=null, nomeColaboradorResponsavel=Fulano),[]>", cliente.toString());
    }

    @Test
    @DisplayName("Deve testar método controlador de remoção de cliente em massa")
    void deveTestarMetodoControladorDeRemocaoDoClienteEmMassa() {
        HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
        when(jwtUtil.obtemUsuarioAtivo(mockedRequest)).thenReturn(ColaboradorEntityBuilder.builder().comEmpresa().build());

        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ResponseEntity<?> remocaoEmMassa = clienteController.removeClientesEmMassa(mockedRequest, ids);

        Assertions.assertEquals("<200 OK OK,[]>", remocaoEmMassa.toString());
    }

    @Test
    @DisplayName("Deve testar método controlador de geração de relatório em PDF")
    void deveTestarMetodoControladorDeGeracaoDeRelatorioEmPdf() throws IOException {
        HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse mockedResponse = Mockito.mock(HttpServletResponse.class);
        when(jwtUtil.obtemUsuarioAtivo(mockedRequest)).thenReturn(ColaboradorEntityBuilder.builder().comEmpresa().build());
        List<Long> ids = new ArrayList<>();
        ids.add(1L);

        Mockito.doNothing().when(relatorioService).exportarPdf(any(), any(), any());

        clienteController.relatorio(mockedResponse, mockedRequest, ids);

        Assertions.assertNotNull(mockedResponse);
    }

}
