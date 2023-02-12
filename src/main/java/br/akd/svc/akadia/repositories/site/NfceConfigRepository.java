package br.akd.svc.akadia.repositories.site;

import br.akd.svc.akadia.models.entities.site.empresa.fiscal.NfceConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NfceConfigRepository extends JpaRepository<NfceConfigEntity, Long> {}
