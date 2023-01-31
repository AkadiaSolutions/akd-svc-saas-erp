package br.akd.svc.akadia.repositories.bckoff;

import br.akd.svc.akadia.models.entities.bckoff.ColaboradorInternoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColaboradorInternoRepository extends JpaRepository<ColaboradorInternoEntity, Long> {}
