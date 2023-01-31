package br.akd.svc.akadia.repositories.site.fiscal;

import br.akd.svc.akadia.models.entities.site.fiscal.NfeConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NfeConfigRepository extends JpaRepository<NfeConfigEntity, Long> {}
