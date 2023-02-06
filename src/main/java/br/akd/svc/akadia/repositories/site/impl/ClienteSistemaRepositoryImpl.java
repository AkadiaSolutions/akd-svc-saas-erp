package br.akd.svc.akadia.repositories.site.impl;

import br.akd.svc.akadia.models.entities.site.ClienteSistemaEntity;
import br.akd.svc.akadia.repositories.site.ClienteSistemaRepository;
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

}
