package br.akd.svc.akadia.services.site;

import br.akd.svc.akadia.models.dto.site.mocks.EmpresaDtoBuilder;
import br.akd.svc.akadia.models.entities.site.mocks.ClienteSistemaEntityBuilder;
import br.akd.svc.akadia.models.entities.site.mocks.EmpresaEntityBuilder;
import br.akd.svc.akadia.repositories.site.impl.ClienteSistemaRepositoryImpl;
import br.akd.svc.akadia.repositories.site.impl.EmpresaRepositoryImpl;
import br.akd.svc.akadia.services.exceptions.InvalidRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("Service: Empresa")
class EmpresaServiceTest {

    @InjectMocks
    EmpresaService empresaService;

    @Mock
    EmpresaRepositoryImpl empresaRepositoryImpl;

    @Mock
    ClienteSistemaRepositoryImpl clienteSistemaRepositoryImpl;

    @Test
    @DisplayName("Deve testar validação de cnpj com sucesso")
    void deveTestarValidacaoDeCnpjComSucesso() {
        when(empresaRepositoryImpl.implementaBuscaPorCnpj(any()))
                .thenReturn(Optional.empty());
        empresaService.validaSeCnpjJaExiste("16733160000180");
        Assertions.assertDoesNotThrow(() -> new Exception());
    }

    @Test
    @DisplayName("Deve testar validação de endpoint com endpoint que já existe")
    void deveTestarValidacaoDeEndpointComEndpointQueJaExiste() {
        when(empresaRepositoryImpl.implementaBuscaPorEndpoint(any()))
                .thenReturn(Optional.of(EmpresaEntityBuilder.builder().build()));
        try {
            empresaService.validaSeEndpointJaExiste("twobrothers");
            Assertions.fail();
        } catch (InvalidRequestException e) {
            Assertions.assertEquals("O endpoint informado já existe",
                    e.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar validação de endpoint com sucesso")
    void deveTestarValidacaoDeEndpointComSucesso() {
        when(empresaRepositoryImpl.implementaBuscaPorEndpoint(any()))
                .thenReturn(Optional.empty());
        empresaService.validaSeEndpointJaExiste("twobrothers");
        Assertions.assertDoesNotThrow(() -> new Exception());
    }

    @Test
    @DisplayName("Deve testar validação de cnpj com cnpj que já existe")
    void deveTestarValidacaoDeCnpjComCnpjQueJaExiste() {
        when(empresaRepositoryImpl.implementaBuscaPorCnpj(any()))
                .thenReturn(Optional.of(EmpresaEntityBuilder.builder().build()));
        try {
            empresaService.validaSeCnpjJaExiste("16733160000180");
            Assertions.fail();
        } catch (InvalidRequestException e) {
            Assertions.assertEquals("O cnpj informado já existe",
                    e.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar criação de nova empresa")
    void deveTestarCriacaoDeNovaEmpresa() {

        when(clienteSistemaRepositoryImpl.implementaBuscaPorId(any()))
                .thenReturn(ClienteSistemaEntityBuilder.builder()
                        .comPlanoComPagamentoNoCredito()
                        .comTelefone()
                        .comEndereco()
                        .build());

        when(clienteSistemaRepositoryImpl.implementaPersistencia(any()))
                .thenReturn(ClienteSistemaEntityBuilder.builder()
                        .comPlanoComPagamentoNoCredito()
                        .comEndereco()
                        .comTelefone()
                        .comEmpresa()
                        .build());

        System.err.println(empresaService.criaNovaEmpresa(1L,
                EmpresaDtoBuilder.builder()
                        .comConfigFiscalComTodasNf()
                        .comEndereco()
                        .comTelefone()
                        .build()));
    }

}
