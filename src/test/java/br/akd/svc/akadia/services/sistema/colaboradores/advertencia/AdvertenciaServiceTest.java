package br.akd.svc.akadia.services.sistema.colaboradores.advertencia;

import br.akd.svc.akadia.modules.erp.colaboradores.advertencia.models.dto.response.AdvertenciaPageResponse;
import br.akd.svc.akadia.modules.global.entity.ArquivoEntity;
import br.akd.svc.akadia.modules.erp.colaboradores.advertencia.models.entity.AdvertenciaEntity;
import br.akd.svc.akadia.modules.erp.colaboradores.advertencia.services.AdvertenciaRelatorioService;
import br.akd.svc.akadia.modules.erp.colaboradores.advertencia.services.AdvertenciaService;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.entity.ColaboradorEntity;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks.AdvertenciaEntityBuilder;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks.ColaboradorEntityBuilder;
import br.akd.svc.akadia.modules.global.enums.TipoArquivoEnum;
import br.akd.svc.akadia.modules.erp.colaboradores.advertencia.models.enums.StatusAdvertenciaEnum;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.repository.ColaboradorRepository;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.repository.impl.ColaboradorRepositoryImpl;
import br.akd.svc.akadia.modules.erp.colaboradores.acao.services.AcaoService;
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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Service: Advertencia")
class AdvertenciaServiceTest {

    @InjectMocks
    AdvertenciaService advertenciaService;

    @Mock
    AcaoService acaoService;

    @Mock
    ColaboradorRepository colaboradorRepository;

    @Mock
    ColaboradorRepositoryImpl colaboradorRepositoryImpl;

    @Mock
    AdvertenciaRelatorioService advertenciaRelatorioService;

