package br.akd.svc.akadia.controllers.sistema.clientes;

import br.akd.svc.akadia.models.dto.sistema.clientes.mocks.ClienteDtoBuilder;
import br.akd.svc.akadia.models.entities.sistema.clientes.ClienteEntity;
import br.akd.svc.akadia.models.entities.sistema.clientes.mocks.ClienteEntityBuilder;
import br.akd.svc.akadia.services.sistema.clientes.ClienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("Controller: Cliente")
class ClienteControllerTest {

    @InjectMocks
    ClienteController clienteController;

    @Mock
    ClienteService clienteService;

    @Test
    @DisplayName("Deve testar método controlador de criação de novo cliente")
    void deveTestarMetodoControladorDeCriacaoDeNovoCliente() {
        when(clienteService.criaNovoCliente(any())).thenReturn(ClienteEntityBuilder.builder().build());
        ResponseEntity<ClienteEntity> cliente = clienteController.criaNovoCliente(ClienteDtoBuilder.builder().build());
        Assertions.assertEquals("<201 CREATED Created,ClienteEntity(id=1, dataCadastro=2023-02-27, " +
                "horaCadastro=17:40, dataNascimento=1998-07-21, nome=Gabriel Lagrota, cpfCnpj=582.645.389-32, " +
                "inscricaoEstadual=145574080114, email=gabrielafonso@mail.com.br, endereco=null, telefone=null, " +
                "colaboradorResponsavel=null, empresa=null),[]>", cliente.toString());
    }

}
