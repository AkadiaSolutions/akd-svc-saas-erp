package br.akd.svc.akadia.services.sistema.patrimonios;

import br.akd.svc.akadia.models.dto.sistema.patrimonios.request.mock.PatrimonioRequestBuilder;
import br.akd.svc.akadia.models.dto.sistema.patrimonios.response.PatrimonioPageResponse;
import br.akd.svc.akadia.models.dto.sistema.patrimonios.response.PatrimonioResponse;
import br.akd.svc.akadia.models.dto.sistema.patrimonios.response.mock.PatrimonioPageResponseBuilder;
import br.akd.svc.akadia.models.dto.sistema.patrimonios.response.mock.PatrimonioResponseBuilder;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks.ColaboradorEntityBuilder;
import br.akd.svc.akadia.models.entities.sistema.patrimonios.PatrimonioEntity;
import br.akd.svc.akadia.models.entities.sistema.patrimonios.mocks.PatrimonioEntityBuilder;
import br.akd.svc.akadia.repositories.sistema.patrimonios.PatrimonioRepository;
import br.akd.svc.akadia.repositories.sistema.patrimonios.impl.PatrimonioRepositoryImpl;
import br.akd.svc.akadia.services.exceptions.InvalidRequestException;
import br.akd.svc.akadia.services.sistema.colaboradores.acao.AcaoService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Service: Patrimonio")
class PatrimonioServiceTest {

    @InjectMocks
    PatrimonioService patrimonioService;

    @Mock
    AcaoService acaoService;

    @Mock
    PatrimonioTypeConverter patrimonioTypeConverter;

    @Mock
    PatrimonioValidationService patrimonioValidationService;

    @Mock
    PatrimonioRepositoryImpl patrimonioRepositoryImpl;

    @Mock
    PatrimonioRepository patrimonioRepository;

    @Test
    @DisplayName("Deve testar método de criação de patrimônio")
    void deveTestarMetodoDeCriacaoDePatrimonio() {

        when(patrimonioRepositoryImpl.implementaPersistencia(any()))
                .thenReturn(PatrimonioEntityBuilder.builder().build());

        Mockito.doNothing().when(acaoService)
                .salvaHistoricoColaborador(any(), any(), any(), any(), any());

        when(patrimonioTypeConverter.converteEntityParaResponse(any()))
                .thenReturn(PatrimonioResponseBuilder.builder().build());

        PatrimonioResponse patrimonioResponse = patrimonioService.criaNovoPatrimonio(
                ColaboradorEntityBuilder.builder().comEmpresa().comAcessoCompleto().build(),
                PatrimonioRequestBuilder.builder().build());

        assertEquals("PatrimonioResponse(id=1, dataCadastro=2023-08-21, horaCadastro=10:20, " +
                        "dataEntrada=2023-08-21, descricao=Dinheiro, valor=100.0, tipoPatrimonio=Ativo)",
                patrimonioResponse.toString());
    }


    @Test
    @DisplayName("Deve testar método de remoção de patrimônio")
    void deveTestarMetodoDeRemocaoDePatrimonio() {

        when(patrimonioRepositoryImpl.implementaBuscaPorId(anyLong(), anyLong()))
                .thenReturn(PatrimonioEntityBuilder.builder().build());

        when(patrimonioRepositoryImpl.implementaPersistencia(any()))
                .thenReturn(PatrimonioEntityBuilder.builder().build());

        when(patrimonioTypeConverter.converteEntityParaResponse(any()))
                .thenReturn(PatrimonioResponseBuilder.builder().build());

        Assertions.assertEquals("PatrimonioResponse(id=1, dataCadastro=2023-08-21, horaCadastro=10:20, " +
                        "dataEntrada=2023-08-21, descricao=Dinheiro, valor=100.0, tipoPatrimonio=Ativo)",
                patrimonioService.removeObjeto(
                        ColaboradorEntityBuilder.builder().comEmpresa().build(), 1L).toString());
    }

