package br.akd.svc.akadia.services.sistema.patrimonios;

import br.akd.svc.akadia.modules.erp.patrimonios.models.entity.PatrimonioEntity;
import br.akd.svc.akadia.models.entities.sistema.patrimonios.mocks.PatrimonioEntityBuilder;
import br.akd.svc.akadia.modules.erp.patrimonios.services.PatrimonioValidationService;
import br.akd.svc.akadia.exceptions.InvalidRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Validation: Patrimonio")
class PatrimonioValidationServiceTest {

    @InjectMocks
    PatrimonioValidationService patrimonioValidationService;

    @Test
    @DisplayName("Deve testar com sucesso validação se patrimônio já está excluído")
    void deveTestarComSucessoValidacaoSePatrimonioJaEstaExcluido() {
        PatrimonioEntity patrimonioEntity = PatrimonioEntityBuilder.builder()
                .build();

        Assertions.assertDoesNotThrow(() -> patrimonioValidationService
                .validaSeObjetoEstaExcluido(patrimonioEntity, "Erro"));
    }

    @Test
    @DisplayName("Deve testar com erro validação se patrimônio já está excluído")
    void deveTestarComErroValidacaoSePatrimonioJaEstaExcluido() {
        PatrimonioEntity patrimonioEntity = PatrimonioEntityBuilder.builder()
                .comExclusao()
                .build();

        Assertions.assertThrows(InvalidRequestException.class, () -> patrimonioValidationService
                .validaSeObjetoEstaExcluido(patrimonioEntity, "Erro"));
    }
}
