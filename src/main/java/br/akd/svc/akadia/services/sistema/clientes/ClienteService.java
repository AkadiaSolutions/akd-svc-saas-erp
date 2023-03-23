package br.akd.svc.akadia.services.sistema.clientes;

import br.akd.svc.akadia.models.dto.sistema.clientes.ClienteDto;
import br.akd.svc.akadia.models.dto.sistema.clientes.responses.ClienteResponse;
import br.akd.svc.akadia.models.dto.sistema.clientes.responses.ExclusaoClienteResponse;
import br.akd.svc.akadia.models.dto.sistema.clientes.responses.MetaDadosCliente;
import br.akd.svc.akadia.models.entities.global.EnderecoEntity;
import br.akd.svc.akadia.models.entities.global.TelefoneEntity;
import br.akd.svc.akadia.models.entities.sistema.clientes.ClienteEntity;
import br.akd.svc.akadia.models.entities.sistema.clientes.ExclusaoClienteEntity;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.ColaboradorEntity;
import br.akd.svc.akadia.repositories.sistema.clientes.ClienteRepository;
import br.akd.svc.akadia.repositories.sistema.clientes.impl.ClienteRepositoryImpl;
import br.akd.svc.akadia.services.exceptions.InvalidRequestException;
import br.akd.svc.akadia.services.exceptions.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

@Slf4j
@Service
public class ClienteService {

    @Autowired
    ClienteRepositoryImpl clienteRepositoryImpl;

    @Autowired
    ClienteRepository clienteRepository;

    private static final String RETORNANDO_CLIENTES = "Retornando lista de clientes encontrados...";

    public void validaSeChavesUnicasJaExistemParaNovoCliente(ClienteDto clienteDto, ColaboradorEntity colaboradorLogado) {
        log.debug("Método de validação de chave única de cliente acessado...");
        if (clienteDto.getCpfCnpj() != null)
            validaSeCpfCnpjJaExiste(clienteDto.getCpfCnpj(), colaboradorLogado.getEmpresa().getId());
        if (clienteDto.getEmail() != null)
            validaSeEmailJaExiste(clienteDto.getEmail(), colaboradorLogado.getEmpresa().getId());
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
        if (clienteDto.getEmail() != null && clienteEntity.getEmail() == null
                || clienteDto.getEmail() != null
                && !clienteEntity.getEmail().equalsIgnoreCase(clienteDto.getEmail())) {
            validaSeEmailJaExiste(clienteDto.getEmail(), colaboradorLogado.getEmpresa().getId());
        }
        if (clienteDto.getInscricaoEstadual() != null && clienteEntity.getInscricaoEstadual() == null
                || clienteDto.getInscricaoEstadual() != null
                && !clienteEntity.getInscricaoEstadual().equalsIgnoreCase(clienteDto.getInscricaoEstadual()))
            validaSeInscricaoEstadualJaExiste(clienteDto.getInscricaoEstadual(), colaboradorLogado.getEmpresa().getId());
    }

    public void validaSeCpfCnpjJaExiste(String cpfCnpj, Long idEmpresa) {
        log.debug("Método de validação de chave única de CPF/CNPJ acessado");
        if (clienteRepositoryImpl.implementaBuscaPorCpfCnpjIdentico(cpfCnpj, idEmpresa).isPresent()) {
            log.warn("O cpf/cnpj informado já existe");
            throw new InvalidRequestException("O cpf/cnpj informado já existe");
        }
        log.debug("Validação de chave única de CPF/CNPJ... OK");
    }

    public void validaSeEmailJaExiste(String email, Long idEmpresa) {
        log.debug("Método de validação de chave única de EMAIL acessado");
        if (clienteRepositoryImpl.implementaBuscaPorEmailIdentico(email, idEmpresa).isPresent()) {
            log.warn("O e-mail informado já existe");
            throw new InvalidRequestException("O e-mail informado já existe");
        }
        log.debug("Validação de chave única de E-MAIL... OK");
    }

    public void validaSeInscricaoEstadualJaExiste(String inscricaoEstadual, Long idEmpresa) {
        log.debug("Método de validação de chave única de EMAIL acessado");
        if (clienteRepositoryImpl.implementaBuscaPorInscricaoEstadualIdentica(inscricaoEstadual, idEmpresa).isPresent()) {
            log.warn("A inscrição estadual informada já existe");
            throw new InvalidRequestException("A inscrição estadual informada já existe");
        }
        log.debug("Validação de chave única de INSCRIÇÃO ESTADUAL... OK");
    }

