package br.akd.svc.akadia.services.sistema.clientes;

import br.akd.svc.akadia.models.dto.sistema.clientes.requests.ClienteRequest;
import br.akd.svc.akadia.models.dto.sistema.clientes.requests.mocks.ClienteRequestBuilder;
import br.akd.svc.akadia.models.entities.sistema.clientes.ClienteEntity;
import br.akd.svc.akadia.models.entities.sistema.clientes.mocks.ClienteEntityBuilder;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.ColaboradorEntity;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks.ColaboradorEntityBuilder;
import br.akd.svc.akadia.repositories.sistema.clientes.impl.ClienteRepositoryImpl;
import br.akd.svc.akadia.services.exceptions.InvalidRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Validation: Cliente")
class ClienteValidationServiceTest {

    @InjectMocks
    ClienteValidationService clienteValidationService;

    @Mock
    ClienteRepositoryImpl clienteRepositoryImpl;

    @Test
    @DisplayName("Deve testar com sucesso se chaves únicas já existem para novo cliente")
    void deveTestarComSucessoSeChavesUnicasJaExistemParaNovoCliente() {
        ClienteRequest clienteRequest = ClienteRequestBuilder.builder()
                .build();

        ColaboradorEntity colaboradorLogado = ColaboradorEntityBuilder.builder()
                .comEmpresa()
                .build();

        Mockito.when(clienteRepositoryImpl.implementaBuscaPorCpfCnpjIdentico(Mockito.any(), Mockito.any()))
                .thenReturn(Optional.empty());

        Assertions.assertDoesNotThrow(() -> clienteValidationService
                .validaSeChavesUnicasJaExistemParaNovoCliente(clienteRequest, colaboradorLogado));
    }

    @Test
    @DisplayName("Deve testar se chaves únicas já existem para cliente atualizado")
    void deveTestarSeChavesUnicasJaExistemParaClienteAtualizado() {
        ClienteRequest clienteRequest = ClienteRequestBuilder.builder()
                .build();

        ClienteEntity clienteEntity = ClienteEntityBuilder.builder()
                .build();

        ColaboradorEntity colaboradorLogado = ColaboradorEntityBuilder.builder()
                .comEmpresa()
                .build();

        Mockito.when(clienteRepositoryImpl.implementaBuscaPorCpfCnpjIdentico(Mockito.any(), Mockito.any()))
                .thenReturn(Optional.empty());

        Assertions.assertDoesNotThrow(() -> clienteValidationService
                .validaSeChavesUnicasJaExistemParaClienteAtualizado(clienteRequest, clienteEntity, colaboradorLogado));
    }

    @Test
    @DisplayName("Deve testar com sucesso validação se CPF/CNPJ já existe")
    void deveTestarComSucessoValidacaoSeCpfCnpjJaExiste() {
        Mockito.when(clienteRepositoryImpl.implementaBuscaPorCpfCnpjIdentico(Mockito.any(), Mockito.any()))
                .thenReturn(Optional.empty());

        Assertions.assertDoesNotThrow(() -> clienteValidationService
                .validaSeCpfCnpjJaExiste("47153427821", 1L));
    }

    @Test
    @DisplayName("Deve testar com erro validação se CPF/CNPJ já existe")
    void deveTestarComErroValidacaoSeCpfCnpjJaExiste() {
        ClienteEntity clienteEntity = ClienteEntityBuilder.builder()
                .build();

        Mockito.when(clienteRepositoryImpl.implementaBuscaPorCpfCnpjIdentico(Mockito.any(), Mockito.any()))
                .thenReturn(Optional.of(clienteEntity));

        Assertions.assertThrows(InvalidRequestException.class,
                () -> clienteValidationService.validaSeCpfCnpjJaExiste("47153427821", 1L));
    }

    @Test
    @DisplayName("Deve testar com sucesso validação se inscrição estadual já existe")
    void deveTestarComSucessoValidacaoSeInscricaoEstadualJaExiste() {
        Mockito.when(clienteRepositoryImpl.implementaBuscaPorInscricaoEstadualIdentica(Mockito.any(), Mockito.any()))
                .thenReturn(Optional.empty());

        Assertions.assertDoesNotThrow(() -> clienteValidationService
                .validaSeCpfCnpjJaExiste("111123213123", 1L));
    }

    @Test
    @DisplayName("Deve testar com erro validação se inscrição estadual já existe")
    void deveTestarComErroValidacaoSeInscricaoEstadualJaExiste() {
        ClienteEntity clienteEntity = ClienteEntityBuilder.builder()
                .build();

        Mockito.when(clienteRepositoryImpl.implementaBuscaPorInscricaoEstadualIdentica(Mockito.any(), Mockito.any()))
                .thenReturn(Optional.of(clienteEntity));

        Assertions.assertThrows(InvalidRequestException.class,
                () -> clienteValidationService.validaSeInscricaoEstadualJaExiste("111123213123", 1L));
    }

    @Test
    @DisplayName("Deve testar com sucesso validação se cliente já está excluído")
    void deveTestarComSucessoValidacaoSeClienteJaEstaExcluido() {
        ClienteEntity clienteEntity = ClienteEntityBuilder.builder()
                .comExclusao(false)
                .build();

        Assertions.assertDoesNotThrow(() -> clienteValidationService
                .validaSeClienteEstaExcluido(clienteEntity, "Erro"));
    }

    @Test
    @DisplayName("Deve testar com erro validação se cliente já está excluído")
    void deveTestarComErroValidacaoSeClienteJaEstaExcluido() {
        ClienteEntity clienteEntity = ClienteEntityBuilder.builder()
                .comExclusao(true)
                .build();

        Assertions.assertThrows(InvalidRequestException.class, () -> clienteValidationService
                .validaSeClienteEstaExcluido(clienteEntity, "Erro"));
    }
}
