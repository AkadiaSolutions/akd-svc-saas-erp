package br.akd.svc.akadia.controllers.sistema.despesas;

import br.akd.svc.akadia.config.security.JWTUtil;
import br.akd.svc.akadia.models.dto.sistema.despesas.request.mock.DespesaRequestBuilder;
import br.akd.svc.akadia.models.dto.sistema.despesas.response.DespesaPageResponse;
import br.akd.svc.akadia.models.dto.sistema.despesas.response.DespesaResponse;
import br.akd.svc.akadia.models.dto.sistema.despesas.response.mock.DespesaPageResponseBuilder;
import br.akd.svc.akadia.models.dto.sistema.despesas.response.mock.DespesaResponseBuilder;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks.ColaboradorEntityBuilder;
import br.akd.svc.akadia.services.sistema.despesas.DespesaRelatorioService;
import br.akd.svc.akadia.services.sistema.despesas.DespesaService;
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

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Controller: Despesas")
class DespesasControllerTest {

    @InjectMocks
    DespesasController despesasController;

    @Mock
    DespesaService despesaService;

    @Mock
    DespesaRelatorioService relatorioService;

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
    @DisplayName("Deve testar método de obtenção de despesas por id")
    void deveTestarObtencaoDeDespesaPorId() {
        ResponseEntity<?> despesa =
                despesasController.obtemDespesaPorId(1L, mockedRequest);

        Assertions.assertEquals("<200 OK OK,[]>", despesa.toString());
    }

    @Test
    @DisplayName("Deve testar método controlador de criação de nova despesa")
    void deveTestarMetodoControladorDeCriacaoDeNovaDespesa() {
        when(despesaService.criaNovaDespesa(any(), any()))
                .thenReturn(DespesaResponseBuilder.builder().build());

        ResponseEntity<DespesaResponse> despesa =
                despesasController.criaNovaDespesa(mockedRequest, DespesaRequestBuilder.builder().build());

        Assertions.assertEquals("<201 CREATED Created,DespesaResponse(id=1, dataCadastro=2023-08-18, " +
                        "horaCadastro=07:55, dataPagamento=2023-08-18, dataAgendamento=Pago, descricao=Gasolina " +
                        "carro, valor=100.0, observacao=Sem recorrências, qtdRecorrencias=0, statusDespesa=PAGO, " +
                        "tipoDespesa=VARIAVEL, tipoRecorrencia=SEM_RECORRENCIA),[]>",
                despesa.toString());
    }

    @Test
    @DisplayName("Deve testar método controlador de remoção de despesa em massa")
    void deveTestarMetodoControladorDeRemocaoDeDespesaEmMassa() {
        List<Long> ids = new ArrayList<>();
        ids.add(1L);

        ResponseEntity<?> remocaoEmMassa = despesasController.removeDespesasEmMassa(mockedRequest, ids);

        Assertions.assertEquals("<200 OK OK,[]>", remocaoEmMassa.toString());
    }

    @Test
    @DisplayName("Deve testar método controlador de remoção de despesa")
    void deveTestarMetodoControladorDeRemocaoDeDespesa() {
        when(despesaService.removeDespesa(any(), anyLong(), anyBoolean()))
                .thenReturn(DespesaResponseBuilder.builder().build());

        ResponseEntity<DespesaResponse> despesa =
                despesasController.removeDespesa(mockedRequest, false, 1L);

        Assertions.assertEquals("<200 OK OK,DespesaResponse(id=1, dataCadastro=2023-08-18, " +
                        "horaCadastro=07:55, dataPagamento=2023-08-18, dataAgendamento=Pago, descricao=Gasolina carro, " +
                        "valor=100.0, observacao=Sem recorrências, qtdRecorrencias=0, statusDespesa=PAGO, " +
                        "tipoDespesa=VARIAVEL, tipoRecorrencia=SEM_RECORRENCIA),[]>",
                despesa.toString());
    }

    @Test
    @DisplayName("Deve testar método controlador de atualização da despesa")
    void deveTestarMetodoControladorDeAtualizacaoDaDespesa() {

        when(despesaService.atualizaDespesa(any(), any(), any()))
                .thenReturn(DespesaResponseBuilder.builder().build());

        ResponseEntity<DespesaResponse> despesa =
                despesasController.atualizaDespesa(mockedRequest, DespesaRequestBuilder.builder().build(), 1L);

        Assertions.assertEquals("<200 OK OK,DespesaResponse(id=1, dataCadastro=2023-08-18, horaCadastro=07:55, " +
                "dataPagamento=2023-08-18, dataAgendamento=Pago, descricao=Gasolina carro, valor=100.0, " +
                "observacao=Sem recorrências, qtdRecorrencias=0, statusDespesa=PAGO, tipoDespesa=VARIAVEL, " +
                "tipoRecorrencia=SEM_RECORRENCIA),[]>", despesa.toString());
    }

    @Test
    @DisplayName("Deve testar método de obtenção de despesas paginadas")
    void deveTestarMetodoObtencaoDeDespesasPaginadas() {
        when(despesaService.realizaBuscaPaginadaPorDespesas(any(), any(), any(), any()))
                .thenReturn(DespesaPageResponseBuilder.builder().build());

        ResponseEntity<DespesaPageResponse> cliente =
                despesasController.obtemDespesasPaginadas(
                        "busca",
                        "08-2023",
                        PageRequest.of(0, 20),
                        mockedRequest);

        Assertions.assertEquals("<200 OK OK,DespesaPageResponse(content=null, empty=true, first=true, " +
                "last=true, number=0, numberOfElements=0, pageNumber=0, pageSize=0, paged=true, unpaged=false, " +
                "size=0, totalElements=0, totalPages=0),[]>",
                cliente.toString());
    }

    @Test
    @DisplayName("Deve testar método controlador de geração de relatório em PDF")
    void deveTestarMetodoControladorDeGeracaoDeRelatorioEmPdf() throws IOException {

        HttpServletResponse mockedResponse = Mockito.mock(HttpServletResponse.class);

        when(jwtUtil.obtemUsuarioAtivo(mockedRequest))
                .thenReturn(ColaboradorEntityBuilder.builder().comEmpresa().build());

        List<Long> ids = new ArrayList<>();
        ids.add(1L);

        Mockito.doNothing().when(relatorioService).exportarPdf(any(), any(), any());

        despesasController.relatorio(mockedResponse, mockedRequest, ids);

        Assertions.assertNotNull(mockedResponse);
    }

}
