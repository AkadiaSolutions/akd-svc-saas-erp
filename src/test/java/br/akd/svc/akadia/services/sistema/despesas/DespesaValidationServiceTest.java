package br.akd.svc.akadia.services.sistema.despesas;

import br.akd.svc.akadia.models.entities.sistema.despesas.mocks.DespesaEntityBuilder;
import br.akd.svc.akadia.modules.erp.despesas.services.DespesaValidationService;
import br.akd.svc.akadia.exceptions.InvalidRequestException;
import br.akd.svc.akadia.utils.Constantes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Validation: Despesa")
class DespesaValidationServiceTest {

    @InjectMocks
    DespesaValidationService despesaValidationService;

    @Test
    @DisplayName("Deve testar método de validação se despesa está excluída com sucesso")
    void deveTestarMetodoDeValidacaoSeDespesaEstaExcluidaComSucesso() {
        despesaValidationService.validaSeDespesaEstaExcluida(
                DespesaEntityBuilder.builder().build(),
                Constantes.DESPESA_JA_EXCLUIDA);

        Assertions.assertDoesNotThrow(() -> new InvalidRequestException(Constantes.DESPESA_JA_EXCLUIDA));
    }

    @Test
    @DisplayName("Deve testar método de validação se despesa está excluída com exception")
    void deveTestarMetodoDeValidacaoSeDespesaEstaExcluidaComException() {
        Assertions.assertThrows(InvalidRequestException.class,
                () -> despesaValidationService.validaSeDespesaEstaExcluida(
                        DespesaEntityBuilder.builder().comExclusao().build(),
                        Constantes.DESPESA_JA_EXCLUIDA));
    }

}
