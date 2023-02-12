package br.akd.svc.akadia.repositories.site;

import br.akd.svc.akadia.models.entities.site.empresa.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<EmpresaEntity, Long> {

    Optional<EmpresaEntity> findByCnpj(String cnpj);
    Optional<EmpresaEntity> findByEndpoint(String endpoint);
    Optional<EmpresaEntity> findByRazaoSocial(String razaoSocial);
    Optional<EmpresaEntity> findByInscricaoEstadual(String inscricaoEstadual);
    Optional<EmpresaEntity> findByInscricaoMunicipal(String inscricaoMunicipal);

}
