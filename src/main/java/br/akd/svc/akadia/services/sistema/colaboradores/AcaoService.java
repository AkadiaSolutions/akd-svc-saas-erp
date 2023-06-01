package br.akd.svc.akadia.services.sistema.colaboradores;

import br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.AcaoPageResponse;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.AcaoEntity;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.ColaboradorEntity;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.ModulosEnum;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.TipoAcaoEnum;
import br.akd.svc.akadia.repositories.sistema.colaboradores.ColaboradorRepository;
import br.akd.svc.akadia.repositories.sistema.colaboradores.impl.ColaboradorRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Slf4j
@Service
public class AcaoService {

    @Autowired
    ColaboradorRepositoryImpl colaboradorRepositoryImpl;

    @Autowired
    ColaboradorRepository colaboradorRepository;

    public void salvaHistoricoColaborador(ColaboradorEntity colaboradorLogado,
                                          Long idObjeto,
                                          ModulosEnum modulo,
                                          TipoAcaoEnum tipo,
                                          String observacao) {

        log.debug("Método de salvamento de histórico do colaborador acessado");

        log.debug("Iniciando construção do objeto AcaoEntity dentro da listagem de histórico de ações do colaborador " +
                "de id {}...", colaboradorLogado.getId());
        colaboradorLogado.getAcoes().add(AcaoEntity.builder()
                .idObjeto(idObjeto)
                .dataCriacao(LocalDate.now().toString())
                .horaCriacao(LocalTime.now().toString())
                .moduloEnum(modulo)
                .tipoAcaoEnum(tipo)
                .observacao(observacao)
                .build());

        log.debug("Realizando persistência da ação...");
        colaboradorRepositoryImpl.implementaPersistencia(colaboradorLogado);
    }

    public AcaoPageResponse obtemAcoesColaborador(ColaboradorEntity colaboradorLogado, Pageable pageable, Long idColaborador) {

        log.debug("Método de serviço obtenção de ações do colaborador de id {} acessado", idColaborador);

        log.debug("Acessando repositório de busca de ações");
        Page<AcaoEntity> acaoPage = colaboradorRepository
                .buscaAcoesPorIdColaborador(pageable, idColaborador, colaboradorLogado.getEmpresa().getId());

        log.debug("Busca de ações por paginação realizada com sucesso. Acessando método de conversão dos objetos do tipo " +
                "Entity para objetos do tipo Response...");
        AcaoPageResponse acaoPageResponse = converteListaDeEntitiesParaPageResponse(acaoPage);

        log.debug("Conversão de tipagem realizada com sucesso");

        log.info("A busca paginada de ações foi realizada com sucesso");
        return acaoPageResponse;
    }

    public AcaoPageResponse converteListaDeEntitiesParaPageResponse(Page<AcaoEntity> acaoEntityPage) {
        log.debug("Método de conversão de ações do tipo Entity para ações do tipo Response acessado");

        log.debug("Criando lista vazia de objetos do tipo AcaoResponse...");
        List<AcaoEntity> acoesEntity = acaoEntityPage.getContent();

        log.debug("Iniciando criação de objeto do tipo AcaoPageResponse, que possui todas as informações referentes " +
                "ao conteúdo da página e à paginação...");
        acaoEntityPage.getPageable();
        AcaoPageResponse acaoPageResponse = AcaoPageResponse.builder()
                .content(acoesEntity)
                .empty(acaoEntityPage.isEmpty())
                .first(acaoEntityPage.isFirst())
                .last(acaoEntityPage.isLast())
                .number(acaoEntityPage.getNumber())
                .numberOfElements(acaoEntityPage.getNumberOfElements())
                .pageNumber(acaoEntityPage.getPageable().getPageNumber())
                .pageSize(acaoEntityPage.getPageable().getPageSize())
                .paged(acaoEntityPage.getPageable().isPaged())
                .unpaged(acaoEntityPage.getPageable().isUnpaged())
                .size(acaoEntityPage.getSize())
                .totalElements(acaoEntityPage.getTotalElements())
                .totalPages(acaoEntityPage.getTotalPages())
                .build();

        log.debug("Objeto do tipo AcaoPageResponse criado com sucesso. Retornando objeto...");
        return acaoPageResponse;
    }

}
