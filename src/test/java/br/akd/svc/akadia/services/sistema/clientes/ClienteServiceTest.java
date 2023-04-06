package br.akd.svc.akadia.services.sistema.clientes;

import br.akd.svc.akadia.models.dto.sistema.clientes.mocks.ClienteDtoBuilder;
import br.akd.svc.akadia.models.entities.sistema.clientes.ClienteEntity;
import br.akd.svc.akadia.models.entities.sistema.clientes.mocks.ClienteEntityBuilder;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks.ColaboradorEntityBuilder;
import br.akd.svc.akadia.repositories.sistema.clientes.impl.ClienteRepositoryImpl;
import br.akd.svc.akadia.services.exceptions.InvalidRequestException;
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
@DisplayName("Service: Cliente")
class ClienteServiceTest {

    @InjectMocks
    ClienteService clienteService;

    @Mock
    ClienteRepositoryImpl clienteRepositoryImpl;

    @Test
    @DisplayName("Deve testar método de criação de novo cliente")
    void deveTestarMetodoDeCriacaoDeNovoCliente() {

        when(clienteRepositoryImpl.implementaPersistencia(any()))
                .thenReturn(ClienteEntityBuilder.builder().comExclusao(false).build());

        ClienteEntity clienteEntity = clienteService.criaNovoCliente(ColaboradorEntityBuilder.builder().comEmpresa().build(),
                ClienteDtoBuilder.builder()
                        .comObjetoExclusaoFalse()
                        .comEndereco()
                        .comTelefone()
                        .build());

        Assertions.assertEquals("ClienteEntity(id=1, dataCadastro=2023-02-27, horaCadastro=17:40, " +
                        "dataNascimento=1998-07-21, nome=Gabriel Lagrota, cpfCnpj=582.645.389-32, " +
                        "inscricaoEstadual=145574080114, email=gabrielafonso@mail.com.br, " +
                        "exclusaoCliente=ExclusaoClienteEntity(id=1, dataExclusao=null, horaExclusao=null, excluido=false, " +
                        "responsavelExclusao=null), endereco=null, telefone=null, colaboradorResponsavel=null, empresa=null)",
                clienteEntity.toString());
    }

    @Test
    @DisplayName("Deve testar método de atualização do cliente")
    void deveTestarMetodoDeAtualizacaoDeCliente() {

        when(clienteRepositoryImpl.implementaBuscaPorId(anyLong()))
                .thenReturn(ClienteEntityBuilder.builder()
                        .comExclusao(false)
                        .comTelefone()
                        .comEndereco()
                        .build());

        when(clienteRepositoryImpl.implementaPersistencia(any()))
                .thenReturn(ClienteEntityBuilder.builder()
                        .comExclusao(false)
                        .comTelefone()
                        .comEndereco()
                        .build());

        ClienteEntity cliente = clienteService.atualizaCliente(ColaboradorEntityBuilder.builder().build(), 1L,
                ClienteDtoBuilder.builder()
                        .comObjetoExclusaoFalse()
                        .comTelefone()
                        .comEndereco()
                        .build());

        Assertions.assertEquals("ClienteEntity(id=1, dataCadastro=2023-02-27, horaCadastro=17:40, " +
                        "dataNascimento=1998-07-21, nome=Gabriel Lagrota, cpfCnpj=582.645.389-32, " +
                        "inscricaoEstadual=145574080114, email=gabrielafonso@mail.com.br, " +
                        "exclusaoCliente=ExclusaoClienteEntity(id=1, dataExclusao=null, horaExclusao=null, " +
                        "excluido=false, responsavelExclusao=null), endereco=EnderecoEntity(id=1, " +
                        "logradouro=Avenida Coronel Manuel Py, numero=583, bairro=Lauzane Paulista, " +
                        "codigoPostal=02442-090, cidade=São Paulo, complemento=Casa 4, estadoEnum=SP), " +
                        "telefone=TelefoneEntity(id=1, prefixo=11, numero=979815415, tipoTelefoneEnum=MOVEL_WHATSAPP), " +
                        "colaboradorResponsavel=null, empresa=null)",
                cliente.toString());
    }

    @Test
    @DisplayName("Deve testar método de validação se cpfCnpj já existe com valor existente")
    void deveTestarMetodoDeValidacaoSeCpfCnpjJaExisteComValorExistente() {

        when(clienteRepositoryImpl.implementaBuscaPorCpfCnpjIdentico(any(), any()))
                .thenReturn(Optional.of(ClienteEntityBuilder.builder().build()));

        try {
            clienteService.validaSeCpfCnpjJaExiste("123.456.789-10", 1L);
            Assertions.fail();
        } catch (InvalidRequestException invalidRequestException) {
            Assertions.assertEquals("O cpf/cnpj informado já existe",
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
        when(clienteRepositoryImpl.implementaBuscaPorId(anyLong()))
                .thenReturn(ClienteEntityBuilder.builder().comExclusao(false).build());
        when(clienteRepositoryImpl.implementaPersistencia(any()))
                .thenReturn(ClienteEntityBuilder.builder().comExclusao(false).build());
        Assertions.assertEquals("ClienteEntity(id=1, dataCadastro=2023-02-27, horaCadastro=17:40, " +
                        "dataNascimento=1998-07-21, nome=Gabriel Lagrota, cpfCnpj=582.645.389-32, " +
                        "inscricaoEstadual=145574080114, email=gabrielafonso@mail.com.br, " +
                        "exclusaoCliente=ExclusaoClienteEntity(id=1, dataExclusao=null, horaExclusao=null, " +
                        "excluido=false, responsavelExclusao=null), endereco=null, telefone=null, " +
                        "colaboradorResponsavel=null, empresa=null)",
                clienteService.removeCliente(ColaboradorEntityBuilder.builder().build(), 1L).toString());
    }

    @Test
    @DisplayName("Deve testar método de remoção de cliente com cliente já excluído")
    void deveTestarMetodoDeRemocaoDeClienteComClienteJaExcluido() {
        when(clienteRepositoryImpl.implementaBuscaPorId(anyLong()))
                .thenReturn(ClienteEntityBuilder.builder().comExclusao(true).build());
        try {
            clienteService.removeCliente(ColaboradorEntityBuilder.builder().build(), 1L);
            Assertions.fail();
        } catch (InvalidRequestException invalidRequestException) {
            Assertions.assertEquals("Não é possível remover um cliente que já foi excluído",
                    invalidRequestException.getMessage());
        }
    }

}
