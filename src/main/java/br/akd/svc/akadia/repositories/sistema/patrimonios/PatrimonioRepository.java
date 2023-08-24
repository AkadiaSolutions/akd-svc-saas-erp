package br.akd.svc.akadia.repositories.sistema.patrimonios;

import br.akd.svc.akadia.models.entities.sistema.patrimonios.PatrimonioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatrimonioRepository extends JpaRepository<PatrimonioEntity, Long> {

    @Query("SELECT p FROM PatrimonioEntity p WHERE p.id=?1 and p.empresa.id = ?2 and p.exclusao IS NULL")
    Optional<PatrimonioEntity> buscaPorId(Long idColaborador, Long idEmpresa);

    @Query("SELECT p FROM PatrimonioEntity p WHERE p.empresa.id = ?1 and p.exclusao IS NULL")
    List<PatrimonioEntity> buscaTodos(Long idEmpresa);

    @Query("SELECT p FROM PatrimonioEntity p WHERE p.empresa.id = ?1 and p.exclusao IS NULL")
    Page<PatrimonioEntity> buscaPaginada(Pageable pageable, Long id);

    @Query("SELECT p FROM PatrimonioEntity p WHERE " +
            "upper(p.descricao) LIKE ?1% and p.empresa.id = ?2 and p.exclusao IS NULL ")
    Page<PatrimonioEntity> buscaPaginadaTypeAhead(Pageable pageable, String busca, Long id);

}
