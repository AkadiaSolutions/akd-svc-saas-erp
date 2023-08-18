package br.akd.svc.akadia.controllers.sistema.colaboradores;

import br.akd.svc.akadia.config.security.JWTUtil;
import br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.ColaboradorPageResponse;
import br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.ColaboradorResponse;
import br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.acao.AcaoPageResponse;
import br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.acesso.AcessoPageResponse;
import br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.mocks.AcaoPageResponseBuilder;
import br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.mocks.AcessoPageResponseBuilder;
import br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.mocks.ColaboradorPageResponseBuilder;
import br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.mocks.ColaboradorResponseBuilder;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks.ColaboradorEntityBuilder;
import br.akd.svc.akadia.repositories.sistema.colaboradores.ColaboradorRepository;
import br.akd.svc.akadia.services.sistema.colaboradores.acao.AcaoService;
import br.akd.svc.akadia.services.sistema.colaboradores.acesso.AcessoService;
import br.akd.svc.akadia.services.sistema.colaboradores.colaborador.ColaboradorRelatorioService;
import br.akd.svc.akadia.services.sistema.colaboradores.colaborador.ColaboradorService;
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
@DisplayName("Controller: Colaborador")
class ColaboradorControllerTest {

    @InjectMocks
    ColaboradorController colaboradorController;

    @Mock
    ColaboradorService colaboradorService;

    @Mock
    AcaoService acaoService;

    @Mock
    AcessoService acessoService;

    @Mock
    ColaboradorRelatorioService relatorioService;

    @Mock
    ColaboradorRepository colaboradorRepository;

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
    @DisplayName("Deve testar método controlador de obtenção de imagem de perfil do colaborador")
    void deveTestarMetodoControladorDeObtencaoDeImagemDePerfilDoColaborador() {
        when(colaboradorService.obtemImagemPerfilColaborador(any(), any()))
                .thenReturn(null);

        ResponseEntity<byte[]> imagemDePerfilDoColaborador =
                colaboradorController.obtemImagemDePerfilDoColaborador(
                        mockedRequest,
                        1L);

        Assertions.assertEquals("<200 OK OK,[]>",
                imagemDePerfilDoColaborador.toString());
    }

    @Test
    @DisplayName("Deve testar método controlador de atualização de imagem de perfil do colaborador")
    void deveTestarMetodoControladorDeAtualizacaoDeImagemDePerfilDoColaborador() throws IOException {
        when(colaboradorService.atualizaImagemPerfilColaborador(any(), any(), any()))
                .thenReturn(ColaboradorResponseBuilder.builder().build());

        ResponseEntity<ColaboradorResponse> colaborador =
                colaboradorController.atualizaImagemPerfilColaborador(
                        mockedRequest,
                        null,
                        1L);

        Assertions.assertEquals("<200 OK OK,ColaboradorResponse(id=1, dataCadastro=2023-02-13, " +
                        "horaCadastro=10:44, matricula=123456, nome=João da Silva, dataNascimento=2021-04-11, " +
                        "email=joaosilva@gmail.com, cpfCnpj=12345678910, salario=2000.0, entradaEmpresa=2023-02-13, " +
                        "saidaEmpresa=null, ocupacao=Técnico Interno, tipoOcupacaoEnum=TECNICO_INTERNO, " +
                        "modeloContratacaoEnum=CLT, modeloTrabalhoEnum=PRESENCIAL, statusColaboradorEnum=ATIVO, " +
                        "fotoPerfil=null, exclusao=null, acessoSistema=null, configuracaoPerfil=null, " +
                        "contratoContratacao=null, endereco=null, telefone=null, expediente=null, dispensa=null, " +
                        "pontos=[], historicoFerias=[], advertencias=[]),[]>",
                colaborador.toString());
    }

    @Test
    @DisplayName("Deve testar método de obtenção de colaborador por id")
    void deveTestarObtencaoDeColaboradorPorId() {
        ResponseEntity<?> colaborador =
                colaboradorController.obtemColaboradorPorId(1L, mockedRequest);

        Assertions.assertEquals("<200 OK OK,[]>", colaborador.toString());
    }

