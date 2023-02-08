package br.akd.svc.akadia.repositories.site;

import br.akd.svc.akadia.models.entities.site.ClienteSistemaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteSistemaRepository extends JpaRepository<ClienteSistemaEntity, Long> {

    Optional<ClienteSistemaEntity> findByEmail(String email);

    Optional<ClienteSistemaEntity> findByCpf(String cpf);

    Optional<ClienteSistemaEntity> findByCodigoClienteAsaas(String codigoClienteAsaas);

    @Query("Select c From ClienteSistemaEntity c where " +
            "c.plano.statusPlanoEnum = ?1 and " +
            "c.plano.dataVencimento < ?2 ")
    List<ClienteSistemaEntity> buscaPorClientesComPlanosVencidosAtivos(String statusPlanoEnum, String dataVencimento);

}
