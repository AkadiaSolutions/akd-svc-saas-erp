package br.akd.svc.akadia.repositories.sistema.clientes.impl;

import br.akd.svc.akadia.models.entities.sistema.clientes.ClienteEntity;
import br.akd.svc.akadia.repositories.sistema.clientes.ClienteRepository;
import br.akd.svc.akadia.services.exceptions.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class ClienteRepositoryImpl {

    @Autowired
    ClienteRepository repository;

    @Transactional
    public ClienteEntity implementaPersistencia(ClienteEntity cliente) {
        log.debug("Método de serviço que implementa persistência do cliente");
        return repository.save(cliente);
    }

    public ClienteEntity implementaBuscaPorId(Long id) {
        log.debug("Método que implementa busca de cliente por id acessado. Id: {}", id);

        Optional<ClienteEntity> clienteOptional = repository.findById(id);

        ClienteEntity clienteEntity;
        if (clienteOptional.isPresent()) {
            clienteEntity = clienteOptional.get();
            log.debug("Cliente encontrado: {}", clienteEntity);
        } else {
            log.warn("Nenhum cliente foi encontrado com o id {}", id);
            throw new ObjectNotFoundException("Nenhum cliente foi encontrado com o id informado");
        }

        log.debug("Retornando o cliente encontrado...");
        return clienteEntity;
    }

    public Optional<ClienteEntity> implementaBuscaPorCpfCnpj(String cpfCnpj, Long id) {
        log.debug("Método que implementa busca de cliente por CPF/CNPJ acessado. CPF/CNPJ: {}", cpfCnpj);
        return repository.buscaPorCpfCnpjNaEmpresaDaSessaoAtual(cpfCnpj, id);
    }

    public Optional<ClienteEntity> implementaBuscaPorEmail(String email, Long id) {
        log.debug("Método que implementa busca de cliente por E-MAIL acessado. E-MAIL: {}", email);
        return repository.buscaPorCpfCnpjNaEmpresaDaSessaoAtual(email, id);
    }

    public Optional<ClienteEntity> implementaBuscaPorInscricaoEstadual(String inscricaoEstadual, Long id) {
        log.debug("Método que implementa busca de cliente por Inscrição estadual acessado. IE: {}", inscricaoEstadual);
        return repository.buscaPorCpfCnpjNaEmpresaDaSessaoAtual(inscricaoEstadual, id);
    }

}