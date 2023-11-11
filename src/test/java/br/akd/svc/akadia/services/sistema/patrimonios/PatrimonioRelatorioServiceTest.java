package br.akd.svc.akadia.services.sistema.patrimonios;

import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.entity.ColaboradorEntity;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks.ColaboradorEntityBuilder;
import br.akd.svc.akadia.modules.erp.patrimonios.models.entity.PatrimonioEntity;
import br.akd.svc.akadia.models.entities.sistema.patrimonios.mocks.PatrimonioEntityBuilder;
import br.akd.svc.akadia.modules.erp.patrimonios.repository.impl.PatrimonioRepositoryImpl;
import br.akd.svc.akadia.modules.erp.colaboradores.acao.services.AcaoService;
import br.akd.svc.akadia.modules.erp.patrimonios.services.PatrimonioRelatorioService;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("RelatorioService: Patrimonio")
class PatrimonioRelatorioServiceTest {

    @InjectMocks
    PatrimonioRelatorioService patrimonioRelatorioService;

    @Mock
    PatrimonioRepositoryImpl patrimonioRepositoryImpl;

    @Mock
    AcaoService acaoService;

    @Test
    @DisplayName("Deve testar método de construção de tabela de objetos")
    void deveTestarMetodoDeConstrucaoDeTabelaDeObjetos() {
        List<PatrimonioEntity> patrimonios = new ArrayList<>();
        patrimonios.add(PatrimonioEntityBuilder.builder().build());

        PdfPTable pdfPTable = patrimonioRelatorioService.constroiTabelaObjetos(patrimonios);

        Assertions.assertNotNull(pdfPTable.toString());
    }

    @Test
    @DisplayName("Deve testar método de construção da tabela de informativos")
    void deveTestarMetodoDeConstrucaoTabelaInformativos() {
        List<PatrimonioEntity> patrimonios = new ArrayList<>();
        patrimonios.add(PatrimonioEntityBuilder.builder().build());

        PdfPTable pdfPTable = patrimonioRelatorioService.constroiTabelaInformativos(patrimonios);

        Assertions.assertNotNull(pdfPTable.toString());
    }

    @Test
    @DisplayName("Deve testar método de construção do parágrafo de data e hora")
    void deveTestarMetodoDeConstrucaoDoParagrafoDeDataHora() {

        ColaboradorEntity colaboradorLogado = ColaboradorEntityBuilder.builder().build();

        Paragraph paragraph = patrimonioRelatorioService.constroiParagrafoDataHora(colaboradorLogado);

        Assertions.assertNotNull(paragraph.toString());
    }

    @Test
    @DisplayName("Deve testar método de construção do parágrafo do título")
    void deveTestarMetodoDeConstrucaoDoParagrafoDoTitulo() {
        Paragraph paragraph = patrimonioRelatorioService.constroiParagrafoTitulo();
        Assertions.assertEquals("[Relatório de patrimônios]", paragraph.toString());
    }

    @Test
    @DisplayName("Deve testar método de exportação de PDF com ids preenchidos")
    void deveTestarMetodoDeExportacaoDePdfComIdsPreenchidos() throws IOException {
        HttpServletResponse mockedResponse = Mockito.mock(HttpServletResponse.class);
        ColaboradorEntity colaboradorLogado = ColaboradorEntityBuilder.builder().build();

        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        ids.add(3L);

        List<PatrimonioEntity> patrimoniosRetornados = new ArrayList<>();
        patrimoniosRetornados.add(PatrimonioEntityBuilder.builder().build());

        when(patrimonioRepositoryImpl.implementaBuscaPorIdEmMassa(any())).thenReturn(patrimoniosRetornados);
        Mockito.doNothing().when(acaoService).salvaHistoricoColaborador(any(), any(), any(), any(), any());

        Assertions.assertThrows(InvalidClassException.class,
                () -> patrimonioRelatorioService.exportarPdf(mockedResponse, colaboradorLogado, ids));
    }

    @Test
    @DisplayName("Deve testar método de exportação de PDF sem nenhum preenchido")
    void deveTestarMetodoDeExportacaoDePdfSemIdsPreenchidos() throws IOException {
        HttpServletResponse mockedResponse = Mockito.mock(HttpServletResponse.class);
        ColaboradorEntity colaboradorLogado = ColaboradorEntityBuilder.builder()
                .comEmpresa()
                .build();

        List<PatrimonioEntity> patrimoniosRetornados = new ArrayList<>();
        patrimoniosRetornados.add(PatrimonioEntityBuilder.builder().build());

        when(patrimonioRepositoryImpl.implementaBuscaPorTodos(any())).thenReturn(patrimoniosRetornados);
        Mockito.doNothing().when(acaoService).salvaHistoricoColaborador(any(), any(), any(), any(), any());

        Assertions.assertThrows(InvalidClassException.class,
                () -> patrimonioRelatorioService.exportarPdf(mockedResponse, colaboradorLogado, new ArrayList<>()));
    }

}
