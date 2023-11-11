package br.akd.svc.akadia.services.sistema.despesas;

import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.entity.ColaboradorEntity;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks.ColaboradorEntityBuilder;
import br.akd.svc.akadia.modules.erp.despesas.models.entity.DespesaEntity;
import br.akd.svc.akadia.models.entities.sistema.despesas.mocks.DespesaEntityBuilder;
import br.akd.svc.akadia.modules.erp.despesas.repository.impl.DespesaRepositoryImpl;
import br.akd.svc.akadia.modules.erp.colaboradores.acao.services.AcaoService;
import br.akd.svc.akadia.modules.erp.despesas.services.DespesaRelatorioService;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Relatorio: Despesa")
class DespesaRelatorioServiceTest {

    @InjectMocks
    DespesaRelatorioService despesaRelatorioService;

    @Mock
    AcaoService acaoService;

    @Mock
    DespesaRepositoryImpl despesaRepositoryImpl;

    @BeforeEach
    void mockSalvamentoDeHistoricoColaborador() {
        doNothing().when(acaoService).salvaHistoricoColaborador(any(), any(), any(), any(), any());
    }

    @Test
    @DisplayName("Deve testar método de construção de tabela de objetos")
    void deveTestarMetodoDeConstrucaoDeTabelaDeObjetos() {
        List<DespesaEntity> despesas = new ArrayList<>();
        despesas.add(DespesaEntityBuilder.builder().build());

        PdfPTable pdfPTable = despesaRelatorioService.constroiTabelaObjetos(despesas);

        Assertions.assertNotNull(pdfPTable.toString());
    }

    @Test
    @DisplayName("Deve testar método de construção da tabela de informativos")
    void deveTestarMetodoDeConstrucaoTabelaInformativos() {
        List<DespesaEntity> despesas = new ArrayList<>();
        despesas.add(DespesaEntityBuilder.builder().build());

        PdfPTable pdfPTable = despesaRelatorioService.constroiTabelaInformativos(despesas);

        Assertions.assertNotNull(pdfPTable.toString());
    }

    @Test
    @DisplayName("Deve testar método de construção do parágrafo de data e hora")
    void deveTestarMetodoDeConstrucaoDoParagrafoDeDataHora() {

        ColaboradorEntity colaboradorLogado = ColaboradorEntityBuilder.builder().build();

        Paragraph paragraph = despesaRelatorioService.constroiParagrafoDataHora(colaboradorLogado);

        Assertions.assertNotNull(paragraph.toString());
    }

    @Test
    @DisplayName("Deve testar método de construção do parágrafo do título")
    void deveTestarMetodoDeConstrucaoDoParagrafoDoTitulo() {
        Paragraph paragraph = despesaRelatorioService.constroiParagrafoTitulo();
        Assertions.assertEquals("[Relatório de despesas]", paragraph.toString());
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

        List<DespesaEntity> despesasRetornadas = new ArrayList<>();
        despesasRetornadas.add(DespesaEntityBuilder.builder().build());

        when(despesaRepositoryImpl.implementaBuscaPorIdEmMassa(any())).thenReturn(despesasRetornadas);

        try {
            despesaRelatorioService.exportarPdf(mockedResponse, colaboradorLogado, ids);
            Assertions.fail();
        } catch (InvalidClassException e) {
            Assertions.assertEquals("java.lang.NullPointerException: Cannot invoke \"java.io.OutputStream.write" +
                    "(byte[], int, int)\" because \"this.out\" is null", e.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar método de exportação de PDF sem nenhum preenchido")
    void deveTestarMetodoDeExportacaoDePdfSemIdsPreenchidos() throws IOException {
        HttpServletResponse mockedResponse = Mockito.mock(HttpServletResponse.class);
        ColaboradorEntity colaboradorLogado = ColaboradorEntityBuilder.builder()
                .comEmpresa()
                .build();

        List<DespesaEntity> despesasRetornadas = new ArrayList<>();
        despesasRetornadas.add(DespesaEntityBuilder.builder().build());

        when(despesaRepositoryImpl.implementaBuscaPorTodos(any())).thenReturn(despesasRetornadas);

        try {
            despesaRelatorioService.exportarPdf(mockedResponse, colaboradorLogado, new ArrayList<>());
            Assertions.fail();
        } catch (InvalidClassException e) {
            Assertions.assertEquals("java.lang.NullPointerException: Cannot invoke \"java.io.OutputStream." +
                    "write(byte[], int, int)\" because \"this.out\" is null", e.getMessage());
        }
    }

}
