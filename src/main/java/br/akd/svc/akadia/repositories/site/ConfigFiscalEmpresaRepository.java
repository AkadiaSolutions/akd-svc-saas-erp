package br.akd.svc.akadia.repositories.site;

import br.akd.svc.akadia.models.entities.site.empresa.fiscal.ConfigFiscalEmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigFiscalEmpresaRepository extends JpaRepository<ConfigFiscalEmpresaEntity, Long> {}
