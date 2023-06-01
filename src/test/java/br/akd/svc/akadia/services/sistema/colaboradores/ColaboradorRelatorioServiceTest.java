package br.akd.svc.akadia.services.sistema.colaboradores;

import br.akd.svc.akadia.models.entities.sistema.colaboradores.ColaboradorEntity;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks.ColaboradorEntityBuilder;
import br.akd.svc.akadia.repositories.sistema.colaboradores.impl.ColaboradorRepositoryImpl;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("RelatorioService: Colaborador")
class ColaboradorRelatorioServiceTest {

    @InjectMocks
    ColaboradorRelatorioService colaboradorRelatorioService;

    @Mock
    ColaboradorRepositoryImpl colaboradorRepositoryImpl;

    @Mock
    AcaoService acaoService;

    @Test
    @DisplayName("Deve testar método de construção de tabela de objetos")
    void deveTestarMetodoDeConstrucaoDeTabelaDeObjetos() {
        List<ColaboradorEntity> colaboradores = new ArrayList<>();
        colaboradores.add(ColaboradorEntityBuilder.builder().build());
        colaboradores.add(ColaboradorEntityBuilder.builder().comEndereco().build());
        colaboradores.add(ColaboradorEntityBuilder.builder().comTelefone().build());

        PdfPTable pdfPTable = colaboradorRelatorioService.constroiTabelaObjetos(colaboradores);

        Assertions.assertNotNull(pdfPTable.toString());
    }

    @Test
    @DisplayName("Deve testar método de construção da tabela de informativos")
    void deveTestarMetodoDeConstrucaoTabelaInformativos() {
        List<ColaboradorEntity> colaboradores = new ArrayList<>();
        colaboradores.add(ColaboradorEntityBuilder.builder().build());

        PdfPTable pdfPTable = colaboradorRelatorioService.constroiTabelaInformativos(colaboradores);

        Assertions.assertNotNull(pdfPTable.toString());
    }

    @Test
    @DisplayName("Deve testar método de construção do parágrafo de data e hora")
    void deveTestarMetodoDeConstrucaoDoParagrafoDeDataHora() {

        ColaboradorEntity colaboradorLogado = ColaboradorEntityBuilder.builder().build();

        Paragraph paragraph = colaboradorRelatorioService.constroiParagrafoDataHora(colaboradorLogado);

        Assertions.assertNotNull(paragraph.toString());
    }

    @Test
    @DisplayName("Deve testar método de construção do parágrafo do título")
    void deveTestarMetodoDeConstrucaoDoParagrafoDoTitulo() {
        Paragraph paragraph = colaboradorRelatorioService.constroiParagrafoTitulo();
        Assertions.assertEquals("[Relatório de colaboradores]", paragraph.toString());
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

        List<ColaboradorEntity> colaboradoresRetornados = new ArrayList<>();
        colaboradoresRetornados.add(ColaboradorEntityBuilder.builder().build());

        when(colaboradorRepositoryImpl.implementaBuscaPorIdEmMassa(any())).thenReturn(colaboradoresRetornados);
        Mockito.doNothing().when(acaoService).salvaHistoricoColaborador(any(), any(), any(), any(), any());

        try {
            colaboradorRelatorioService.exportarPdf(mockedResponse, colaboradorLogado, ids);
            Assertions.fail();
        } catch (InvalidClassException e) {
            Assertions.assertEquals("java.lang.NullPointerException: Cannot invoke " +
                    "\"java.io.OutputStream.write(byte[], int, int)\" because \"this.out\" is null", e.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar método de exportação de PDF sem nenhum preenchido")
    void deveTestarMetodoDeExportacaoDePdfSemIdsPreenchidos() throws IOException {
        HttpServletResponse mockedResponse = Mockito.mock(HttpServletResponse.class);
        ColaboradorEntity colaboradorLogado = ColaboradorEntityBuilder.builder()
                .comEmpresa()
                .build();

        List<ColaboradorEntity> colaboradoresRetornados = new ArrayList<>();
        colaboradoresRetornados.add(ColaboradorEntityBuilder.builder().build());

        when(colaboradorRepositoryImpl.implementaBuscaPorTodos(any())).thenReturn(colaboradoresRetornados);
        Mockito.doNothing().when(acaoService).salvaHistoricoColaborador(any(), any(), any(), any(), any());

        try {
            colaboradorRelatorioService.exportarPdf(mockedResponse, colaboradorLogado, new ArrayList<>());
            Assertions.fail();
        } catch (InvalidClassException e) {
            Assertions.assertEquals("java.lang.NullPointerException: Cannot invoke " +
                    "\"java.io.OutputStream.write(byte[], int, int)\" because \"this.out\" is null", e.getMessage());
        }
    }

}