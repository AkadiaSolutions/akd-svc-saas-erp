package br.akd.svc.akadia.repositories.bckoff.impl;

import br.akd.svc.akadia.models.entities.bckoff.LeadEntity;
import br.akd.svc.akadia.repositories.bckoff.LeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeadRepositoryImpl {

    @Autowired
    LeadRepository leadRepository;

    public LeadEntity implementaPersistencia(LeadEntity leadEntity) {
        return leadRepository.save(leadEntity);
    }

}