    public ClienteEntity criaNovoCliente(ColaboradorEntity colaboradorLogado, ClienteDto clienteDto) {

        log.debug("Método de serviço de criação de novo cliente acessado");

        log.debug("Iniciando acesso ao método de validação de chave única...");
        validaSeChavesUnicasJaExistemParaNovoCliente(clienteDto, colaboradorLogado);

        log.debug("Iniciando criação do objeto ClienteEntity...");
        ClienteEntity clienteEntity = ClienteEntity.builder()
                .dataCadastro(LocalDate.now().toString())
                .horaCadastro(LocalTime.now().toString())
                .dataNascimento(clienteDto.getDataNascimento())
                .nome(clienteDto.getNome())
                .cpfCnpj(clienteDto.getCpfCnpj())
                .inscricaoEstadual(clienteDto.getInscricaoEstadual())
                .qtdOrdensRealizadas(0)
                .giroTotal(0.0)
                .statusCliente(clienteDto.getStatusCliente())
                .email(clienteDto.getEmail())
                .exclusaoCliente(ExclusaoClienteEntity.builder()
                        .excluido(false)
                        .build())
                .endereco(clienteDto.getEndereco() == null || clienteDto.getEndereco().getLogradouro().isEmpty()
                        ? null
                        : EnderecoEntity.builder()
                        .logradouro(clienteDto.getEndereco().getLogradouro())
                        .numero(clienteDto.getEndereco().getNumero())
                        .bairro(clienteDto.getEndereco().getBairro())
                        .cidade(clienteDto.getEndereco().getCidade())
                        .complemento(clienteDto.getEndereco().getComplemento())
                        .estadoEnum(clienteDto.getEndereco().getEstadoEnum())
                        .build())
                .telefone(clienteDto.getTelefone() == null
                        ? null
                        : TelefoneEntity.builder()
                        .prefixo(clienteDto.getTelefone().getPrefixo())
                        .numero(clienteDto.getTelefone().getNumero())
                        .tipoTelefoneEnum(clienteDto.getTelefone().getTipoTelefoneEnum())
                        .build())
                .colaboradorResponsavel(colaboradorLogado)
                .empresa(colaboradorLogado.getEmpresa())
                .build();
        log.debug("Objeto clienteEntity criado com sucesso");

        log.debug("Iniciando acesso ao método de implementação da persistência do cliente...");
        ClienteEntity clientePersistido = clienteRepositoryImpl.implementaPersistencia(clienteEntity);

        log.info("Cliente persistido com sucesso");
        return clientePersistido;
    }

