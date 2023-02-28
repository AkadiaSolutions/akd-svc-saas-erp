package br.akd.svc.akadia.services.sistema.clientes;

import br.akd.svc.akadia.models.dto.sistema.clientes.mocks.ClienteDtoBuilder;
import br.akd.svc.akadia.models.entities.sistema.clientes.ClienteEntity;
import br.akd.svc.akadia.models.entities.sistema.clientes.mocks.ClienteEntityBuilder;
import br.akd.svc.akadia.repositories.sistema.clientes.impl.ClienteRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@SpringBootTest
@DisplayName("Service: Cliente")
class ClienteServiceTest {

    @InjectMocks
    ClienteService clienteService;

    @Mock
    ClienteRepositoryImpl clienteRepositoryImpl;

    @Test
    @DisplayName("Deve testar método de criação de novo cliente")
    void deveTestarMetodoDeCriacaoDeNovoCliente() {

        Mockito.when(clienteRepositoryImpl.implementaPersistencia(any()))
                .thenReturn(ClienteEntityBuilder.builder().build());

        ClienteEntity clienteEntity = clienteService.criaNovoCliente(ClienteDtoBuilder.builder()
                .comEndereco()
                .comTelefone()
                .build());

        Assertions.assertEquals("ClienteEntity(id=1, dataCadastro=2023-02-27, horaCadastro=17:40, " +
                "dataNascimento=1998-07-21, nome=Gabriel Lagrota, cpfCnpj=582.645.389-32, inscricaoEstadual=145574080114, " +
                "email=gabrielafonso@mail.com.br, endereco=null, telefone=null, colaboradorResponsavel=null, " +
                "empresa=null)", clienteEntity.toString());
    }

    @Test
    @DisplayName("Deve testar método de atualização do cliente")
    void deveTestarMetodoDeAtualizacaoDeCliente() {

        Mockito.when(clienteRepositoryImpl.implementaBuscaPorId(anyLong()))
                .thenReturn(ClienteEntityBuilder.builder()
                        .comTelefone()
                        .comEndereco()
                        .build());

        Mockito.when(clienteRepositoryImpl.implementaPersistencia(any()))
                .thenReturn(ClienteEntityBuilder.builder()
                        .comTelefone()
                        .comEndereco()
                        .build());

        ClienteEntity cliente = clienteService.atualizaCliente(1L, ClienteDtoBuilder.builder()
                .comTelefone()
                .comEndereco()
                .build());

        Assertions.assertEquals("ClienteEntity(id=1, dataCadastro=2023-02-27, horaCadastro=17:40, " +
                "dataNascimento=1998-07-21, nome=Gabriel Lagrota, cpfCnpj=582.645.389-32, " +
                "inscricaoEstadual=145574080114, email=gabrielafonso@mail.com.br, endereco=EnderecoEntity(id=1, " +
                "logradouro=Avenida Coronel Manuel Py, numero=583, bairro=Lauzane Paulista, codigoPostal=02442-090, " +
                "cidade=São Paulo, complemento=Casa 4, estadoEnum=SP), telefone=TelefoneEntity(id=1, prefixo=11, " +
                "numero=979815415, tipoTelefoneEnum=MOVEL_WHATSAPP), colaboradorResponsavel=null, empresa=null)",
                cliente.toString());
    }

}
