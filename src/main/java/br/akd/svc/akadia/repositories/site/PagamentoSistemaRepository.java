package br.akd.svc.akadia.repositories.site;

import br.akd.svc.akadia.models.entities.site.PagamentoSistemaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PagamentoSistemaRepository extends JpaRepository<PagamentoSistemaEntity, Long> {

    Optional<PagamentoSistemaEntity> findByCodigoPagamentoAsaas(String codigoPagamentoAsaas);

}
