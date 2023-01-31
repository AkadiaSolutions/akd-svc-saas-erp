package br.akd.svc.akadia.repositories.site;

import br.akd.svc.akadia.models.entities.site.CartaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoRepository extends JpaRepository<CartaoEntity, Long> {}
