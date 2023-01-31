package br.akd.svc.akadia.repositories.site;

import br.akd.svc.akadia.models.entities.site.ClienteSistemaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteSistemaRepository extends JpaRepository<ClienteSistemaEntity, Long> {}
