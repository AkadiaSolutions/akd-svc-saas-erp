package br.akd.svc.akadia.services.sistema.clientes;

import br.akd.svc.akadia.models.dto.sistema.clientes.requests.mocks.ClienteRequestBuilder;
import br.akd.svc.akadia.modules.erp.clientes.models.dto.response.page.ClientePageResponse;
import br.akd.svc.akadia.modules.erp.clientes.models.dto.response.ClienteResponse;
import br.akd.svc.akadia.models.dto.sistema.clientes.responses.mocks.ClientePageResponseBuilder;
import br.akd.svc.akadia.models.dto.sistema.clientes.responses.mocks.ClienteResponseBuilder;
import br.akd.svc.akadia.modules.erp.clientes.models.entity.ClienteEntity;
import br.akd.svc.akadia.models.entities.sistema.clientes.mocks.ClienteEntityBuilder;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks.ColaboradorEntityBuilder;
import br.akd.svc.akadia.modules.erp.clientes.repository.ClienteRepository;
import br.akd.svc.akadia.modules.erp.clientes.repository.impl.ClienteRepositoryImpl;
import br.akd.svc.akadia.modules.erp.clientes.services.ClienteService;
import br.akd.svc.akadia.modules.erp.clientes.services.ClienteTypeConverter;
import br.akd.svc.akadia.modules.erp.clientes.services.ClienteValidationService;
import br.akd.svc.akadia.exceptions.InvalidRequestException;
import br.akd.svc.akadia.modules.erp.colaboradores.acao.services.AcaoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Service: Cliente")
class ClienteServiceTest {

    @InjectMocks
    ClienteService clienteService;

    @Mock
    ClienteValidationService clienteValidationService;

    @Mock
    ClienteTypeConverter clienteTypeConverter;

    @Mock
    ClienteRepositoryImpl clienteRepositoryImpl;

    @Mock
    ClienteRepository clienteRepository;

    @Mock
    AcaoService acaoService;

    @Test
    @DisplayName("Deve testar método de criação de novo cliente")
    void deveTestarMetodoDeCriacaoDeNovoCliente() {

        when(clienteRepositoryImpl.implementaBuscaPorId(any(), any()))
                .thenReturn(ClienteEntityBuilder.builder().comColaborador().comExclusao(false).build());

        when(clienteRepositoryImpl.implementaPersistencia(any()))
                .thenReturn(ClienteEntityBuilder.builder().comColaborador().comExclusao(false).build());

        doNothing().when(acaoService).salvaHistoricoColaborador(any(), any(), any(), any(), any());

        when(clienteTypeConverter.converteClienteEntityParaClienteResponse(any()))
                .thenReturn(ClienteResponseBuilder.builder().build());

        when(clienteTypeConverter.converteClienteEntityParaClienteResponse(any()))
                .thenReturn(ClienteResponseBuilder.builder().build());

        ClienteResponse clienteResponse = clienteService.criaNovoCliente(
                ColaboradorEntityBuilder.builder()
                        .comEmpresa()
                        .comAcessoCompleto()
                        .build(),
                ClienteRequestBuilder.builder()
                        .comEndereco()
                        .comTelefone()
                        .build());

        Assertions.assertEquals("ClienteResponse(id=1, dataCadastro=2023-02-27, horaCadastro=17:40, " +
                "dataNascimento=1998-07-21, nome=Gabriel Lagrota, cpfCnpj=582.645.389-32, " +
                "inscricaoEstadual=145574080114, email=gabrielafonso@mail.com.br, statusCliente=COMUM, " +
                "tipoPessoa=FISICA, qtdOrdensRealizadas=0, giroTotal=0.0, exclusaoEntity=null, endereco=null, " +
                "telefone=null, nomeColaboradorResponsavel=Fulano)", clienteResponse.toString());
    }

