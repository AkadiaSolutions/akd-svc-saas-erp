package br.akd.svc.akadia.modules.erp.colaboradores.colaborador.services;

import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.dto.response.page.ColaboradorPageResponse;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.dto.response.ColaboradorResponse;
import br.akd.svc.akadia.modules.global.entity.ArquivoEntity;
import br.akd.svc.akadia.modules.global.entity.EnderecoEntity;
import br.akd.svc.akadia.modules.global.entity.ExclusaoEntity;
import br.akd.svc.akadia.modules.global.entity.TelefoneEntity;
import br.akd.svc.akadia.modules.global.entity.AcessoSistemaEntity;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.entity.ColaboradorEntity;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.entity.ConfiguracaoPerfilEntity;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.entity.ExpedienteEntity;
import br.akd.svc.akadia.modules.global.enums.TipoArquivoEnum;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.enums.ModulosEnum;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.enums.PermissaoEnum;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.enums.TemaTelaEnum;
import br.akd.svc.akadia.modules.erp.colaboradores.acao.models.enums.TipoAcaoEnum;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.repository.ColaboradorRepository;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.repository.impl.ColaboradorRepositoryImpl;
import br.akd.svc.akadia.modules.erp.colaboradores.acao.services.AcaoService;
import br.akd.svc.akadia.exceptions.InvalidRequestException;
import br.akd.svc.akadia.utils.Constantes;
import br.akd.svc.akadia.utils.ConversorDeDados;
import br.akd.svc.akadia.utils.SecurityUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Slf4j
@Service
public class ColaboradorService {

    @Autowired
    AcaoService acaoService;

    @Autowired
    ColaboradorTypeConverter colaboradorTypeConverter;

    @Autowired
    ColaboradorRepositoryImpl colaboradorRepositoryImpl;

    @Autowired
    ColaboradorRepository colaboradorRepository;

    String BUSCA_COLABORADOR_POR_ID = "Iniciando acesso ao método de implementação de busca de colaborador por id...";

    public String criaNovoColaborador(ColaboradorEntity colaboradorLogado,
                                      MultipartFile contratoColaborador,
                                      String colaboradorEmJson) throws IOException {

        log.debug("Método de serviço de criação de novo colaborador acessado");

        log.debug(Constantes.VERIFICANDO_SE_COLABORADOR_PODE_ALTERAR_DADOS);
        SecurityUtil.verificaSePodeRealizarAlteracoes(colaboradorLogado.getAcessoSistema());

        log.debug("Convertendo objeto colaborador recebido de Json para entity...");
        ColaboradorEntity colaboradorEntity = new ObjectMapper().readValue(colaboradorEmJson, ColaboradorEntity.class);

        log.debug("Iniciando setagem de dados do colaborador...");
        colaboradorEntity.setDataCadastro(LocalDate.now().toString());
        colaboradorEntity.setHoraCadastro(LocalTime.now().toString());
        colaboradorEntity.setMatricula(geraMatriculaUnica());
        colaboradorEntity.setContratoContratacao(contratoColaborador != null
                ? ArquivoEntity.builder()
                .nome(contratoColaborador.getOriginalFilename())
                .tipo(realizaTratamentoTipoDeArquivoDoContratoColaborador(contratoColaborador.getContentType()))
                .tamanho(contratoColaborador.getSize())
                .arquivo(contratoColaborador.getBytes())
                .build()
                : null);
        colaboradorEntity.setExclusao(null);
        colaboradorEntity.getAcessoSistema().setSenhaCriptografada(
                Boolean.TRUE.equals(colaboradorEntity.getAcessoSistema().getAcessoSistemaAtivo())
                        ? new BCryptPasswordEncoder().encode(colaboradorEntity.getAcessoSistema().getSenha())
                        : null);
        colaboradorEntity.setConfiguracaoPerfil(ConfiguracaoPerfilEntity.builder()
                .temaTelaEnum(TemaTelaEnum.TELA_CLARA)
                .dataUltimaAtualizacao(LocalDate.now().toString())
                .horaUltimaAtualizacao(LocalTime.now().toString())
                .build());
        colaboradorEntity.setDispensa(null);
        colaboradorEntity.setEmpresa(colaboradorLogado.getEmpresa());
        log.debug("Setagem de dados realizada com sucesso");

        log.debug(Constantes.INICIANDO_IMPL_PERSISTENCIA_COLABORADOR);
        ColaboradorEntity colaboradorPersistido = colaboradorRepositoryImpl.implementaPersistencia(colaboradorEntity);

        log.debug(Constantes.INICIANDO_SALVAMENTO_HISTORICO_COLABORADOR);
        acaoService.salvaHistoricoColaborador(colaboradorLogado, colaboradorPersistido.getId(),
                ModulosEnum.COLABORADORES, TipoAcaoEnum.CRIACAO, null);

        log.info("Colaborador criado com sucesso");
        return colaboradorPersistido.getMatricula();
    }

