package br.akd.svc.akadia.repositories.site;

import br.akd.svc.akadia.models.entities.site.PlanoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanoRepository extends JpaRepository<PlanoEntity, Long> {}
