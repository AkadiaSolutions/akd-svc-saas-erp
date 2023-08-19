package br.akd.svc.akadia.services.sistema.despesas;

import br.akd.svc.akadia.models.dto.sistema.despesas.request.mock.DespesaRequestBuilder;
import br.akd.svc.akadia.models.dto.sistema.despesas.response.DespesaPageResponse;
import br.akd.svc.akadia.models.dto.sistema.despesas.response.DespesaResponse;
import br.akd.svc.akadia.models.dto.sistema.despesas.response.mock.DespesaPageResponseBuilder;
import br.akd.svc.akadia.models.dto.sistema.despesas.response.mock.DespesaResponseBuilder;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks.ColaboradorEntityBuilder;
import br.akd.svc.akadia.models.entities.sistema.despesas.DespesaEntity;
import br.akd.svc.akadia.models.entities.sistema.despesas.mocks.DespesaEntityBuilder;
import br.akd.svc.akadia.repositories.sistema.despesas.DespesaRepository;
import br.akd.svc.akadia.repositories.sistema.despesas.impl.DespesaRepositoryImpl;
import br.akd.svc.akadia.services.exceptions.InvalidRequestException;
import br.akd.svc.akadia.services.sistema.colaboradores.acao.AcaoService;
import br.akd.svc.akadia.utils.SecurityUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Service: Despesa")
class DespesaServiceTest {

    @InjectMocks
    DespesaService despesaService;

    @Mock
    DespesaValidationService despesaValidationService;

    @Mock
    DespesaTypeConverter despesaTypeConverter;

    @Mock
    AcaoService acaoService;

    @Mock
    SecurityUtil securityUtil;

    @Mock
    DespesaRepositoryImpl despesaRepositoryImpl;

    @Mock
    DespesaRepository despesaRepository;

    @Test
    @DisplayName("Deve testar método de criação de despesa")
    void deveTestarMetodoDeCriacaoDeDespesa() {

        when(despesaRepositoryImpl.implementaPersistencia(any()))
                .thenReturn(DespesaEntityBuilder.builder().build());

        Mockito.doNothing().when(acaoService)
                .salvaHistoricoColaborador(any(), any(), any(), any(), any());

        when(despesaTypeConverter.converteDespesaEntityParaDespesaResponse(any()))
                .thenReturn(DespesaResponseBuilder.builder().build());

        DespesaResponse despesaResponse = despesaService.criaNovaDespesa(
                ColaboradorEntityBuilder.builder().comEmpresa().comAcessoCompleto().build(),
                DespesaRequestBuilder.builder().build());

        assertEquals("DespesaResponse(id=1, dataCadastro=2023-08-18, horaCadastro=07:55, " +
                "dataPagamento=2023-08-18, dataAgendamento=Pago, descricao=Gasolina carro, valor=100.0, " +
                "observacao=Sem recorrências, qtdRecorrencias=0, statusDespesa=PAGO, tipoDespesa=VARIAVEL, " +
                "tipoRecorrencia=SEM_RECORRENCIA)", despesaResponse.toString());
    }

    @Test
    @DisplayName("Deve testar método de criação de recorrências")
    void deveTestarMetodoDeCriacaoDeRecorrencias() {
        List<DespesaEntity> despesaEntities = despesaService.geraRecorrencias(
                ColaboradorEntityBuilder.builder().comEmpresa().comAcessoCompleto().build(),
                DespesaRequestBuilder.builder().comRecorrencia().build());

        Assertions.assertNotNull(despesaEntities);
    }

    @Test
    @DisplayName("Deve testar método de remoção de despesas")
    void deveTestarMetodoDeRemocaoDeDespesas() {

        when(despesaRepositoryImpl.implementaBuscaPorId(any(), any()))
                .thenReturn(DespesaEntityBuilder.builder().build());

        doNothing().when(despesaValidationService)
                .validaSeDespesaEstaExcluida(any(), any());

        when(despesaRepositoryImpl.implementaPersistencia(any()))
                .thenReturn(DespesaEntityBuilder.builder().build());

        when(despesaTypeConverter.converteDespesaEntityParaDespesaResponse(any()))
                .thenReturn(DespesaResponseBuilder.builder().build());

        doNothing().when(acaoService)
                .salvaHistoricoColaborador(any(), any(), any(), any(), any());

        DespesaResponse despesaResponse = despesaService.removeDespesa(
                ColaboradorEntityBuilder.builder().comEmpresa().comAcessoCompleto().build(),
                1L,
                false);

        assertEquals("DespesaResponse(id=1, dataCadastro=2023-08-18, horaCadastro=07:55, " +
                        "dataPagamento=2023-08-18, dataAgendamento=Pago, descricao=Gasolina carro, valor=100.0, " +
                        "observacao=Sem recorrências, qtdRecorrencias=0, statusDespesa=PAGO, tipoDespesa=VARIAVEL, " +
                        "tipoRecorrencia=SEM_RECORRENCIA)",
                despesaResponse.toString());
    }

    @Test
    @DisplayName("Deve testar método de remoção de recorrências em massa")
    void deveTestarMetodoDeRemocaoDeRecorrenciasEmMassa() {

        doNothing().when(despesaRepositoryImpl)
                .implementaPersistenciaEmMassa(any());

        doNothing().when(acaoService)
                .salvaHistoricoColaborador(any(), any(), any(), any(), any());

        List<DespesaEntity> despesaEntities = new ArrayList<>();
        despesaEntities.add(DespesaEntityBuilder.builder().build());

        despesaService.removeRecorrenciasEmMassa(
                ColaboradorEntityBuilder.builder().comEmpresa().comAcessoCompleto().build(),
                despesaEntities);

        assertDoesNotThrow(() ->
                new InvalidRequestException("Nenhuma despesa foi encontrada para remoção"));
    }