    public ColaboradorResponse atualizaColaborador(ColaboradorEntity colaboradorLogado,
                                                   Long id,
                                                   MultipartFile contratoColaborador,
                                                   String colaboradorEmJson) throws IOException {
        log.debug("Método de serviço de atualização de colaborador acessado");

        log.debug(Constantes.VERIFICANDO_SE_COLABORADOR_PODE_ALTERAR_DADOS);
        SecurityUtil.verificaSePodeRealizarAlteracoes(colaboradorLogado.getAcessoSistema());

        log.debug("Convertendo objeto colaborador recebido de Json para entity...");
        ColaboradorEntity colaborador = new ObjectMapper().readValue(colaboradorEmJson, ColaboradorEntity.class);

        log.debug(BUSCA_COLABORADOR_POR_ID);
        ColaboradorEntity colaboradorEncontrado =
                colaboradorRepositoryImpl.implementaBuscaPorId(id, colaboradorLogado.getEmpresa().getId());

        log.debug("Iniciando acesso ao método de validação de alteração de dados de colaborador excluído...");
        validaSeColaboradorEstaExcluido(colaboradorEncontrado,
                "Não é possível atualizar um colaborador excluído");

        log.debug("Iniciando criação do objeto ColaboradorEntity...");
        ColaboradorEntity novoColaboradorAtualizado = ColaboradorEntity.builder()
                .id(id)
                .dataCadastro(colaboradorEncontrado.getDataCadastro())
                .horaCadastro(colaboradorEncontrado.getHoraCadastro())
                .matricula(colaboradorEncontrado.getMatricula())
                .nome(colaborador.getNome())
                .dataNascimento(colaborador.getDataNascimento())
                .email(colaborador.getEmail())
                .cpfCnpj(colaborador.getCpfCnpj())
                .salario(colaborador.getSalario())
                .entradaEmpresa(colaborador.getEntradaEmpresa())
                .saidaEmpresa(colaborador.getSaidaEmpresa())
                .contratoContratacao(contratoColaborador != null
                        ? ArquivoEntity.builder()
                        .nome(contratoColaborador.getOriginalFilename())
                        .tipo(realizaTratamentoTipoDeArquivoDoContratoColaborador(contratoColaborador.getContentType()))
                        .tamanho(contratoColaborador.getSize())
                        .arquivo(contratoColaborador.getBytes())
                        .build()
                        : null)
                .ocupacao(colaborador.getOcupacao())
                .tipoOcupacaoEnum(colaborador.getTipoOcupacaoEnum())
                .modeloContratacaoEnum(colaborador.getModeloContratacaoEnum())
                .modeloTrabalhoEnum(colaborador.getModeloTrabalhoEnum())
                .statusColaboradorEnum(colaborador.getStatusColaboradorEnum())
                .exclusao(colaboradorEncontrado.getExclusao())
                .acessoSistema(constroiObjetoAcessoSistemaParaAtualizacaoDeColaborador(colaboradorEncontrado, colaborador))
                .configuracaoPerfil(colaboradorEncontrado.getConfiguracaoPerfil())
                .endereco(realizaTratamentoEnderecoDoColaboradorAtualizado(colaborador.getEndereco(), colaboradorEncontrado))
                .telefone(colaborador.getTelefone() == null
                        ? null
                        : TelefoneEntity.builder()
                        .id(obtemIdTelefoneDoColaboradorAtualizavelSeTiver(colaboradorEncontrado))
                        .prefixo(colaborador.getTelefone().getPrefixo())
                        .numero(colaborador.getTelefone().getNumero())
                        .tipoTelefone(colaborador.getTelefone().getTipoTelefone())
                        .build())
                .expediente(colaborador.getExpediente() != null
                        ? ExpedienteEntity.builder()
                        .horaEntrada(colaborador.getExpediente().getHoraEntrada())
                        .horaEntradaAlmoco(colaborador.getExpediente().getHoraEntradaAlmoco())
                        .horaSaidaAlmoco(colaborador.getExpediente().getHoraSaidaAlmoco())
                        .horaSaida(colaborador.getExpediente().getHoraSaida())
                        .cargaHorariaSemanal(colaborador.getExpediente().getCargaHorariaSemanal())
                        .escalaEnum(colaborador.getExpediente().getEscalaEnum())
                        .build()
                        : null)
                .fotoPerfil(colaboradorEncontrado.getFotoPerfil())
                .dispensa(colaboradorEncontrado.getDispensa())
                .acoes(colaboradorEncontrado.getAcoes())
                .advertencias(colaboradorEncontrado.getAdvertencias())
                .acessos(colaboradorEncontrado.getAcessos())
                .historicoFerias(colaboradorEncontrado.getHistoricoFerias())
                .pontos(colaboradorEncontrado.getPontos())
                .empresa(colaboradorLogado.getEmpresa())
                .build();
        log.debug("Objeto colaborador construído com sucesso");

        log.debug(Constantes.INICIANDO_IMPL_PERSISTENCIA_COLABORADOR);
        ColaboradorEntity colaboradorPersistido = colaboradorRepositoryImpl.implementaPersistencia(novoColaboradorAtualizado);

        log.debug(Constantes.INICIANDO_SALVAMENTO_HISTORICO_COLABORADOR);
        acaoService.salvaHistoricoColaborador(colaboradorLogado, colaboradorPersistido.getId(),
                ModulosEnum.COLABORADORES, TipoAcaoEnum.ALTERACAO, null);

        log.debug("Colaborador persistido com sucesso. Convertendo colaboradorEntity para colaboradorResponse...");
        ColaboradorResponse colaboradorResponse = colaboradorTypeConverter.
                converteColaboradorEntityParaColaboradorResponse(colaboradorPersistido);

        log.info("Colaborador criado com sucesso");
        return colaboradorResponse;
    }

