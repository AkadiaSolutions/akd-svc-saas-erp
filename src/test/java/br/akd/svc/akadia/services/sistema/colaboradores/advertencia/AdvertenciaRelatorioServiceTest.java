package br.akd.svc.akadia.services.sistema.colaboradores.advertencia;

import br.akd.svc.akadia.modules.erp.colaboradores.advertencia.models.entity.AdvertenciaEntity;
import br.akd.svc.akadia.modules.erp.colaboradores.advertencia.services.AdvertenciaRelatorioService;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.entity.ColaboradorEntity;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks.AdvertenciaEntityBuilder;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks.ColaboradorEntityBuilder;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.servlet.http.HttpServletResponse;
import java.io.InvalidClassException;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("RelatorioService: Advertencia")
class AdvertenciaRelatorioServiceTest {

    @InjectMocks
    AdvertenciaRelatorioService advertenciaRelatorioService;

    @Test
    @DisplayName("Deve testar método de construção do parágrafo de data e hora")
    void deveTestarMetodoDeConstrucaoDoCabecalho() {
        ColaboradorEntity colaboradorEntity = ColaboradorEntityBuilder.builder().comEmpresa().build();
        AdvertenciaEntity advertenciaEntity = AdvertenciaEntityBuilder.builder().build();
        Document document = new Document();
        document.open();

        advertenciaRelatorioService.constroiCabecalho(document, colaboradorEntity, advertenciaEntity);
    }

    @Test
    @DisplayName("Deve testar método de construção do parágrafo do título")
    void deveTestarMetodoDeConstrucaoDoTitulo() {
        Paragraph paragraph = advertenciaRelatorioService.constroiParagrafoTitulo();
        Assertions.assertEquals("[ADVERTÊNCIA DISCIPLINAR]", paragraph.toString());
    }

    @Test
    @DisplayName("Deve testar método de exportação de PDF")
    void deveTestarMetodoDeExportacaoDePdf() {
        HttpServletResponse mockedResponse = Mockito.mock(HttpServletResponse.class);
        ColaboradorEntity colaboradorLogado = ColaboradorEntityBuilder.builder().comEmpresa().build();
        ColaboradorEntity colaboradorEntity = ColaboradorEntityBuilder.builder().comEmpresa().build();
        AdvertenciaEntity advertenciaEntity = AdvertenciaEntityBuilder.builder().build();

        Assertions.assertThrows(InvalidClassException.class,
                () -> advertenciaRelatorioService
                        .exportarPdf(mockedResponse, colaboradorLogado, colaboradorEntity, advertenciaEntity));
    }

}
