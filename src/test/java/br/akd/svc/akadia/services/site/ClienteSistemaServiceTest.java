package br.akd.svc.akadia.services.site;

import br.akd.svc.akadia.models.dto.site.mocks.ClienteSistemaDtoBuilder;
import br.akd.svc.akadia.models.entities.site.mocks.ClienteSistemaEntityBuilder;
import br.akd.svc.akadia.repositories.site.impl.ClienteSistemaRepositoryImpl;
import br.akd.svc.akadia.services.global.exceptions.InvalidRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@DisplayName("Service: ClienteSistema")
@ExtendWith(MockitoExtension.class)
class ClienteSistemaServiceTest {

    @InjectMocks
    ClienteSistemaService clienteSistemaService;

    @Mock
    ClienteSistemaRepositoryImpl clienteSistemaRepositoryImpl;

    @Test
    @DisplayName("Deve testar validação de e-mail com sucesso")
    void deveTestarValidacaoDeEmailComSucesso() {
        Mockito.when(clienteSistemaRepositoryImpl.implementaBuscaPorEmail(Mockito.any()))
                .thenReturn(Optional.empty());
        clienteSistemaService.validaSeEmailJaExiste(ClienteSistemaDtoBuilder.builder().build());
    }

    @Test
    @DisplayName("Deve testar validação de e-mail com e-mail que já existe")
    void deveTestarValidacaoDeEmailComEmailQueJaExiste() {
        Mockito.when(clienteSistemaRepositoryImpl.implementaBuscaPorEmail(Mockito.any()))
                .thenReturn(Optional.of(ClienteSistemaEntityBuilder.builder().build()));
        try {
            clienteSistemaService.validaSeEmailJaExiste(ClienteSistemaDtoBuilder.builder().build());
            Assertions.fail();
        } catch (InvalidRequestException e) {
            Assertions.assertEquals("O e-mail informado já existe",
                    e.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar validação de cpf com sucesso")
    void deveTestarValidacaoDeCpfComSucesso() {
        Mockito.when(clienteSistemaRepositoryImpl.implementaBuscaPorCpf(Mockito.any()))
                .thenReturn(Optional.empty());
        clienteSistemaService.validaSeCpfJaExiste(ClienteSistemaDtoBuilder.builder().build());
    }

    @Test
    @DisplayName("Deve testar validação de cpf com cpf que já existe")
    void deveTestarValidacaoDeCpfComCpfQueJaExiste() {
        Mockito.when(clienteSistemaRepositoryImpl.implementaBuscaPorCpf(Mockito.any()))
                .thenReturn(Optional.of(ClienteSistemaEntityBuilder.builder().build()));
        try {
            clienteSistemaService.validaSeCpfJaExiste(ClienteSistemaDtoBuilder.builder().build());
            Assertions.fail();
        } catch (InvalidRequestException e) {
            Assertions.assertEquals("O cpf informado já existe",
                    e.getMessage());
        }
    }

}