    public AcessoSistemaEntity constroiObjetoAcessoSistemaParaAtualizacaoDeColaborador(ColaboradorEntity colaboradorPreAtualizacao,
                                                                                       ColaboradorEntity colaboradorNovo) {

        if (Boolean.FALSE.equals(colaboradorNovo.getAcessoSistema().getAcessoSistemaAtivo())) return
                AcessoSistemaEntity.builder()
                        .acessoSistemaAtivo(false)
                        .senha(null)
                        .senhaCriptografada(null)
                        .permissaoEnum(PermissaoEnum.LEITURA_BASICA)
                        .privilegios(new HashSet<>())
                        .build();
        else {
            return AcessoSistemaEntity.builder()
                    .senha((colaboradorNovo.getAcessoSistema().getSenha() != null && !"".equals(colaboradorNovo.getAcessoSistema().getSenha()))
                            ? colaboradorNovo.getAcessoSistema().getSenha()
                            : colaboradorPreAtualizacao.getAcessoSistema().getSenha())
                    .senhaCriptografada(new BCryptPasswordEncoder().encode(colaboradorNovo.getAcessoSistema().getSenha()))
                    .acessoSistemaAtivo(colaboradorNovo.getAcessoSistema().getAcessoSistemaAtivo())
                    .permissaoEnum(colaboradorNovo.getAcessoSistema().getPermissaoEnum())
                    .privilegios(colaboradorNovo.getAcessoSistema().getPrivilegios() != null
                            ? new HashSet<>(colaboradorNovo.getAcessoSistema().getPrivilegios())
                            : new HashSet<>())
                    .build();
        }
    }

    public TipoArquivoEnum realizaTratamentoTipoDeArquivoDoContratoColaborador(String tipoArquivo) {

        if (tipoArquivo == null) return TipoArquivoEnum.PDF;

        switch (tipoArquivo) {
            case "application/vnd.openxmlformats-officedocument.wordprocessingml.document":
                return TipoArquivoEnum.DOCX;
            case "image/png":
                return TipoArquivoEnum.PNG;
            case "image/jpeg":
                return TipoArquivoEnum.JPG;
            default:
                return TipoArquivoEnum.PDF;
        }
    }

