package br.akd.svc.akadia.repositories.sistema.colaboradores;

import br.akd.svc.akadia.models.entities.sistema.colaboradores.ColaboradorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ColaboradorRepository extends JpaRepository<ColaboradorEntity, Long> {

    @Query("SELECT c FROM ColaboradorEntity c WHERE c.acessoSistema.nomeUsuario = ?1")
    Optional<ColaboradorEntity> buscaPorUsername(String username);

}
