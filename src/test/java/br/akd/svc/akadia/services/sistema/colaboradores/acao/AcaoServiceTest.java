package br.akd.svc.akadia.services.sistema.colaboradores.acao;

import br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.acao.AcaoPageResponse;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.AcaoEntity;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.ColaboradorEntity;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks.AcaoEntityBuilder;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks.ColaboradorEntityBuilder;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.ModulosEnum;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.TipoAcaoEnum;
import br.akd.svc.akadia.repositories.sistema.colaboradores.ColaboradorRepository;
import br.akd.svc.akadia.repositories.sistema.colaboradores.impl.ColaboradorRepositoryImpl;
import br.akd.svc.akadia.services.sistema.colaboradores.acao.AcaoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Service: Acao")
class AcaoServiceTest {

    @InjectMocks
    AcaoService acaoService;

    @Mock
    ColaboradorRepository colaboradorRepository;

    @Mock
    ColaboradorRepositoryImpl colaboradorRepositoryImpl;

    @Test
    @DisplayName("Deve testar método de conversão de entities para PageResponse")
    void deveTestarMetodoDeConversaoDeEntitiesParaPageResponse() {

        List<AcaoEntity> acoes = new ArrayList<>();
        acoes.add(AcaoEntityBuilder.builder().build());

        Pageable pageable = PageRequest.of(0, 10);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), acoes.size());
        Page<AcaoEntity> acoesPaged = new PageImpl<>(acoes.subList(start, end), pageable, acoes.size());

        AcaoPageResponse acaoPageResponse = acaoService.converteListaDeEntitiesParaPageResponse(acoesPaged);

        Assertions.assertEquals("AcaoPageResponse(content=[AcaoEntity(id=1, idObjeto=1, dataCriacao=2023-02-13, " +
                "horaCriacao=10:44, moduloEnum=COLABORADORES, tipoAcaoEnum=CRIACAO, observacao=observação)], " +
                "empty=false, first=true, last=true, number=0, numberOfElements=1, pageNumber=0, pageSize=10, " +
                "paged=true, unpaged=false, size=10, totalElements=1, totalPages=1)", acaoPageResponse.toString());
    }

    @Test
    @DisplayName("Deve testar método de obtenção de ações do colaborador")
    void deveTestarMetodoDeObtencaoAcoesColaborador() {

        ColaboradorEntity colaboradorLogado = ColaboradorEntityBuilder.builder()
                .comEmpresa()
                .build();

        List<AcaoEntity> acoes = new ArrayList<>();
        acoes.add(AcaoEntityBuilder.builder().build());

        Pageable pageable = PageRequest.of(0, 10);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), acoes.size());
        Page<AcaoEntity> acoesPaged = new PageImpl<>(acoes.subList(start, end), pageable, acoes.size());

        when(colaboradorRepository.buscaAcoesPorIdColaborador(any(), any(), any())).thenReturn(acoesPaged);

        AcaoPageResponse acaoPageResponse =
                acaoService.obtemAcoesColaborador(colaboradorLogado, pageable, 1L);

        Assertions.assertEquals("AcaoPageResponse(content=[AcaoEntity(id=1, idObjeto=1, dataCriacao=2023-02-13, " +
                "horaCriacao=10:44, moduloEnum=COLABORADORES, tipoAcaoEnum=CRIACAO, observacao=observação)], " +
                "empty=false, first=true, last=true, number=0, numberOfElements=1, pageNumber=0, pageSize=10, " +
                "paged=true, unpaged=false, size=10, totalElements=1, totalPages=1)", acaoPageResponse.toString());
    }

    @Test
    @DisplayName("Deve testar método de salvamento de histórico do colaborador")
    void deveTestarMetodoDeSalvamentoDeHistoricoDoColaborador() {

        ColaboradorEntity colaboradorLogado = ColaboradorEntityBuilder.builder()
                .comEmpresa()
                .build();

        when(colaboradorRepositoryImpl.implementaPersistencia(any())).thenReturn(colaboradorLogado);

        try {
            acaoService.salvaHistoricoColaborador(
                    colaboradorLogado,
                    1L,
                    ModulosEnum.COLABORADORES,
                    TipoAcaoEnum.CRIACAO,
                    "observação");
        } catch (Exception e) {
            Assertions.fail();
        }
    }

}