    public EnderecoEntity realizaTratamentoEnderecoDoColaboradorAtualizado(EnderecoEntity endereco,
                                                                           ColaboradorEntity colaboradorEntity) {
        if (endereco == null) return null;
        return EnderecoEntity.builder()
                .id(obtemIdEnderecoDoColaboradorAtualizavelSeTiver(colaboradorEntity))
                .codigoPostal(endereco.getCodigoPostal())
                .logradouro(endereco.getLogradouro() != null
                        ? endereco.getLogradouro().toUpperCase()
                        : null)
                .numero(endereco.getNumero())
                .bairro(endereco.getBairro() != null
                        ? endereco.getBairro().toUpperCase()
                        : null)
                .cidade(endereco.getCidade() != null
                        ? endereco.getCidade().toUpperCase()
                        : null)
                .complemento(endereco.getComplemento() != null
                        ? endereco.getComplemento().toUpperCase()
                        : null)
                .estado(endereco.getEstado())
                .build();
    }

    public Long obtemIdTelefoneDoColaboradorAtualizavelSeTiver(ColaboradorEntity colaboradorEntity) {
        log.debug("Método de obtenção do ID do telefone do colaborador se o colaborador tiver um acessado");
        if (colaboradorEntity.getTelefone() != null) {
            log.debug("O colaborador possui telefone cadastrado. Seu id é: {}", colaboradorEntity.getTelefone().getId());
            return colaboradorEntity.getTelefone().getId();
        } else {
            log.debug("O colaborador não possui nenhum telefone cadastrado. Retornando null...");
            return null;
        }
    }

    public Long obtemIdEnderecoDoColaboradorAtualizavelSeTiver(ColaboradorEntity colaboradorEncontrado) {
        log.debug("Método de obtenção do ID do endereço do colaborador se o colaborador tiver um acessado");
        if (colaboradorEncontrado.getEndereco() != null) {
            log.debug("O colaborador possui endereço cadastrado. Seu id é: {}", colaboradorEncontrado.getEndereco().getId());
            return colaboradorEncontrado.getEndereco().getId();
        } else {
            log.debug("O colaborador não possui nenhum endereço cadastrado. Retornando null...");
            return null;
        }
    }

    public ColaboradorResponse removeColaborador(ColaboradorEntity colaboradorLogado, Long id) {
        log.debug("Método de serviço de remoção de colaborador acessado");

        log.debug(BUSCA_COLABORADOR_POR_ID);
        ColaboradorEntity colaboradorEncontrado =
                colaboradorRepositoryImpl.implementaBuscaPorId(id, colaboradorLogado.getEmpresa().getId());

        log.debug("Iniciando acesso ao método de validação de exclusão de colaborador que já foi excluído...");
        validaSeColaboradorEstaExcluido(colaboradorEncontrado,
                "O colaborador selecionado já foi excluído");

        log.debug("Atualizando objeto ExclusaoColaborador do colaborador com dados referentes à sua exclusão...");
        ExclusaoEntity exclusao = ExclusaoEntity.builder()
                .dataExclusao(LocalDate.now().toString())
                .horaExclusao(LocalTime.now().toString())
                .responsavelExclusao(colaboradorLogado)
                .build();
        log.debug("Objeto Exclusao do colaborador de id {} setado com sucesso", id);

        log.debug("Setando objeto exclusão criado no colaborador encontrado...");
        colaboradorEncontrado.setExclusao(exclusao);

        log.debug("Persistindo colaborador excluído no banco de dados...");
        ColaboradorEntity colaboradorExcluido = colaboradorRepositoryImpl.implementaPersistencia(colaboradorEncontrado);

        log.debug(Constantes.INICIANDO_SALVAMENTO_HISTORICO_COLABORADOR);
        acaoService.salvaHistoricoColaborador(colaboradorLogado, null,
                ModulosEnum.COLABORADORES, TipoAcaoEnum.REMOCAO, null);

        log.info("Colaborador excluído com sucesso");
        return ColaboradorResponse.builder()
                .id(colaboradorExcluido.getId())
                .dataCadastro(colaboradorExcluido.getDataCadastro())
                .horaCadastro(colaboradorExcluido.getHoraCadastro())
                .nome(colaboradorExcluido.getNome())
                .endereco(colaboradorExcluido.getEndereco())
                .telefone(colaboradorExcluido.getTelefone())
                .build();
    }

