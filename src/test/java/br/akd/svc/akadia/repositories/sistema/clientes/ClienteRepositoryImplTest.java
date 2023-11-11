package br.akd.svc.akadia.repositories.sistema.clientes;

import br.akd.svc.akadia.modules.erp.clientes.models.entity.ClienteEntity;
import br.akd.svc.akadia.models.entities.sistema.clientes.mocks.ClienteEntityBuilder;
import br.akd.svc.akadia.modules.erp.clientes.repository.ClienteRepository;
import br.akd.svc.akadia.modules.erp.clientes.repository.impl.ClienteRepositoryImpl;
import br.akd.svc.akadia.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("RepositoryImpl: Cliente")
class ClienteRepositoryImplTest {

    @InjectMocks
    ClienteRepositoryImpl clienteRepositoryImpl;

    @Mock
    ClienteRepository clienteRepository;

    @Test
    @DisplayName("Deve testar implementação da persistência de novo cliente")
    void deveTestarImplantacaoDaPersistenciaDeNovoCliente() {
        when(clienteRepository.save(any())).thenReturn(ClienteEntity.builder().build());
        ClienteEntity cliente = clienteRepositoryImpl.implementaPersistencia(ClienteEntityBuilder.builder().build());
        Assertions.assertEquals("ClienteEntity(id=null, dataCadastro=null, horaCadastro=null, " +
                        "dataNascimento=null, nome=null, cpfCnpj=null, inscricaoEstadual=null, email=null, " +
                        "statusCliente=null, tipoPessoa=null, qtdOrdensRealizadas=null, giroTotal=null, " +
                        "exclusao=null, endereco=null, telefone=null, colaboradorResponsavel=null, empresa=null)",
                cliente.toString());
    }

    @Test
    @DisplayName("Deve testar implementação da persistência de novo cliente em massa")
    void deveTestarImplantacaoDaPersistenciaDeNovoClienteEmMassa() {
        when(clienteRepository.saveAll(any())).thenReturn(new ArrayList<>());
        List<ClienteEntity> listaClientes = new ArrayList<>();
        clienteRepositoryImpl.implementaPersistenciaEmMassa(listaClientes);
        Assertions.assertNotNull(listaClientes);
    }

    @Test
    @DisplayName("Deve testar implementação da busca por todos os clientes")
    void deveTestarImplantacaoDaBuscaDeTodosOsClientes() {
        when(clienteRepository.findAll()).thenReturn(new ArrayList<>());
        Assertions.assertEquals(clienteRepositoryImpl.implementaBuscaPorTodos(1L), new ArrayList<>());
    }

    @Test
    @DisplayName("Deve testar implantação da busca por id de cliente com sucesso")
    void deveTestarImplantacaoDaBuscaPorIdClienteComSucesso() {
        when(clienteRepository.buscaPorId(anyLong(), anyLong())).thenReturn(Optional.of(ClienteEntityBuilder.builder().build()));
        ClienteEntity cliente = clienteRepositoryImpl.implementaBuscaPorId(999L, 999L);
        Assertions.assertEquals("ClienteEntity(id=1, dataCadastro=2023-02-27, horaCadastro=17:40, " +
                        "dataNascimento=1998-07-21, nome=Gabriel Lagrota, cpfCnpj=582.645.389-32, " +
                        "inscricaoEstadual=145574080114, email=gabrielafonso@mail.com.br, statusCliente=null, " +
                        "tipoPessoa=null, qtdOrdensRealizadas=null, giroTotal=null, exclusao=null, endereco=null, " +
                        "telefone=null, colaboradorResponsavel=null, empresa=null)",
                cliente.toString());
    }

