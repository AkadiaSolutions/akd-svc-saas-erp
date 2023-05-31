package br.akd.svc.akadia.services.sistema.clientes;

import br.akd.svc.akadia.models.dto.sistema.clientes.mocks.ClienteDtoBuilder;
import br.akd.svc.akadia.models.dto.sistema.clientes.responses.ClienteResponse;
import br.akd.svc.akadia.models.entities.sistema.clientes.mocks.ClienteEntityBuilder;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks.ColaboradorEntityBuilder;
import br.akd.svc.akadia.repositories.sistema.clientes.impl.ClienteRepositoryImpl;
import br.akd.svc.akadia.services.exceptions.InvalidRequestException;
import br.akd.svc.akadia.services.sistema.colaboradores.AcaoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("Service: Cliente")
class ClienteServiceTest {

    @InjectMocks
    ClienteService clienteService;

    @Mock
    ClienteRepositoryImpl clienteRepositoryImpl;

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

        ClienteResponse clienteResponse = clienteService.criaNovoCliente(
                ColaboradorEntityBuilder.builder()
                        .comEmpresa()
                        .comAcessoCompleto()
                        .build(),
                ClienteDtoBuilder.builder()
                        .comObjetoExclusaoFalse()
                        .comEndereco()
                        .comTelefone()
                        .build());

        Assertions.assertEquals("ClienteResponse(id=1, dataCadastro=2023-02-27, horaCadastro=17:40, " +
                "dataNascimento=1998-07-21, nome=Gabriel Lagrota, cpfCnpj=582.645.389-32, inscricaoEstadual=145574080114, " +
                "email=gabrielafonso@mail.com.br, statusCliente=null, tipoPessoa=null, qtdOrdensRealizadas=null, " +
                "giroTotal=null, exclusaoCliente=ExclusaoClienteResponse(dataExclusao=null, horaExclusao=null, " +
                "excluido=false), endereco=null, telefone=null, nomeColaboradorResponsavel=João da Silva)", clienteResponse.toString());
    }

    @Test
    @DisplayName("Deve testar método de atualização do cliente")
    void deveTestarMetodoDeAtualizacaoDeCliente() {

        when(clienteRepositoryImpl.implementaBuscaPorId(any(), any()))
                .thenReturn(ClienteEntityBuilder.builder().comColaborador().comExclusao(false).build());

        when(clienteRepositoryImpl.implementaPersistencia(any()))
                .thenReturn(ClienteEntityBuilder.builder().comColaborador().comExclusao(false).build());

        doNothing().when(acaoService).salvaHistoricoColaborador(any(), any(), any(), any(), any());

        ClienteResponse cliente = clienteService.atualizaCliente(
                ColaboradorEntityBuilder.builder()
                        .comEmpresa()
                        .comAcessoCompleto()
                        .build(),
                1L,
                ClienteDtoBuilder.builder()
                        .comObjetoExclusaoFalse()
                        .comTelefone()
                        .comEndereco()
                        .build());

        Assertions.assertEquals("ClienteResponse(id=1, dataCadastro=2023-02-27, horaCadastro=17:40, " +
                        "dataNascimento=1998-07-21, nome=Gabriel Lagrota, cpfCnpj=582.645.389-32, " +
                        "inscricaoEstadual=145574080114, email=gabrielafonso@mail.com.br, statusCliente=null, " +
                        "tipoPessoa=null, qtdOrdensRealizadas=null, giroTotal=null, " +
                        "exclusaoCliente=ExclusaoClienteResponse(dataExclusao=null, horaExclusao=null, excluido=false), " +
                        "endereco=null, telefone=null, nomeColaboradorResponsavel=João da Silva)",
                cliente.toString());
    }

    @Test
    @DisplayName("Deve testar método de validação se cpfCnpj já existe com CPF existente")
    void deveTestarMetodoDeValidacaoSeCpfCnpjJaExisteComCpfExistente() {
        when(clienteRepositoryImpl.implementaBuscaPorCpfCnpjIdentico(any(), any()))
                .thenReturn(Optional.of(ClienteEntityBuilder.builder().build()));

        try {
            clienteService.validaSeCpfCnpjJaExiste("12345678910", 1L);
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

        try {
            clienteService.validaSeCpfCnpjJaExiste("72381189000110", 1L);
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

        try {
            clienteService.validaSeInscricaoEstadualJaExiste("12.123.123.12", 1L);
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

        Assertions.assertEquals("ClienteResponse(id=1, dataCadastro=2023-02-27, horaCadastro=17:40, " +
                        "dataNascimento=1998-07-21, nome=Gabriel Lagrota, cpfCnpj=582.645.389-32, " +
                        "inscricaoEstadual=145574080114, email=gabrielafonso@mail.com.br, statusCliente=null, " +
                        "tipoPessoa=null, qtdOrdensRealizadas=null, giroTotal=null, " +
                        "exclusaoCliente=ExclusaoClienteResponse(dataExclusao=null, horaExclusao=null, excluido=false), " +
                        "endereco=null, telefone=null, nomeColaboradorResponsavel=João da Silva)",
                clienteService.removeCliente(ColaboradorEntityBuilder.builder().comEmpresa().build(), 1L).toString());
    }

    @Test
    @DisplayName("Deve testar método de remoção de cliente com cliente já excluído")
    void deveTestarMetodoDeRemocaoDeClienteComClienteJaExcluido() {
        when(clienteRepositoryImpl.implementaBuscaPorId(anyLong(), anyLong()))
                .thenReturn(ClienteEntityBuilder.builder().comExclusao(true).build());
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

        ClienteResponse clienteResponse = clienteService.realizaBuscaDeClientePorId(
                ColaboradorEntityBuilder.builder().comEmpresa().build(),
                1L);

        Assertions.assertEquals("ClienteResponse(id=1, dataCadastro=2023-02-27, horaCadastro=17:40, " +
                "dataNascimento=1998-07-21, nome=Gabriel Lagrota, cpfCnpj=582.645.389-32, " +
                "inscricaoEstadual=145574080114, email=gabrielafonso@mail.com.br, statusCliente=null, tipoPessoa=null, " +
                "qtdOrdensRealizadas=null, giroTotal=null, exclusaoCliente=ExclusaoClienteResponse(dataExclusao=null, " +
                "horaExclusao=null, excluido=false), endereco=null, telefone=null, " +
                "nomeColaboradorResponsavel=João da Silva)", clienteResponse.toString());
    }

}