    public void removeColaboradoresEmMassa(ColaboradorEntity colaboradorLogado, List<Long> ids) {
        log.debug("Método de serviço de remoção de colaborador acessado");

        List<ColaboradorEntity> colaboradoresEncontrados = new ArrayList<>();

        for (Long id : ids) {
            log.debug(BUSCA_COLABORADOR_POR_ID);
            ColaboradorEntity colaboradorEncontrado = colaboradorRepositoryImpl.implementaBuscaPorId(id, colaboradorLogado.getEmpresa().getId());
            colaboradoresEncontrados.add(colaboradorEncontrado);
        }

        log.debug("Iniciando acesso ao método de validação de exclusão de colaborador que já foi excluído...");
        for (ColaboradorEntity colaborador : colaboradoresEncontrados) {
            validaSeColaboradorEstaExcluido(colaborador,
                    "O Colaborador selecionado já foi excluído");
            log.debug("Atualizando objeto ExclusaoColaborador do colaborador com dados referentes à sua exclusão...");
            ExclusaoEntity exclusao = ExclusaoEntity.builder()
                    .dataExclusao(LocalDate.now().toString())
                    .horaExclusao(LocalTime.now().toString())
                    .responsavelExclusao(colaboradorLogado)
                    .build();

            colaborador.setExclusao(exclusao);
            log.debug("Objeto ExclusaoColaborador do colaborador de id {} setado com sucesso", colaborador.getId());
        }

        log.debug("Verificando se listagem de colaboradores encontrados está preenchida...");
        if (!colaboradoresEncontrados.isEmpty()) {
            log.debug("Persistindo colaborador excluído no banco de dados...");
            colaboradorRepositoryImpl.implementaPersistenciaEmMassa(colaboradoresEncontrados);

            log.debug(Constantes.INICIANDO_SALVAMENTO_HISTORICO_COLABORADOR);
            acaoService.salvaHistoricoColaborador(colaboradorLogado, null, ModulosEnum.COLABORADORES,
                    TipoAcaoEnum.REMOCAO_EM_MASSA, colaboradoresEncontrados.size() + " Itens removidos");
        } else throw new InvalidRequestException("Nenhum colaborador foi encontrado para remoção");

        log.info("colaboradores excluídos com sucesso");
    }

    public void validaSeColaboradorEstaExcluido(ColaboradorEntity colaborador, String mensagemCasoEstejaExcluido) {
        log.debug("Método de validação de colaborador excluído acessado");
        if (colaborador.getExclusao() != null) {
            log.debug("colaborador de id {}: Validação de colaborador já excluído falhou. Não é possível realizar operações " +
                    "em um colaborador que já foi excluído.", colaborador.getId());
            throw new InvalidRequestException(mensagemCasoEstejaExcluido);
        }
        log.debug("O colaborador de id {} não está excluído", colaborador.getId());
    }

    public ColaboradorResponse realizaBuscaDeColaboradorPorId(ColaboradorEntity colaboradorLogado, Long id) {
        log.debug("Método de serviço de obtenção de colaborador por id. ID recebido: {}", id);

        log.debug("Acessando repositório de busca de colaborador por ID...");
        ColaboradorEntity colaborador =
                colaboradorRepositoryImpl.implementaBuscaPorId(id, colaboradorLogado.getEmpresa().getId());

        log.debug("Busca de colaboradores por id realizada com sucesso. Acessando método de conversão dos objeto do tipo " +
                "Entity para objeto do tipo Response...");
        ColaboradorResponse colaboradorResponse = colaboradorTypeConverter.
                converteColaboradorEntityParaColaboradorResponse(colaborador);
        log.debug("Conversão de tipagem realizada com sucesso");

        log.info("A busca de colaborador por id foi realizada com sucesso");
        return colaboradorResponse;
    }