    public ClienteEntity atualizaCliente(ColaboradorEntity colaboradorLogado, Long id, ClienteDto clienteDto) {
        log.debug("Método de serviço de criação de novo cliente acessado");

        log.debug("Iniciando acesso ao método de implementação de busca pelo cliente por id...");
        ClienteEntity clienteEncontrado = clienteRepositoryImpl.implementaBuscaPorId(id);

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
                .email(clienteDto.getEmail())
                .exclusaoCliente(ExclusaoClienteEntity.builder()
                        .id(clienteEncontrado.getId())
                        .excluido(false)
                        .build())
                .endereco(clienteDto.getEndereco() == null || clienteDto.getEndereco().getLogradouro().isEmpty()
                        ? null
                        : EnderecoEntity.builder()
                        .id(obtemIdEnderecoDoClienteAtualizavelSeTiver(clienteEncontrado))
                        .logradouro(clienteDto.getEndereco().getLogradouro())
                        .numero(clienteDto.getEndereco().getNumero())
                        .bairro(clienteDto.getEndereco().getBairro())
                        .cidade(clienteDto.getEndereco().getCidade())
                        .complemento(clienteDto.getEndereco().getComplemento())
                        .estadoEnum(clienteDto.getEndereco().getEstadoEnum())
                        .build())
                .telefone(clienteDto.getTelefone() == null
                        ? null
                        : TelefoneEntity.builder()
                        .id(obtemIdTelefoneDoClienteAtualizavelSeTiver(clienteEncontrado))
                        .prefixo(clienteDto.getTelefone().getPrefixo())
                        .numero(clienteDto.getTelefone().getNumero())
                        .tipoTelefoneEnum(clienteDto.getTelefone().getTipoTelefoneEnum())
                        .build())
                .colaboradorResponsavel(colaboradorLogado)
                .empresa(colaboradorLogado.getEmpresa())
                .build();
        log.debug("Objeto cliente construído com sucesso");

        log.debug("Iniciando acesso ao método de implementação da persistência do cliente...");
        ClienteEntity clientePersistido = clienteRepositoryImpl.implementaPersistencia(novoClienteAtualizado);

        log.info("Cliente persistido com sucesso");
        return clientePersistido;
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

    public ClienteEntity removeCliente(ColaboradorEntity colaboradorLogado, Long id) {
        log.debug("Método de serviço de remoção de cliente acessado");

        log.debug("Iniciando acesso ao método de implementação de busca pelo cliente por id...");
        ClienteEntity clienteEncontrado = clienteRepositoryImpl.implementaBuscaPorId(id);

        log.debug("Iniciando acesso ao método de validação de exclusão de cliente que já foi excluído...");
        validaSeClienteEstaExcluido(clienteEncontrado,
                "Não é possível remover um cliente que já foi excluído");

        log.debug("Atualizando objeto ExclusaoCliente do cliente com dados referentes à sua exclusão...");
        clienteEncontrado.getExclusaoCliente().setDataExclusao(LocalDate.now().toString());
        clienteEncontrado.getExclusaoCliente().setHoraExclusao(LocalTime.now().toString());
        clienteEncontrado.getExclusaoCliente().setExcluido(true);
        clienteEncontrado.getExclusaoCliente().setResponsavelExclusao(colaboradorLogado);
        log.debug("Objeto ExclusaoCliente do cliente de id {} setado com sucesso", id);

        log.debug("Persistindo cliente excluído no banco de dados...");
        ClienteEntity clienteExcluido = clienteRepositoryImpl.implementaPersistencia(clienteEncontrado);

        log.info("Cliente excluído com sucesso");
        return clienteExcluido;
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

    public MetaDadosCliente obtemMetaDadosDosClientes(List<String> filtrosBusca) {
        Example<ClienteEntity> example = geraObjetoClienteExample(filtrosBusca);
        List<ClienteEntity> clientes = clienteRepository.findAll(example);
        return MetaDadosCliente.builder()
                .totalClientesCadastrados(clientes.size())
                .clienteComMaiorGiro(retornaClienteComMaiorGiro(clientes))
                .clienteComMaisOrdens(retornaClienteComMaisOrdens(clientes))
                .bairroComMaisClientes(retornaBairroComMaisClientes(clientes))
                .build();
    }

    private ClienteEntity retornaClienteComMaiorGiro(List<ClienteEntity> clientes) {
        Optional<ClienteEntity> clienteOptional = clientes.stream()
                .max(Comparator.comparingDouble(ClienteEntity::getGiroTotal));
        if (clienteOptional.isPresent() && clienteOptional.get().getGiroTotal() == 0) return null;
        return clienteOptional.orElse(null);
    }

    private ClienteEntity retornaClienteComMaisOrdens(List<ClienteEntity> clientes) {
        Optional<ClienteEntity> clienteOptional = clientes.stream()
                .max(Comparator.comparingInt(ClienteEntity::getQtdOrdensRealizadas));
        if (clienteOptional.isPresent() && clienteOptional.get().getQtdOrdensRealizadas() == 0) return null;
        return clienteOptional.orElse(null);
    }

    private String retornaBairroComMaisClientes(List<ClienteEntity> clientes) {
        Map<String, Integer> quantidadeDeClientesPorBairro = new HashMap<>();

        String maiorBairro = null;
        Integer maiorValor = 0;

        for (ClienteEntity cliente : clientes) {
            if (cliente.getEndereco() != null && cliente.getEndereco().getBairro() != null) {
                String bairroAtual = cliente.getEndereco().getBairro().toUpperCase();
                if (quantidadeDeClientesPorBairro.containsKey(bairroAtual))
                    quantidadeDeClientesPorBairro.replace(bairroAtual, (quantidadeDeClientesPorBairro.get(bairroAtual) + 1));
                else
                    quantidadeDeClientesPorBairro.put(bairroAtual, 1);
            }
        }

        for (Map.Entry<String, Integer> clientesBairro : quantidadeDeClientesPorBairro.entrySet()) {
            if (clientesBairro.getValue() > maiorValor) {
                maiorBairro = clientesBairro.getKey();
                maiorValor = clientesBairro.getValue();
            }
        }

        return maiorBairro;
    }

    public Page<ClienteResponse> realizaBuscaPaginadaPorClientes(Pageable pageable, List<String> filtrosBusca) {
        Example<ClienteEntity> example = geraObjetoClienteExample(filtrosBusca);
        return converteClientesEntityParaClientesResponse(pageable, clienteRepository.findAll(example, pageable));
    }

    public Example<ClienteEntity> geraObjetoClienteExample(List<String> filtrosBusca) {
        ExampleMatcher customExampleMatcher = ExampleMatcher.matchingAll()
                .withMatcher("nome", ExampleMatcher.GenericPropertyMatchers.startsWith().ignoreCase())
                .withMatcher("cpfCnpj", ExampleMatcher.GenericPropertyMatchers.startsWith())
                .withMatcher("email", ExampleMatcher.GenericPropertyMatchers.startsWith().ignoreCase())
                .withMatcher("dataCadastro", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("endereco.bairro", ExampleMatcher.GenericPropertyMatchers.startsWith().ignoreCase())
                .withIgnoreNullValues()
                .withIgnorePaths("id", "horaCadastro", "dataNascimento",
                        "inscricaoEstadual", "exclusaoCliente", "telefone", "colaboradorResponsavel", "empresa");

        ClienteEntity clienteExample = new ClienteEntity();

        for (String filtro : filtrosBusca) {
            if (filtro.contains("nome=")) clienteExample.setNome(filtro.replace("nome=", ""));
            if (filtro.contains("cpfCnpj=")) clienteExample.setCpfCnpj(filtro.replace("cpfCnpj=", ""));
            if (filtro.contains("email=")) clienteExample.setEmail(filtro.replace("email=", ""));
            if (filtro.contains("data=")) clienteExample.setDataCadastro(filtro.replace("data=", ""));
            if (filtro.contains("mesAno=")) clienteExample.setDataCadastro(filtro.replace("mesAno=", ""));
            if (filtro.contains("bairro=")) {
                EnderecoEntity endereco = new EnderecoEntity();
                endereco.setBairro(filtro.replace("bairro=", ""));
                clienteExample.setEndereco(endereco);
            }
        }
        return Example.of(clienteExample, customExampleMatcher);
    }

    public Page<ClienteResponse> converteClientesEntityParaClientesResponse(Pageable pageable, Page<ClienteEntity> clientesEntity) {
        List<ClienteResponse> clientesResponse = new ArrayList<>();
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

        return new PageImpl<>(clientesResponse, pageable, clientesResponse.size());
    }

    public List<ClienteEntity> obtemClientesPeloNome(ColaboradorEntity colaboradorLogado, String nome) {
        log.debug("Método de serviço de obtenção de lista de colaboradores pelo nome acessado");
        log.debug("Iniciando acesso à implementação do método do repositório de busca por nome...");
        List<ClienteEntity> clientes =
                clienteRepositoryImpl.implementaBuscaPorNomeSemelhante(nome, colaboradorLogado.getEmpresa().getId());

        if (clientes.isEmpty()) {
            log.warn("Nenhum cliente foi encontrado com o nome informado: {}", nome);
            throw new ObjectNotFoundException("Nenhum cliente foi encontrado com o nome informado");
        } else {
            log.info(RETORNANDO_CLIENTES);
            return clientes;
        }
    }

    public List<ClienteEntity> obtemClientesPeloEmail(ColaboradorEntity colaboradorLogado, String email) {
        log.debug("Método de serviço de obtenção de lista de colaboradores pelo email acessado");
        log.debug("Iniciando acesso à implementação do método do repositório de busca por email...");
        List<ClienteEntity> clientes =
                clienteRepositoryImpl.implementaBuscaPorEmailSemelhante(email, colaboradorLogado.getEmpresa().getId());

        if (clientes.isEmpty()) {
            log.warn("Nenhum cliente foi encontrado com o email informado: {}", email);
            throw new ObjectNotFoundException("Nenhum cliente foi encontrado com o email informado");
        } else {
            log.info(RETORNANDO_CLIENTES);
            return clientes;
        }
    }

    public List<ClienteEntity> obtemClientesPeloCpfCnpj(ColaboradorEntity colaboradorLogado, String cpfCnpj) {
        log.debug("Método de serviço de obtenção de lista de colaboradores pelo cpf ou cnpj acessado");
        log.debug("Iniciando acesso à implementação do método do repositório de busca por cpf ou cnpj...");
        List<ClienteEntity> clientes =
                clienteRepositoryImpl.implementaBuscaPorCpfCnpjSemelhante(cpfCnpj, colaboradorLogado.getEmpresa().getId());

        if (clientes.isEmpty()) {
            log.warn("Nenhum cliente foi encontrado com o cpf ou cnpj informado: {}", cpfCnpj);
            throw new ObjectNotFoundException("Nenhum cliente foi encontrado com o cpf ou cnpj informado");
        } else {
            log.info(RETORNANDO_CLIENTES);
            return clientes;
        }
    }

    public List<ClienteEntity> obtemClientesPelaInscricaoEstadual(ColaboradorEntity colaboradorLogado, String inscricaoEstadual) {
        log.debug("Método de serviço de obtenção de lista de colaboradores pela inscrição estadual acessado");
        log.debug("Iniciando acesso à implementação do método do repositório de busca por inscrição estadual...");
        List<ClienteEntity> clientes =
                clienteRepositoryImpl.implementaBuscaPorInscricaoEstadualSemelhante(inscricaoEstadual, colaboradorLogado.getEmpresa().getId());

        if (clientes.isEmpty()) {
            log.warn("Nenhum cliente foi encontrado com a inscrição estadual informada: {}", inscricaoEstadual);
            throw new ObjectNotFoundException("Nenhum cliente foi encontrado com a inscrição estadual informada");
        } else {
            log.info(RETORNANDO_CLIENTES);
            return clientes;
        }
    }

    public List<ClienteEntity> obtemClientesPeloTelefone(ColaboradorEntity colaboradorLogado, String telefone) {
        log.debug("Método de serviço de obtenção de lista de colaboradores pelo telefone acessado");
        log.debug("Iniciando acesso à implementação do método do repositório de busca por telefone...");
        List<ClienteEntity> clientes =
                clienteRepositoryImpl.implementaBuscaPorTelefoneSemelhante(telefone, colaboradorLogado.getEmpresa().getId());

        if (clientes.isEmpty()) {
            log.warn("Nenhum cliente foi encontrado com o telefone informado: {}", telefone);
            throw new ObjectNotFoundException("Nenhum cliente foi encontrado com o telefone informado");
        } else {
            log.info(RETORNANDO_CLIENTES);
            return clientes;
        }
    }

    public List<ClienteEntity> obtemClientesPorRangeDeData(ColaboradorEntity colaboradorLogado, String dataInicio, String dataFim) {
        log.debug("Método de serviço de obtenção de lista de colaboradores por range de data acessado");

        log.debug("Iniciando acesso ao método de validação das datas recebidas...");
        realizaValidacaoDasDatasRecebidas(dataInicio, dataFim);

        log.debug("Iniciando acesso à implementação do método do repositório de busca por período...");
        List<ClienteEntity> clientes =
                clienteRepositoryImpl.implementaBuscaPorPeriodo(dataInicio, dataFim, colaboradorLogado.getEmpresa().getId());

        if (clientes.isEmpty()) {
            log.warn("Nenhum cliente foi encontrado no range de datas informado informado: {} à {}", dataInicio, dataFim);
            throw new ObjectNotFoundException("Nenhum cliente foi encontrado na data indicada");
        } else {
            log.info(RETORNANDO_CLIENTES);
            return clientes;
        }
    }

    public void realizaValidacaoDasDatasRecebidas(String dataInicio, String dataFim) {

        log.debug("Método responsável por realizar as validações da data de início e data final recebidos acessado");

        LocalDate dataInicioLocalDate;
        LocalDate dataFimLocalDate;

        try {
            log.debug("Tentando converter a data de início da busca para o tipo LocalDate...");
            dataInicioLocalDate = LocalDate.parse(dataInicio);
        } catch (Exception e) {
            log.warn("A data de início recebida é inválida e não pode ser convertida: {}", dataInicio);
            throw new InvalidRequestException("A data de início não possui um padrão válido");
        }

        try {
            log.debug("Tentando converter a data final da busca para o tipo LocalDate...");
            dataFimLocalDate = LocalDate.parse(dataFim);
        } catch (Exception e) {
            log.warn("A data final recebida é inválida e não pode ser convertida: {}", dataFim);
            throw new InvalidRequestException("A data final não possui um padrão válido");
        }

        if (dataInicioLocalDate.isAfter(dataFimLocalDate)) {
            log.warn("A data de início recebida ({}) não pode ser posterior à data final recebida ({})", dataInicio, dataFim);
            throw new InvalidRequestException("A data de início deve ser anterior ou igual à data final");
        }

    }

    public List<ClienteEntity> obtemClientesPorMes(ColaboradorEntity colaboradorLogado, String mes, String ano) {
        log.debug("Método de serviço de obtenção de lista de colaboradores por range de data acessado");

        log.debug("Iniciando acesso ao método de validação dos dados recebidos...");
        realizaValidacaoDoMesAno(mes, ano);

        log.debug("Convertendo valores recebidos para valores inteiros...");
        int mesParaInteiro = Integer.parseInt(mes);
        int anoParaInteiro = Integer.parseInt(ano);

        log.debug("Setando data de início da busca...");
        LocalDate dataInicio = LocalDate.of(anoParaInteiro, mesParaInteiro, 1);
        log.debug("Data de início setada: {}", dataInicio);

        log.debug("Setando último dia do mês da busca...");
        int ultimoDiaDoMes = LocalDate.now()
                .withMonth(mesParaInteiro)
                .withYear(anoParaInteiro)
                .with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
        log.debug("Último dia do mês da busca setado: {}", ultimoDiaDoMes);

        log.debug("Setando data final da busca...");
        LocalDate dataFim = LocalDate.of(anoParaInteiro, mesParaInteiro, ultimoDiaDoMes);
        log.debug("Data final setada: {}", dataFim);

        log.debug("Iniciando acesso à implementação do método do repositório de busca por período...");
        List<ClienteEntity> clientes =
                clienteRepositoryImpl.implementaBuscaPorPeriodo(dataInicio.toString(),
                        dataFim.toString(),
                        colaboradorLogado.getEmpresa().getId());

        if (clientes.isEmpty()) {
            log.warn("Nenhum cliente foi encontrado no range de datas informado informado: {} à {}", dataInicio, dataFim);
            throw new ObjectNotFoundException("Nenhum cliente foi encontrado no mês e ano indicados");
        } else {
            log.info(RETORNANDO_CLIENTES);
            return clientes;
        }
    }

    public void realizaValidacaoDoMesAno(String mes, String ano) {

        log.debug("Método responsável por realizar as validações do mês e ano recebidos acessado");

        int mesConvertidoParaInteiro;
        int anoConvertidoParaInteiro;

        try {
            log.debug("Tentando converter o mês da busca para o tipo inteiro...");
            mesConvertidoParaInteiro = Integer.parseInt(mes);
            log.debug("Mês convertido para inteiro com sucesso: {}", mesConvertidoParaInteiro);
        } catch (Exception e) {
            log.warn("O mês recebido é inválido e não pode ser convertido: {}", mes);
            throw new InvalidRequestException("O mês não possui um padrão válido");
        }

        try {
            log.debug("Tentando converter o ano da busca para o tipo inteiro...");
            anoConvertidoParaInteiro = Integer.parseInt(ano);
            log.debug("Ano convertido para inteiro com sucesso: {}", anoConvertidoParaInteiro);
        } catch (Exception e) {
            log.warn("O ano recebido é inválido e não pode ser convertido: {}", ano);
            throw new InvalidRequestException("O ano não possui um padrão válido");
        }

        if (mesConvertidoParaInteiro < 1 || mesConvertidoParaInteiro > 12)
            throw new InvalidRequestException("O mês deve possuir um valor entre 1 e 12");

        if (anoConvertidoParaInteiro < 2020 || anoConvertidoParaInteiro > 2099)
            throw new InvalidRequestException("O ano deve possuir um valor entre 2020 e 2099");

    }

}
