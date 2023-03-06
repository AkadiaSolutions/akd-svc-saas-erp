package br.akd.svc.akadia.services.sistema.clientes;

import br.akd.svc.akadia.models.dto.sistema.clientes.ClienteDto;
import br.akd.svc.akadia.models.entities.global.EnderecoEntity;
import br.akd.svc.akadia.models.entities.global.TelefoneEntity;
import br.akd.svc.akadia.models.entities.sistema.clientes.ClienteEntity;
import br.akd.svc.akadia.models.entities.sistema.clientes.ExclusaoClienteEntity;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.ColaboradorEntity;
import br.akd.svc.akadia.repositories.sistema.clientes.impl.ClienteRepositoryImpl;
import br.akd.svc.akadia.services.exceptions.InvalidRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Slf4j
@Service
public class ClienteService {

    @Autowired
    ClienteRepositoryImpl clienteRepositoryImpl;

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
        if (clienteDto.getCpfCnpj() != null && !clienteEntity.getCpfCnpj().equals(clienteDto.getCpfCnpj()))
            validaSeCpfCnpjJaExiste(clienteDto.getCpfCnpj(), colaboradorLogado.getEmpresa().getId());
        if (clienteDto.getEmail() != null && !clienteEntity.getEmail().equalsIgnoreCase(clienteDto.getEmail()))
            validaSeEmailJaExiste(clienteDto.getEmail(), colaboradorLogado.getEmpresa().getId());
        if (clienteDto.getInscricaoEstadual() != null && !clienteEntity.getInscricaoEstadual().equalsIgnoreCase(clienteDto.getInscricaoEstadual()))
            validaSeInscricaoEstadualJaExiste(clienteDto.getInscricaoEstadual(), colaboradorLogado.getEmpresa().getId());
    }

    public void validaSeCpfCnpjJaExiste(String cpfCnpj, Long idEmpresa) {
        log.debug("Método de validação de chave única de CPF/CNPJ acessado");
        if (clienteRepositoryImpl.implementaBuscaPorCpfCnpj(cpfCnpj, idEmpresa).isPresent()) {
            log.warn("O cpf/cnpj informado já existe");
            throw new InvalidRequestException("O cpf/cnpj informado já existe");
        }
        log.debug("Validação de chave única de CPF/CNPJ... OK");
    }

    public void validaSeEmailJaExiste(String email, Long idEmpresa) {
        log.debug("Método de validação de chave única de EMAIL acessado");
        if (clienteRepositoryImpl.implementaBuscaPorEmail(email, idEmpresa).isPresent()) {
            log.warn("O e-mail informado já existe");
            throw new InvalidRequestException("O e-mail informado já existe");
        }
        log.debug("Validação de chave única de E-MAIL... OK");
    }

    public void validaSeInscricaoEstadualJaExiste(String inscricaoEstadual, Long idEmpresa) {
        log.debug("Método de validação de chave única de EMAIL acessado");
        if (clienteRepositoryImpl.implementaBuscaPorInscricaoEstadual(inscricaoEstadual, idEmpresa).isPresent()) {
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
                .email(clienteDto.getEmail())
                .exclusaoCliente(ExclusaoClienteEntity.builder()
                        .excluido(false)
                        .build())
                .endereco(clienteDto.getEndereco().getLogradouro().isEmpty()
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
        }
        else {
            log.debug("O cliente não possui nenhum telefone cadastrado. Retornando null...");
            return null;
        }
    }

    public Long obtemIdEnderecoDoClienteAtualizavelSeTiver(ClienteEntity clienteEncontrado) {
        log.debug("Método de obtenção do ID do endereço do cliente se o cliente tiver um acessado");
        if (clienteEncontrado.getEndereco() != null) {
            log.debug("O cliente possui endereço cadastrado. Seu id é: {}", clienteEncontrado.getEndereco().getId());
            return clienteEncontrado.getEndereco().getId();
        }
        else {
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

}