    @Test
    @DisplayName("Deve testar método de atualização do cliente")
    void deveTestarMetodoDeAtualizacaoDeCliente() {

        when(clienteRepositoryImpl.implementaBuscaPorId(any(), any()))
                .thenReturn(ClienteEntityBuilder.builder().comColaborador().comExclusao(false).build());

        when(clienteRepositoryImpl.implementaPersistencia(any()))
                .thenReturn(ClienteEntityBuilder.builder().comColaborador().comExclusao(false).build());

        doNothing().when(acaoService).salvaHistoricoColaborador(any(), any(), any(), any(), any());

        when(clienteTypeConverter.converteClienteEntityParaClienteResponse(any()))
                .thenReturn(ClienteResponseBuilder.builder().build());

        ClienteResponse cliente = clienteService.atualizaCliente(
                ColaboradorEntityBuilder.builder()
                        .comEmpresa()
                        .comAcessoCompleto()
                        .build(),
                1L,
                ClienteRequestBuilder.builder()
                        .comTelefone()
                        .comEndereco()
                        .build());

        Assertions.assertEquals("ClienteResponse(id=1, dataCadastro=2023-02-27, horaCadastro=17:40, " +
                        "dataNascimento=1998-07-21, nome=Gabriel Lagrota, cpfCnpj=582.645.389-32, " +
                        "inscricaoEstadual=145574080114, email=gabrielafonso@mail.com.br, statusCliente=COMUM, " +
                        "tipoPessoa=FISICA, qtdOrdensRealizadas=0, giroTotal=0.0, exclusaoEntity=null, endereco=null, " +
                        "telefone=null, nomeColaboradorResponsavel=Fulano)",
                cliente.toString());
    }

