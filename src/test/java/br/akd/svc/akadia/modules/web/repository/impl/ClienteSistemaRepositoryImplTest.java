package br.akd.svc.akadia.modules.web.repository.impl;

import br.akd.svc.akadia.models.entities.site.mocks.ClienteSistemaEntityBuilder;
import br.akd.svc.akadia.modules.web.repository.ClienteSistemaRepository;
import br.akd.svc.akadia.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("RepositoryImpl: ClienteSistema")
class ClienteSistemaRepositoryImplTest {

    @InjectMocks
    ClienteSistemaRepositoryImpl clienteSistemaRepositoryImpl;

    @Mock
    ClienteSistemaRepository clienteSistemaRepository;

    @Test
    @DisplayName("Deve testar método de implementação de busca de todos os clientes")
    void deveTestarMetodoDeImplementacaoDeBuscaDeTodosOsClientes() {
        when(clienteSistemaRepository.findAll()).thenReturn(new ArrayList<>());
        Assertions.assertEquals("[]",
                clienteSistemaRepositoryImpl.buscaTodosClientes().toString());
    }

    @Test
    @DisplayName("Deve testar método de implementação de persistência")
    void deveTestarMetodoDeImplementacaoDePersistencia() {
        when(clienteSistemaRepository.save(any())).thenReturn(ClienteSistemaEntityBuilder.builder().build());
        Assertions.assertEquals("ClienteSistemaEntity(id=1, codigoClienteAsaas=cus_000005113026, " +
                        "dataCadastro=2023-02-03, horaCadastro=10:40, dataNascimento=2023-02-03, " +
                        "email=fulano@gmail.com, nome=Fulano, senha=123, cpf=12345678910, saldo=0.0, plano=null, " +
                        "telefone=null, endereco=null, cartao=null)",
                clienteSistemaRepositoryImpl.implementaPersistencia(ClienteSistemaEntityBuilder.builder().build()).toString());
    }

    @Test
    @DisplayName("Deve testar método de implementação de busca por e-mail")
    void deveTestarMetodoDeImplementacaoDeBuscaPorEmail() {
        when(clienteSistemaRepository.findByEmail(any()))
                .thenReturn(Optional.of(ClienteSistemaEntityBuilder.builder().build()));
        Assertions.assertEquals("Optional[ClienteSistemaEntity(id=1, codigoClienteAsaas=cus_000005113026, " +
                        "dataCadastro=2023-02-03, horaCadastro=10:40, dataNascimento=2023-02-03, " +
                        "email=fulano@gmail.com, nome=Fulano, senha=123, cpf=12345678910, saldo=0.0, plano=null, " +
                        "telefone=null, endereco=null, cartao=null)]",
                clienteSistemaRepositoryImpl.implementaBuscaPorEmail("email@email.com.br").toString());
    }

    @Test
    @DisplayName("Deve testar método de implementação de busca por cpf")
    void deveTestarMetodoDeImplementacaoDeBuscaPorCpf() {
        when(clienteSistemaRepository.findByCpf(any()))
                .thenReturn(Optional.of(ClienteSistemaEntityBuilder.builder().build()));
        Assertions.assertEquals("Optional[ClienteSistemaEntity(id=1, codigoClienteAsaas=cus_000005113026, " +
                        "dataCadastro=2023-02-03, horaCadastro=10:40, dataNascimento=2023-02-03, " +
                        "email=fulano@gmail.com, nome=Fulano, senha=123, cpf=12345678910, saldo=0.0, plano=null, " +
                        "telefone=null, endereco=null, cartao=null)]",
                clienteSistemaRepositoryImpl.implementaBuscaPorCpf("123.456.789-10").toString());
    }

    @Test
    @DisplayName("Deve testar método de implementação de busca por id")
    void deveTestarMetodoDeImplementacaoDeBuscaPorId() {
        when(clienteSistemaRepository.findById(any()))
                .thenReturn(Optional.of(ClienteSistemaEntityBuilder.builder().build()));
        Assertions.assertEquals("ClienteSistemaEntity(id=1, codigoClienteAsaas=cus_000005113026, " +
                        "dataCadastro=2023-02-03, horaCadastro=10:40, dataNascimento=2023-02-03, " +
                        "email=fulano@gmail.com, nome=Fulano, senha=123, cpf=12345678910, saldo=0.0, plano=null, " +
                        "telefone=null, endereco=null, cartao=null)",
                clienteSistemaRepositoryImpl.implementaBuscaPorId(1L).toString());
    }

    @Test
    @DisplayName("Deve testar método de implementação de busca por id sem nenhum cliente encontrado")
    void deveTestarMetodoDeImplementacaoDeBuscaPorIdSemNenhumClienteEncontrado() {
        when(clienteSistemaRepository.findById(any()))
                .thenReturn(Optional.empty());
        try {
            clienteSistemaRepositoryImpl.implementaBuscaPorId(1L);
            Assertions.fail();
        }
        catch (ObjectNotFoundException objectNotFoundException) {
            Assertions.assertEquals("Nenhum cliente foi encontrado com o id informado",
                    objectNotFoundException.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar método de implementação de busca por código de cliente ASAAS")
    void deveTestarMetodoDeImplementacaoDeBuscaPorCodigoClienteAsaas() {
        when(clienteSistemaRepository.findByCodigoClienteAsaas(any()))
                .thenReturn(Optional.of(ClienteSistemaEntityBuilder.builder().build()));
        Assertions.assertEquals("ClienteSistemaEntity(id=1, codigoClienteAsaas=cus_000005113026, " +
                        "dataCadastro=2023-02-03, horaCadastro=10:40, dataNascimento=2023-02-03, " +
                        "email=fulano@gmail.com, nome=Fulano, senha=123, cpf=12345678910, saldo=0.0, plano=null, " +
                        "telefone=null, endereco=null, cartao=null)",
                clienteSistemaRepositoryImpl.implementaBuscaPorCodigoClienteAsaas("cus_123").toString());
    }

    @Test
    @DisplayName("Deve testar método de implementação de busca por código de cliente Asaas sem nenhum cliente encontrado")
    void deveTestarMetodoDeImplementacaoDeBuscaPorCodigoDeClienteAsaasSemNenhumClienteEncontrado() {
        when(clienteSistemaRepository.findByCodigoClienteAsaas(any()))
                .thenReturn(Optional.empty());
        try {
            clienteSistemaRepositoryImpl.implementaBuscaPorCodigoClienteAsaas("cus_123");
            Assertions.fail();
        }
        catch (ObjectNotFoundException objectNotFoundException) {
            Assertions.assertEquals("Nenhum cliente foi encontrado com o codigo Asaas informado",
                    objectNotFoundException.getMessage());
        }
    }

}