    @Test
    @DisplayName("Deve testar método de conversão de entities para PageResponse")
    void deveTestarMetodoDeConversaoDeEntitiesParaPageResponse() {

        List<AdvertenciaEntity> advertencias = new ArrayList<>();
        advertencias.add(AdvertenciaEntityBuilder.builder().build());

        Pageable pageable = PageRequest.of(0, 10);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), advertencias.size());
        Page<AdvertenciaEntity> advertenciasPaged = new PageImpl<>(advertencias.subList(start, end), pageable, advertencias.size());

        AdvertenciaPageResponse advertenciaPageResponse = advertenciaService.converteListaDeEntitiesParaPageResponse(advertenciasPaged);

        Assertions.assertEquals("AdvertenciaPageResponse(content=[AdvertenciaEntity(id=1, " +
                        "dataCadastro=2023-02-13, horaCadastro=10:44, motivo=Brigou na loja, " +
                        "descricao=Cuspiu no cliente, statusAdvertenciaEnum=ASSINADA, advertenciaAssinada=null)], " +
                        "empty=false, first=true, last=true, number=0, numberOfElements=1, offset=0, pageNumber=0, " +
                        "pageSize=10, paged=true, unpaged=false, size=10, totalElements=1, totalPages=1)",
                advertenciaPageResponse.toString());
    }

    @Test
    @DisplayName("Deve testar método de obtenção de advertências do colaborador")
    void deveTestarMetodoDeObtencaoAdvertenciasColaborador() {

        ColaboradorEntity colaboradorLogado = ColaboradorEntityBuilder.builder()
                .comEmpresa()
                .build();

        List<AdvertenciaEntity> advertencias = new ArrayList<>();
        advertencias.add(AdvertenciaEntityBuilder.builder().build());

        Pageable pageable = PageRequest.of(0, 10);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), advertencias.size());
        Page<AdvertenciaEntity> advertenciasPaged = new PageImpl<>(advertencias.subList(start, end), pageable, advertencias.size());

        when(colaboradorRepository.buscaAdvertenciasPorIdColaborador(any(), any(), any())).thenReturn(advertenciasPaged);

        AdvertenciaPageResponse advertenciaPageResponse =
                advertenciaService.obtemAdvertenciasColaborador(colaboradorLogado, pageable, 1L);

        Assertions.assertEquals("AdvertenciaPageResponse(content=[AdvertenciaEntity(id=1, " +
                        "dataCadastro=2023-02-13, horaCadastro=10:44, motivo=Brigou na loja, " +
                        "descricao=Cuspiu no cliente, statusAdvertenciaEnum=ASSINADA, advertenciaAssinada=null)], " +
                        "empty=false, first=true, last=true, number=0, numberOfElements=1, offset=0, pageNumber=0, " +
                        "pageSize=10, paged=true, unpaged=false, size=10, totalElements=1, totalPages=1)",
                advertenciaPageResponse.toString());
    }

    @Test
    @DisplayName("Deve testar método de tratamento de tipo de arquivo da advertência")
    void deveTestarMetodoDeTratamentoDeTipoDeArquivoDaAdvertencia() {

        advertenciaService.realizaTratamentoTipoDeArquivoDoContratoAdvertencia(
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        advertenciaService.realizaTratamentoTipoDeArquivoDoContratoAdvertencia("null");
        advertenciaService.realizaTratamentoTipoDeArquivoDoContratoAdvertencia("image/png");
        advertenciaService.realizaTratamentoTipoDeArquivoDoContratoAdvertencia("image/jpeg");

        TipoArquivoEnum tipoArquivoEnum =
                advertenciaService.realizaTratamentoTipoDeArquivoDoContratoAdvertencia("nenhum");

        Assertions.assertEquals("pdf", tipoArquivoEnum.getDesc());
    }

    @Test
    @DisplayName("Deve testar método de salvamento de advertência")
    void deveTestarMetodoDeSalvamentoDeAdvertencia() throws IOException {

        HttpServletResponse mockedResponse = Mockito.mock(HttpServletResponse.class);
        ColaboradorEntity colaboradorEntity = ColaboradorEntityBuilder.builder()
                .comAcessoCompleto()
                .comEmpresa()
                .build();

        when(colaboradorRepositoryImpl.implementaBuscaPorId(any(), any())).thenReturn(colaboradorEntity);
        when(colaboradorRepositoryImpl.implementaPersistencia(any())).thenReturn(colaboradorEntity);
        doNothing().when(advertenciaRelatorioService).exportarPdf(any(), any(), any(), any());
        doNothing().when(acaoService).salvaHistoricoColaborador(any(), any(), any(), any(), any());

        advertenciaService.geraAdvertenciaColaborador(colaboradorEntity, mockedResponse, 1L, null, "{}");
    }

    @Test
    @DisplayName("Deve testar método de busca de advertência por id")
    void deveTestarMetodoDeBuscaDeAdvertenciaPorId() {

        List<AdvertenciaEntity> advertencias = new ArrayList<>();
        advertencias.add(AdvertenciaEntityBuilder.builder().build());

        AdvertenciaEntity advertenciaEncontrada =
                advertenciaService.realizaBuscaAdvertenciaPorIdNaListaDeAdvertenciasDoColaborador(
                        advertencias, 1L);

        Assertions.assertEquals("AdvertenciaEntity(id=1, dataCadastro=2023-02-13, horaCadastro=10:44, " +
                "motivo=Brigou na loja, descricao=Cuspiu no cliente, statusAdvertenciaEnum=ASSINADA, " +
                "advertenciaAssinada=null)", advertenciaEncontrada.toString());
    }

    @Test
    @DisplayName("Deve testar método de gerar PDF padrão da advertência")
    void deveTestarMetodoDeGerarPdfPadraoDaAdvertencia() throws IOException {

        ColaboradorEntity colaboradorEntity = ColaboradorEntityBuilder.builder()
                .comAcessoCompleto()
                .comEmpresa()
                .comAdvertencia()
                .build();
        HttpServletResponse mockedResponse = Mockito.mock(HttpServletResponse.class);

        when(colaboradorRepositoryImpl.implementaBuscaPorId(any(), any())).thenReturn(colaboradorEntity);
        doNothing().when(advertenciaRelatorioService).exportarPdf(any(), any(), any(), any());
        doNothing().when(acaoService).salvaHistoricoColaborador(any(), any(), any(), any(), any());

        advertenciaService.geraPdfPadraoAdvertencia(colaboradorEntity, mockedResponse, 1L, 1L);
    }

    @Test
    @DisplayName("Deve testar método de anexar arquivo na advertência")
    void deveTestarMetodoDeAnexarArquivoNaAdvertencia() throws IOException {

        ColaboradorEntity colaboradorEntity = ColaboradorEntityBuilder.builder()
                .comAdvertencia()
                .comEmpresa()
                .comAcessoCompleto()
                .build();
        MultipartFile multipartFile = new MockMultipartFile(
                "test.pdf",
                "test.pdf",
                "text/plain",
                "Mock".getBytes(StandardCharsets.UTF_8));

        when(colaboradorRepositoryImpl.implementaBuscaPorId(any(), any())).thenReturn(colaboradorEntity);
        when(colaboradorRepositoryImpl.implementaPersistencia(any())).thenReturn(colaboradorEntity);
        doNothing().when(acaoService).salvaHistoricoColaborador(any(), any(), any(), any(), any());

        advertenciaService.anexaArquivoAdvertencia(colaboradorEntity, multipartFile, 1L, 1L);
    }

    @Test
    @DisplayName("Deve testar método de alterar status da advertência")
    void deveTestarMetodoDeAlterarStatusDaAdvertencia() {

        ColaboradorEntity colaboradorEntity = ColaboradorEntityBuilder.builder()
                .comAdvertencia()
                .comEmpresa()
                .comAcessoCompleto()
                .build();

        when(colaboradorRepositoryImpl.implementaBuscaPorId(any(), any())).thenReturn(colaboradorEntity);
        when(colaboradorRepositoryImpl.implementaPersistencia(any())).thenReturn(colaboradorEntity);
        doNothing().when(acaoService).salvaHistoricoColaborador(any(), any(), any(), any(), any());

        advertenciaService.alteraStatusAdvertencia(
                colaboradorEntity,
                StatusAdvertenciaEnum.ASSINADA,
                1L,
                1L);
    }

    @Test
    @DisplayName("Deve testar método de remover advertência")
    void deveTestarMetodoDeRemoverAdvertencia() {

        ColaboradorEntity colaboradorEntity = ColaboradorEntityBuilder.builder()
                .comAdvertencia()
                .comEmpresa()
                .comAcessoCompleto()
                .build();

        when(colaboradorRepositoryImpl.implementaBuscaPorId(any(), any())).thenReturn(colaboradorEntity);
        when(colaboradorRepositoryImpl.implementaPersistencia(any())).thenReturn(colaboradorEntity);
        doNothing().when(acaoService).salvaHistoricoColaborador(any(), any(), any(), any(), any());

        advertenciaService.removerAdvertencia(
                colaboradorEntity,
                1L,
                1L);
    }

    @Test
    @DisplayName("Deve testar método de obtenção de anexo da advertência")
    void deveTestarMetodoDeObtencaoDeAnexoDaAdvetencia() {

        ColaboradorEntity colaboradorEntity = ColaboradorEntityBuilder.builder()
                .comAdvertenciaComArquivo()
                .comEmpresa()
                .comAcessoCompleto()
                .build();

        when(colaboradorRepositoryImpl.implementaBuscaPorId(any(), any())).thenReturn(colaboradorEntity);
        doNothing().when(acaoService).salvaHistoricoColaborador(any(), any(), any(), any(), any());

        ArquivoEntity arquivoEntity = advertenciaService.obtemAnexoAdvertencia(
                colaboradorEntity,
                1L,
                1L);

        Assertions.assertEquals("ArquivoEntity(id=1, nome=arquivo.pdf, tamanho=1000, tipo=PDF, arquivo=[])", arquivoEntity.toString());
    }

}
