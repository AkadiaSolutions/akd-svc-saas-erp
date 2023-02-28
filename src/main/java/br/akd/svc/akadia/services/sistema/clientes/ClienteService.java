package br.akd.svc.akadia.services.sistema.clientes;

import br.akd.svc.akadia.models.dto.sistema.clientes.ClienteDto;
import br.akd.svc.akadia.models.entities.global.EnderecoEntity;
import br.akd.svc.akadia.models.entities.global.TelefoneEntity;
import br.akd.svc.akadia.models.entities.sistema.clientes.ClienteEntity;
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

    public void validaSeChavesUnicasJaExistemParaNovoCliente(ClienteDto clienteDto) {
        log.debug("Método de validação de chave única de cliente acessado...");
        // TODO DEPENDÊNCIA: LÓGICA DE AUTENTICAÇÃO. ID DA EMPRESA MOCKADO NAS VALIDAÇÕES. NECESSÁRIO CRIAR MÉTODO
        //  PARA OBTENÇÃO DO ID DA EMPRESA
        if (clienteDto.getCpfCnpj() != null) validaSeCpfCnpjJaExiste(clienteDto.getCpfCnpj(), 1L);
        if (clienteDto.getEmail() != null) validaSeEmailJaExiste(clienteDto.getEmail(), 1L);
        if (clienteDto.getInscricaoEstadual() != null)
            validaSeInscricaoEstadualJaExiste(clienteDto.getInscricaoEstadual(), 1L);
    }

    public void validaSeChavesUnicasJaExistemParaClienteAtualizado(ClienteDto clienteDto,
                                                                   ClienteEntity clienteEntity) {
        log.debug("Método de validação de chave única para atualização de cliente acessado...");
        // TODO DEPENDÊNCIA: LÓGICA DE AUTENTICAÇÃO. ID DA EMPRESA MOCKADO NAS VALIDAÇÕES. NECESSÁRIO CRIAR MÉTODO
        //  PARA OBTENÇÃO DO ID DA EMPRESA
        if (clienteDto.getCpfCnpj() != null && !clienteEntity.getCpfCnpj().equals(clienteDto.getCpfCnpj()))
            validaSeCpfCnpjJaExiste(clienteDto.getCpfCnpj(), 1L);
        if (clienteDto.getEmail() != null && !clienteEntity.getEmail().equalsIgnoreCase(clienteDto.getEmail()))
            validaSeEmailJaExiste(clienteDto.getEmail(), 1L);
        if (clienteDto.getInscricaoEstadual() != null && !clienteEntity.getInscricaoEstadual().equalsIgnoreCase(clienteDto.getInscricaoEstadual()))
            validaSeInscricaoEstadualJaExiste(clienteDto.getInscricaoEstadual(), 1L);
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

    public ClienteEntity criaNovoCliente(ClienteDto clienteDto) {

        log.debug("Método de serviço de criação de novo cliente acessado");

        log.debug("Iniciando acesso ao método de validação de chave única...");
        validaSeChavesUnicasJaExistemParaNovoCliente(clienteDto);

        log.debug("Iniciando criação do objeto ClienteEntity...");
        ClienteEntity clienteEntity = ClienteEntity.builder()
                .dataCadastro(LocalDate.now().toString())
                .horaCadastro(LocalTime.now().toString())
                .dataNascimento(clienteDto.getDataNascimento())
                .nome(clienteDto.getNome())
                .cpfCnpj(clienteDto.getCpfCnpj())
                .inscricaoEstadual(clienteDto.getInscricaoEstadual())
                .email(clienteDto.getEmail())
                .endereco(clienteDto.getEndereco().getLogradouro().isEmpty()
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
                .colaboradorResponsavel(null) //TODO DEPENDENCIA: LÓGICA DE AUTENTICAÇÃO
                .empresa(null) //TODO DEPENDENCIA: LÓGICA DE AUTENTICAÇÃO
                .build();
        log.debug("Objeto clienteEntity criado com sucesso");

        log.debug("Iniciando acesso ao método de implementação da persistência do cliente...");
        ClienteEntity clientePersistido = clienteRepositoryImpl.implementaPersistencia(clienteEntity);

        log.info("Cliente persistido com sucesso");
        return clientePersistido;
    }

    public ClienteEntity atualizaCliente(Long id, ClienteDto clienteDto) {
        log.debug("Método de serviço de criação de novo cliente acessado");

        log.debug("Iniciando acesso ao método de implementação de busca pelo cliente por id...");
        ClienteEntity clienteEncontrado = clienteRepositoryImpl.implementaBuscaPorId(id);

        log.debug("Iniciando acesso ao método de validação de chave única...");
        validaSeChavesUnicasJaExistemParaClienteAtualizado(clienteDto, clienteEncontrado);

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
                .colaboradorResponsavel(null) //TODO DEPENDENCIA: LÓGICA DE AUTENTICAÇÃO
                .empresa(null) //TODO DEPENDENCIA: LÓGICA DE AUTENTICAÇÃO
                .build();
        log.debug("Objeto cliente construído com sucesso");

        log.debug("Iniciando acesso ao método de implementação da persistência do cliente...");
        ClienteEntity clientePersistido = clienteRepositoryImpl.implementaPersistencia(novoClienteAtualizado);

        log.info("Cliente persistido com sucesso");
        return clientePersistido;
    }

    public Long obtemIdTelefoneDoClienteAtualizavelSeTiver(ClienteEntity clienteEncontrado) {
        if (clienteEncontrado.getTelefone() != null) return clienteEncontrado.getTelefone().getId();
        else return null;
    }

    public Long obtemIdEnderecoDoClienteAtualizavelSeTiver(ClienteEntity clienteEncontrado) {
        if (clienteEncontrado.getEndereco() != null) return clienteEncontrado.getEndereco().getId();
        else return null;
    }

}