    public ColaboradorPageResponse realizaBuscaPaginadaPorColaboradores(ColaboradorEntity colaboradorLogado,
                                                                        Pageable pageable,
                                                                        String campoBusca) {
        log.debug("Método de serviço de obtenção paginada de colaboradores acessado. Campo de busca: {}",
                campoBusca != null ? campoBusca : "Nulo");

        log.debug("Acessando repositório de busca de colaboradores");
        Page<ColaboradorEntity> colaboradorPage = campoBusca != null && !campoBusca.isEmpty()
                ? colaboradorRepository.buscaPorColaboradoresTypeAhead(pageable, campoBusca, colaboradorLogado.getEmpresa().getId())
                : colaboradorRepository.buscaPorColaboradores(pageable, colaboradorLogado.getEmpresa().getId());

        log.debug("Busca de colaboradores por paginação realizada com sucesso. Acessando método de conversão dos objetos do tipo " +
                "Entity para objetos do tipo Response...");
        ColaboradorPageResponse colaboradorPageResponse =
                colaboradorTypeConverter.converteListaDeColaboradoresEntityParaColaboradoresResponse(colaboradorPage);

        log.debug("Conversão de tipagem realizada com sucesso");

        log.info("A busca paginada de colaboradores foi realizada com sucesso");
        return colaboradorPageResponse;
    }

    public ColaboradorResponse atualizaImagemPerfilColaborador(ColaboradorEntity colaboradorLogado,
                                                               Long id,
                                                               MultipartFile fotoPerfil) throws IOException {

        log.debug("Método de serviço de atualização de foto de perfil de colaborador acessado");

        log.debug(Constantes.VERIFICANDO_SE_COLABORADOR_PODE_ALTERAR_DADOS);
        SecurityUtil.verificaSePodeRealizarAlteracoes(colaboradorLogado.getAcessoSistema());

        log.debug("Iniciando construção do objeto ArquivoEntity da foto de perfil do colaborador...");
        ArquivoEntity fotoPerfilEntity = fotoPerfil != null
                ? ArquivoEntity.builder()
                .nome(fotoPerfil.getOriginalFilename())
                .tipo(realizaTratamentoTipoDeArquivoDoContratoColaborador(fotoPerfil.getContentType()))
                .tamanho(fotoPerfil.getSize())
                .arquivo(fotoPerfil.getBytes())
                .build()
                : null;

        log.debug("Acessando repositório de busca de colaborador por ID...");
        ColaboradorEntity colaborador =
                colaboradorRepositoryImpl.implementaBuscaPorId(id, colaboradorLogado.getEmpresa().getId());

        log.debug("Acoplando foto de perfil ao objeto do colaborador...");
        colaborador.setFotoPerfil(fotoPerfilEntity);

        log.debug(Constantes.INICIANDO_IMPL_PERSISTENCIA_COLABORADOR);
        ColaboradorEntity colaboradorPersistido = colaboradorRepositoryImpl.implementaPersistencia(colaborador);

        if (fotoPerfil != null) {
            log.debug(Constantes.INICIANDO_SALVAMENTO_HISTORICO_COLABORADOR);
            acaoService.salvaHistoricoColaborador(colaboradorLogado, colaboradorPersistido.getId(),
                    ModulosEnum.COLABORADORES, TipoAcaoEnum.ALTERACAO, "Alteração da foto de perfil do colaborador "
                            + colaboradorPersistido.getNome());
        } else {
            log.debug(Constantes.INICIANDO_SALVAMENTO_HISTORICO_COLABORADOR);
            acaoService.salvaHistoricoColaborador(colaboradorLogado, colaboradorPersistido.getId(),
                    ModulosEnum.COLABORADORES, TipoAcaoEnum.REMOCAO, "Remoção da foto de perfil do colaborador "
                            + colaboradorPersistido.getNome());
        }

        return colaboradorTypeConverter.converteColaboradorEntityParaColaboradorResponse(colaboradorPersistido);
    }

    public byte[] obtemImagemPerfilColaborador(ColaboradorEntity colaboradorLogado, Long id) {
        return colaboradorRepositoryImpl.implementaBuscaDeImagemDePerfilPorId(
                id, colaboradorLogado.getEmpresa().getId()).getArquivo();
    }

    public String geraMatriculaUnica() {

        log.debug("Método responsável por gerar matrícula do colaborador acessado");
        long min = 1000000L;
        long max = 9999999L;
        while (true) {
            log.debug("Gerando matrícula...");

            long matriculaAleatoria =
                    (long) (ConversorDeDados.RANDOM.nextFloat() * (max - min) + min);

            String matricula = Long.toString(matriculaAleatoria);

            log.debug("Matrícula gerada: {}", matricula);
            if (Boolean.FALSE.equals(colaboradorRepository.existsByMatricula(matricula)))
                return matricula;

            else log.debug("A matrícula gerada já existe. Tentando novamente...");
        }
    }

}
