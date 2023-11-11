package br.akd.svc.akadia.modules.backoffice.repository.impl;

import br.akd.svc.akadia.modules.backoffice.models.entity.LeadEntity;
import br.akd.svc.akadia.modules.backoffice.repository.LeadRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class LeadRepositoryImpl {

    @Autowired
    LeadRepository leadRepository;

    @Transactional
    public LeadEntity implementaPersistencia(LeadEntity leadEntity) {
        log.debug("Método de implementação de persistência do objeto LeadEntity acessado. Objeto recebido: {}", leadEntity);
        return leadRepository.save(leadEntity);
    }

}
