package br.akd.svc.akadia.repositories.site.impl;

import br.akd.svc.akadia.models.entities.site.ClienteSistemaEntity;
import br.akd.svc.akadia.repositories.site.ClienteSistemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteSistemaRepositoryImpl {

    @Autowired
    ClienteSistemaRepository clienteSistemaRepository;

    public ClienteSistemaEntity implementaPersistencia(ClienteSistemaEntity clienteSistema) {
        return clienteSistemaRepository.save(clienteSistema);
    }

    public Optional<ClienteSistemaEntity> implementaBuscaPorEmail(String email) {
        return clienteSistemaRepository.findByEmail(email);
    }

    public Optional<ClienteSistemaEntity> implementaBuscaPorCpf(String cpf) {
        return clienteSistemaRepository.findByCpf(cpf);
    }

}
