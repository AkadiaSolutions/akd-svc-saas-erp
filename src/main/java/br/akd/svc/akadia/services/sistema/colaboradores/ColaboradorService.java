package br.akd.svc.akadia.services.sistema.colaboradores;

import br.akd.svc.akadia.models.dto.global.EnderecoDto;
import br.akd.svc.akadia.models.dto.global.ParentescoDto;
import br.akd.svc.akadia.models.dto.sistema.colaboradores.ColaboradorDto;
import br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.AcessoSistemaResponse;
import br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.ColaboradorPageResponse;
import br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.ColaboradorResponse;
import br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.ExclusaoColaboradorResponse;
import br.akd.svc.akadia.models.entities.global.EnderecoEntity;
import br.akd.svc.akadia.models.entities.global.ParentescoEntity;
import br.akd.svc.akadia.models.entities.global.TelefoneEntity;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.*;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.TemaTelaEnum;
import br.akd.svc.akadia.repositories.sistema.colaboradores.ColaboradorRepository;
import br.akd.svc.akadia.repositories.sistema.colaboradores.impl.ColaboradorRepositoryImpl;
import br.akd.svc.akadia.services.exceptions.InvalidRequestException;
import br.akd.svc.akadia.utils.ConversorDeDados;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class ColaboradorService {
    @Autowired
    ColaboradorRepositoryImpl colaboradorRepositoryImpl;

    @Autowired
    ColaboradorRepository colaboradorRepository;

    String BUSCA_COLABORADOR_POR_ID = "Iniciando acesso ao método de implementação de busca de colaborador por id...";

    public ColaboradorResponse criaNovoColaborador(ColaboradorEntity colaboradorLogado, ColaboradorDto colaboradorDto) {

        log.debug("Método de serviço de criação de novo colaborador acessado");

        log.debug("Iniciando criação do objeto ColaboradorEntity...");
        ColaboradorEntity colaboradorEntity = ColaboradorEntity.builder()
                .dataCadastro(LocalDate.now().toString())
                .horaCadastro(LocalTime.now().toString())
                .matricula(geraMatriculaUnica())
                .fotoPerfil(colaboradorDto.getFotoPerfil())
                .nome(colaboradorDto.getNome())
                .dataNascimento(colaboradorDto.getDataNascimento())
                .email(colaboradorDto.getEmail())
                .cpfCnpj(colaboradorDto.getCpfCnpj())
                .ativo(colaboradorDto.getAtivo())
                .salario(colaboradorDto.getSalario())
                .entradaEmpresa(colaboradorDto.getEntradaEmpresa())
                .saidaEmpresa(colaboradorDto.getSaidaEmpresa())
                .contratoContratacao(colaboradorDto.getContratoContratacao())
                .ocupacao(colaboradorDto.getOcupacao())
                .tipoOcupacaoEnum(colaboradorDto.getTipoOcupacaoEnum())
                .modeloContratacaoEnum(colaboradorDto.getModeloContratacaoEnum())
                .modeloTrabalhoEnum(colaboradorDto.getModeloTrabalhoEnum())
                .statusColaboradorEnum(colaboradorDto.getStatusColaboradorEnum())
                .exclusao(ExclusaoColaboradorEntity.builder()
                        .excluido(false)
                        .dataExclusao(null)
                        .horaExclusao(null)
                        .responsavelExclusao(null)
                        .build())
                .acessoSistema(AcessoSistemaEntity.builder()
                        .nomeUsuario(colaboradorDto.getAcessoSistema().getNomeUsuario())
                        .senha(colaboradorDto.getAcessoSistema().getSenha())
                        .senhaCriptografada(new BCryptPasswordEncoder().encode(colaboradorDto.getAcessoSistema().getSenha()))
                        .acessoSistemaAtivo(colaboradorDto.getAcessoSistema().getAcessoSistemaAtivo())
                        .privilegios(realizaTratamentoDosPrivilegiosDoNovoColaborador(colaboradorDto.getAcessoSistema().getPrivilegios()))
                        .build())
                .configuracaoPerfil(ConfiguracaoPerfilEntity.builder()
                        .temaTelaEnum(TemaTelaEnum.TELA_CLARA)
                        .dataUltimaAtualizacao(LocalDate.now().toString())
                        .horaUltimaAtualizacao(LocalTime.now().toString())
                        .build())
                .endereco(realizaTratamentoEnderecoDoNovoColaborador(colaboradorDto.getEndereco()))
                .telefone(colaboradorDto.getTelefone() == null
                        ? null
                        : TelefoneEntity.builder()
                        .prefixo(colaboradorDto.getTelefone().getPrefixo())
                        .numero(colaboradorDto.getTelefone().getNumero())
                        .tipoTelefone(colaboradorDto.getTelefone().getTipoTelefone())
                        .build())
                .expediente(ExpedienteEntity.builder()
                        .horaEntrada(colaboradorDto.getExpediente().getHoraEntrada())
                        .horaEntradaAlmoco(colaboradorDto.getExpediente().getHoraEntradaAlmoco())
                        .horaSaidaAlmoco(colaboradorDto.getExpediente().getHoraSaidaAlmoco())
                        .horaSaida(colaboradorDto.getExpediente().getHoraSaida())
                        .build())
                .dispensa(null)
                .parentescos(realizaTratamentoParentescosDoNovoColaborador(colaboradorDto.getParentescos()))
                .empresa(colaboradorLogado.getEmpresa())
                .build();
        log.debug("Objeto colaboradorEntity criado com sucesso");

        log.debug("Iniciando acesso ao método de implementação da persistência do colaborador...");
        ColaboradorEntity colaboradorPersistido = colaboradorRepositoryImpl.implementaPersistencia(colaboradorEntity);

        log.debug("Colaborador persistido com sucesso. Convertendo colaboradorEntity para colaboradorResponse...");
        ColaboradorResponse colaboradorResponse = converteColaboradorEntityParaColaboradorResponse(colaboradorPersistido);

        log.info("Colaborador criado com sucesso");
        return colaboradorResponse;
    }

    private Set<ModulosEnum> realizaTratamentoDosPrivilegiosDoNovoColaborador(List<ModulosEnum> privilegios) {
        //TODO Elaborar lógica
        return new HashSet<>();
    }

    private List<ParentescoEntity> realizaTratamentoParentescosDoNovoColaborador(List<ParentescoDto> parentescos) {
        //TODO Elaborar lógica
        return new ArrayList<>();
    }

    private EnderecoEntity realizaTratamentoEnderecoDoNovoColaborador(EnderecoDto enderecoDto) {
        if (enderecoDto == null) return null;
        return EnderecoEntity.builder()
                .codigoPostal(enderecoDto.getCodigoPostal())
                .logradouro(enderecoDto.getLogradouro() != null
                        ? enderecoDto.getLogradouro().toUpperCase()
                        : null)
                .numero(enderecoDto.getNumero())
                .bairro(enderecoDto.getBairro() != null
                        ? enderecoDto.getBairro().toUpperCase()
                        : null)
                .cidade(enderecoDto.getCidade() != null
                        ? enderecoDto.getCidade().toUpperCase()
                        : null)
                .complemento(enderecoDto.getComplemento() != null
                        ? enderecoDto.getComplemento().toUpperCase()
                        : null)
                .estado(enderecoDto.getEstado())
                .build();
    }

    public ColaboradorResponse atualizaColaborador(ColaboradorEntity colaboradorLogado, Long id, ColaboradorDto colaboradorDto) {
        log.debug("Método de serviço de atualização de colaborador acessado");

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
                .fotoPerfil(colaboradorDto.getFotoPerfil())
                .nome(colaboradorDto.getNome())
                .dataNascimento(colaboradorDto.getDataNascimento())
                .email(colaboradorDto.getEmail())
                .cpfCnpj(colaboradorDto.getCpfCnpj())
                .ativo(colaboradorDto.getAtivo())
                .salario(colaboradorDto.getSalario())
                .entradaEmpresa(colaboradorDto.getEntradaEmpresa())
                .saidaEmpresa(colaboradorDto.getSaidaEmpresa())
                .contratoContratacao(colaboradorDto.getContratoContratacao())
                .ocupacao(colaboradorDto.getOcupacao())
                .tipoOcupacaoEnum(colaboradorDto.getTipoOcupacaoEnum())
                .modeloContratacaoEnum(colaboradorDto.getModeloContratacaoEnum())
                .modeloTrabalhoEnum(colaboradorDto.getModeloTrabalhoEnum())
                .statusColaboradorEnum(colaboradorDto.getStatusColaboradorEnum())
                .exclusao(colaboradorEncontrado.getExclusao())
                .acessoSistema(AcessoSistemaEntity.builder()
                        .nomeUsuario(colaboradorDto.getAcessoSistema().getNomeUsuario())
                        .senha(colaboradorDto.getAcessoSistema().getSenha())
                        .senhaCriptografada(new BCryptPasswordEncoder().encode(colaboradorDto.getAcessoSistema().getSenha()))
                        .acessoSistemaAtivo(colaboradorDto.getAcessoSistema().getAcessoSistemaAtivo())
                        .privilegios(realizaTratamentoDosPrivilegiosDoColaboradorAtualizado(
                                colaboradorDto.getAcessoSistema().getPrivilegios()))
                        .build())
                .configuracaoPerfil(colaboradorEncontrado.getConfiguracaoPerfil())
                .endereco(realizaTratamentoEnderecoDoColaboradorAtualizado(colaboradorDto.getEndereco(), colaboradorEncontrado))
                .telefone(colaboradorDto.getTelefone() == null
                        ? null
                        : TelefoneEntity.builder()
                        .id(obtemIdTelefoneDoColaboradorAtualizavelSeTiver(colaboradorEncontrado))
                        .prefixo(colaboradorDto.getTelefone().getPrefixo())
                        .numero(colaboradorDto.getTelefone().getNumero())
                        .tipoTelefone(colaboradorDto.getTelefone().getTipoTelefone())
                        .build())
                .expediente(ExpedienteEntity.builder()
                        .horaEntrada(colaboradorDto.getExpediente().getHoraEntrada())
                        .horaEntradaAlmoco(colaboradorDto.getExpediente().getHoraEntradaAlmoco())
                        .horaSaidaAlmoco(colaboradorDto.getExpediente().getHoraSaidaAlmoco())
                        .horaSaida(colaboradorDto.getExpediente().getHoraSaida())
                        .build())
                .dispensa(null)
                .parentescos(realizaTratamentoParentescosDoColaboradorAtualizado(colaboradorDto.getParentescos()))
                .empresa(colaboradorLogado.getEmpresa())
                .build();
        log.debug("Objeto colaborador construído com sucesso");

        log.debug("Iniciando acesso ao método de implementação da persistência do colaborador...");
        ColaboradorEntity colaboradorPersistido = colaboradorRepositoryImpl.implementaPersistencia(novoColaboradorAtualizado);

        log.debug("Colaborador persistido com sucesso. Convertendo colaboradorEntity para colaboradorResponse...");
        ColaboradorResponse colaboradorResponse = converteColaboradorEntityParaColaboradorResponse(colaboradorPersistido);

        log.info("Colaborador criado com sucesso");
        return colaboradorResponse;
    }

    private Set<ModulosEnum> realizaTratamentoDosPrivilegiosDoColaboradorAtualizado(List<ModulosEnum> privilegios) {
        //TODO Elaborar lógica
        return new HashSet<>();
    }

    private List<ParentescoEntity> realizaTratamentoParentescosDoColaboradorAtualizado(List<ParentescoDto> parentescos) {
        //TODO Elaborar lógica
        return new ArrayList<>();
    }

    private EnderecoEntity realizaTratamentoEnderecoDoColaboradorAtualizado(EnderecoDto enderecoDto,
                                                                            ColaboradorEntity colaboradorEntity) {
        if (enderecoDto == null) return null;
        return EnderecoEntity.builder()
                .id(obtemIdEnderecoDoColaboradorAtualizavelSeTiver(colaboradorEntity))
                .codigoPostal(enderecoDto.getCodigoPostal())
                .logradouro(enderecoDto.getLogradouro() != null
                        ? enderecoDto.getLogradouro().toUpperCase()
                        : null)
                .numero(enderecoDto.getNumero())
                .bairro(enderecoDto.getBairro() != null
                        ? enderecoDto.getBairro().toUpperCase()
                        : null)
                .cidade(enderecoDto.getCidade() != null
                        ? enderecoDto.getCidade().toUpperCase()
                        : null)
                .complemento(enderecoDto.getComplemento() != null
                        ? enderecoDto.getComplemento().toUpperCase()
                        : null)
                .estado(enderecoDto.getEstado())
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
        colaboradorEncontrado.getExclusao().setDataExclusao(LocalDate.now().toString());
        colaboradorEncontrado.getExclusao().setHoraExclusao(LocalTime.now().toString());
        colaboradorEncontrado.getExclusao().setExcluido(true);
        colaboradorEncontrado.getExclusao().setResponsavelExclusao(colaboradorLogado);
        log.debug("Objeto ExclusaoColaborador do colaborador de id {} setado com sucesso", id);

        log.debug("Persistindo colaborador excluído no banco de dados...");
        ColaboradorEntity colaboradorExcluido = colaboradorRepositoryImpl.implementaPersistencia(colaboradorEncontrado);

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
            colaborador.getExclusao().setDataExclusao(LocalDate.now().toString());
            colaborador.getExclusao().setHoraExclusao(LocalTime.now().toString());
            colaborador.getExclusao().setExcluido(true);
            colaborador.getExclusao().setResponsavelExclusao(colaboradorLogado);
            log.debug("Objeto ExclusaoColaborador do colaborador de id {} setado com sucesso", colaborador.getId());
        }

        log.debug("Verificando se listagem de colaboradores encontrados está preenchida...");
        if (!colaboradoresEncontrados.isEmpty()) {
            log.debug("Persistindo colaborador excluído no banco de dados...");
            colaboradorRepositoryImpl.implementaPersistenciaEmMassa(colaboradoresEncontrados);
        } else throw new InvalidRequestException("Nenhum colaborador foi encontrado para remoção");

        log.info("colaboradores excluídos com sucesso");
    }

    public void validaSeColaboradorEstaExcluido(ColaboradorEntity colaborador, String mensagemCasoEstejaExcluido) {
        log.debug("Método de validação de colaborador excluído acessado");
        if (Boolean.TRUE.equals(colaborador.getExclusao().getExcluido())) {
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
        ColaboradorResponse colaboradorResponse = converteColaboradorEntityParaColaboradorResponse(colaborador);
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
                converteListaDeColaboradoresEntityParaColaboradoresResponse(colaboradorPage);

        log.debug("Conversão de tipagem realizada com sucesso");

        log.info("A busca paginada de colaboradores foi realizada com sucesso");
        return colaboradorPageResponse;
    }

    public ColaboradorPageResponse converteListaDeColaboradoresEntityParaColaboradoresResponse(Page<ColaboradorEntity> colaboradoresEntity) {
        log.debug("Método de conversão de colaboradores do tipo Entity para colaboradores do tipo Response acessado");

        log.debug("Criando lista vazia de objetos do tipo ColaboradorResponse...");
        List<ColaboradorResponse> colaboradoresResponse = new ArrayList<>();

        log.debug("Iniciando iteração da lista de ColaboradorEntity obtida na busca para conversão para objetos do tipo " +
                "ColaboradorResponse...");
        for (ColaboradorEntity colaborador : colaboradoresEntity.getContent()) {
            ColaboradorResponse colaboradorResponse = ColaboradorResponse.builder()
                    .id(colaborador.getId())
                    .dataCadastro(colaborador.getDataCadastro())
                    .horaCadastro(colaborador.getHoraCadastro())
                    .matricula(colaborador.getMatricula())
                    .fotoPerfil(colaborador.getFotoPerfil())
                    .nome(colaborador.getNome())
                    .dataNascimento(colaborador.getDataNascimento())
                    .email(colaborador.getEmail())
                    .cpfCnpj(colaborador.getCpfCnpj())
                    .ativo(colaborador.getAtivo())
                    .salario(colaborador.getSalario())
                    .entradaEmpresa(colaborador.getEntradaEmpresa())
                    .saidaEmpresa(colaborador.getSaidaEmpresa())
                    .contratoContratacao(colaborador.getContratoContratacao())
                    .ocupacao(colaborador.getOcupacao())
                    .tipoOcupacaoEnum(colaborador.getTipoOcupacaoEnum())
                    .modeloContratacaoEnum(colaborador.getModeloContratacaoEnum())
                    .modeloTrabalhoEnum(colaborador.getModeloTrabalhoEnum())
                    .statusColaboradorEnum(colaborador.getStatusColaboradorEnum())
                    .acessoSistema(AcessoSistemaResponse.builder()
                            .nomeUsuario(colaborador.getAcessoSistema().getNomeUsuario())
                            .acessoSistemaAtivo(colaborador.getAcessoSistema().getAcessoSistemaAtivo())
                            .privilegios(colaborador.getAcessoSistema().getPrivilegios())
                            .build())
                    .configuracaoPerfil(colaborador.getConfiguracaoPerfil())
                    .exclusao(ExclusaoColaboradorResponse.builder()
                            .dataExclusao(colaborador.getExclusao().getDataExclusao())
                            .horaExclusao(colaborador.getExclusao().getHoraExclusao())
                            .excluido(colaborador.getExclusao().getExcluido())
                            .build())
                    .endereco(colaborador.getEndereco())
                    .telefone(colaborador.getTelefone())
                    .expediente(colaborador.getExpediente())
                    .dispensa(colaborador.getDispensa())
                    .pontos(colaborador.getPontos())
                    .historicoFerias(colaborador.getHistoricoFerias())
                    .advertencias(colaborador.getAdvertencias())
                    .parentescos(colaborador.getParentescos())
                    .build();
            colaboradoresResponse.add(colaboradorResponse);
        }
        log.debug("Iteração finalizada com sucesso. Listagem de objetos do tipo ColaboradorResponse preenchida");

        log.debug("Iniciando criação de objeto do tipo ColaboradorPageResponse, que possui todas as informações referentes " +
                "ao conteúdo da página e à paginação...");
        colaboradoresEntity.getPageable();
        ColaboradorPageResponse colaboradorPageResponse = ColaboradorPageResponse.builder()
                .content(colaboradoresResponse)
                .empty(colaboradoresEntity.isEmpty())
                .first(colaboradoresEntity.isFirst())
                .last(colaboradoresEntity.isLast())
                .number(colaboradoresEntity.getNumber())
                .numberOfElements(colaboradoresEntity.getNumberOfElements())
                .offset(colaboradoresEntity.getPageable().getOffset())
                .pageNumber(colaboradoresEntity.getPageable().getPageNumber())
                .pageSize(colaboradoresEntity.getPageable().getPageSize())
                .paged(colaboradoresEntity.getPageable().isPaged())
                .unpaged(colaboradoresEntity.getPageable().isUnpaged())
                .size(colaboradoresEntity.getSize())
                .totalElements(colaboradoresEntity.getTotalElements())
                .totalPages(colaboradoresEntity.getTotalPages())
                .build();

        log.debug("Objeto do tipo ColaboradorPageResponse criado com sucesso. Retornando objeto...");
        return colaboradorPageResponse;
    }

    public ColaboradorResponse converteColaboradorEntityParaColaboradorResponse(ColaboradorEntity colaborador) {
        log.debug("Método de conversão de objeto do tipo ColaboradorEntity para objeto do tipo ColaboradorResponse acessado");

        log.debug("Iniciando construção do objeto ColaboradorResponse...");
        ColaboradorResponse colaboradorResponse = ColaboradorResponse.builder()
                .id(colaborador.getId())
                .dataCadastro(colaborador.getDataCadastro())
                .horaCadastro(colaborador.getHoraCadastro())
                .nome(colaborador.getNome())
                .exclusao(ExclusaoColaboradorResponse.builder()
                        .dataExclusao(colaborador.getExclusao().getDataExclusao())
                        .horaExclusao(colaborador.getExclusao().getHoraExclusao())
                        .excluido(colaborador.getExclusao().getExcluido())
                        .build())
                .endereco(colaborador.getEndereco())
                .telefone(colaborador.getTelefone())
                .build();
        log.debug("Objeto ColaboradorResponse buildado com sucesso. Retornando...");
        return colaboradorResponse;
    }

    public Long geraMatriculaUnica() {

        //TODO Loggear

        long min = 1000000L;
        long max = 9999999L;
        while (true) {
            long matriculaAleatoria =
                    (long) (ConversorDeDados.RANDOM.nextFloat() * (max - min) + min);

            if (Boolean.FALSE.equals(colaboradorRepository.existsByMatricula(matriculaAleatoria))) return matriculaAleatoria;
        }
    }
}
