package br.akd.svc.akadia.repositories.sistema.colaboradores;

import br.akd.svc.akadia.models.entities.sistema.colaboradores.ColaboradorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColaboradorRepository extends JpaRepository<ColaboradorEntity, Long> {
}
