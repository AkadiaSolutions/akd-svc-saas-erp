package br.akd.svc.akadia.controllers.sistema.clientes;

import br.akd.svc.akadia.config.security.JWTUtil;
import br.akd.svc.akadia.models.dto.sistema.clientes.mocks.ClienteDtoBuilder;
import br.akd.svc.akadia.models.entities.sistema.clientes.ClienteEntity;
import br.akd.svc.akadia.models.entities.sistema.clientes.mocks.ClienteEntityBuilder;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks.ColaboradorEntityBuilder;
import br.akd.svc.akadia.services.sistema.clientes.ClienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("Controller: Cliente")
class ClienteControllerTest {

    @InjectMocks
    ClienteController clienteController;

    @Mock
    ClienteService clienteService;

    @Mock
    JWTUtil jwtUtil;

    @Test
    @DisplayName("Deve testar método controlador de busca de cliente por mês e ano")
    void deveTestarMetodoControladorDeBuscaDeClientePorMesAno() {

        when(clienteService.removeCliente(any(), any())).thenReturn(ClienteEntityBuilder.builder().build());
        HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
        when(jwtUtil.obtemUsuarioAtivo(mockedRequest)).thenReturn(ColaboradorEntityBuilder.builder().comEmpresa().build());

        ResponseEntity<List<ClienteEntity>> clientes =
                clienteController.buscaClientesPorMesAno(mockedRequest, "3", "2023");

        Assertions.assertEquals("<200 OK OK,[],[]>", clientes.toString());
    }

    @Test
    @DisplayName("Deve testar método controlador de busca de cliente por range de data")
    void deveTestarMetodoControladorDeBuscaDeClientePorRangeDeData() {

        when(clienteService.removeCliente(any(), any())).thenReturn(ClienteEntityBuilder.builder().build());
        HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
        when(jwtUtil.obtemUsuarioAtivo(mockedRequest)).thenReturn(ColaboradorEntityBuilder.builder().comEmpresa().build());

        ResponseEntity<List<ClienteEntity>> clientes =
                clienteController.buscaClientesPorRangeDeData(mockedRequest, "2023-03-07", "2023-03-08");

        Assertions.assertEquals("<200 OK OK,[],[]>", clientes.toString());
    }

    @Test
    @DisplayName("Deve testar método controlador de busca de cliente por telefone")
    void deveTestarMetodoControladorDeBuscaDeClientePorTelefone() {

        when(clienteService.removeCliente(any(), any())).thenReturn(ClienteEntityBuilder.builder().build());
        HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
        when(jwtUtil.obtemUsuarioAtivo(mockedRequest)).thenReturn(ColaboradorEntityBuilder.builder().comEmpresa().build());

        ResponseEntity<List<ClienteEntity>> clientes =
                clienteController.buscaClientesPeloTelefone(mockedRequest, "954568765");

        Assertions.assertEquals("<200 OK OK,[],[]>", clientes.toString());
    }

    @Test
    @DisplayName("Deve testar método controlador de busca de cliente por inscrição estadual")
    void deveTestarMetodoControladorDeBuscaDeClientePorInscricaoEstadual() {

        when(clienteService.removeCliente(any(), any())).thenReturn(ClienteEntityBuilder.builder().build());
        HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
        when(jwtUtil.obtemUsuarioAtivo(mockedRequest)).thenReturn(ColaboradorEntityBuilder.builder().comEmpresa().build());

        ResponseEntity<List<ClienteEntity>> clientes =
                clienteController.buscaClientesPelaInscricaoEstadual(mockedRequest, "123.456.789-10");

        Assertions.assertEquals("<200 OK OK,[],[]>", clientes.toString());
    }

    @Test
    @DisplayName("Deve testar método controlador de busca de cliente por cpf/cnpj")
    void deveTestarMetodoControladorDeBuscaDeClientePorCpfOuCnpj() {

        when(clienteService.removeCliente(any(), any())).thenReturn(ClienteEntityBuilder.builder().build());
        HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
        when(jwtUtil.obtemUsuarioAtivo(mockedRequest)).thenReturn(ColaboradorEntityBuilder.builder().comEmpresa().build());

        ResponseEntity<List<ClienteEntity>> clientes =
                clienteController.buscaClientesPeloCpfOuCnpj(mockedRequest, "123.456.789-10");

        Assertions.assertEquals("<200 OK OK,[],[]>", clientes.toString());
    }

