package br.akd.svc.akadia.repositories.sistema.colaboradores;

import br.akd.svc.akadia.models.entities.sistema.colaboradores.AcaoEntity;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.AcessoEntity;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.AdvertenciaEntity;
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

    Boolean existsByMatricula(String matricula);

    @Query("SELECT c FROM ColaboradorEntity c WHERE c.empresa.id = ?1 and c.exclusao.excluido = FALSE")
    List<ColaboradorEntity> buscaTodos(Long idEmpresa);

    @Query("SELECT c FROM ColaboradorEntity c WHERE c.matricula = ?1")
    Optional<ColaboradorEntity> buscaPorMatricula(String matricula);

    @Query("SELECT c FROM ColaboradorEntity c WHERE c.id=?1 and c.empresa.id = ?2 and c.exclusao.excluido = FALSE")
    Optional<ColaboradorEntity> buscaPorId(Long idColaborador, Long idEmpresa);

    @Query("SELECT a FROM ColaboradorEntity c join c.acoes a WHERE c.id=?1 and c.empresa.id = ?2 and c.exclusao.excluido = FALSE")
    Page<AcaoEntity> buscaAcoesPorIdColaborador(Pageable pageable, Long idColaborador, Long idEmpresa);

    @Query("SELECT a FROM ColaboradorEntity c join c.advertencias a WHERE c.id=?1 and c.empresa.id = ?2 and c.exclusao.excluido = FALSE")
    Page<AdvertenciaEntity> buscaAdvertenciasPorIdColaborador(Pageable pageable, Long idColaborador, Long idEmpresa);

    @Query("SELECT a FROM ColaboradorEntity c join c.acessos a WHERE c.id=?1 and c.empresa.id = ?2 and c.exclusao.excluido = FALSE")
    Page<AcessoEntity> buscaAcessosPorIdColaborador(Pageable pageable, Long idColaborador, Long idEmpresa);

    @Query("SELECT upper(c.ocupacao) FROM ColaboradorEntity c WHERE c.empresa.id = ?1 and c.ocupacao IS NOT NULL")
    List<String> buscaTodasOcupacoesDaEmpresa(Long id);

    @Query("SELECT c FROM ColaboradorEntity c WHERE c.empresa.id = ?1 and c.exclusao.excluido = FALSE")
    Page<ColaboradorEntity> buscaPorColaboradores(Pageable pageable, Long id);

    @Query("SELECT c FROM ColaboradorEntity c WHERE " +
            "upper(c.nome) LIKE ?1% and c.empresa.id = ?2 and c.exclusao.excluido = FALSE " +
            "or upper(c.email) LIKE ?1% and c.empresa.id = ?2 and c.exclusao.excluido = FALSE " +
            "or upper(c.cpfCnpj) LIKE ?1% and c.empresa.id = ?2 and c.exclusao.excluido = FALSE")
    Page<ColaboradorEntity> buscaPorColaboradoresTypeAhead(Pageable pageable, String busca, Long id);
}
