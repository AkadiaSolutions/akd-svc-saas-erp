package br.akd.svc.akadia.services.sistema.colaboradores.acesso;

import br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.acesso.AcessoPageResponse;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.AcessoEntity;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.ColaboradorEntity;
import br.akd.svc.akadia.repositories.sistema.colaboradores.ColaboradorRepository;
import br.akd.svc.akadia.repositories.sistema.colaboradores.impl.ColaboradorRepositoryImpl;
import br.akd.svc.akadia.services.exceptions.UnauthorizedAccessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AcessoService {

    @Autowired
    ColaboradorRepositoryImpl colaboradorRepositoryImpl;

    @Autowired
    ColaboradorRepository colaboradorRepository;

    @Transactional
    public void registraAcessoColaborador(String matricula) {
        log.debug("Método responsável por realizar a criação do log de acessos do colaborador acessado");

        log.debug("Realizando a busca do colaborador pela matrícula informada: {}...", matricula);
        Optional<ColaboradorEntity> colaboradorOptional = colaboradorRepository.buscaPorMatricula(matricula);
        if (!colaboradorOptional.isPresent()) {
            log.error("Ocorreu um erro no login. O Colaborador não foi encontrado pela matrícula {}", matricula);
            throw new UnauthorizedAccessException("Ops! Ocorreu um erro ao tentar realizar o login. Favor entrar " +
                    "em contato com o suporte técnico");
        }

        log.debug("Adicionando objeto Acesso ao colaborador...");
        ColaboradorEntity colaborador = colaboradorOptional.get();
        AcessoEntity novoAcesso = new AcessoEntity(null, LocalDate.now().toString(), LocalTime.now().toString());
        colaborador.addAcesso(novoAcesso);

        log.debug("Realizando persistência do colaborador com acesso registrado...");
        colaboradorRepositoryImpl.implementaPersistencia(colaborador);

        log.debug("Colaborador atualizado com novo login cadastrado com sucesso");
    }

    public AcessoPageResponse obtemAcessosColaborador(ColaboradorEntity colaboradorLogado, Pageable pageable, Long idColaborador) {

        log.debug("Método de serviço obtenção de acessos do colaborador de id {} acessado", idColaborador);

        log.debug("Acessando repositório de busca de acessos");
        Page<AcessoEntity> acessoPage = colaboradorRepository
                .buscaAcessosPorIdColaborador(pageable, idColaborador, colaboradorLogado.getEmpresa().getId());

        log.debug("Busca de acessos por paginação realizada com sucesso. Acessando método de conversão dos objetos do tipo " +
                "Entity para objetos do tipo Response...");
        AcessoPageResponse acessoPageResponse = converteListaDeEntitiesParaPageResponse(acessoPage);

        log.debug("Conversão de tipagem realizada com sucesso");

        log.info("A busca paginada de acessos foi realizada com sucesso");
        return acessoPageResponse;
    }

    public AcessoPageResponse converteListaDeEntitiesParaPageResponse(Page<AcessoEntity> acessoEntityPage) {
        log.debug("Método de conversão de acessos do tipo Entity para acessos do tipo Response acessado");

        log.debug("Criando lista vazia de objetos do tipo AcaoResponse...");
        List<AcessoEntity> acessoEntityList = acessoEntityPage.getContent();

        log.debug("Iniciando criação de objeto do tipo AcessoPageResponse, que possui todas as informações referentes " +
                "ao conteúdo da página e à paginação...");
        acessoEntityPage.getPageable();
        AcessoPageResponse acessoPageResponse = AcessoPageResponse.builder()
                .content(acessoEntityList)
                .empty(acessoEntityPage.isEmpty())
                .first(acessoEntityPage.isFirst())
                .last(acessoEntityPage.isLast())
                .number(acessoEntityPage.getNumber())
                .numberOfElements(acessoEntityPage.getNumberOfElements())
                .offset(acessoEntityPage.getPageable().getOffset())
                .pageNumber(acessoEntityPage.getPageable().getPageNumber())
                .pageSize(acessoEntityPage.getPageable().getPageSize())
                .paged(acessoEntityPage.getPageable().isPaged())
                .unpaged(acessoEntityPage.getPageable().isUnpaged())
                .size(acessoEntityPage.getSize())
                .totalElements(acessoEntityPage.getTotalElements())
                .totalPages(acessoEntityPage.getTotalPages())
                .build();

        log.debug("Objeto do tipo AcessoPageResponse criado com sucesso. Retornando objeto...");
        return acessoPageResponse;
    }

}
