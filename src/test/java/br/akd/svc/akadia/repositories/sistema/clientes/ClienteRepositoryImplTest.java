package br.akd.svc.akadia.repositories.sistema.clientes;

import br.akd.svc.akadia.models.entities.sistema.clientes.ClienteEntity;
import br.akd.svc.akadia.models.entities.sistema.clientes.mocks.ClienteEntityBuilder;
import br.akd.svc.akadia.repositories.sistema.clientes.impl.ClienteRepositoryImpl;
import br.akd.svc.akadia.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("RepositoryImpl: Cliente")
class ClienteRepositoryImplTest {

    @InjectMocks
    ClienteRepositoryImpl clienteRepositoryImpl;

    @Mock
    ClienteRepository clienteRepository;

    @Test
    @DisplayName("Deve testar implantação da persistência de novo cliente")
    void deveTestarImplantacaoDaPersistenciaDeNovoCliente() {
        when(clienteRepository.save(any())).thenReturn(ClienteEntity.builder().build());
        ClienteEntity cliente = clienteRepositoryImpl.implementaPersistencia(ClienteEntityBuilder.builder().build());
        Assertions.assertEquals("ClienteEntity(id=null, dataCadastro=null, horaCadastro=null, " +
                "dataNascimento=null, nome=null, cpfCnpj=null, inscricaoEstadual=null, email=null, " +
                "exclusaoCliente=null, endereco=null, telefone=null, colaboradorResponsavel=null, empresa=null)",
                cliente.toString());
    }

    @Test
    @DisplayName("Deve testar implantação da busca por id de cliente com sucesso")
    void deveTestarImplantacaoDaBuscaPorIdClienteComSucesso() {
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(ClienteEntity.builder().build()));
        ClienteEntity cliente = clienteRepositoryImpl.implementaBuscaPorId(999L);
        Assertions.assertEquals("ClienteEntity(id=null, dataCadastro=null, horaCadastro=null, " +
                "dataNascimento=null, nome=null, cpfCnpj=null, inscricaoEstadual=null, email=null, " +
                "exclusaoCliente=null, endereco=null, telefone=null, colaboradorResponsavel=null, empresa=null)",
                cliente.toString());
    }

    @Test
    @DisplayName("Deve testar implantação da busca por id do cliente com ObjectNotFoundException lançada")
    void deveTestarImplantacaoDaBuscaPorIdClienteComObjectNotFoundException() {
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.empty());
        try {
            clienteRepositoryImpl.implementaBuscaPorId(999L);
            Assertions.fail();
        }
        catch (ObjectNotFoundException exception) {
            Assertions.assertEquals("Nenhum cliente foi encontrado com o id informado", exception.getMessage());
        }
    }

}