    @Test
    @DisplayName("Deve testar método de remoção de despesas em massa")
    void deveTestarMetodoDeRemocaoDeDespesasEmMassa() {

        when(despesaRepositoryImpl.implementaBuscaPorId(anyLong(), anyLong()))
                .thenReturn(DespesaEntityBuilder.builder().build());

        doNothing().when(despesaValidationService)
                .validaSeDespesaEstaExcluida(any(), any());

        doNothing().when(despesaRepositoryImpl)
                .implementaPersistenciaEmMassa(any());

        doNothing().when(acaoService)
                .salvaHistoricoColaborador(any(), any(), any(), any(), any());

        List<Long> ids = new ArrayList<>();
        ids.add(1L);

        despesaService.removeDespesasEmMassa(
                ColaboradorEntityBuilder.builder().comEmpresa().comAcessoCompleto().build(),
                ids);

        assertDoesNotThrow(() ->
                new InvalidRequestException("Nenhuma despesa foi encontrada para remoção"));
    }

    @Test
    @DisplayName("Deve testar método de atualização de despesa")
    void deveTestarMetodoDeAtualizacaoDeDespesa() {

        Mockito.when(despesaRepositoryImpl.implementaBuscaPorId(anyLong(), anyLong()))
                .thenReturn(DespesaEntityBuilder.builder().build());

        doNothing().when(despesaValidationService)
                .validaSeDespesaEstaExcluida(any(), any());

        when(despesaRepositoryImpl.implementaPersistencia(any()))
                .thenReturn(DespesaEntityBuilder.builder().build());

        doNothing().when(acaoService)
                .salvaHistoricoColaborador(any(), any(), any(), any(), any());

        when(despesaTypeConverter.converteDespesaEntityParaDespesaResponse(any()))
                .thenReturn(DespesaResponseBuilder.builder().build());

        DespesaResponse despesaResponse = despesaService.atualizaDespesa(
                ColaboradorEntityBuilder.builder().comEmpresa().comAcessoCompleto().build(),
                1L,
                DespesaRequestBuilder.builder().build());

        assertEquals("DespesaResponse(id=1, dataCadastro=2023-08-18, horaCadastro=07:55, " +
                "dataPagamento=2023-08-18, dataAgendamento=Pago, descricao=Gasolina carro, valor=100.0, " +
                "observacao=Sem recorrências, qtdRecorrencias=0, statusDespesa=PAGO, tipoDespesa=VARIAVEL, " +
                "tipoRecorrencia=SEM_RECORRENCIA)", despesaResponse.toString());
    }

    @Test
    @DisplayName("Deve testar método de busca de despesa por id")
    void deveTestarMetodoDeBuscaDeDespesaPorId() {

        when(despesaRepositoryImpl.implementaBuscaPorId(anyLong(), anyLong()))
                .thenReturn(DespesaEntityBuilder.builder().build());

        when(despesaTypeConverter.converteDespesaEntityParaDespesaResponse(any()))
                .thenReturn(DespesaResponseBuilder.builder().build());

        DespesaResponse despesaResponse = despesaService.realizaBuscaDeDespesaPorId(
                ColaboradorEntityBuilder.builder().comEmpresa().comAcessoCompleto().build(),
                1L);

        assertEquals("DespesaResponse(id=1, dataCadastro=2023-08-18, horaCadastro=07:55, " +
                        "dataPagamento=2023-08-18, dataAgendamento=Pago, descricao=Gasolina carro, " +
                        "valor=100.0, observacao=Sem recorrências, qtdRecorrencias=0, statusDespesa=PAGO, " +
                        "tipoDespesa=VARIAVEL, tipoRecorrencia=SEM_RECORRENCIA)",
                despesaResponse.toString());
    }

    @Test
    @DisplayName("Deve testar método de busca paginada de despesas com campo de busca preenchido")
    void deveTestarMetodoDeBuscaPaginadaDeDespesasComCampoBuscaPreenchido() {
        List<DespesaEntity> despesaEntities = new ArrayList<>();
        despesaEntities.add(DespesaEntityBuilder.builder()
                .build());
        Pageable pageable = PageRequest.of(0, 10);

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), despesaEntities.size());
        Page<DespesaEntity> despesaPaged = new PageImpl<>(despesaEntities.subList(start, end), pageable, despesaEntities.size());

        when(despesaRepository
                .buscaPorDespesasTypeAhead(any(), any(), any(), any(), any()))
                .thenReturn(despesaPaged);

        when(despesaTypeConverter.converteListaDeDespesasEntityParaDespesasResponse(any()))
                .thenReturn(DespesaPageResponseBuilder.builder().build());

        DespesaPageResponse despesaPageResponse = despesaService.realizaBuscaPaginadaPorDespesas(
                ColaboradorEntityBuilder.builder().comEmpresa().comAcessoCompleto().build(),
                PageRequest.of(0, 10),
                "08-2023",
                "busca");

        Assertions.assertEquals("DespesaPageResponse(content=null, empty=true, first=true, last=true, " +
                        "number=0, numberOfElements=0, pageNumber=0, pageSize=0, paged=true, unpaged=false, size=0, " +
                        "totalElements=0, totalPages=0)",
                despesaPageResponse.toString());
    }

}