    @Test
    @DisplayName("Deve testar método de obtenção de colaboradores paginados")
    void deveTestarMetodoObtencaoDeColaboradoresPaginados() {
        when(colaboradorService.realizaBuscaPaginadaPorColaboradores(any(), any(), any()))
                .thenReturn(ColaboradorPageResponseBuilder.builder().build());

        ResponseEntity<ColaboradorPageResponse> colaborador =
                colaboradorController.obtemColaboradoresPaginados(
                        "busca",
                        PageRequest.of(0, 20),
                        mockedRequest);

        Assertions.assertEquals("<200 OK OK,ColaboradorPageResponse(content=[], empty=true, first=true, " +
                        "last=true, number=0, numberOfElements=0, offset=0, pageNumber=0, pageSize=10, paged=true, " +
                        "unpaged=false, size=10, totalElements=0, totalPages=0),[]>",
                colaborador.toString());
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

        colaboradorController.relatorio(mockedResponse, mockedRequest, ids);

        Assertions.assertNotNull(mockedResponse);
    }

    @Test
    @DisplayName("Deve testar método controlador de criação de novo colaborador")
    void deveTestarMetodoControladorDeCriacaoDeNovoColaborador() throws IOException {
        when(colaboradorService.criaNovoColaborador(any(), any(), any()))
                .thenReturn(ColaboradorResponseBuilder.builder().build().toString());

        ResponseEntity<String> colaborador =
                colaboradorController.criaNovoColaborador(
                        mockedRequest,
                        null,
                        ColaboradorEntityBuilder.builder().build().toString());

        Assertions.assertEquals("<201 CREATED Created,ColaboradorResponse(id=1, dataCadastro=2023-02-13, " +
                        "horaCadastro=10:44, matricula=123456, nome=João da Silva, dataNascimento=2021-04-11, " +
                        "email=joaosilva@gmail.com, cpfCnpj=12345678910, salario=2000.0, entradaEmpresa=2023-02-13, " +
                        "saidaEmpresa=null, ocupacao=Técnico Interno, tipoOcupacaoEnum=TECNICO_INTERNO, " +
                        "modeloContratacaoEnum=CLT, modeloTrabalhoEnum=PRESENCIAL, statusColaboradorEnum=ATIVO, " +
                        "fotoPerfil=null, exclusao=null, acessoSistema=null, configuracaoPerfil=null, " +
                        "contratoContratacao=null, endereco=null, telefone=null, expediente=null, dispensa=null, " +
                        "pontos=[], historicoFerias=[], advertencias=[]),[]>",
                colaborador.toString());
    }

    @Test
    @DisplayName("Deve testar método controlador de atualização de colaborador")
    void deveTestarMetodoControladorDeAtualizacaoDeColaborador() throws IOException {

        when(colaboradorService.atualizaColaborador(any(), any(), any(), any()))
                .thenReturn(ColaboradorResponseBuilder.builder().build());

        ResponseEntity<String> colaborador =
                colaboradorController.atualizaColaborador(
                        mockedRequest,
                        null,
                        ColaboradorEntityBuilder.builder().build().toString(),
                        1L);

        Assertions.assertEquals("<200 OK OK,123456,[]>",
                colaborador.toString());
    }

    @Test
    @DisplayName("Deve testar método controlador de remoção de colaboradores em massa")
    void deveTestarMetodoControladorDeRemocaoDeColaboradoresEmMassa() {
        List<Long> ids = new ArrayList<>();
        ids.add(1L);

        ResponseEntity<?> remocaoEmMassa = colaboradorController.removeColaboradoresEmMassa(mockedRequest, ids);

        Assertions.assertEquals("<200 OK OK,[]>", remocaoEmMassa.toString());
    }