    @Test
    @DisplayName("Deve testar método de remoção de patrimônio com patrimônio já excluído")
    void deveTestarMetodoDeRemocaoDePatrimonioComPatrimonioJaExcluido() {
        when(patrimonioRepositoryImpl.implementaBuscaPorId(anyLong(), anyLong()))
                .thenReturn(PatrimonioEntityBuilder.builder().comExclusao().build());

        Mockito.doThrow(new InvalidRequestException("O patrimônio selecionado já foi excluído"))
                .when(patrimonioValidationService).validaSeObjetoEstaExcluido(any(), any());

        try {
            patrimonioService.removeObjeto(ColaboradorEntityBuilder.builder().comEmpresa().build(), 1L);
            Assertions.fail();
        } catch (InvalidRequestException invalidRequestException) {
            Assertions.assertEquals("O patrimônio selecionado já foi excluído",
                    invalidRequestException.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar método de remoção de patrimônio em massa")
    void deveTestarMetodoDeRemocaoDePatrimonioEmMassa() {
        when(patrimonioRepositoryImpl.implementaBuscaPorId(anyLong(), anyLong()))
                .thenReturn(PatrimonioEntityBuilder.builder().build());

        List<Long> ids = new ArrayList<>();
        ids.add(1L);

        patrimonioService.removeEmMassa(ColaboradorEntityBuilder.builder().comEmpresa().build(), ids);
        Assertions.assertNotNull(ids);
    }

    @Test
    @DisplayName("Deve testar método de remoção de patrimônio com exception")
    void deveTestarMetodoDeRemocaoDePatrimonioEmMassaComException() {
        when(patrimonioRepositoryImpl.implementaBuscaPorId(anyLong(), anyLong()))
                .thenReturn(PatrimonioEntityBuilder.builder().build());

        List<Long> ids = new ArrayList<>();
        try {
            patrimonioService.removeEmMassa(ColaboradorEntityBuilder.builder().comEmpresa().build(), ids);
            Assertions.fail();
        } catch (InvalidRequestException invalidRequestException) {
            Assertions.assertEquals("Nenhum patrimônio foi encontrado para remoção",
                    invalidRequestException.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar método de busca de patrimônio por ID")
    void deveTestarMetodoDeBuscaDePatrimonioPorId() {

        when(patrimonioRepositoryImpl.implementaBuscaPorId(any(), any()))
                .thenReturn(PatrimonioEntityBuilder.builder().build());

        when(patrimonioTypeConverter.converteEntityParaResponse(any()))
                .thenReturn(PatrimonioResponseBuilder.builder().build());

        PatrimonioResponse patrimonioResponse = patrimonioService.realizaBuscaPorId(
                ColaboradorEntityBuilder.builder().comEmpresa().build(),
                1L);

        Assertions.assertEquals("PatrimonioResponse(id=1, dataCadastro=2023-08-21, horaCadastro=10:20, " +
                        "dataEntrada=2023-08-21, descricao=Dinheiro, valor=100.0, tipoPatrimonio=Ativo)",
                patrimonioResponse.toString());
    }

    @Test
    @DisplayName("Deve testar método de busca paginada de patrimônios com campo de busca preenchido")
    void deveTestarMetodoDeBuscaPaginadaDePatrimoniosComCampoBuscaPreenchido() {
        List<PatrimonioEntity> patrimonios = new ArrayList<>();
        patrimonios.add(PatrimonioEntityBuilder.builder().build());
        Pageable pageable = PageRequest.of(0, 10);

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), patrimonios.size());
        Page<PatrimonioEntity> patrimoniosPaged =
                new PageImpl<>(patrimonios.subList(start, end), pageable, patrimonios.size());

        when(patrimonioRepository.buscaPaginadaTypeAhead(any(), any(), any()))
                .thenReturn(patrimoniosPaged);

        when(patrimonioTypeConverter.converteListaEntityListaResponse(any()))
                .thenReturn(PatrimonioPageResponseBuilder.builder().build());

        PatrimonioPageResponse patrimonioPageResponse = patrimonioService.realizaBuscaPaginada(
                ColaboradorEntityBuilder.builder().comEmpresa().comAcessoCompleto().build(),
                PageRequest.of(0, 10),
                "busca");

        Assertions.assertEquals("PatrimonioPageResponse(content=null, empty=true, first=true, last=true, " +
                        "number=0, numberOfElements=0, pageNumber=0, pageSize=0, paged=true, unpaged=false, size=0, " +
                        "totalElements=0, totalPages=0)",
                patrimonioPageResponse.toString());
    }

    @Test
    @DisplayName("Deve testar método de busca paginada de patrimônios com campo de busca nulo")
    void deveTestarMetodoDeBuscaPaginadaDePatrimoniosComCampoBuscaVazio() {
        List<PatrimonioEntity> patrimonios = new ArrayList<>();
        patrimonios.add(PatrimonioEntityBuilder.builder()
                .build());
        Pageable pageable = PageRequest.of(0, 10);

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), patrimonios.size());
        Page<PatrimonioEntity> patrimoniosPaged =
                new PageImpl<>(patrimonios.subList(start, end), pageable, patrimonios.size());

        when(patrimonioRepository.buscaPaginada(any(), any()))
                .thenReturn(patrimoniosPaged);

        when(patrimonioTypeConverter.converteListaEntityListaResponse(any()))
                .thenReturn(PatrimonioPageResponseBuilder.builder().build());

        PatrimonioPageResponse patrimonioPageResponse = patrimonioService.realizaBuscaPaginada(
                ColaboradorEntityBuilder.builder().comEmpresa().comAcessoCompleto().build(),
                PageRequest.of(0, 10),
                null);

        Assertions.assertEquals("PatrimonioPageResponse(content=null, empty=true, first=true, last=true, " +
                        "number=0, numberOfElements=0, pageNumber=0, pageSize=0, paged=true, unpaged=false, size=0, " +
                        "totalElements=0, totalPages=0)",
                patrimonioPageResponse.toString());
    }

    @Test
    @DisplayName("Deve testar método de atualização do patrimônio")
    void deveTestarMetodoDeAtualizacaoDePatrimonio() {

        when(patrimonioRepositoryImpl.implementaBuscaPorId(any(), any()))
                .thenReturn(PatrimonioEntityBuilder.builder().build());

        when(patrimonioRepositoryImpl.implementaPersistencia(any()))
                .thenReturn(PatrimonioEntityBuilder.builder().build());

        doNothing().when(acaoService).salvaHistoricoColaborador(any(), any(), any(), any(), any());

        when(patrimonioTypeConverter.converteEntityParaResponse(any()))
                .thenReturn(PatrimonioResponseBuilder.builder().build());

        PatrimonioResponse patrimonio = patrimonioService.atualizaObjeto(
                ColaboradorEntityBuilder.builder()
                        .comEmpresa()
                        .comAcessoCompleto()
                        .build(),
                1L,
                PatrimonioRequestBuilder.builder()
                        .build());

        Assertions.assertEquals("PatrimonioResponse(id=1, dataCadastro=2023-08-21, horaCadastro=10:20, " +
                        "dataEntrada=2023-08-21, descricao=Dinheiro, valor=100.0, tipoPatrimonio=Ativo)",
                patrimonio.toString());
    }

}