    @Test
    @DisplayName("Deve testar método controlador de busca de cliente por nome")
    void deveTestarMetodoControladorDeBuscaDeClientePorNome() {

        when(clienteService.removeCliente(any(), any())).thenReturn(ClienteEntityBuilder.builder().build());
        HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
        when(jwtUtil.obtemUsuarioAtivo(mockedRequest)).thenReturn(ColaboradorEntityBuilder.builder().comEmpresa().build());

        ResponseEntity<List<ClienteEntity>> clientes =
                clienteController.buscaClientesPeloNome(mockedRequest, "Fulano");

        Assertions.assertEquals("<200 OK OK,[],[]>", clientes.toString());
    }

    @Test
    @DisplayName("Deve testar método controlador de busca de cliente por e-mail")
    void deveTestarMetodoControladorDeBuscaDeClientePorEmail() {

        when(clienteService.removeCliente(any(), any())).thenReturn(ClienteEntityBuilder.builder().build());
        HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
        when(jwtUtil.obtemUsuarioAtivo(mockedRequest)).thenReturn(ColaboradorEntityBuilder.builder().comEmpresa().build());

        ResponseEntity<List<ClienteEntity>> clientes =
                clienteController.buscaClientesPeloEmail(mockedRequest, "email@email.com.br");

        Assertions.assertEquals("<200 OK OK,[],[]>", clientes.toString());
    }

    @Test
    @DisplayName("Deve testar método controlador de criação de novo cliente")
    void deveTestarMetodoControladorDeCriacaoDeNovoCliente() {

        when(clienteService.criaNovoCliente(any(), any())).thenReturn(ClienteEntityBuilder.builder().build());
        HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
        when(jwtUtil.obtemUsuarioAtivo(mockedRequest)).thenReturn(ColaboradorEntityBuilder.builder().comEmpresa().build());

        ResponseEntity<ClienteEntity> cliente =
                clienteController.criaNovoCliente(mockedRequest, ClienteDtoBuilder.builder().build());

        Assertions.assertEquals("<201 CREATED Created,ClienteEntity(id=1, dataCadastro=2023-02-27, " +
                "horaCadastro=17:40, dataNascimento=1998-07-21, nome=Gabriel Lagrota, cpfCnpj=582.645.389-32, " +
                "inscricaoEstadual=145574080114, email=gabrielafonso@mail.com.br, exclusaoCliente=null, endereco=null, " +
                "telefone=null, colaboradorResponsavel=null, empresa=null),[]>", cliente.toString());
    }

    @Test
    @DisplayName("Deve testar método controlador de atualização de cliente")
    void deveTestarMetodoControladorDeAtualizacaoDoCliente() {

        when(clienteService.atualizaCliente(any(), any(), any())).thenReturn(ClienteEntityBuilder.builder().build());
        HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
        when(jwtUtil.obtemUsuarioAtivo(mockedRequest)).thenReturn(ColaboradorEntityBuilder.builder().comEmpresa().build());

        ResponseEntity<ClienteEntity> cliente =
                clienteController.atualizaCliente(mockedRequest, ClienteDtoBuilder.builder().build(), 1L);

        Assertions.assertEquals("<200 OK OK,ClienteEntity(id=1, dataCadastro=2023-02-27, horaCadastro=17:40, " +
                "dataNascimento=1998-07-21, nome=Gabriel Lagrota, cpfCnpj=582.645.389-32, " +
                "inscricaoEstadual=145574080114, email=gabrielafonso@mail.com.br, exclusaoCliente=null, " +
                "endereco=null, telefone=null, colaboradorResponsavel=null, empresa=null),[]>", cliente.toString());
    }

    @Test
    @DisplayName("Deve testar método controlador de remoção de cliente")
    void deveTestarMetodoControladorDeRemocaoDoCliente() {

        when(clienteService.removeCliente(any(), any())).thenReturn(ClienteEntityBuilder.builder().build());
        HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
        when(jwtUtil.obtemUsuarioAtivo(mockedRequest)).thenReturn(ColaboradorEntityBuilder.builder().comEmpresa().build());

        ResponseEntity<ClienteEntity> cliente =
                clienteController.removeCliente(mockedRequest, 1L);

        Assertions.assertEquals("<200 OK OK,ClienteEntity(id=1, dataCadastro=2023-02-27, horaCadastro=17:40, " +
                "dataNascimento=1998-07-21, nome=Gabriel Lagrota, cpfCnpj=582.645.389-32, " +
                "inscricaoEstadual=145574080114, email=gabrielafonso@mail.com.br, exclusaoCliente=null, " +
                "endereco=null, telefone=null, colaboradorResponsavel=null, empresa=null),[]>", cliente.toString());
    }

}
