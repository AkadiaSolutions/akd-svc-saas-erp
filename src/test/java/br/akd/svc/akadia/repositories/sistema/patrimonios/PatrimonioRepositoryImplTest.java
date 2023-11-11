package br.akd.svc.akadia.repositories.sistema.patrimonios;

import br.akd.svc.akadia.modules.erp.patrimonios.models.entity.PatrimonioEntity;
import br.akd.svc.akadia.models.entities.sistema.patrimonios.mocks.PatrimonioEntityBuilder;
import br.akd.svc.akadia.modules.erp.patrimonios.repository.PatrimonioRepository;
import br.akd.svc.akadia.modules.erp.patrimonios.repository.impl.PatrimonioRepositoryImpl;
import br.akd.svc.akadia.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Repository: Patrimonio")
class PatrimonioRepositoryImplTest {

    @InjectMocks
    PatrimonioRepositoryImpl patrimonioRepositoryImpl;

    @Mock
    PatrimonioRepository patrimonioRepository;

    @Test
    @DisplayName("Deve testar método de implementação de persistência")
    void deveTestarMetodoDeImplementacaoDePersistencia() {

        when(patrimonioRepository.save(any()))
                .thenReturn(PatrimonioEntityBuilder.builder().build());

        PatrimonioEntity patrimonioEntity = patrimonioRepositoryImpl
                .implementaPersistencia(PatrimonioEntityBuilder.builder().build());

        assertEquals("PatrimonioEntity(id=1, dataCadastro=2023-08-21, horaCadastro=10:20, " +
                        "dataEntrada=2023-08-21, descricao=Dinheiro, valor=100.0, tipoPatrimonio=ATIVO, " +
                        "exclusao=null, colaboradorResponsavel=null, empresa=null)",
                patrimonioEntity.toString());
    }


    @Test
    @DisplayName("Deve testar método de implementação de persistência em massa")
    void deveTestarMetodoDeimplementacaoDePersistenciaEmMassa() {

        List<PatrimonioEntity> patrimonios = new ArrayList<>();
        patrimonios.add(PatrimonioEntityBuilder.builder().build());

        try {
            patrimonioRepositoryImpl.implementaPersistenciaEmMassa(patrimonios);
            assertDoesNotThrow(() -> new RuntimeException());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @DisplayName("Deve testar método de implementação de busca por id com sucesso")
    void deveTestarMetodoDeimplementacaoDeBuscaPorIdComSucesso() {

        when(patrimonioRepository.buscaPorId(anyLong(), anyLong()))
                .thenReturn(Optional.of(PatrimonioEntityBuilder.builder().build()));

        PatrimonioEntity patrimonioEntity = patrimonioRepositoryImpl
                .implementaBuscaPorId(1L, 1L);

        assertEquals("PatrimonioEntity(id=1, dataCadastro=2023-08-21, horaCadastro=10:20, " +
                        "dataEntrada=2023-08-21, descricao=Dinheiro, valor=100.0, tipoPatrimonio=ATIVO, " +
                        "exclusao=null, colaboradorResponsavel=null, empresa=null)",
                patrimonioEntity.toString());
    }

    @Test
    @DisplayName("Deve testar método de implementação de busca por id com erro")
    void deveTestarMetodoDeimplementacaoDeBuscaPorIdComErro() {
        when(patrimonioRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(ObjectNotFoundException.class, () ->
                patrimonioRepositoryImpl.implementaBuscaPorId(1L, 1L));
    }

    @Test
    @DisplayName("Deve testar método de implementação de busca por todos os itens")
    void deveTestarMetodoDeimplementacaoDeBuscaPorTodosOsItens() {

        List<PatrimonioEntity> patrimonioEntities = new ArrayList<>();
        patrimonioEntities.add(PatrimonioEntityBuilder.builder().build());

        when(patrimonioRepository.buscaTodos(anyLong()))
                .thenReturn(patrimonioEntities);

        List<PatrimonioEntity> retornoConsulta = patrimonioRepositoryImpl
                .implementaBuscaPorTodos(1L);

        assertEquals("[PatrimonioEntity(id=1, dataCadastro=2023-08-21, horaCadastro=10:20, " +
                        "dataEntrada=2023-08-21, descricao=Dinheiro, valor=100.0, tipoPatrimonio=ATIVO, " +
                        "exclusao=null, colaboradorResponsavel=null, empresa=null)]",
                retornoConsulta.toString());
    }


    @Test
    @DisplayName("Deve testar método de implementação de busca por id em massa com sucesso")
    void deveTestarMetodoDeimplementacaoDeBuscaPorIdEmMassaComSucesso() {

        List<PatrimonioEntity> patrimonioEntities = new ArrayList<>();
        patrimonioEntities.add(PatrimonioEntityBuilder.builder().build());

        List<Long> ids = new ArrayList<>();
        ids.add(1L);

        when(patrimonioRepository.findAllById(any()))
                .thenReturn(patrimonioEntities);

        List<PatrimonioEntity> retornoConsulta = patrimonioRepositoryImpl
                .implementaBuscaPorIdEmMassa(ids);

        assertEquals("[PatrimonioEntity(id=1, dataCadastro=2023-08-21, horaCadastro=10:20, " +
                        "dataEntrada=2023-08-21, descricao=Dinheiro, valor=100.0, tipoPatrimonio=ATIVO, " +
                        "exclusao=null, colaboradorResponsavel=null, empresa=null)]",
                retornoConsulta.toString());
    }

    @Test
    @DisplayName("Deve testar método de implementação de busca por id em massa com erro")
    void deveTestarMetodoDeimplementacaoDeBuscaPorIdEmMassaComErro() {

        List<PatrimonioEntity> patrimonioEntities = new ArrayList<>();

        List<Long> ids = new ArrayList<>();
        ids.add(1L);

        when(patrimonioRepository.findAllById(any()))
                .thenReturn(patrimonioEntities);

        assertThrows(ObjectNotFoundException.class,
                () -> patrimonioRepositoryImpl.implementaBuscaPorIdEmMassa(ids));
    }

}
