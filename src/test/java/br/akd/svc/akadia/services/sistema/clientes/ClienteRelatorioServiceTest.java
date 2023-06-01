package br.akd.svc.akadia.services.sistema.clientes;

import br.akd.svc.akadia.models.entities.sistema.clientes.ClienteEntity;
import br.akd.svc.akadia.models.entities.sistema.clientes.mocks.ClienteEntityBuilder;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.ColaboradorEntity;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks.ColaboradorEntityBuilder;
import br.akd.svc.akadia.repositories.sistema.clientes.impl.ClienteRepositoryImpl;
import br.akd.svc.akadia.services.sistema.colaboradores.AcaoService;
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
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("RelatorioService: Cliente")
class ClienteRelatorioServiceTest {

    @InjectMocks
    ClienteRelatorioService clienteRelatorioService;

    @Mock
    ClienteRepositoryImpl clienteRepositoryImpl;

    @Mock
    AcaoService acaoService;

    @Mock
    OutputStream outputStream;

    @Test
    @DisplayName("Deve testar método de construção de tabela de objetos")
    void deveTestarMetodoDeConstrucaoDeTabelaDeObjetos() {
        List<ClienteEntity> clientes = new ArrayList<>();
        clientes.add(ClienteEntityBuilder.builder().build());
        clientes.add(ClienteEntityBuilder.builder().comEndereco().build());
        clientes.add(ClienteEntityBuilder.builder().comTelefone().build());

        PdfPTable pdfPTable = clienteRelatorioService.constroiTabelaObjetos(clientes);

        Assertions.assertNotNull(pdfPTable.toString());
    }

    @Test
    @DisplayName("Deve testar método de construção da tabela de informativos")
    void deveTestarMetodoDeConstrucaoTabelaInformativos() {
        List<ClienteEntity> clientes = new ArrayList<>();
        clientes.add(ClienteEntityBuilder.builder().build());

        PdfPTable pdfPTable = clienteRelatorioService.constroiTabelaInformativos(clientes);

        Assertions.assertNotNull(pdfPTable.toString());
    }

    @Test
    @DisplayName("Deve testar método de construção do parágrafo de data e hora")
    void deveTestarMetodoDeConstrucaoDoParagrafoDeDataHora() {

        ColaboradorEntity colaboradorLogado = ColaboradorEntityBuilder.builder().build();

        Paragraph paragraph = clienteRelatorioService.constroiParagrafoDataHora(colaboradorLogado);

        Assertions.assertNotNull(paragraph.toString());
    }

    @Test
    @DisplayName("Deve testar método de construção do parágrafo do título")
    void deveTestarMetodoDeConstrucaoDoParagrafoDoTitulo() {
        Paragraph paragraph = clienteRelatorioService.constroiParagrafoTitulo();
        Assertions.assertEquals("[Relatório de clientes]", paragraph.toString());
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

        List<ClienteEntity> clientesRetornados = new ArrayList<>();
        clientesRetornados.add(ClienteEntityBuilder.builder().build());

        when(clienteRepositoryImpl.implementaBuscaPorIdEmMassa(any())).thenReturn(clientesRetornados);
        Mockito.doNothing().when(acaoService).salvaHistoricoColaborador(any(), any(), any(), any(), any());

        try {
            clienteRelatorioService.exportarPdf(mockedResponse, colaboradorLogado, ids);
            Assertions.fail();
        }
        catch (InvalidClassException e) {
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

        List<ClienteEntity> clientesRetornados = new ArrayList<>();
        clientesRetornados.add(ClienteEntityBuilder.builder().build());

        when(clienteRepositoryImpl.implementaBuscaPorTodos(any())).thenReturn(clientesRetornados);
        Mockito.doNothing().when(acaoService).salvaHistoricoColaborador(any(), any(), any(), any(), any());

        try {
            clienteRelatorioService.exportarPdf(mockedResponse, colaboradorLogado, new ArrayList<>());
            Assertions.fail();
        }
        catch (InvalidClassException e) {
            Assertions.assertEquals("java.lang.NullPointerException: Cannot invoke " +
                    "\"java.io.OutputStream.write(byte[], int, int)\" because \"this.out\" is null", e.getMessage());
        }
    }

}
