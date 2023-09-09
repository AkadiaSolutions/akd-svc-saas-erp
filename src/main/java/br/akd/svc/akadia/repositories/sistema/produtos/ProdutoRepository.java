package br.akd.svc.akadia.repositories.sistema.produtos;

import br.akd.svc.akadia.models.entities.sistema.produto.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {
}
