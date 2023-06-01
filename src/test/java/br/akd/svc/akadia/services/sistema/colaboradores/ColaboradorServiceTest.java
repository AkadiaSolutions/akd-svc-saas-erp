package br.akd.svc.akadia.services.sistema.colaboradores;

import br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.ColaboradorPageResponse;
import br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.ColaboradorResponse;
import br.akd.svc.akadia.models.entities.global.EnderecoEntity;
import br.akd.svc.akadia.models.entities.global.mocks.ArquivoEntityBuilder;
import br.akd.svc.akadia.models.entities.global.mocks.EnderecoEntityBuilder;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.AcessoSistemaEntity;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.ColaboradorEntity;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks.ColaboradorEntityBuilder;
import br.akd.svc.akadia.repositories.sistema.colaboradores.ColaboradorRepository;
import br.akd.svc.akadia.repositories.sistema.colaboradores.impl.ColaboradorRepositoryImpl;
import br.akd.svc.akadia.services.exceptions.InvalidRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("Service: Colaborador")
class ColaboradorServiceTest {

    @InjectMocks
    ColaboradorService colaboradorService;

    @Mock
    ColaboradorRepositoryImpl colaboradorRepositoryImpl;

    @Mock
    ColaboradorRepository colaboradorRepository;

    @Mock
    AcaoService acaoService;

    ColaboradorEntity colaboradorLogado = ColaboradorEntityBuilder.builder()
            .comAcessoCompleto()
            .comEmpresa()
            .comAdvertenciaComArquivo()
            .comEndereco()
            .comTelefone()
            .comExclusao()
            .build();

    @Test
    @DisplayName("Deve testar método de criação de novo colaborador")
    void deveTestarMetodoDeCriacaoDeNovoColaborador() {

    }

    @Test
    @DisplayName("Deve testar método de atualização do colaborador")
    void deveTestarMetodoDeAtualizacaoDoColaborador() {

    }

    @Test
    @DisplayName("Deve testar método de construção de objeto AcessoSistema para atualização de colaborador")
    void deveTestarMetodoDeConstrucaoDeObjetoAcessoSistemaParaAtualizacaoDeColaborador() {
        AcessoSistemaEntity acessoSistemaEntity =
                colaboradorService.constroiObjetoAcessoSistemaParaAtualizacaoDeColaborador(
                        ColaboradorEntityBuilder.builder().comAcessoCompleto().build(),
                        ColaboradorEntityBuilder.builder().comAcessoCompleto().build());

        Assertions.assertNotNull(acessoSistemaEntity.toString());
    }

    @Test
    @DisplayName("Deve testar método de construção de objeto AcessoSistema para atualização de colaborador sem acesso")
    void deveTestarMetodoDeConstrucaoDeObjetoAcessoSistemaParaAtualizacaoDeColaboradorSemAcesso() {
        AcessoSistemaEntity acessoSistemaEntity =
                colaboradorService.constroiObjetoAcessoSistemaParaAtualizacaoDeColaborador(
                        ColaboradorEntityBuilder.builder().semAcesso().build(),
                        ColaboradorEntityBuilder.builder().semAcesso().build());

        Assertions.assertEquals("AcessoSistemaEntity(id=null, acessoSistemaAtivo=false, senha=null, " +
                "senhaCriptografada=null, permissaoEnum=LEITURA_BASICA, privilegios=[])", acessoSistemaEntity.toString());
    }

