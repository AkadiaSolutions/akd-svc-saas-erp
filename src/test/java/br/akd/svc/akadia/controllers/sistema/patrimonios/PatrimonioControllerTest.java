package br.akd.svc.akadia.controllers.sistema.patrimonios;

import br.akd.svc.akadia.config.security.JWTUtil;
import br.akd.svc.akadia.models.dto.sistema.patrimonios.request.mock.PatrimonioRequestBuilder;
import br.akd.svc.akadia.modules.erp.patrimonios.models.dto.response.page.PatrimonioPageResponse;
import br.akd.svc.akadia.modules.erp.patrimonios.models.dto.response.PatrimonioResponse;
import br.akd.svc.akadia.models.dto.sistema.patrimonios.response.mock.PatrimonioPageResponseBuilder;
import br.akd.svc.akadia.models.dto.sistema.patrimonios.response.mock.PatrimonioResponseBuilder;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks.ColaboradorEntityBuilder;
import br.akd.svc.akadia.modules.erp.patrimonios.controllers.PatrimonioController;
import br.akd.svc.akadia.modules.erp.patrimonios.services.PatrimonioRelatorioService;
import br.akd.svc.akadia.modules.erp.patrimonios.services.PatrimonioService;
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
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Controller: Patrimonio")
class PatrimonioControllerTest {

    @InjectMocks
    PatrimonioController patrimonioController;

    @Mock
    PatrimonioService patrimonioService;

    @Mock
    PatrimonioRelatorioService relatorioService;

    @Mock
    JWTUtil jwtUtil;

    HttpServletRequest mockedRequest;

    @BeforeEach
    void mockedRequestSetup() {

        HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);

        when(jwtUtil.obtemUsuarioAtivo(mockedRequest))
                .thenReturn(ColaboradorEntityBuilder.builder().comEmpresa().build());
    }

    @Test
    @DisplayName("Deve testar método controlador de criação de novo patrimônio")
    void deveTestarMetodoControladorDeCriacaoDeNovoPatrimonio() {
        when(patrimonioService.criaNovoPatrimonio(any(), any()))
                .thenReturn(PatrimonioResponseBuilder.builder().build());

        ResponseEntity<PatrimonioResponse> patrimonio = patrimonioController
                .criaNovoPatrimonio(mockedRequest, PatrimonioRequestBuilder.builder().build());

        Assertions.assertEquals("<201 CREATED Created,PatrimonioResponse(id=1, dataCadastro=2023-08-21, " +
                        "horaCadastro=10:20, dataEntrada=2023-08-21, descricao=Dinheiro, valor=100.0, " +
                        "tipoPatrimonio=Ativo),[]>",
                patrimonio.toString());
    }

    @Test
    @DisplayName("Deve testar método controlador de atualização de patrimônio")
    void deveTestarMetodoControladorDeAtualizacaoDoPatrimonio() {

        when(patrimonioService.atualizaObjeto(any(), any(), any())).thenReturn(PatrimonioResponse.builder().build());
        HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
        when(jwtUtil.obtemUsuarioAtivo(mockedRequest)).thenReturn(ColaboradorEntityBuilder.builder().comEmpresa().build());

        ResponseEntity<PatrimonioResponse> patrimonio = patrimonioController
                .atualizaPatrimonio(mockedRequest, PatrimonioRequestBuilder.builder().build(), 1L);

        Assertions.assertEquals("<200 OK OK,PatrimonioResponse(id=null, dataCadastro=null, horaCadastro=null, " +
                        "dataEntrada=null, descricao=null, valor=null, tipoPatrimonio=null),[]>",
                patrimonio.toString());
    }

    @Test
    @DisplayName("Deve testar método de obtenção de patrimônio por id")
    void deveTestarMetodoDeVerificacaoDeObtencaoDePatrimonioPorId() {
        HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
        when(jwtUtil.obtemUsuarioAtivo(mockedRequest)).thenReturn(ColaboradorEntityBuilder.builder().comEmpresa().build());

        ResponseEntity<?> patrimonio =
                patrimonioController.obtemObjetoPorId(1L, mockedRequest);

        Assertions.assertEquals("<200 OK OK,[]>", patrimonio.toString());
    }

    @Test
    @DisplayName("Deve testar método de obtenção de patrimônios paginados")
    void deveTestarMetodoObtencaoDePatrimoniosPaginados() {
        when(patrimonioService.realizaBuscaPaginada(any(), any(), any()))
                .thenReturn(PatrimonioPageResponseBuilder.builder().build());
        HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
        when(jwtUtil.obtemUsuarioAtivo(mockedRequest)).thenReturn(ColaboradorEntityBuilder.builder().comEmpresa().build());

        ResponseEntity<PatrimonioPageResponse> patrimonio = patrimonioController
                .obtemPatrimoniosPaginados("busca", PageRequest.of(0, 20), mockedRequest);

        Assertions.assertEquals("<200 OK OK,PatrimonioPageResponse(content=null, empty=true, first=true, " +
                "last=true, number=0, numberOfElements=0, pageNumber=0, pageSize=0, paged=true, unpaged=false, " +
                "size=0, totalElements=0, totalPages=0),[]>", patrimonio.toString());
    }

    @Test
    @DisplayName("Deve testar método controlador de remoção de patrimônio")
    void deveTestarMetodoControladorDeRemocaoDoPatrimonio() {

        when(patrimonioService.removeObjeto(any(), anyLong()))
                .thenReturn(PatrimonioResponseBuilder.builder().build());

        HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
        when(jwtUtil.obtemUsuarioAtivo(mockedRequest))
                .thenReturn(ColaboradorEntityBuilder.builder().comEmpresa().build());

        ResponseEntity<PatrimonioResponse> patrimonio =
                patrimonioController.removePatrimonio(mockedRequest, 1L);

        Assertions.assertEquals("<200 OK OK,PatrimonioResponse(id=1, dataCadastro=2023-08-21, " +
                        "horaCadastro=10:20, dataEntrada=2023-08-21, descricao=Dinheiro, valor=100.0, " +
                        "tipoPatrimonio=Ativo),[]>",
                patrimonio.toString());
    }

    @Test
    @DisplayName("Deve testar método controlador de remoção de patrimônio em massa")
    void deveTestarMetodoControladorDeRemocaoDoPatrimonioEmMassa() {
        HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
        when(jwtUtil.obtemUsuarioAtivo(mockedRequest)).thenReturn(ColaboradorEntityBuilder.builder().comEmpresa().build());

        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ResponseEntity<?> remocaoEmMassa = patrimonioController
                .removePatrimoniosEmMassa(mockedRequest, ids);

        Assertions.assertEquals("<200 OK OK,[]>", remocaoEmMassa.toString());
    }

    @Test
    @DisplayName("Deve testar método controlador de geração de relatório em PDF")
    void deveTestarMetodoControladorDeGeracaoDeRelatorioEmPdf() throws IOException {
        HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse mockedResponse = Mockito.mock(HttpServletResponse.class);
        when(jwtUtil.obtemUsuarioAtivo(mockedRequest)).thenReturn(ColaboradorEntityBuilder.builder().comEmpresa().build());
        List<Long> ids = new ArrayList<>();
        ids.add(1L);

        Mockito.doNothing().when(relatorioService).exportarPdf(any(), any(), any());

        patrimonioController.relatorio(mockedResponse, mockedRequest, ids);

        Assertions.assertNotNull(mockedResponse);
    }


}
