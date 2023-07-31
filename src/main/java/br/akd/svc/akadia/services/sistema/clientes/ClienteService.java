package br.akd.svc.akadia.services.sistema.clientes;

import br.akd.svc.akadia.models.dto.global.EnderecoDto;
import br.akd.svc.akadia.models.dto.sistema.clientes.requests.ClienteRequest;
import br.akd.svc.akadia.models.dto.sistema.clientes.responses.ClientePageResponse;
import br.akd.svc.akadia.models.dto.sistema.clientes.responses.ClienteResponse;
import br.akd.svc.akadia.models.entities.global.EnderecoEntity;
import br.akd.svc.akadia.models.entities.global.TelefoneEntity;
import br.akd.svc.akadia.models.entities.sistema.clientes.ClienteEntity;
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
    ClienteValidationService clienteValidationService;

    @Autowired
    ClienteTypeConverter clienteTypeConverter;

    @Autowired
    ClienteRepositoryImpl clienteRepositoryImpl;

    @Autowired
    ClienteRepository clienteRepository;

    String BUSCA_CLIENTE_POR_ID = "Iniciando acesso ao método de implementação de busca pelo cliente por id...";

    public ClienteResponse criaNovoCliente(ColaboradorEntity colaboradorLogado, ClienteRequest clienteRequest) {

        log.debug("Método de serviço de criação de novo cliente acessado");

        log.debug(Constantes.VERIFICANDO_SE_COLABORADOR_PODE_ALTERAR_DADOS);
        SecurityUtil.verificaSePodeRealizarAlteracoes(colaboradorLogado.getAcessoSistema());

        log.debug("Iniciando acesso ao método de validação de chave única...");
        clienteValidationService.validaSeChavesUnicasJaExistemParaNovoCliente(clienteRequest, colaboradorLogado);

        log.debug("Iniciando criação do objeto ClienteEntity...");
        ClienteEntity clienteEntity = ClienteEntity.builder()
                .dataCadastro(LocalDate.now().toString())
                .horaCadastro(LocalTime.now().toString())
                .dataNascimento(clienteRequest.getDataNascimento())
                .nome(clienteRequest.getNome() != null ? clienteRequest.getNome().toUpperCase() : null)
                .cpfCnpj(clienteRequest.getCpfCnpj())
                .inscricaoEstadual(clienteRequest.getInscricaoEstadual())
                .qtdOrdensRealizadas(0)
                .giroTotal(0.0)
                .statusCliente(clienteRequest.getStatusCliente())
                .tipoPessoa(clienteRequest.getTipoPessoa())
                .email(clienteRequest.getEmail() != null ? clienteRequest.getEmail().toLowerCase() : null)
                .exclusao(null)
                .endereco(realizaTratamentoEnderecoDoNovoCliente(clienteRequest.getEndereco()))
                .telefone(clienteRequest.getTelefone() == null
                        ? null
                        : TelefoneEntity.builder()
                        .prefixo(clienteRequest.getTelefone().getPrefixo())
                        .numero(clienteRequest.getTelefone().getNumero())
                        .tipoTelefone(clienteRequest.getTelefone().getTipoTelefone())
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
        ClienteResponse clienteResponse = clienteTypeConverter.converteClienteEntityParaClienteResponse(clientePersistido);

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

    public ClienteResponse atualizaCliente(ColaboradorEntity colaboradorLogado, Long id, ClienteRequest clienteRequest) {
        log.debug("Método de serviço de atualização de cliente acessado");

        log.debug(Constantes.VERIFICANDO_SE_COLABORADOR_PODE_ALTERAR_DADOS);
        SecurityUtil.verificaSePodeRealizarAlteracoes(colaboradorLogado.getAcessoSistema());

        log.debug(BUSCA_CLIENTE_POR_ID);
        ClienteEntity clienteEncontrado = clienteRepositoryImpl.implementaBuscaPorId(id, colaboradorLogado.getEmpresa().getId());

        log.debug("Iniciando acesso ao método de validação de alteração de dados de cliente excluído...");
        clienteValidationService.validaSeClienteEstaExcluido(clienteEncontrado, "Não é possível atualizar um cliente excluído");

        log.debug("Iniciando acesso ao método de validação de chave única...");
        clienteValidationService.validaSeChavesUnicasJaExistemParaClienteAtualizado(clienteRequest, clienteEncontrado, colaboradorLogado);

        log.debug("Iniciando criação do objeto ClienteEntity...");
        ClienteEntity novoClienteAtualizado = ClienteEntity.builder()
                .id(clienteEncontrado.getId())
                .dataCadastro(clienteEncontrado.getDataCadastro())
                .horaCadastro(clienteEncontrado.getHoraCadastro())
                .dataNascimento(clienteRequest.getDataNascimento())
                .nome(clienteRequest.getNome())
                .cpfCnpj(clienteRequest.getCpfCnpj())
                .inscricaoEstadual(clienteRequest.getInscricaoEstadual())
                .qtdOrdensRealizadas(clienteEncontrado.getQtdOrdensRealizadas())
                .giroTotal(clienteEncontrado.getGiroTotal())
                .statusCliente(clienteRequest.getStatusCliente())
                .tipoPessoa(clienteRequest.getTipoPessoa())
                .email(clienteRequest.getEmail())
                .exclusao(clienteEncontrado.getExclusao())
                .endereco(realizaTratamentoEnderecoDoClienteAtualizado(clienteRequest.getEndereco(), clienteEncontrado))
                .telefone(clienteRequest.getTelefone() == null
                        ? null
                        : TelefoneEntity.builder()
                        .id(obtemIdTelefoneDoClienteAtualizavelSeTiver(clienteEncontrado))
                        .prefixo(clienteRequest.getTelefone().getPrefixo())
                        .numero(clienteRequest.getTelefone().getNumero())
                        .tipoTelefone(clienteRequest.getTelefone().getTipoTelefone())
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
        ClienteResponse clienteResponse = clienteTypeConverter.converteClienteEntityParaClienteResponse(clientePersistido);

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
        clienteValidationService.validaSeClienteEstaExcluido(clienteEncontrado,
                "O cliente selecionado já foi excluído");

        log.debug("Atualizando objeto Exclusao do cliente com dados referentes à sua exclusão...");
        clienteEncontrado.getExclusao().setDataExclusao(LocalDate.now().toString());
        clienteEncontrado.getExclusao().setHoraExclusao(LocalTime.now().toString());
        clienteEncontrado.getExclusao().setResponsavelExclusao(colaboradorLogado);
        log.debug("Objeto Exclusao do cliente de id {} setado com sucesso", id);

        log.debug("Persistindo cliente excluído no banco de dados...");
        ClienteEntity clienteExcluido = clienteRepositoryImpl.implementaPersistencia(clienteEncontrado);

        log.debug(Constantes.INICIANDO_SALVAMENTO_HISTORICO_COLABORADOR);
        acaoService.salvaHistoricoColaborador(colaboradorLogado, null,
                ModulosEnum.CLIENTES, TipoAcaoEnum.REMOCAO, null);

        log.info("Cliente excluído com sucesso");
        return clienteTypeConverter.converteClienteEntityParaClienteResponse(clienteExcluido);
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
            clienteValidationService.validaSeClienteEstaExcluido(cliente,
                    "O cliente selecionado já foi excluído");
            log.debug("Atualizando objeto Exclusao do cliente com dados referentes à sua exclusão...");
            cliente.getExclusao().setDataExclusao(LocalDate.now().toString());
            cliente.getExclusao().setHoraExclusao(LocalTime.now().toString());
            cliente.getExclusao().setResponsavelExclusao(colaboradorLogado);
            log.debug("Objeto Exclusao do cliente de id {} setado com sucesso", cliente.getId());
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

    public ClienteResponse realizaBuscaDeClientePorId(ColaboradorEntity colaboradorLogado, Long id) {
        log.debug("Método de serviço de obtenção de cliente por id. ID recebido: {}", id);

        log.debug("Acessando repositório de busca de cliente por ID...");
        ClienteEntity cliente = clienteRepositoryImpl.implementaBuscaPorId(id, colaboradorLogado.getEmpresa().getId());

        log.debug("Busca de clientes por id realizada com sucesso. Acessando método de conversão dos objeto do tipo " +
                "Entity para objeto do tipo Response...");
        ClienteResponse clienteResponse = clienteTypeConverter.converteClienteEntityParaClienteResponse(cliente);
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
        ClientePageResponse clientePageResponse = clienteTypeConverter.converteListaDeClientesEntityParaClientesResponse(clientePage);
        log.debug("Conversão de tipagem realizada com sucesso");

        log.info("A busca paginada de clientes foi realizada com sucesso");
        return clientePageResponse;
    }

}