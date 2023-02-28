package br.akd.svc.akadia.services.sistema.clientes;

import br.akd.svc.akadia.models.dto.sistema.clientes.ClienteDto;
import br.akd.svc.akadia.models.entities.global.EnderecoEntity;
import br.akd.svc.akadia.models.entities.global.TelefoneEntity;
import br.akd.svc.akadia.models.entities.sistema.clientes.ClienteEntity;
import br.akd.svc.akadia.repositories.sistema.clientes.impl.ClienteRepositoryImpl;
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

    public ClienteEntity criaNovoCliente(ClienteDto clienteDto) {

        log.debug("Método de serviço de criação de novo cliente acessado");

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

}