    @Test
    @DisplayName("Deve testar método controlador de remoção de colaborador")
    void deveTestarMetodoControladorDeRemocaoDeColaborador() {
        when(colaboradorService.removeColaborador(any(), anyLong()))
                .thenReturn(ColaboradorResponseBuilder.builder().build());

        ResponseEntity<ColaboradorResponse> colaborador =
                colaboradorController.removeColaborador(mockedRequest, 1L);

        Assertions.assertEquals("<200 OK OK,ColaboradorResponse(id=1, dataCadastro=2023-02-13, " +
                        "horaCadastro=10:44, matricula=123456, nome=João da Silva, dataNascimento=2021-04-11, " +
                        "email=joaosilva@gmail.com, cpfCnpj=12345678910, salario=2000.0, entradaEmpresa=2023-02-13, " +
                        "saidaEmpresa=null, ocupacao=Técnico Interno, tipoOcupacaoEnum=TECNICO_INTERNO, " +
                        "modeloContratacaoEnum=CLT, modeloTrabalhoEnum=PRESENCIAL, statusColaboradorEnum=ATIVO, " +
                        "fotoPerfil=null, exclusao=null, acessoSistema=null, configuracaoPerfil=null, " +
                        "contratoContratacao=null, endereco=null, telefone=null, expediente=null, dispensa=null, " +
                        "pontos=[], historicoFerias=[], advertencias=[]),[]>",
                colaborador.toString());
    }

    @Test
    @DisplayName("Deve testar método controlador de obtenção de ocupações do colaborador")
    void deveTestarMetodoControladorDeObtencaoDeOcupacoesDoColaborador() {
        List<String> ocupacoes = new ArrayList<>();
        ocupacoes.add("Técnico");

        when(jwtUtil.obtemUsuarioAtivo(mockedRequest))
                .thenReturn(ColaboradorEntityBuilder.builder().comEmpresa().build());

        when(colaboradorRepository.buscaTodasOcupacoesDaEmpresa(anyLong()))
                .thenReturn(ocupacoes);

        ResponseEntity<?> obtemOcupacoes = colaboradorController.obtemOcupacoes(mockedRequest);

        Assertions.assertEquals("<200 OK OK,[Técnico],[]>",
                obtemOcupacoes.toString());
    }

    @Test
    @DisplayName("Deve testar método controlador de obtenção de ações do colaborador")
    void deveTestarMetodoControladorDeObtencaoDeAcoesDoColaborador() {
        when(acaoService.obtemAcoesColaborador(any(), any(), any()))
                .thenReturn(AcaoPageResponseBuilder.builder().build());

        ResponseEntity<AcaoPageResponse> acao =
                colaboradorController.obtemAcoesColaboradorPaginada(
                        PageRequest.of(0, 20),
                        mockedRequest,
                        1L);

        Assertions.assertEquals("<200 OK OK,AcaoPageResponse(content=[], empty=true, first=true, last=true, " +
                        "number=0, numberOfElements=0, pageNumber=0, pageSize=10, paged=true, unpaged=false, size=10, " +
                        "totalElements=0, totalPages=0),[]>",
                acao.toString());
    }

    @Test
    @DisplayName("Deve testar método controlador de obtenção de acessos do colaborador")
    void deveTestarMetodoControladorDeObtencaoDeAcessosDoColaborador() {
        when(acessoService.obtemAcessosColaborador(any(), any(), any()))
                .thenReturn(AcessoPageResponseBuilder.builder().build());

        ResponseEntity<AcessoPageResponse> acesso =
                colaboradorController.obtemAcessosColaboradorPaginada(
                        PageRequest.of(0, 20),
                        mockedRequest,
                        1L);

        Assertions.assertEquals("<200 OK OK,AcessoPageResponse(content=[], empty=true, first=true, last=true, " +
                        "number=0, numberOfElements=0, offset=0, pageNumber=0, pageSize=10, paged=true, unpaged=false, " +
                        "size=10, totalElements=0, totalPages=0),[]>",
                acesso.toString());
    }
}