    @Test
    @DisplayName("Deve testar método de tratamento do tipo de arquivo do contrato do colaborador")
    void deveTestarMetodoDeTratamentoDoTipoDeArquivoDoContratoColaborador() {
        colaboradorService.realizaTratamentoTipoDeArquivoDoContratoColaborador(
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        colaboradorService.realizaTratamentoTipoDeArquivoDoContratoColaborador(
                "image/png");
        colaboradorService.realizaTratamentoTipoDeArquivoDoContratoColaborador(
                "image/jpeg");
        colaboradorService.realizaTratamentoTipoDeArquivoDoContratoColaborador(
                "outro");
        colaboradorService.realizaTratamentoTipoDeArquivoDoContratoColaborador(
                null);
    }

    @Test
    @DisplayName("Deve testar método de tratamento do endereço do colaborador atualizado")
    void deveTestarMetodoDeTratamentoEnderecoDoColaboradorAtualizado() {
        EnderecoEntity endereco = colaboradorService.realizaTratamentoEnderecoDoColaboradorAtualizado(
                EnderecoEntityBuilder.builder().build(), ColaboradorEntityBuilder.builder().comEndereco().build());

        Assertions.assertEquals("EnderecoEntity(id=1, logradouro=AVENIDA CORONEL MANUEL PY, numero=583, " +
                "bairro=LAUZANE PAULISTA, codigoPostal=02442-090, cidade=SÃO PAULO, complemento=CASA 4, estado=SP)",
                endereco.toString());
    }

    @Test
    @DisplayName("Deve testar método de obtenção de ir do telefone do colaborador atualizável se houver")
    void deveTestarMetodoDeObtencaoDeIdTelefoneDoColaboradorAtualizavelSeTiver() {
        Long idTelefone = colaboradorService.obtemIdTelefoneDoColaboradorAtualizavelSeTiver(
                ColaboradorEntityBuilder.builder().comTelefone().build());
        Assertions.assertEquals(1L, idTelefone);
    }

    @Test
    @DisplayName("Deve testar método de obtenção de ir do telefone do colaborador atualizável sem telefone")
    void deveTestarMetodoDeObtencaoDeIdTelefoneDoColaboradorAtualizavelSemTelefone() {
        Assertions.assertNull(colaboradorService.obtemIdTelefoneDoColaboradorAtualizavelSeTiver(
                ColaboradorEntityBuilder.builder().build()));
    }

    @Test
    @DisplayName("Deve testar método de obtenção de ir do endereço do colaborador atualizável com endereço")
    void deveTestarMetodoDeObtencaoDeIdEnderecoDoColaboradorAtualizavelComEndereco() {
        Long idEndereco = colaboradorService.obtemIdEnderecoDoColaboradorAtualizavelSeTiver(
                ColaboradorEntityBuilder.builder().comEndereco().build());
        Assertions.assertEquals(1L, idEndereco);
    }

    @Test
    @DisplayName("Deve testar método de obtenção de ir do endereço do colaborador atualizável sem endereço")
    void deveTestarMetodoDeObtencaoDeIdEnderecoDoColaboradorAtualizavelSemEndereco() {
        Assertions.assertNull(colaboradorService.obtemIdEnderecoDoColaboradorAtualizavelSeTiver(
                ColaboradorEntityBuilder.builder().build()));
    }

    @Test
    @DisplayName("Deve testar método de remoção de colaborador")
    void deveTestarMetodoDeRemocaoDeColaborador() {
        when(colaboradorRepositoryImpl.implementaBuscaPorId(anyLong(), anyLong()))
                .thenReturn(ColaboradorEntityBuilder.builder().semExclusao().build());

        when(colaboradorRepositoryImpl.implementaPersistencia(any()))
                .thenReturn(ColaboradorEntityBuilder.builder().semExclusao().build());

        Assertions.assertEquals("ColaboradorResponse(id=1, dataCadastro=2023-02-13, horaCadastro=10:44, " +
                        "matricula=null, nome=João da Silva, dataNascimento=null, email=null, cpfCnpj=null, " +
                        "salario=null, entradaEmpresa=null, saidaEmpresa=null, ocupacao=null, tipoOcupacaoEnum=null, " +
                        "modeloContratacaoEnum=null, modeloTrabalhoEnum=null, statusColaboradorEnum=null, " +
                        "fotoPerfil=null, exclusao=null, acessoSistema=null, configuracaoPerfil=null, " +
                        "contratoContratacao=null, endereco=null, telefone=null, expediente=null, dispensa=null, " +
                        "pontos=null, historicoFerias=null, advertencias=null)",
                colaboradorService.removeColaborador(colaboradorLogado, 1L).toString());
    }

    @Test
    @DisplayName("Deve testar método de remoção de colaborador com colaborador já excluído")
    void deveTestarMetodoDeRemocaoDeColaboradorComColaboradorJaExcluido() {
        when(colaboradorRepositoryImpl.implementaBuscaPorId(anyLong(), anyLong()))
                .thenReturn(ColaboradorEntityBuilder.builder().comExclusao().build());
        try {
            colaboradorService.removeColaborador(colaboradorLogado, 1L);
            Assertions.fail();
        } catch (InvalidRequestException invalidRequestException) {
            Assertions.assertEquals("O colaborador selecionado já foi excluído",
                    invalidRequestException.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar método de remoção de colaboradores em massa")
    void deveTestarMetodoDeRemocaoDeColaboradoresEmMassa() {
        when(colaboradorRepositoryImpl.implementaBuscaPorId(anyLong(), anyLong()))
                .thenReturn(ColaboradorEntityBuilder.builder().comAcessoCompleto().semExclusao().build());

        List<Long> ids = new ArrayList<>();
        ids.add(1L);

        colaboradorService.removeColaboradoresEmMassa(colaboradorLogado, ids);
        Assertions.assertNotNull(ids);
    }

    @Test
    @DisplayName("Deve testar método de remoção de colaboradores em massa com exception")
    void deveTestarMetodoDeRemocaoDeColaboradoresEmMassaComException() {
        when(colaboradorRepositoryImpl.implementaBuscaPorId(anyLong(), anyLong()))
                .thenReturn(ColaboradorEntityBuilder.builder().comAcessoCompleto().comExclusao().build());

        List<Long> ids = new ArrayList<>();
        try {
            colaboradorService.removeColaboradoresEmMassa(colaboradorLogado, ids);
            Assertions.fail();
        } catch (InvalidRequestException invalidRequestException) {
            Assertions.assertEquals("Nenhum colaborador foi encontrado para remoção",
                    invalidRequestException.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar método de validação se colaborador está excluído")
    void deveTestarMetodoDeValidacaoSeColaboradorEstaExcluido() {
        try {
            colaboradorService.validaSeColaboradorEstaExcluido(
                    ColaboradorEntityBuilder.builder().comExclusao().build(), "Excluído");
        } catch (InvalidRequestException invalidRequestException) {
            Assertions.assertEquals("Excluído", invalidRequestException.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar método de busca de colaborador por id")
    void deveTestarMetodoDeBuscaDeColaboradorPorId() {
        when(colaboradorRepositoryImpl.implementaBuscaPorId(any(), any()))
                .thenReturn(ColaboradorEntityBuilder.builder().comEmpresa().comExclusao().comAcessoCompleto().build());

        colaboradorService.realizaBuscaDeColaboradorPorId(colaboradorLogado, 1L);

    }

    @Test
    @DisplayName("Deve testar método de busca paginada por colaboradores")
    void deveTestarMetodoDeBuscaPaginadaPorColaboradores() {
        List<ColaboradorEntity> colaboradores = new ArrayList<>();
        colaboradores.add(ColaboradorEntityBuilder.builder()
                .comExclusao()
                .comAcessoCompleto()
                .comEmpresa()
                .build());
        Pageable pageable = PageRequest.of(0, 10);

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), colaboradores.size());
        Page<ColaboradorEntity> colaboradoresPaged =
                new PageImpl<>(colaboradores.subList(start, end), pageable, colaboradores.size());

        when(colaboradorRepository.buscaPorColaboradoresTypeAhead(any(), any(), any())).thenReturn(colaboradoresPaged);

        ColaboradorPageResponse colaboradorPageResponse = colaboradorService.realizaBuscaPaginadaPorColaboradores(
                colaboradorLogado,
                PageRequest.of(0, 10),
                "busca");

        Assertions.assertEquals("ColaboradorPageResponse(content=[ColaboradorResponse(id=1, " +
                "dataCadastro=2023-02-13, horaCadastro=10:44, matricula=123456, nome=João da Silva, " +
                "dataNascimento=2021-04-11, email=joaosilva@gmail.com, cpfCnpj=12345678910, salario=2000.0, " +
                "entradaEmpresa=2023-02-13, saidaEmpresa=null, ocupacao=Técnico Interno, " +
                "tipoOcupacaoEnum=TECNICO_INTERNO, modeloContratacaoEnum=CLT, modeloTrabalhoEnum=PRESENCIAL, " +
                "statusColaboradorEnum=ATIVO, fotoPerfil=null, " +
                "exclusao=ExclusaoColaboradorResponse(dataExclusao=2023-02-13, horaExclusao=10:44, excluido=true), " +
                "acessoSistema=AcessoSistemaResponse(acessoSistemaAtivo=true, permissaoEnum=LEITURA_AVANCADA_ALTERACAO, " +
                "privilegios=[]), configuracaoPerfil=null, contratoContratacao=null, endereco=null, telefone=null, " +
                "expediente=null, dispensa=null, pontos=[], historicoFerias=[], advertencias=[])], empty=false, " +
                "first=true, last=true, number=0, numberOfElements=1, offset=0, pageNumber=0, pageSize=10, paged=true, " +
                "unpaged=false, size=10, totalElements=1, totalPages=1)", colaboradorPageResponse.toString());
    }

    @Test
    @DisplayName("Deve testar método de conversão em massa de colaborador Entity para colaborador Response")
    void deveTestarMetodoDeConversaoEmMassaDeColaboradorEntityParaColaboradorResponse() {
        List<ColaboradorEntity> colaboradores = new ArrayList<>();
        colaboradores.add(
                ColaboradorEntityBuilder.builder()
                        .comAcessoCompleto()
                        .comEmpresa()
                        .comTelefone()
                        .comEndereco()
                        .comExclusao()
                        .build());
        colaboradores.add(
                ColaboradorEntityBuilder.builder()
                        .comAcessoCompleto()
                        .comExclusao()
                        .build());

        Pageable pageable = PageRequest.of(0, 10);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), colaboradores.size());
        Page<ColaboradorEntity> colaboradoresPaged =
                new PageImpl<>(colaboradores.subList(start, end), pageable, colaboradores.size());

        ColaboradorPageResponse colaboradorPageResponse =
                colaboradorService.converteListaDeColaboradoresEntityParaColaboradoresResponse(colaboradoresPaged);

        Assertions.assertEquals("ColaboradorPageResponse(content=[ColaboradorResponse(id=1, " +
                "dataCadastro=2023-02-13, horaCadastro=10:44, matricula=123456, nome=João da Silva, " +
                "dataNascimento=2021-04-11, email=joaosilva@gmail.com, cpfCnpj=12345678910, salario=2000.0, " +
                "entradaEmpresa=2023-02-13, saidaEmpresa=null, ocupacao=Técnico Interno, " +
                "tipoOcupacaoEnum=TECNICO_INTERNO, modeloContratacaoEnum=CLT, modeloTrabalhoEnum=PRESENCIAL, " +
                "statusColaboradorEnum=ATIVO, fotoPerfil=null, " +
                "exclusao=ExclusaoColaboradorResponse(dataExclusao=2023-02-13, horaExclusao=10:44, excluido=true), " +
                "acessoSistema=AcessoSistemaResponse(acessoSistemaAtivo=true, permissaoEnum=LEITURA_AVANCADA_ALTERACAO, " +
                "privilegios=[]), configuracaoPerfil=null, contratoContratacao=null, endereco=EnderecoEntity(id=1, " +
                "logradouro=Avenida Coronel Manuel Py, numero=583, bairro=Lauzane Paulista, codigoPostal=02442-090, " +
                "cidade=São Paulo, complemento=Casa 4, estado=SP), telefone=TelefoneEntity(id=1, prefixo=11, " +
                "numero=979815415, tipoTelefone=MOVEL_WHATSAPP), expediente=null, dispensa=null, pontos=[], " +
                "historicoFerias=[], advertencias=[]), ColaboradorResponse(id=1, dataCadastro=2023-02-13, " +
                "horaCadastro=10:44, matricula=123456, nome=João da Silva, dataNascimento=2021-04-11, " +
                "email=joaosilva@gmail.com, cpfCnpj=12345678910, salario=2000.0, entradaEmpresa=2023-02-13, " +
                "saidaEmpresa=null, ocupacao=Técnico Interno, tipoOcupacaoEnum=TECNICO_INTERNO, " +
                "modeloContratacaoEnum=CLT, modeloTrabalhoEnum=PRESENCIAL, statusColaboradorEnum=ATIVO, " +
                "fotoPerfil=null, exclusao=ExclusaoColaboradorResponse(dataExclusao=2023-02-13, horaExclusao=10:44, " +
                "excluido=true), acessoSistema=AcessoSistemaResponse(acessoSistemaAtivo=true, " +
                "permissaoEnum=LEITURA_AVANCADA_ALTERACAO, privilegios=[]), configuracaoPerfil=null, " +
                "contratoContratacao=null, endereco=null, telefone=null, expediente=null, dispensa=null, pontos=[], " +
                "historicoFerias=[], advertencias=[])], empty=false, first=true, last=true, number=0, " +
                "numberOfElements=2, offset=0, pageNumber=0, pageSize=10, paged=true, unpaged=false, size=10, " +
                "totalElements=2, totalPages=1)", colaboradorPageResponse.toString());
    }

    @Test
    @DisplayName("Deve testar método de conversão de colaborador Entity para colaborador Response")
    void deveTestarMetodoDeConversaoDeColaboradorEntityParaColaboradorResponse() {
        ColaboradorEntity colaboradorEntity = ColaboradorEntityBuilder.builder()
                .comAcessoCompleto()
                .comEmpresa()
                .comTelefone()
                .comEndereco()
                .comExclusao()
                .build();

        ColaboradorResponse colaboradorResponse = colaboradorService.converteColaboradorEntityParaColaboradorResponse(colaboradorEntity);

        Assertions.assertEquals("ColaboradorResponse(id=1, dataCadastro=2023-02-13, horaCadastro=10:44, " +
                "matricula=123456, nome=João da Silva, dataNascimento=2021-04-11, email=joaosilva@gmail.com, " +
                "cpfCnpj=12345678910, salario=2000.0, entradaEmpresa=2023-02-13, saidaEmpresa=null, " +
                "ocupacao=Técnico Interno, tipoOcupacaoEnum=TECNICO_INTERNO, modeloContratacaoEnum=CLT, " +
                "modeloTrabalhoEnum=PRESENCIAL, statusColaboradorEnum=ATIVO, fotoPerfil=null, " +
                "exclusao=ExclusaoColaboradorResponse(dataExclusao=2023-02-13, horaExclusao=10:44, " +
                "excluido=true), acessoSistema=AcessoSistemaResponse(acessoSistemaAtivo=true, " +
                "permissaoEnum=LEITURA_AVANCADA_ALTERACAO, privilegios=[]), configuracaoPerfil=null, " +
                "contratoContratacao=null, endereco=EnderecoEntity(id=1, logradouro=Avenida Coronel Manuel Py, " +
                "numero=583, bairro=Lauzane Paulista, codigoPostal=02442-090, cidade=São Paulo, " +
                "complemento=Casa 4, estado=SP), telefone=TelefoneEntity(id=1, prefixo=11, numero=979815415, " +
                "tipoTelefone=MOVEL_WHATSAPP), expediente=null, dispensa=null, pontos=[], historicoFerias=[], " +
                "advertencias=null)", colaboradorResponse.toString());
    }

    @Test
    @DisplayName("Deve testar método de atualização de imagem de perfil do colaborador")
    void deveTestarMetodoDeAtualizacaoDeImagemPerfilColaborador() throws IOException {

        MultipartFile multipartFile = new MockMultipartFile(
                "test.pdf",
                "test.pdf",
                "text/plain",
                "Mock".getBytes(StandardCharsets.UTF_8));

        when(colaboradorRepositoryImpl.implementaPersistencia(any()))
                .thenReturn(ColaboradorEntityBuilder.builder().comEmpresa().comExclusao().comAcessoCompleto().build());
        when(colaboradorRepositoryImpl.implementaBuscaPorId(any(), any()))
                .thenReturn(ColaboradorEntityBuilder.builder().comEmpresa().comExclusao().comAcessoCompleto().build());
        doNothing().when(acaoService).salvaHistoricoColaborador(any(), any(), any(), any(), any());

        ColaboradorResponse colaboradorResponse =
                colaboradorService.atualizaImagemPerfilColaborador(colaboradorLogado, 1L, multipartFile);

        Assertions.assertEquals("ColaboradorResponse(id=1, dataCadastro=2023-02-13, horaCadastro=10:44, " +
                        "matricula=123456, nome=João da Silva, dataNascimento=2021-04-11, email=joaosilva@gmail.com, " +
                        "cpfCnpj=12345678910, salario=2000.0, entradaEmpresa=2023-02-13, saidaEmpresa=null, " +
                        "ocupacao=Técnico Interno, tipoOcupacaoEnum=TECNICO_INTERNO, modeloContratacaoEnum=CLT, " +
                        "modeloTrabalhoEnum=PRESENCIAL, statusColaboradorEnum=ATIVO, fotoPerfil=null, " +
                        "exclusao=ExclusaoColaboradorResponse(dataExclusao=2023-02-13, horaExclusao=10:44, excluido=true), " +
                        "acessoSistema=AcessoSistemaResponse(acessoSistemaAtivo=true, permissaoEnum=LEITURA_AVANCADA_ALTERACAO, " +
                        "privilegios=[]), configuracaoPerfil=null, contratoContratacao=null, endereco=null, telefone=null, " +
                        "expediente=null, dispensa=null, pontos=[], historicoFerias=[], advertencias=null)",
                colaboradorResponse.toString());
    }

    @Test
    @DisplayName("Deve testar método de obtenção de imagem de perfil do colaborador")
    void deveTestarMetodoDeobtencaoImagemPerfilColaborador() {
        when(colaboradorRepositoryImpl.implementaBuscaDeImagemDePerfilPorId(any(), any()))
                .thenReturn(ArquivoEntityBuilder.builder().build());
        byte[] imagem = colaboradorService.obtemImagemPerfilColaborador(colaboradorLogado, 1L);
        Assertions.assertEquals("[]", Arrays.toString(imagem));
    }

    @Test
    @DisplayName("Deve testar método de gerar matrícula única para colaborador")
    void deveTestarMetodoDeGerarMatriculaUnicaParaColaborador() {
        String matriculaUnica = colaboradorService.geraMatriculaUnica();
        Assertions.assertNotNull(matriculaUnica);
    }

}
