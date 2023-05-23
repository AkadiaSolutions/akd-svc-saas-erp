package br.akd.svc.akadia.services.sistema.clientes;

import br.akd.svc.akadia.models.dto.global.EnderecoDto;
import br.akd.svc.akadia.models.dto.sistema.clientes.ClienteDto;
import br.akd.svc.akadia.models.dto.sistema.clientes.responses.ClientePageResponse;
import br.akd.svc.akadia.models.dto.sistema.clientes.responses.ClienteResponse;
import br.akd.svc.akadia.models.dto.sistema.clientes.responses.ExclusaoClienteResponse;
import br.akd.svc.akadia.models.entities.global.EnderecoEntity;
import br.akd.svc.akadia.models.entities.global.TelefoneEntity;
import br.akd.svc.akadia.models.entities.sistema.clientes.ClienteEntity;
import br.akd.svc.akadia.models.entities.sistema.clientes.ExclusaoClienteEntity;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.ColaboradorEntity;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.ModulosEnum;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.TipoAcaoEnum;
import br.akd.svc.akadia.repositories.sistema.clientes.ClienteRepository;
import br.akd.svc.akadia.repositories.sistema.clientes.impl.ClienteRepositoryImpl;
import br.akd.svc.akadia.services.exceptions.InvalidRequestException;
import br.akd.svc.akadia.services.sistema.colaboradores.AcaoService;
import br.akd.svc.akadia.utils.Constantes;
import br.akd.svc.akadia.utils.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ClienteService {

    @Autowired
    AcaoService acaoService;

    @Autowired
    ClienteRepositoryImpl clienteRepositoryImpl;

    @Autowired
    ClienteRepository clienteRepository;

    String BUSCA_CLIENTE_POR_ID = "Iniciando acesso ao método de implementação de busca pelo cliente por id...";

    public void validaSeChavesUnicasJaExistemParaNovoCliente(ClienteDto clienteDto, ColaboradorEntity colaboradorLogado) {
        log.debug("Método de validação de chave única de cliente acessado...");
        if (clienteDto.getCpfCnpj() != null)
            validaSeCpfCnpjJaExiste(clienteDto.getCpfCnpj(), colaboradorLogado.getEmpresa().getId());
        if (clienteDto.getInscricaoEstadual() != null)
            validaSeInscricaoEstadualJaExiste(clienteDto.getInscricaoEstadual(), colaboradorLogado.getEmpresa().getId());
    }

    public void validaSeChavesUnicasJaExistemParaClienteAtualizado(ClienteDto clienteDto,
                                                                   ClienteEntity clienteEntity,
                                                                   ColaboradorEntity colaboradorLogado) {
        log.debug("Método de validação de chave única para atualização de cliente acessado...");
        if (clienteDto.getCpfCnpj() != null && clienteEntity.getCpfCnpj() == null
                || clienteDto.getCpfCnpj() != null
                && !clienteEntity.getCpfCnpj().equals(clienteDto.getCpfCnpj()))
            validaSeCpfCnpjJaExiste(clienteDto.getCpfCnpj(), colaboradorLogado.getEmpresa().getId());
        if (clienteDto.getInscricaoEstadual() != null && clienteEntity.getInscricaoEstadual() == null
                || clienteDto.getInscricaoEstadual() != null
                && !clienteEntity.getInscricaoEstadual().equalsIgnoreCase(clienteDto.getInscricaoEstadual()))
            validaSeInscricaoEstadualJaExiste(clienteDto.getInscricaoEstadual(), colaboradorLogado.getEmpresa().getId());
    }

    public void validaSeCpfCnpjJaExiste(String cpfCnpj, Long idEmpresa) {
        log.debug("Método de validação de chave única de CPF/CNPJ acessado");
        if (clienteRepositoryImpl.implementaBuscaPorCpfCnpjIdentico(cpfCnpj, idEmpresa).isPresent()) {
            String mensagemErro = cpfCnpj.length() == 11 ? "O CPF informado já existe" : "O CNPJ informado já existe";
            log.warn(mensagemErro + ": {}", cpfCnpj);
            throw new InvalidRequestException(mensagemErro);
        }
        log.debug("Validação de chave única de CPF/CNPJ... OK");
    }

    public void validaSeInscricaoEstadualJaExiste(String inscricaoEstadual, Long idEmpresa) {
        log.debug("Método de validação de chave única de EMAIL acessado");
        if (clienteRepositoryImpl.implementaBuscaPorInscricaoEstadualIdentica(inscricaoEstadual, idEmpresa).isPresent()) {
            log.warn("A inscrição estadual informada já existe");
            throw new InvalidRequestException("A inscrição estadual informada já existe");
        }
        log.debug("Validação de chave única de INSCRIÇÃO ESTADUAL... OK");
    }

    public ClienteResponse criaNovoCliente(ColaboradorEntity colaboradorLogado, ClienteDto clienteDto) {

        log.debug("Método de serviço de criação de novo cliente acessado");

        log.debug("Iniciando acesso ao método de verificação se colaborador logado possui nível de permissão " +
                "suficiente para realizar alterações");
        SecurityUtil.verificaSePodeRealizarAlteracoes(colaboradorLogado.getAcessoSistema());

        log.debug("Iniciando acesso ao método de validação de chave única...");
        validaSeChavesUnicasJaExistemParaNovoCliente(clienteDto, colaboradorLogado);

        log.debug("Iniciando criação do objeto ClienteEntity...");
        ClienteEntity clienteEntity = ClienteEntity.builder()
                .dataCadastro(LocalDate.now().toString())
                .horaCadastro(LocalTime.now().toString())
                .dataNascimento(clienteDto.getDataNascimento())
                .nome(clienteDto.getNome() != null ? clienteDto.getNome().toUpperCase() : null)
                .cpfCnpj(clienteDto.getCpfCnpj())
                .inscricaoEstadual(clienteDto.getInscricaoEstadual())
                .qtdOrdensRealizadas(0)
                .giroTotal(0.0)
                .statusCliente(clienteDto.getStatusCliente())
                .tipoPessoa(clienteDto.getTipoPessoa())
                .email(clienteDto.getEmail() != null ? clienteDto.getEmail().toLowerCase() : null)
                .exclusaoCliente(ExclusaoClienteEntity.builder()
                        .excluido(false)
                        .build())
                .endereco(realizaTratamentoEnderecoDoNovoCliente(clienteDto.getEndereco()))
                .telefone(clienteDto.getTelefone() == null
                        ? null
                        : TelefoneEntity.builder()
                        .prefixo(clienteDto.getTelefone().getPrefixo())
                        .numero(clienteDto.getTelefone().getNumero())
                        .tipoTelefone(clienteDto.getTelefone().getTipoTelefone())
                        .build())
                .colaboradorResponsavel(colaboradorLogado)
                .empresa(colaboradorLogado.getEmpresa())
                .build();
        log.debug("Objeto clienteEntity criado com sucesso");

        log.debug("Iniciando acesso ao método de implementação da persistência do cliente...");
        ClienteEntity clientePersistido = clienteRepositoryImpl.implementaPersistencia(clienteEntity);

        log.debug(Constantes.INICIANDO_SALVAMENTO_HISTORICO_COLABORADOR);
        acaoService.salvaHistoricoColaborador(colaboradorLogado, clientePersistido.getId(),
                ModulosEnum.CLIENTES, TipoAcaoEnum.CRIACAO, null);

        log.debug("Cliente persistido com sucesso. Convertendo clienteEntity para clienteResponse...");
        ClienteResponse clienteResponse = converteClienteEntityParaClienteResponse(clientePersistido);

        log.info("Cliente criado com sucesso");
        return clienteResponse;
    }

    private EnderecoEntity realizaTratamentoEnderecoDoNovoCliente(EnderecoDto enderecoDto) {
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

    public ClienteResponse atualizaCliente(ColaboradorEntity colaboradorLogado, Long id, ClienteDto clienteDto) {
        log.debug("Método de serviço de atualização de cliente acessado");

        log.debug("Iniciando acesso ao método de verificação se colaborador logado possui nível de permissão " +
                "suficiente para realizar alterações");
        SecurityUtil.verificaSePodeRealizarAlteracoes(colaboradorLogado.getAcessoSistema());

        log.debug(BUSCA_CLIENTE_POR_ID);
        ClienteEntity clienteEncontrado = clienteRepositoryImpl.implementaBuscaPorId(id, colaboradorLogado.getEmpresa().getId());

        log.debug("Iniciando acesso ao método de validação de alteração de dados de cliente excluído...");
        validaSeClienteEstaExcluido(clienteEncontrado, "Não é possível atualizar um cliente excluído");

        log.debug("Iniciando acesso ao método de validação de chave única...");
        validaSeChavesUnicasJaExistemParaClienteAtualizado(clienteDto, clienteEncontrado, colaboradorLogado);

        log.debug("Iniciando criação do objeto ClienteEntity...");
        ClienteEntity novoClienteAtualizado = ClienteEntity.builder()
                .id(clienteEncontrado.getId())
                .dataCadastro(clienteEncontrado.getDataCadastro())
                .horaCadastro(clienteEncontrado.getHoraCadastro())
                .dataNascimento(clienteDto.getDataNascimento())
                .nome(clienteDto.getNome())
                .cpfCnpj(clienteDto.getCpfCnpj())
                .inscricaoEstadual(clienteDto.getInscricaoEstadual())
                .qtdOrdensRealizadas(clienteEncontrado.getQtdOrdensRealizadas())
                .giroTotal(clienteEncontrado.getGiroTotal())
                .statusCliente(clienteDto.getStatusCliente())
                .tipoPessoa(clienteDto.getTipoPessoa())
                .email(clienteDto.getEmail())
                .exclusaoCliente(clienteEncontrado.getExclusaoCliente())
                .endereco(realizaTratamentoEnderecoDoClienteAtualizado(clienteDto.getEndereco(), clienteEncontrado))
                .telefone(clienteDto.getTelefone() == null
                        ? null
                        : TelefoneEntity.builder()
                        .id(obtemIdTelefoneDoClienteAtualizavelSeTiver(clienteEncontrado))
                        .prefixo(clienteDto.getTelefone().getPrefixo())
                        .numero(clienteDto.getTelefone().getNumero())
                        .tipoTelefone(clienteDto.getTelefone().getTipoTelefone())
                        .build())
                .colaboradorResponsavel(colaboradorLogado)
                .empresa(colaboradorLogado.getEmpresa())
                .build();
        log.debug("Objeto cliente construído com sucesso");

        log.debug("Iniciando acesso ao método de implementação da persistência do cliente...");
        ClienteEntity clientePersistido = clienteRepositoryImpl.implementaPersistencia(novoClienteAtualizado);

        log.debug(Constantes.INICIANDO_SALVAMENTO_HISTORICO_COLABORADOR);
        acaoService.salvaHistoricoColaborador(colaboradorLogado, clientePersistido.getId(),
                ModulosEnum.CLIENTES, TipoAcaoEnum.ALTERACAO, null);

        log.debug("Cliente persistido com sucesso. Convertendo clienteEntity para clienteResponse...");
        ClienteResponse clienteResponse = converteClienteEntityParaClienteResponse(clientePersistido);

        log.info("Cliente criado com sucesso");
        return clienteResponse;
    }

    private EnderecoEntity realizaTratamentoEnderecoDoClienteAtualizado(EnderecoDto enderecoDto,
                                                                        ClienteEntity clienteEntity) {
        if (enderecoDto == null) return null;
        return EnderecoEntity.builder()
                .id(obtemIdEnderecoDoClienteAtualizavelSeTiver(clienteEntity))
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

    public Long obtemIdTelefoneDoClienteAtualizavelSeTiver(ClienteEntity clienteEncontrado) {
        log.debug("Método de obtenção do ID do telefone do cliente se o cliente tiver um acessado");
        if (clienteEncontrado.getTelefone() != null) {
            log.debug("O cliente possui telefone cadastrado. Seu id é: {}", clienteEncontrado.getTelefone().getId());
            return clienteEncontrado.getTelefone().getId();
        } else {
            log.debug("O cliente não possui nenhum telefone cadastrado. Retornando null...");
            return null;
        }
    }

    public Long obtemIdEnderecoDoClienteAtualizavelSeTiver(ClienteEntity clienteEncontrado) {
        log.debug("Método de obtenção do ID do endereço do cliente se o cliente tiver um acessado");
        if (clienteEncontrado.getEndereco() != null) {
            log.debug("O cliente possui endereço cadastrado. Seu id é: {}", clienteEncontrado.getEndereco().getId());
            return clienteEncontrado.getEndereco().getId();
        } else {
            log.debug("O cliente não possui nenhum endereço cadastrado. Retornando null...");
            return null;
        }
    }

    public ClienteResponse removeCliente(ColaboradorEntity colaboradorLogado, Long id) {
        log.debug("Método de serviço de remoção de cliente acessado");

        log.debug(BUSCA_CLIENTE_POR_ID);
        ClienteEntity clienteEncontrado = clienteRepositoryImpl.implementaBuscaPorId(id, colaboradorLogado.getEmpresa().getId());

        log.debug("Iniciando acesso ao método de validação de exclusão de cliente que já foi excluído...");
        validaSeClienteEstaExcluido(clienteEncontrado,
                "O cliente selecionado já foi excluído");

        log.debug("Atualizando objeto ExclusaoCliente do cliente com dados referentes à sua exclusão...");
        clienteEncontrado.getExclusaoCliente().setDataExclusao(LocalDate.now().toString());
        clienteEncontrado.getExclusaoCliente().setHoraExclusao(LocalTime.now().toString());
        clienteEncontrado.getExclusaoCliente().setExcluido(true);
        clienteEncontrado.getExclusaoCliente().setResponsavelExclusao(colaboradorLogado);
        log.debug("Objeto ExclusaoCliente do cliente de id {} setado com sucesso", id);

        log.debug("Persistindo cliente excluído no banco de dados...");
        ClienteEntity clienteExcluido = clienteRepositoryImpl.implementaPersistencia(clienteEncontrado);

        log.debug(Constantes.INICIANDO_SALVAMENTO_HISTORICO_COLABORADOR);
        acaoService.salvaHistoricoColaborador(colaboradorLogado, null,
                ModulosEnum.CLIENTES, TipoAcaoEnum.REMOCAO, null);

        log.info("Cliente excluído com sucesso");
        return ClienteResponse.builder()
                .id(clienteExcluido.getId())
                .dataCadastro(clienteExcluido.getDataCadastro())
                .horaCadastro(clienteExcluido.getHoraCadastro())
                .dataNascimento(clienteExcluido.getDataNascimento())
                .nome(clienteExcluido.getNome())
                .cpfCnpj(clienteExcluido.getCpfCnpj())
                .inscricaoEstadual(clienteExcluido.getInscricaoEstadual())
                .email(clienteExcluido.getEmail())
                .statusCliente(clienteExcluido.getStatusCliente())
                .tipoPessoa(clienteExcluido.getTipoPessoa())
                .qtdOrdensRealizadas(clienteExcluido.getQtdOrdensRealizadas())
                .giroTotal(clienteExcluido.getGiroTotal())
                .exclusaoCliente(ExclusaoClienteResponse.builder()
                        .dataExclusao(clienteExcluido.getExclusaoCliente().getDataExclusao())
                        .horaExclusao(clienteExcluido.getExclusaoCliente().getHoraExclusao())
                        .excluido(clienteExcluido.getExclusaoCliente().getExcluido())
                        .build())
                .endereco(clienteExcluido.getEndereco())
                .telefone(clienteExcluido.getTelefone())
                .nomeColaboradorResponsavel(clienteExcluido.getColaboradorResponsavel().getNome())
                .build();
    }

    public void removeClientesEmMassa(ColaboradorEntity colaboradorLogado, List<Long> ids) {
        log.debug("Método de serviço de remoção de cliente acessado");

        List<ClienteEntity> clientesEncontrados = new ArrayList<>();

        for (Long id : ids) {
            log.debug(BUSCA_CLIENTE_POR_ID);
            ClienteEntity clienteEncontrado = clienteRepositoryImpl.implementaBuscaPorId(id, colaboradorLogado.getEmpresa().getId());
            clientesEncontrados.add(clienteEncontrado);
        }

        log.debug("Iniciando acesso ao método de validação de exclusão de cliente que já foi excluído...");
        for (ClienteEntity cliente : clientesEncontrados) {
            validaSeClienteEstaExcluido(cliente,
                    "O cliente selecionado já foi excluído");
            log.debug("Atualizando objeto ExclusaoCliente do cliente com dados referentes à sua exclusão...");
            cliente.getExclusaoCliente().setDataExclusao(LocalDate.now().toString());
            cliente.getExclusaoCliente().setHoraExclusao(LocalTime.now().toString());
            cliente.getExclusaoCliente().setExcluido(true);
            cliente.getExclusaoCliente().setResponsavelExclusao(colaboradorLogado);
            log.debug("Objeto ExclusaoCliente do cliente de id {} setado com sucesso", cliente.getId());
        }

        log.debug("Verificando se listagem de clientes encontrados está preenchida...");
        if (!clientesEncontrados.isEmpty()) {
            log.debug("Persistindo cliente excluído no banco de dados...");
            clienteRepositoryImpl.implementaPersistenciaEmMassa(clientesEncontrados);

            log.debug(Constantes.INICIANDO_SALVAMENTO_HISTORICO_COLABORADOR);
            acaoService.salvaHistoricoColaborador(colaboradorLogado, null,
                    ModulosEnum.CLIENTES, TipoAcaoEnum.REMOCAO_EM_MASSA, clientesEncontrados.size() + " Itens removidos");
        } else throw new InvalidRequestException("Nenhum cliente foi encontrado para remoção");

        log.info("Clientes excluídos com sucesso");
    }

    public void validaSeClienteEstaExcluido(ClienteEntity cliente, String mensagemCasoEstejaExcluido) {
        log.debug("Método de validação de cliente excluído acessado");
        if (Boolean.TRUE.equals(cliente.getExclusaoCliente().getExcluido())) {
            log.debug("Cliente de id {}: Validação de cliente já excluído falhou. Não é possível realizar operações " +
                    "em um cliente que já foi excluído.", cliente.getId());
            throw new InvalidRequestException(mensagemCasoEstejaExcluido);
        }
        log.debug("O cliente de id {} não está excluído", cliente.getId());
    }

    public ClienteResponse realizaBuscaDeClientePorId(ColaboradorEntity colaboradorLogado, Long id) {
        log.debug("Método de serviço de obtenção de cliente por id. ID recebido: {}", id);

        log.debug("Acessando repositório de busca de cliente por ID...");
        ClienteEntity cliente = clienteRepositoryImpl.implementaBuscaPorId(id, colaboradorLogado.getEmpresa().getId());

        log.debug("Busca de clientes por id realizada com sucesso. Acessando método de conversão dos objeto do tipo " +
                "Entity para objeto do tipo Response...");
        ClienteResponse clienteResponse = converteClienteEntityParaClienteResponse(cliente);
        log.debug("Conversão de tipagem realizada com sucesso");

        log.info("A busca de cliente por id foi realizada com sucesso");
        return clienteResponse;
    }

    public ClientePageResponse realizaBuscaPaginadaPorClientes(ColaboradorEntity colaboradorLogado,
                                                               Pageable pageable,
                                                               String campoBusca) {
        log.debug("Método de serviço de obtenção paginada de clientes acessado. Campo de busca: {}",
                campoBusca != null ? campoBusca : "Nulo");

        log.debug("Acessando repositório de busca de clientes");
        Page<ClienteEntity> clientePage = campoBusca != null && !campoBusca.isEmpty()
                ? clienteRepository.buscaPorClientesTypeAhead(pageable, campoBusca, colaboradorLogado.getEmpresa().getId())
                : clienteRepository.buscaPorClientes(pageable, colaboradorLogado.getEmpresa().getId());

        log.debug("Busca de clientes por paginação realizada com sucesso. Acessando método de conversão dos objetos do tipo " +
                "Entity para objetos do tipo Response...");
        ClientePageResponse clientePageResponse = converteListaDeClientesEntityParaClientesResponse(clientePage);
        log.debug("Conversão de tipagem realizada com sucesso");

        log.info("A busca paginada de clientes foi realizada com sucesso");
        return clientePageResponse;
    }

    public ClientePageResponse converteListaDeClientesEntityParaClientesResponse(Page<ClienteEntity> clientesEntity) {
        log.debug("Método de conversão de clientes do tipo Entity para clientes do tipo Response acessado");

        log.debug("Criando lista vazia de objetos do tipo ClienteResponse...");
        List<ClienteResponse> clientesResponse = new ArrayList<>();

        log.debug("Iniciando iteração da lista de ClienteEntity obtida na busca para conversão para objetos do tipo " +
                "ClienteResponse...");
        for (ClienteEntity cliente : clientesEntity.getContent()) {
            ClienteResponse clienteResponse = ClienteResponse.builder()
                    .id(cliente.getId())
                    .dataCadastro(cliente.getDataCadastro())
                    .horaCadastro(cliente.getHoraCadastro())
                    .dataNascimento(cliente.getDataNascimento())
                    .nome(cliente.getNome())
                    .cpfCnpj(cliente.getCpfCnpj())
                    .inscricaoEstadual(cliente.getInscricaoEstadual())
                    .email(cliente.getEmail())
                    .statusCliente(cliente.getStatusCliente())
                    .tipoPessoa(cliente.getTipoPessoa())
                    .qtdOrdensRealizadas(cliente.getQtdOrdensRealizadas())
                    .giroTotal(cliente.getGiroTotal())
                    .exclusaoCliente(ExclusaoClienteResponse.builder()
                            .dataExclusao(cliente.getExclusaoCliente().getDataExclusao())
                            .horaExclusao(cliente.getExclusaoCliente().getHoraExclusao())
                            .excluido(cliente.getExclusaoCliente().getExcluido())
                            .build())
                    .endereco(cliente.getEndereco())
                    .telefone(cliente.getTelefone())
                    .nomeColaboradorResponsavel(cliente.getColaboradorResponsavel().getNome())
                    .build();
            clientesResponse.add(clienteResponse);
        }
        log.debug("Iteração finalizada com sucesso. Listagem de objetos do tipo ClienteResponse preenchida");

        log.debug("Iniciando criação de objeto do tipo ClientePageResponse, que possui todas as informações referentes " +
                "ao conteúdo da página e à paginação...");
        clientesEntity.getPageable();
        ClientePageResponse clientePageResponse = ClientePageResponse.builder()
                .content(clientesResponse)
                .empty(clientesEntity.isEmpty())
                .first(clientesEntity.isFirst())
                .last(clientesEntity.isLast())
                .number(clientesEntity.getNumber())
                .numberOfElements(clientesEntity.getNumberOfElements())
                .offset(clientesEntity.getPageable().getOffset())
                .pageNumber(clientesEntity.getPageable().getPageNumber())
                .pageSize(clientesEntity.getPageable().getPageSize())
                .paged(clientesEntity.getPageable().isPaged())
                .unpaged(clientesEntity.getPageable().isUnpaged())
                .size(clientesEntity.getSize())
                .totalElements(clientesEntity.getTotalElements())
                .totalPages(clientesEntity.getTotalPages())
                .build();

        log.debug("Objeto do tipo ClientePageResponse criado com sucesso. Retornando objeto...");
        return clientePageResponse;
    }

    public ClienteResponse converteClienteEntityParaClienteResponse(ClienteEntity cliente) {
        log.debug("Método de conversão de objeto do tipo ClienteEntity para objeto do tipo ClienteResponse acessado");

        log.debug("Iniciando construção do objeto ClienteResponse...");
        ClienteResponse clienteResponse = ClienteResponse.builder()
                .id(cliente.getId())
                .dataCadastro(cliente.getDataCadastro())
                .horaCadastro(cliente.getHoraCadastro())
                .dataNascimento(cliente.getDataNascimento())
                .nome(cliente.getNome())
                .cpfCnpj(cliente.getCpfCnpj())
                .inscricaoEstadual(cliente.getInscricaoEstadual())
                .email(cliente.getEmail())
                .statusCliente(cliente.getStatusCliente())
                .tipoPessoa(cliente.getTipoPessoa())
                .qtdOrdensRealizadas(cliente.getQtdOrdensRealizadas())
                .giroTotal(cliente.getGiroTotal())
                .exclusaoCliente(ExclusaoClienteResponse.builder()
                        .dataExclusao(cliente.getExclusaoCliente().getDataExclusao())
                        .horaExclusao(cliente.getExclusaoCliente().getHoraExclusao())
                        .excluido(cliente.getExclusaoCliente().getExcluido())
                        .build())
                .endereco(cliente.getEndereco())
                .telefone(cliente.getTelefone())
                .nomeColaboradorResponsavel(cliente.getColaboradorResponsavel().getNome())
                .build();
        log.debug("Objeto ClienteResponse buildado com sucesso. Retornando...");
        return clienteResponse;
    }

}