    @Test
    @DisplayName("Deve testar método de validação se cpfCnpj já existe com CPF existente")
    void deveTestarMetodoDeValidacaoSeCpfCnpjJaExisteComCpfExistente() {
        when(clienteRepositoryImpl.implementaBuscaPorCpfCnpjIdentico(any(), any()))
                .thenReturn(Optional.of(ClienteEntityBuilder.builder().build()));

        doThrow(new InvalidRequestException("O CPF informado já existe"))
                .when(clienteValidationService).validaSeCpfCnpjJaExiste(any(), any());

        try {
            clienteValidationService.validaSeCpfCnpjJaExiste("12345678910", 1L);
            Assertions.fail();
        } catch (InvalidRequestException invalidRequestException) {
            Assertions.assertEquals("O CPF informado já existe",
                    invalidRequestException.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar método de validação se cpfCnpj já existe com CNPJ existente")
    void deveTestarMetodoDeValidacaoSeCpfCnpjJaExisteComCnpjExistente() {
        when(clienteRepositoryImpl.implementaBuscaPorCpfCnpjIdentico(any(), any()))
                .thenReturn(Optional.of(ClienteEntityBuilder.builder().build()));

        doThrow(new InvalidRequestException("O CNPJ informado já existe"))
                .when(clienteValidationService).validaSeCpfCnpjJaExiste(any(), any());

        try {
            clienteValidationService.validaSeCpfCnpjJaExiste("72381189000110", 1L);
            Assertions.fail();
        } catch (InvalidRequestException invalidRequestException) {
            Assertions.assertEquals("O CNPJ informado já existe",
                    invalidRequestException.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar método de validação se inscrição estadual já existe com valor existente")
    void deveTestarMetodoDeValidacaoSeInscricaoEstadualJaExisteComValorExistente() {
        when(clienteRepositoryImpl.implementaBuscaPorInscricaoEstadualIdentica(any(), any()))
                .thenReturn(Optional.of(ClienteEntityBuilder.builder().build()));

        doThrow(new InvalidRequestException("A inscrição estadual informada já existe"))
                .when(clienteValidationService).validaSeInscricaoEstadualJaExiste(any(), any());

        try {
            clienteValidationService.validaSeInscricaoEstadualJaExiste("12.123.123.12", 1L);
            Assertions.fail();
        } catch (InvalidRequestException invalidRequestException) {
            Assertions.assertEquals("A inscrição estadual informada já existe",
                    invalidRequestException.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar método de obtenção de id do telefone do cliente atualizável se tiver sem telefone")
    void deveTestarMetodoDeObtencaoDeIdTelefoneDoClienteAtualizavelSeTiverSemTelefone() {
        Assertions.assertNull(clienteService
                .obtemIdTelefoneDoClienteAtualizavelSeTiver(ClienteEntityBuilder.builder().build()));
    }

    @Test
    @DisplayName("Deve testar método de obtenção de id do endereço do cliente atualizável se tiver sem endereço")
    void deveTestarMetodoDeObtencaoDeIdEnderecoDoClienteAtualizavelSeTiverSemEndereco() {
        Assertions.assertNull(clienteService
                .obtemIdEnderecoDoClienteAtualizavelSeTiver(ClienteEntityBuilder.builder().build()));
    }

    @Test
    @DisplayName("Deve testar método de remoção de cliente")
    void deveTestarMetodoDeRemocaoDeCliente() {

        when(clienteRepositoryImpl.implementaBuscaPorId(anyLong(), anyLong()))
                .thenReturn(ClienteEntityBuilder.builder().comColaborador().comExclusao(false).build());

        when(clienteRepositoryImpl.implementaPersistencia(any()))
                .thenReturn(ClienteEntityBuilder.builder().comColaborador().comExclusao(false).build());

        when(clienteTypeConverter.converteClienteEntityParaClienteResponse(any()))
                .thenReturn(ClienteResponseBuilder.builder().build());

        Assertions.assertEquals("ClienteResponse(id=1, dataCadastro=2023-02-27, horaCadastro=17:40, " +
                        "dataNascimento=1998-07-21, nome=Gabriel Lagrota, cpfCnpj=582.645.389-32, " +
                        "inscricaoEstadual=145574080114, email=gabrielafonso@mail.com.br, statusCliente=COMUM, " +
                        "tipoPessoa=FISICA, qtdOrdensRealizadas=0, giroTotal=0.0, exclusaoEntity=null, " +
                        "endereco=null, telefone=null, nomeColaboradorResponsavel=Fulano)",
                clienteService.removeCliente(ColaboradorEntityBuilder.builder().comEmpresa().build(), 1L).toString());
    }

    @Test
    @DisplayName("Deve testar método de remoção de cliente com cliente já excluído")
    void deveTestarMetodoDeRemocaoDeClienteComClienteJaExcluido() {
        when(clienteRepositoryImpl.implementaBuscaPorId(anyLong(), anyLong()))
                .thenReturn(ClienteEntityBuilder.builder().comExclusao(true).build());

        Mockito.doThrow(new InvalidRequestException("O cliente selecionado já foi excluído"))
                .when(clienteValidationService).validaSeClienteEstaExcluido(any(), any());

        try {
            clienteService.removeCliente(ColaboradorEntityBuilder.builder().comEmpresa().build(), 1L);
            Assertions.fail();
        } catch (InvalidRequestException invalidRequestException) {
            Assertions.assertEquals("O cliente selecionado já foi excluído",
                    invalidRequestException.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar método de remoção de cliente em massa")
    void deveTestarMetodoDeRemocaoDeClienteEmMassa() {
        when(clienteRepositoryImpl.implementaBuscaPorId(anyLong(), anyLong()))
                .thenReturn(ClienteEntityBuilder.builder().comExclusao(false).build());

        List<Long> ids = new ArrayList<>();
        ids.add(1L);

        clienteService.removeClientesEmMassa(ColaboradorEntityBuilder.builder().comEmpresa().build(), ids);
        Assertions.assertNotNull(ids);
    }

    @Test
    @DisplayName("Deve testar método de remoção de cliente com exception")
    void deveTestarMetodoDeRemocaoDeClienteEmMassaComException() {
        when(clienteRepositoryImpl.implementaBuscaPorId(anyLong(), anyLong()))
                .thenReturn(ClienteEntityBuilder.builder().comExclusao(false).build());

        List<Long> ids = new ArrayList<>();
        try {
            clienteService.removeClientesEmMassa(ColaboradorEntityBuilder.builder().comEmpresa().build(), ids);
            Assertions.fail();
        } catch (InvalidRequestException invalidRequestException) {
            Assertions.assertEquals("Nenhum cliente foi encontrado para remoção",
                    invalidRequestException.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar método de busca de cliente por ID")
    void deveTestarMetodoDeBuscaDeClientePorId() {

        when(clienteRepositoryImpl.implementaBuscaPorId(any(), any()))
                .thenReturn(ClienteEntityBuilder.builder().comColaborador().comExclusao(false).build());

        when(clienteTypeConverter.converteClienteEntityParaClienteResponse(any()))
                .thenReturn(ClienteResponseBuilder.builder().build());

        ClienteResponse clienteResponse = clienteService.realizaBuscaDeClientePorId(
                ColaboradorEntityBuilder.builder().comEmpresa().build(),
                1L);

        Assertions.assertEquals("ClienteResponse(id=1, dataCadastro=2023-02-27, horaCadastro=17:40, " +
                "dataNascimento=1998-07-21, nome=Gabriel Lagrota, cpfCnpj=582.645.389-32, " +
                "inscricaoEstadual=145574080114, email=gabrielafonso@mail.com.br, statusCliente=COMUM, " +
                "tipoPessoa=FISICA, qtdOrdensRealizadas=0, giroTotal=0.0, exclusaoEntity=null, endereco=null, " +
                "telefone=null, nomeColaboradorResponsavel=Fulano)", clienteResponse.toString());
    }

    @Test
    @DisplayName("Deve testar método de busca paginada de clientes com campo de busca preenchido")
    void deveTestarMetodoDeBuscaPaginadaDeClientesComCampoBuscaPreenchido() {
        List<ClienteEntity> clientes = new ArrayList<>();
        clientes.add(ClienteEntityBuilder.builder()
                .comExclusao(true)
                .comColaborador()
                .build());
        Pageable pageable = PageRequest.of(0, 10);

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), clientes.size());
        Page<ClienteEntity> clientesPaged = new PageImpl<>(clientes.subList(start, end), pageable, clientes.size());

        when(clienteRepository.buscaPorClientesTypeAhead(any(), any(), any())).thenReturn(clientesPaged);

        when(clienteTypeConverter.converteListaDeClientesEntityParaClientesResponse(any()))
                .thenReturn(ClientePageResponseBuilder.builder().build());

        ClientePageResponse clientePageResponse = clienteService.realizaBuscaPaginadaPorClientes(
                ColaboradorEntityBuilder.builder().comEmpresa().comAcessoCompleto().build(),
                PageRequest.of(0, 10),
                "busca");

        Assertions.assertEquals("ClientePageResponse(content=null, empty=true, first=true, last=true, " +
                "number=0, numberOfElements=0, pageNumber=0, pageSize=0, paged=true, unpaged=false, size=0, " +
                "totalElements=0, totalPages=0)", clientePageResponse.toString());
    }

    @Test
    @DisplayName("Deve testar método de busca paginada de clientes com campo de busca nulo")
    void deveTestarMetodoDeBuscaPaginadaDeClientesComCampoBuscaVazio() {
        List<ClienteEntity> clientes = new ArrayList<>();
        clientes.add(ClienteEntityBuilder.builder()
                .comExclusao(true)
                .comColaborador()
                .build());
        Pageable pageable = PageRequest.of(0, 10);

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), clientes.size());
        Page<ClienteEntity> clientesPaged = new PageImpl<>(clientes.subList(start, end), pageable, clientes.size());

        when(clienteRepository.buscaPorClientes(any(), any())).thenReturn(clientesPaged);

        when(clienteTypeConverter.converteListaDeClientesEntityParaClientesResponse(any()))
                .thenReturn(ClientePageResponseBuilder.builder().build());

        ClientePageResponse clientePageResponse = clienteService.realizaBuscaPaginadaPorClientes(
                ColaboradorEntityBuilder.builder().comEmpresa().comAcessoCompleto().build(),
                PageRequest.of(0, 10),
                null);

        Assertions.assertEquals("ClientePageResponse(content=null, empty=true, first=true, last=true, " +
                "number=0, numberOfElements=0, pageNumber=0, pageSize=0, paged=true, unpaged=false, size=0, " +
                "totalElements=0, totalPages=0)", clientePageResponse.toString());
    }

}
