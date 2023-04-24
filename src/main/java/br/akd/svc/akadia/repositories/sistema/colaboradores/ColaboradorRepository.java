package br.akd.svc.akadia.repositories.sistema.colaboradores;

import br.akd.svc.akadia.models.entities.sistema.colaboradores.ColaboradorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ColaboradorRepository extends JpaRepository<ColaboradorEntity, Long> {

    Boolean existsByMatricula(Long matricula);

    @Query("SELECT c FROM ColaboradorEntity c WHERE c.acessoSistema.nomeUsuario = ?1")
    Optional<ColaboradorEntity> buscaPorUsername(String username);

    @Query("SELECT c FROM ColaboradorEntity c WHERE c.id=?1 and c.empresa.id = ?2 and c.exclusao.excluido = FALSE")
    Optional<ColaboradorEntity> buscaPorId(Long idColaborador, Long idEmpresa);

    @Query("SELECT c FROM ColaboradorEntity c WHERE c.empresa.id = ?1 and c.exclusao.excluido = FALSE")
    List<ColaboradorEntity> buscaTodos(Long id);

    @Query("SELECT c FROM ColaboradorEntity c WHERE c.empresa.id = ?1 and c.exclusao.excluido = FALSE")
    Page<ColaboradorEntity> buscaPorColaboradores(Pageable pageable, Long id);

    @Query("SELECT c FROM ColaboradorEntity c WHERE " +
            "upper(c.nome) LIKE ?1% and c.empresa.id = ?2 and c.exclusao.excluido = FALSE " +
            "or upper(c.email) LIKE ?1% and c.empresa.id = ?2 and c.exclusao.excluido = FALSE " +
            "or upper(c.cpfCnpj) LIKE ?1% and c.empresa.id = ?2 and c.exclusao.excluido = FALSE")
    Page<ColaboradorEntity> buscaPorColaboradoresTypeAhead(Pageable pageable, String busca, Long id);
}
