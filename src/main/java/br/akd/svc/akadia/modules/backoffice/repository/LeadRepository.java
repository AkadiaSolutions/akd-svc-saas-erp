package br.akd.svc.akadia.modules.backoffice.repository;

import br.akd.svc.akadia.modules.backoffice.models.entity.LeadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadRepository extends JpaRepository<LeadEntity, Long> {}
