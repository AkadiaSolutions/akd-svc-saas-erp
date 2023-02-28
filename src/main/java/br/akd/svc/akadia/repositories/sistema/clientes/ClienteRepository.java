package br.akd.svc.akadia.repositories.sistema.clientes;

import br.akd.svc.akadia.models.entities.sistema.clientes.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    @Query("SELECT c FROM ClienteEntity c WHERE c.cpfCnpj = ?1 and c.empresa.id = ?2")
    Optional<ClienteEntity> buscaPorCpfCnpjNaEmpresaDaSessaoAtual(String cpfCnpj, Long id);

    @Query("SELECT c FROM ClienteEntity c WHERE c.email = ?1 and c.empresa.id = ?2")
    Optional<ClienteEntity> buscaPorEmailNaEmpresaDaSessaoAtual(String email, Long id);

    @Query("SELECT c FROM ClienteEntity c WHERE c.inscricaoEstadual = ?1 and c.empresa.id = ?2")
    Optional<ClienteEntity> buscaPorInscricaoEstadualNaEmpresaDaSessaoAtual(String inscricaoEstadual, Long id);

}
