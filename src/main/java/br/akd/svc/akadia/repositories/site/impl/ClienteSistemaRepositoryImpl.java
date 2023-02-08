package br.akd.svc.akadia.repositories.site.impl;

import br.akd.svc.akadia.models.entities.site.ClienteSistemaEntity;
import br.akd.svc.akadia.repositories.site.ClienteSistemaRepository;
import br.akd.svc.akadia.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClienteSistemaRepositoryImpl {

    @Autowired
    ClienteSistemaRepository clienteSistemaRepository;

    @Transactional
    public ClienteSistemaEntity implementaPersistencia(ClienteSistemaEntity clienteSistema) {
        return clienteSistemaRepository.save(clienteSistema);
    }

    public Optional<ClienteSistemaEntity> implementaBuscaPorEmail(String email) {
        return clienteSistemaRepository.findByEmail(email);
    }

    public Optional<ClienteSistemaEntity> implementaBuscaPorCpf(String cpf) {
        return clienteSistemaRepository.findByCpf(cpf);
    }

    public Optional<ClienteSistemaEntity> implementaBuscaPorId(Long id) {
        return clienteSistemaRepository.findById(id);
    }

    public ClienteSistemaEntity implementaBuscaPorCodigoClienteAsaas(String codigoClienteAsaas) {
        Optional<ClienteSistemaEntity> clienteOptional =
                clienteSistemaRepository.findByCodigoClienteAsaas(codigoClienteAsaas);

        ClienteSistemaEntity clienteSistema;
        if (clienteOptional.isPresent()) clienteSistema = clienteOptional.get();
        else throw new ObjectNotFoundException("Nenhum cliente foi encontrado com o codigo Asaas informado");

        return clienteSistema;
    }

}