    @Test
    @DisplayName("Deve testar implementação da busca por CPF ou CNPJ identico ao passado por parâmetro")
    void deveTestarImplantacaoDaBuscaPorCpfCnpjIdentico() {
        when(clienteRepository.buscaPorCpfCnpjIdenticoNaEmpresaDaSessaoAtual(anyString(), anyLong()))
                .thenReturn(Optional.of(ClienteEntityBuilder.builder().build()));
        Assertions.assertEquals("Optional[ClienteEntity(id=1, dataCadastro=2023-02-27, horaCadastro=17:40, " +
                        "dataNascimento=1998-07-21, nome=Gabriel Lagrota, cpfCnpj=582.645.389-32, " +
                        "inscricaoEstadual=145574080114, email=gabrielafonso@mail.com.br, statusCliente=null, " +
                        "tipoPessoa=null, qtdOrdensRealizadas=null, giroTotal=null, exclusao=null, endereco=null, " +
                        "telefone=null, colaboradorResponsavel=null, empresa=null)]",
                clienteRepositoryImpl.implementaBuscaPorCpfCnpjIdentico("47253227822", 1L).toString());
    }

    @Test
    @DisplayName("Deve testar implementação da busca por inscrição estadual identica ao passado por parâmetro")
    void deveTestarImplantacaoDaBuscaPorInscricaoEstadualIdentica() {
        when(clienteRepository.buscaPorInscricaoEstadualIdenticaNaEmpresaDaSessaoAtual(anyString(), anyLong()))
                .thenReturn(Optional.of(ClienteEntityBuilder.builder().build()));
        Assertions.assertEquals("Optional[ClienteEntity(id=1, dataCadastro=2023-02-27, horaCadastro=17:40, " +
                        "dataNascimento=1998-07-21, nome=Gabriel Lagrota, cpfCnpj=582.645.389-32, " +
                        "inscricaoEstadual=145574080114, email=gabrielafonso@mail.com.br, statusCliente=null, " +
                        "tipoPessoa=null, qtdOrdensRealizadas=null, giroTotal=null, exclusao=null, endereco=null, " +
                        "telefone=null, colaboradorResponsavel=null, empresa=null)]",
                clienteRepositoryImpl.implementaBuscaPorInscricaoEstadualIdentica(
                "111111111111", 1L).toString());
    }

    @Test
    @DisplayName("Deve testar implementação da busca por id em massa com sucesso")
    void deveTestarImplantacaoDaBuscaPorIdEmMassaComSucesso() {
        List<ClienteEntity> clientes = new ArrayList<>();
        clientes.add(ClienteEntityBuilder.builder().build());
        when(clienteRepository.findAllById(any())).thenReturn(clientes);
        List<Long> listaIds = new ArrayList<>();
        Assertions.assertEquals("[ClienteEntity(id=1, dataCadastro=2023-02-27, horaCadastro=17:40, " +
                        "dataNascimento=1998-07-21, nome=Gabriel Lagrota, cpfCnpj=582.645.389-32, " +
                        "inscricaoEstadual=145574080114, email=gabrielafonso@mail.com.br, statusCliente=null, " +
                        "tipoPessoa=null, qtdOrdensRealizadas=null, giroTotal=null, exclusao=null, endereco=null, " +
                        "telefone=null, colaboradorResponsavel=null, empresa=null)]",
                clienteRepositoryImpl.implementaBuscaPorIdEmMassa(listaIds).toString());
    }

    @Test
    @DisplayName("Deve testar implementação da busca por id em massa com exception")
    void deveTestarImplantacaoDaBuscaPorIdEmMassaComException() {
        when(clienteRepository.findAllById(any())).thenReturn(new ArrayList<>());
        try {
            List<Long> listaIds = new ArrayList<>();
            clienteRepositoryImpl.implementaBuscaPorIdEmMassa(listaIds);
            Assertions.fail();
        } catch (ObjectNotFoundException objectNotFoundException) {
            Assertions.assertEquals("Nenhum cliente foi encontrado com os ids informados",
                    objectNotFoundException.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar implantação da busca por id do cliente com ObjectNotFoundException lançada")
    void deveTestarImplantacaoDaBuscaPorIdClienteComObjectNotFoundException() {
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.empty());
        try {
            clienteRepositoryImpl.implementaBuscaPorId(999L, 999L);
            Assertions.fail();
        } catch (ObjectNotFoundException exception) {
            Assertions.assertEquals("Nenhum cliente foi encontrado com o id informado", exception.getMessage());
        }
    }

}
