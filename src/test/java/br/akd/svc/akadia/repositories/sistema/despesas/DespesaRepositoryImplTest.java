package br.akd.svc.akadia.repositories.sistema.despesas;

import br.akd.svc.akadia.modules.erp.despesas.models.entity.DespesaEntity;
import br.akd.svc.akadia.models.entities.sistema.despesas.mocks.DespesaEntityBuilder;
import br.akd.svc.akadia.modules.erp.despesas.repository.DespesaRepository;
import br.akd.svc.akadia.modules.erp.despesas.repository.impl.DespesaRepositoryImpl;
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
@DisplayName("Repository: Despesa")
class DespesaRepositoryImplTest {

    @InjectMocks
    DespesaRepositoryImpl despesaRepositoryImpl;

    @Mock
    DespesaRepository despesaRepository;

    @Test
    @DisplayName("Deve testar método de implementação de persistência")
    void deveTestarMetodoDeimplementacaoDePersistencia() {

        when(despesaRepository.save(any()))
                .thenReturn(DespesaEntityBuilder.builder().build());

        DespesaEntity despesaEntity = despesaRepositoryImpl
                .implementaPersistencia(DespesaEntityBuilder.builder().build());

        assertEquals("DespesaEntity(id=1, dataCadastro=2023-08-18, horaCadastro=07:55, " +
                        "dataPagamento=2023-08-18, dataAgendamento=Pago, descricao=Gasolina carro, valor=100.0, " +
                        "observacao=Sem recorrências, tipoRecorrencia=SEM_RECORRENCIA, statusDespesa=PAGO, " +
                        "tipoDespesa=VARIAVEL, exclusao=null, colaboradorResponsavel=null, empresa=null)",
                despesaEntity.toString());
    }

    @Test
    @DisplayName("Deve testar método de implementação de persistência em massa")
    void deveTestarMetodoDeimplementacaoDePersistenciaEmMassa() {

        List<DespesaEntity> despesas = new ArrayList<>();
        despesas.add(DespesaEntityBuilder.builder().build());

        try {
            despesaRepositoryImpl.implementaPersistenciaEmMassa(despesas);
            assertDoesNotThrow(() -> new RuntimeException());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @DisplayName("Deve testar método de implementação de busca por id com sucesso")
    void deveTestarMetodoDeimplementacaoDeBuscaPorIdComSucesso() {

        when(despesaRepository.buscaPorId(anyLong(), anyLong()))
                .thenReturn(Optional.of(DespesaEntityBuilder.builder().build()));

        DespesaEntity despesaEntity = despesaRepositoryImpl
                .implementaBuscaPorId(1L, 1L);

        assertEquals("DespesaEntity(id=1, dataCadastro=2023-08-18, horaCadastro=07:55, " +
                        "dataPagamento=2023-08-18, dataAgendamento=Pago, descricao=Gasolina carro, valor=100.0, " +
                        "observacao=Sem recorrências, tipoRecorrencia=SEM_RECORRENCIA, statusDespesa=PAGO, " +
                        "tipoDespesa=VARIAVEL, exclusao=null, colaboradorResponsavel=null, empresa=null)",
                despesaEntity.toString());
    }

    @Test
    @DisplayName("Deve testar método de implementação de busca por id com erro")
    void deveTestarMetodoDeimplementacaoDeBuscaPorIdComErro() {
        when(despesaRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(ObjectNotFoundException.class, () ->
                despesaRepositoryImpl.implementaBuscaPorId(1L, 1L));
    }

    @Test
    @DisplayName("Deve testar método de implementação de busca por todos os itens")
    void deveTestarMetodoDeimplementacaoDeBuscaPorTodosOsItens() {

        List<DespesaEntity> despesaEntities = new ArrayList<>();
        despesaEntities.add(DespesaEntityBuilder.builder().build());

        when(despesaRepository.buscaTodos(anyLong()))
                .thenReturn(despesaEntities);

        List<DespesaEntity> retornoConsulta = despesaRepositoryImpl
                .implementaBuscaPorTodos(1L);

        assertEquals("[DespesaEntity(id=1, dataCadastro=2023-08-18, horaCadastro=07:55, " +
                        "dataPagamento=2023-08-18, dataAgendamento=Pago, descricao=Gasolina carro, valor=100.0, " +
                        "observacao=Sem recorrências, tipoRecorrencia=SEM_RECORRENCIA, statusDespesa=PAGO, " +
                        "tipoDespesa=VARIAVEL, exclusao=null, colaboradorResponsavel=null, empresa=null)]",
                retornoConsulta.toString());
    }


    @Test
    @DisplayName("Deve testar método de implementação de busca por id em massa com sucesso")
    void deveTestarMetodoDeimplementacaoDeBuscaPorIdEmMassaComSucesso() {

        List<DespesaEntity> despesaEntities = new ArrayList<>();
        despesaEntities.add(DespesaEntityBuilder.builder().build());

        List<Long> ids = new ArrayList<>();
        ids.add(1L);

        when(despesaRepository.findAllById(any()))
                .thenReturn(despesaEntities);

        List<DespesaEntity> retornoConsulta = despesaRepositoryImpl
                .implementaBuscaPorIdEmMassa(ids);

        assertEquals("[DespesaEntity(id=1, dataCadastro=2023-08-18, horaCadastro=07:55, " +
                        "dataPagamento=2023-08-18, dataAgendamento=Pago, descricao=Gasolina carro, valor=100.0, " +
                        "observacao=Sem recorrências, tipoRecorrencia=SEM_RECORRENCIA, statusDespesa=PAGO, " +
                        "tipoDespesa=VARIAVEL, exclusao=null, colaboradorResponsavel=null, empresa=null)]",
                retornoConsulta.toString());
    }

    @Test
    @DisplayName("Deve testar método de implementação de busca por id em massa com erro")
    void deveTestarMetodoDeimplementacaoDeBuscaPorIdEmMassaComErro() {

        List<DespesaEntity> despesaEntities = new ArrayList<>();

        List<Long> ids = new ArrayList<>();
        ids.add(1L);

        when(despesaRepository.findAllById(any()))
                .thenReturn(despesaEntities);

        assertThrows(ObjectNotFoundException.class,
                () -> despesaRepositoryImpl.implementaBuscaPorIdEmMassa(ids));
    }
}
