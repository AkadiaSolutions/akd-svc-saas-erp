package br.akd.svc.akadia.modules.erp.produtos.repository;

import br.akd.svc.akadia.modules.erp.produtos.models.entity.ProdutoEntity;
import br.akd.svc.akadia.modules.erp.produtos.models.entity.id.ProdutoId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, ProdutoId> {
    @Query("SELECT p FROM ProdutoEntity p WHERE p.empresa.id = ?1 and p.exclusao IS NULL")
    Page<ProdutoEntity> buscaPaginada(Pageable pageable, Long id);

    @Query("SELECT p FROM ProdutoEntity p WHERE " +
            "(upper(p.descricao) LIKE ?1% and p.empresa.id = ?2 and p.exclusao IS NULL) OR " +
            "(upper(p.categoria) LIKE ?1% and p.empresa.id = ?2 and p.exclusao IS NULL) OR " +
            "(upper(p.marca) LIKE ?1% and p.empresa.id = ?2 and p.exclusao IS NULL) OR " +
            "(upper(p.sigla) LIKE ?1% and p.empresa.id = ?2 and p.exclusao IS NULL) OR " +
            "(upper(p.codigoInterno) LIKE ?1% and p.empresa.id = ?2 and p.exclusao IS NULL)")
    Page<ProdutoEntity> buscaPaginadaTypeAhead(Pageable pageable, String busca, Long id);
}
