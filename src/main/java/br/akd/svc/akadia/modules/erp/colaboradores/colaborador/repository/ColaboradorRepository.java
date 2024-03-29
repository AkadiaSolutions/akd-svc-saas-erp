package br.akd.svc.akadia.modules.erp.colaboradores.colaborador.repository;

import br.akd.svc.akadia.modules.global.entity.ArquivoEntity;
import br.akd.svc.akadia.modules.erp.colaboradores.acao.models.entity.AcaoEntity;
import br.akd.svc.akadia.modules.erp.colaboradores.acesso.models.entity.AcessoEntity;
import br.akd.svc.akadia.modules.erp.colaboradores.advertencia.models.entity.AdvertenciaEntity;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.entity.ColaboradorEntity;
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

    @Query("SELECT c FROM ColaboradorEntity c WHERE c.empresa.id = ?1 and c.exclusao IS NULL")
    List<ColaboradorEntity> buscaTodos(Long idEmpresa);

    @Query("SELECT c FROM ColaboradorEntity c WHERE c.matricula = ?1")
    Optional<ColaboradorEntity> buscaPorMatricula(String matricula);

    @Query("SELECT c.fotoPerfil FROM ColaboradorEntity c WHERE c.id=?1 and c.empresa.id = ?2 and c.exclusao IS NULL")
    Optional<ArquivoEntity> buscaImagemPerfilPorId(Long idColaborador, Long idEmpresa);

    @Query("SELECT c FROM ColaboradorEntity c WHERE c.id=?1 and c.empresa.id = ?2 and c.exclusao IS NULL")
    Optional<ColaboradorEntity> buscaPorId(Long idColaborador, Long idEmpresa);

    @Query("SELECT a FROM ColaboradorEntity c join c.acoes a WHERE c.id=?1 and c.empresa.id = ?2 and c.exclusao IS NULL")
    Page<AcaoEntity> buscaAcoesPorIdColaborador(Pageable pageable, Long idColaborador, Long idEmpresa);

    @Query("SELECT a FROM ColaboradorEntity c join c.advertencias a WHERE c.id=?1 and c.empresa.id = ?2 and c.exclusao IS NULL")
    Page<AdvertenciaEntity> buscaAdvertenciasPorIdColaborador(Pageable pageable, Long idColaborador, Long idEmpresa);

    @Query("SELECT a FROM ColaboradorEntity c join c.acessos a WHERE c.id=?1 and c.empresa.id = ?2 and c.exclusao IS NULL")
    Page<AcessoEntity> buscaAcessosPorIdColaborador(Pageable pageable, Long idColaborador, Long idEmpresa);

    @Query("SELECT upper(c.ocupacao) FROM ColaboradorEntity c WHERE c.empresa.id = ?1 and c.ocupacao IS NOT NULL")
    List<String> buscaTodasOcupacoesDaEmpresa(Long id);

    @Query("SELECT c FROM ColaboradorEntity c WHERE c.empresa.id = ?1 and c.exclusao IS NULL")
    Page<ColaboradorEntity> buscaPorColaboradores(Pageable pageable, Long id);

    @Query("SELECT c FROM ColaboradorEntity c WHERE " +
            "upper(c.nome) LIKE ?1% and c.empresa.id = ?2 and c.exclusao IS NULL " +
            "or upper(c.email) LIKE ?1% and c.empresa.id = ?2 and c.exclusao IS NULL " +
            "or upper(c.cpfCnpj) LIKE ?1% and c.empresa.id = ?2 and c.exclusao IS NULL")
    Page<ColaboradorEntity> buscaPorColaboradoresTypeAhead(Pageable pageable, String busca, Long id);
}
