package br.akd.svc.akadia.repositories.sistema.despesas;

import br.akd.svc.akadia.models.entities.sistema.despesas.DespesaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DespesaRepository extends JpaRepository<DespesaEntity, Long> {

    @Query("SELECT c FROM DespesaEntity c WHERE c.id=?1 and c.empresa.id = ?2 and c.exclusao IS NULL")
    Optional<DespesaEntity> buscaPorId(Long idDespesa, Long idEmpresa);

    @Query("SELECT d FROM DespesaEntity d WHERE " +
            "d.empresa.id = ?3 and d.exclusao IS NULL and d.dataAgendamento between ?1 and ?2" +
            "OR d.empresa.id = ?3 and d.exclusao IS NULL and d.dataPagamento between ?1 and ?2")
    Page<DespesaEntity> buscaPorDespesas(Pageable pageable, String dataInicio, String dataFim, Long id);

    @Query("SELECT d FROM DespesaEntity d WHERE " +
            "upper(d.descricao) LIKE ?3% and d.empresa.id = ?4 and d.exclusao IS NULL and d.dataAgendamento between ?1 and ?2" +
            "OR upper(d.descricao) LIKE ?3% and d.empresa.id = ?4 and d.exclusao IS NULL and d.dataPagamento between ?1 and ?2")
    Page<DespesaEntity> buscaPorDespesasTypeAhead(Pageable pageable, String dataInicio, String dataFim, String busca, Long id);

    @Query("SELECT d FROM DespesaEntity d WHERE d.empresa.id = ?1 and d.exclusao IS NULL")
    List<DespesaEntity> buscaTodos(Long id);
}
