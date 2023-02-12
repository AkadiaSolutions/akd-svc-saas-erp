package br.akd.svc.akadia.repositories.site;

import br.akd.svc.akadia.models.entities.site.empresa.fiscal.NfseConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NfseConfigRepository extends JpaRepository<NfseConfigEntity, Long> {}
