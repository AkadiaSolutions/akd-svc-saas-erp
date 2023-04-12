package br.akd.svc.akadia.repositories.sistema.clientes;

import br.akd.svc.akadia.models.entities.sistema.clientes.ClienteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
    @Query("SELECT c FROM ClienteEntity c WHERE c.cpfCnpj = ?1 " +
            "and c.empresa.id = ?2 " +
            "and c.exclusaoCliente.excluido = FALSE")
    Optional<ClienteEntity> buscaPorCpfCnpjIdenticoNaEmpresaDaSessaoAtual(String cpfCnpj, Long id);

    @Query("SELECT c FROM ClienteEntity c WHERE c.inscricaoEstadual = ?1 " +
            "and c.empresa.id = ?2 " +
            "and c.exclusaoCliente.excluido = FALSE")
    Optional<ClienteEntity> buscaPorInscricaoEstadualIdenticaNaEmpresaDaSessaoAtual(String inscricaoEstadual, Long id);

    @Query("SELECT c FROM ClienteEntity c WHERE c.empresa.id = ?1 and c.exclusaoCliente.excluido = FALSE")
    List<ClienteEntity> buscaTodos(Long id);

    @Query("SELECT c FROM ClienteEntity c WHERE c.empresa.id = ?1 and c.exclusaoCliente.excluido = FALSE")
    Page<ClienteEntity> buscaPorClientes(Pageable pageable, Long id);

    @Query("SELECT c FROM ClienteEntity c WHERE " +
            "upper(c.nome) LIKE ?1% and c.empresa.id = ?2 and c.exclusaoCliente.excluido = FALSE " +
            "or upper(c.email) LIKE ?1% and c.empresa.id = ?2 and c.exclusaoCliente.excluido = FALSE " +
            "or upper(c.cpfCnpj) LIKE ?1% and c.empresa.id = ?2 and c.exclusaoCliente.excluido = FALSE")
    Page<ClienteEntity> buscaPorClientesTypeAhead(Pageable pageable, String busca, Long id);
}
