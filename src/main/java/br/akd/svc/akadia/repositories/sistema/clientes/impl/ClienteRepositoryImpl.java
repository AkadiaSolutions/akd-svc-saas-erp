package br.akd.svc.akadia.repositories.sistema.clientes.impl;

import br.akd.svc.akadia.models.entities.sistema.clientes.ClienteEntity;
import br.akd.svc.akadia.repositories.sistema.clientes.ClienteRepository;
import br.akd.svc.akadia.services.exceptions.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    public Optional<ClienteEntity> implementaBuscaPorCpfCnpjIdentico(String cpfCnpj, Long id) {
        log.debug("Método que implementa busca de cliente por CPF/CNPJ acessado. CPF/CNPJ: {}", cpfCnpj);
        return repository.buscaPorCpfCnpjIdenticoNaEmpresaDaSessaoAtual(cpfCnpj, id);
    }

    public Optional<ClienteEntity> implementaBuscaPorEmailIdentico(String email, Long id) {
        log.debug("Método que implementa busca de cliente por E-MAIL acessado. E-MAIL: {}", email);
        return repository.buscaPorEmailIdenticoNaEmpresaDaSessaoAtual(email, id);
    }

    public Optional<ClienteEntity> implementaBuscaPorInscricaoEstadualIdentica(String inscricaoEstadual, Long id) {
        log.debug("Método que implementa busca de cliente por Inscrição estadual acessado. IE: {}", inscricaoEstadual);
        return repository.buscaPorInscricaoEstadualIdenticaNaEmpresaDaSessaoAtual(inscricaoEstadual, id);
    }

    public List<ClienteEntity> implementaBuscaPorNomeSemelhante(String nome, Long id) {
        log.debug("Método que implementa busca de clientes por nome acessado. Nome: {}", nome);
        return repository.buscaPorNomeSemelhanteNaEmpresaDaSessaoAtual(nome.toUpperCase().trim(), id);
    }

    public List<ClienteEntity> implementaBuscaPorEmailSemelhante(String email, Long id) {
        log.debug("Método que implementa busca de clientes por email acessado. E-mail: {}", email);
        return repository.buscaPorEmailSemelhanteNaEmpresaDaSessaoAtual(email.toUpperCase().trim(), id);
    }

    public List<ClienteEntity> implementaBuscaPorCpfCnpjSemelhante(String cpfCnpj, Long id) {
        log.debug("Método que implementa busca de clientes por cpf ou cnpj acessado. CPF/CNPJ: {}", cpfCnpj);
        return repository.buscaPorCpfCnpjSemelhanteNaEmpresaDaSessaoAtual(cpfCnpj.trim(), id);
    }

    public List<ClienteEntity> implementaBuscaPorInscricaoEstadualSemelhante(String inscricaoEstadual, Long id) {
        log.debug("Método que implementa busca de clientes por inscrição estadual acessado. IE: {}", inscricaoEstadual);
        return repository.buscaPorInscricaoEstadualSemelhanteNaEmpresaDaSessaoAtual(inscricaoEstadual.trim(), id);
    }

    public List<ClienteEntity> implementaBuscaPorTelefoneSemelhante(String telefone, Long id) {
        log.debug("Método que implementa busca de clientes por telefone acessado. TEL: {}", telefone);
        return repository.buscaPorTelefoneSemelhanteNaEmpresaDaSessaoAtual(telefone.trim(), id);
    }

    public List<ClienteEntity> implementaBuscaPorPeriodo(String dataInicio, String dataFim, Long id) {
        log.debug("Método que implementa busca de clientes por período acessado. Período: {} à {}", dataInicio, dataFim);
        return repository.buscaPorPeriodoNaEmpresaDaSessaoAtual(dataInicio, dataFim, id);
    }

}