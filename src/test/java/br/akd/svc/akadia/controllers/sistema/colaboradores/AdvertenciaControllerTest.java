package br.akd.svc.akadia.controllers.sistema.colaboradores;

import br.akd.svc.akadia.config.security.JWTUtil;
import br.akd.svc.akadia.modules.erp.colaboradores.advertencia.models.dto.response.AdvertenciaPageResponse;
import br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.mocks.AdvertenciaPageResponseBuilder;
import br.akd.svc.akadia.models.entities.global.mocks.ArquivoEntityBuilder;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks.ColaboradorEntityBuilder;
import br.akd.svc.akadia.modules.erp.colaboradores.advertencia.models.enums.StatusAdvertenciaEnum;
import br.akd.svc.akadia.modules.erp.colaboradores.advertencia.controllers.AdvertenciaController;
import br.akd.svc.akadia.modules.erp.colaboradores.advertencia.services.AdvertenciaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Controller: Advertencia")
class AdvertenciaControllerTest {

    @InjectMocks
    AdvertenciaController advertenciaController;

    @Mock
    AdvertenciaService advertenciaService;

    @Mock
    JWTUtil jwtUtil;

    HttpServletRequest mockedRequest;

    @BeforeEach
    void mockedRequestSetup() {

        mockedRequest = Mockito.mock(HttpServletRequest.class);

        when(jwtUtil.obtemUsuarioAtivo(mockedRequest))
                .thenReturn(ColaboradorEntityBuilder.builder().comEmpresa().build());
    }

    @Test
    @DisplayName("Deve testar método controlador de criação de nova advertência")
    void deveTestarMetodoControladorDeCriacaoDeNovaAdvertencia() throws IOException {

        HttpServletResponse mockedResponse = Mockito.mock(HttpServletResponse.class);

        doNothing().when(advertenciaService)
                .geraAdvertenciaColaborador(any(), any(), any(), any(), any());

        advertenciaController.criaNovaAdvertencia(
                mockedRequest,
                mockedResponse,
                1L,
                null,
                "Advertência");

        Assertions.assertDoesNotThrow(() -> new RuntimeException());
    }

    @Test
    @DisplayName("Deve testar método controlador de obtenção de advertências paginadas")
    void deveTestarMetodoControladorDeObtencaoDeAdvertenciasPaginadas() {
        when(advertenciaService.obtemAdvertenciasColaborador(any(), any(), any()))
                .thenReturn(AdvertenciaPageResponseBuilder.builder().build());

        ResponseEntity<AdvertenciaPageResponse> advertencia =
                advertenciaController.obtemAdvertenciasColaboradorPaginada(
                        PageRequest.of(0, 20),
                        mockedRequest,
                        1L);

        Assertions.assertEquals("<200 OK OK,AdvertenciaPageResponse(content=[], empty=true, first=true, " +
                        "last=true, number=0, numberOfElements=0, offset=0, pageNumber=0, pageSize=10, " +
                        "paged=true, unpaged=false, size=10, totalElements=0, totalPages=0),[]>",
                advertencia.toString());
    }

    @Test
    @DisplayName("Deve testar método controlador de geração de PDF da advertência")
    void deveTestarMetodoControladorDeGeracaoDePdfAdvertencia() throws IOException {

        HttpServletResponse mockedResponse = Mockito.mock(HttpServletResponse.class);

        when(jwtUtil.obtemUsuarioAtivo(mockedRequest))
                .thenReturn(ColaboradorEntityBuilder.builder().comEmpresa().build());

        Mockito.doNothing().when(advertenciaService).geraPdfPadraoAdvertencia(any(), any(), any(), any());

        advertenciaController.geraPdfPadraoAdvertencia(
                mockedRequest,
                mockedResponse,
                1L,
                1L);

        Assertions.assertNotNull(mockedResponse);
    }

    @Test
    @DisplayName("Deve testar método controlador de anexar arquivo em advertência")
    void deveTestarMetodoControladorDeAnexarArquivoEmAdvertencia() throws IOException {

        HttpServletResponse mockedResponse = Mockito.mock(HttpServletResponse.class);

        when(jwtUtil.obtemUsuarioAtivo(mockedRequest))
                .thenReturn(ColaboradorEntityBuilder.builder().comEmpresa().build());

        Mockito.doNothing().when(advertenciaService)
                .anexaArquivoAdvertencia(any(), any(), any(), any());

        advertenciaController.anexaArquivoAdvertencia(
                mockedRequest,
                null,
                1L,
                1L);

        Assertions.assertNotNull(mockedResponse);
    }

    @Test
    @DisplayName("Deve testar método controlador de alteração de status da advertência")
    void deveTestarMetodoControladorDeAlteracaoDeStatusDaAdvertencia() {
        doNothing().when(advertenciaService)
                .alteraStatusAdvertencia(any(), any(), any(), any());

        advertenciaController.alteraStatusAdvertencia(
                mockedRequest,
                StatusAdvertenciaEnum.ASSINADA.toString(),
                1L,
                1L);

        Assertions.assertDoesNotThrow(() -> new RuntimeException());
    }

    @Test
    @DisplayName("Deve testar método controlador de remoção de advertência")
    void deveTestarMetodoControladorDeRemocaoDeAdvertencia() {
        doNothing().when(advertenciaService)
                .removerAdvertencia(any(), any(), any());

        advertenciaController.removeAdvertencia(
                mockedRequest,
                1L,
                1L);

        Assertions.assertDoesNotThrow(() -> new RuntimeException());
    }

    @Test
    @DisplayName("Deve testar método controlador de obtenção de anexo da advertência")
    void deveTestarMetodoControladorDeObtencaoDeAnexoDaAdvertencia() {
        when(advertenciaService.obtemAnexoAdvertencia(any(), any(), any()))
                .thenReturn(ArquivoEntityBuilder.builder().build());

        ResponseEntity<byte[]> anexo =
                advertenciaController.obtemAnexoAdvertencia(
                        mockedRequest,
                        1L,
                        1L);

        Assertions.assertDoesNotThrow(() -> new RuntimeException());
    }
}
