package br.akd.svc.akadia.modules.erp.clientes.repository;

import br.akd.svc.akadia.modules.erp.clientes.models.entity.ClienteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    @Query("SELECT c FROM ClienteEntity c WHERE c.id=?1 and c.empresa.id = ?2 and c.exclusao IS NULL")
    Optional<ClienteEntity> buscaPorId(Long idCliente, Long idEmpresa);

    @Query("SELECT c FROM ClienteEntity c WHERE c.cpfCnpj = ?1 " +
            "and c.empresa.id = ?2 " +
            "and c.exclusao IS NULL")
    Optional<ClienteEntity> buscaPorCpfCnpjIdenticoNaEmpresaDaSessaoAtual(String cpfCnpj, Long id);

    @Query("SELECT c FROM ClienteEntity c WHERE c.inscricaoEstadual = ?1 " +
            "and c.empresa.id = ?2 " +
            "and c.exclusao IS NULL")
    Optional<ClienteEntity> buscaPorInscricaoEstadualIdenticaNaEmpresaDaSessaoAtual(String inscricaoEstadual, Long id);

    @Query("SELECT c FROM ClienteEntity c WHERE c.empresa.id = ?1 and c.exclusao IS NULL")
    List<ClienteEntity> buscaTodos(Long id);

    @Query("SELECT c FROM ClienteEntity c WHERE c.empresa.id = ?1 and c.exclusao IS NULL")
    Page<ClienteEntity> buscaPorClientes(Pageable pageable, Long id);

    @Query("SELECT c FROM ClienteEntity c WHERE " +
            "upper(c.nome) LIKE ?1% and c.empresa.id = ?2 and c.exclusao IS NULL " +
            "or upper(c.email) LIKE ?1% and c.empresa.id = ?2 and c.exclusao IS NULL " +
            "or upper(c.cpfCnpj) LIKE ?1% and c.empresa.id = ?2 and c.exclusao IS NULL")
    Page<ClienteEntity> buscaPorClientesTypeAhead(Pageable pageable, String busca, Long id);
}
