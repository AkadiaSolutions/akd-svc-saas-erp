package br.akd.svc.akadia.repositories.site.impl;

import br.akd.svc.akadia.models.entities.site.ClienteSistemaEntity;
import br.akd.svc.akadia.repositories.site.ClienteSistemaRepository;
import br.akd.svc.akadia.services.exceptions.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ClienteSistemaRepositoryImpl {

    @Autowired
    ClienteSistemaRepository clienteSistemaRepository;

    public List<ClienteSistemaEntity> buscaTodosClientes() {
        return clienteSistemaRepository.findAll();
    }

    @Transactional
    public ClienteSistemaEntity implementaPersistencia(ClienteSistemaEntity clienteSistema) {
        log.debug("Método que implementa persistência do objeto ClienteSistemaEntity acessado. Cliente a ser " +
                "persistido: {}", clienteSistema);
        return clienteSistemaRepository.save(clienteSistema);
    }

    public Optional<ClienteSistemaEntity> implementaBuscaPorEmail(String email) {
        return clienteSistemaRepository.findByEmail(email);
    }

    public Optional<ClienteSistemaEntity> implementaBuscaPorCpf(String cpf) {
        return clienteSistemaRepository.findByCpf(cpf);
    }

    public ClienteSistemaEntity implementaBuscaPorId(Long id) {
        log.debug("Método de implementação de busca de clienteSistemaEntity por id ({}) acessado...", id);

        Optional<ClienteSistemaEntity> clienteOptional =
                clienteSistemaRepository.findById(id);

        ClienteSistemaEntity clienteSistema;
        if (clienteOptional.isPresent()) {
            clienteSistema = clienteOptional.get();
            log.debug("Cliente encontrado: {}", clienteSistema);
        } else {
            log.error("Nenhum cliente foi encontrado com o id informado: {}", id);
            throw new ObjectNotFoundException("Nenhum cliente foi encontrado com o id informado");
        }

        return clienteSistema;
    }

    public ClienteSistemaEntity implementaBuscaPorCodigoClienteAsaas(String codigoClienteAsaas) {
        log.debug("Método que implementa busca por cliente Asaas pelo seu código de cliente acessado");

        Optional<ClienteSistemaEntity> clienteOptional =
                clienteSistemaRepository.findByCodigoClienteAsaas(codigoClienteAsaas);

        ClienteSistemaEntity clienteSistema;
        if (clienteOptional.isPresent()) {
            clienteSistema = clienteOptional.get();
            log.debug("Cliente encontrado: {}", clienteSistema);
        } else {
            log.warn("Nenhum cliente foi encontrado com o código Asaas informado: {}", codigoClienteAsaas);
            throw new ObjectNotFoundException("Nenhum cliente foi encontrado com o codigo Asaas informado");
        }

        log.debug("Retornando cliente...");
        return clienteSistema;
    }

    public void implementaBuscaPorPlanosVencidosAtivos() {
        clienteSistemaRepository
                .buscaPorClientesComPlanosVencidosAtivos("ATIVO", LocalDate.now().toString());
    }

}
