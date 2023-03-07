package br.akd.svc.akadia.repositories.sistema.clientes;

import br.akd.svc.akadia.models.entities.sistema.clientes.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
    @Query("SELECT c FROM ClienteEntity c WHERE c.cpfCnpj = ?1 " +
            "and c.empresa.id = ?2")
    Optional<ClienteEntity> buscaPorCpfCnpjIdenticoNaEmpresaDaSessaoAtual(String cpfCnpj, Long id);

    @Query("SELECT c FROM ClienteEntity c WHERE c.email = ?1 " +
            "and c.empresa.id = ?2")
    Optional<ClienteEntity> buscaPorEmailIdenticoNaEmpresaDaSessaoAtual(String email, Long id);

    @Query("SELECT c FROM ClienteEntity c WHERE c.inscricaoEstadual = ?1 " +
            "and c.empresa.id = ?2")
    Optional<ClienteEntity> buscaPorInscricaoEstadualIdenticaNaEmpresaDaSessaoAtual(String inscricaoEstadual, Long id);

    @Query("SELECT c FROM ClienteEntity c WHERE upper(c.nome) LIKE ?1% and c.empresa.id = ?2")
    List<ClienteEntity> buscaPorNomeSemelhanteNaEmpresaDaSessaoAtual(String nome, Long id);

    @Query("SELECT c FROM ClienteEntity c WHERE upper(c.email) LIKE ?1% and c.empresa.id = ?2")
    List<ClienteEntity> buscaPorEmailSemelhanteNaEmpresaDaSessaoAtual(String email, Long id);

    @Query("SELECT c FROM ClienteEntity c WHERE c.cpfCnpj LIKE ?1% and c.empresa.id = ?2")
    List<ClienteEntity> buscaPorCpfCnpjSemelhanteNaEmpresaDaSessaoAtual(String cpfCnpj, Long id);

    @Query("SELECT c FROM ClienteEntity c WHERE c.inscricaoEstadual LIKE ?1% and c.empresa.id = ?2")
    List<ClienteEntity> buscaPorInscricaoEstadualSemelhanteNaEmpresaDaSessaoAtual(String inscricaoEstadual, Long id);

    @Query("SELECT c FROM ClienteEntity c WHERE c.telefone.numero LIKE ?1% and c.empresa.id = ?2")
    List<ClienteEntity> buscaPorTelefoneSemelhanteNaEmpresaDaSessaoAtual(String telefone, Long id);

    @Query("SELECT c FROM ClienteEntity c WHERE c.dataCadastro between ?1 and ?2 and c.empresa.id = ?3")
    List<ClienteEntity> buscaPorPeriodoNaEmpresaDaSessaoAtual(String dataInicio, String dataFim, Long id);
}
