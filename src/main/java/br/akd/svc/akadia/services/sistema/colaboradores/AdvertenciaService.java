package br.akd.svc.akadia.services.sistema.colaboradores;

import br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.AdvertenciaPageResponse;
import br.akd.svc.akadia.models.entities.global.ArquivoEntity;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.AdvertenciaEntity;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.ColaboradorEntity;
import br.akd.svc.akadia.models.enums.global.TipoArquivoEnum;
import br.akd.svc.akadia.repositories.sistema.colaboradores.ColaboradorRepository;
import br.akd.svc.akadia.repositories.sistema.colaboradores.impl.ColaboradorRepositoryImpl;
import br.akd.svc.akadia.utils.SecurityUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Slf4j
@Service
public class AdvertenciaService {

    @Autowired
    ColaboradorRepositoryImpl colaboradorRepositoryImpl;

    @Autowired
    ColaboradorRepository colaboradorRepository;

    @Autowired
    AdvertenciaRelatorioService advertenciaRelatorioService;

    public void geraAdvertenciaColaborador(ColaboradorEntity colaboradorLogado,
                                             HttpServletResponse res,
                                             Long idColaboradorAlvo,
                                             MultipartFile contratoAdvertencia,
                                             String advertenciaEmJson) throws IOException {

        log.debug("Método de serviço de criação de nova advertência acessado");

        log.debug("Iniciando acesso ao método de verificação se colaborador logado possui nível de permissão " +
                "suficiente para realizar alterações");
        SecurityUtil.verificaSePodeRealizarAlteracoes(colaboradorLogado.getAcessoSistema());

        log.debug("Convertendo objeto advertência recebido de Json para entity...");
        AdvertenciaEntity advertenciaEntity = new ObjectMapper().readValue(advertenciaEmJson, AdvertenciaEntity.class);

        log.debug("Iniciando setagem de dados da advertência...");
        advertenciaEntity.setDataCadastro(LocalDate.now().toString());
        advertenciaEntity.setHoraCadastro(LocalTime.now().toString());
        advertenciaEntity.setAdvertenciaAssinada(contratoAdvertencia != null
                ? ArquivoEntity.builder()
                .nome(contratoAdvertencia.getOriginalFilename())
                .tipo(realizaTratamentoTipoDeArquivoDoContratoAdvertencia(contratoAdvertencia.getContentType()))
                .tamanho(contratoAdvertencia.getSize())
                .arquivo(contratoAdvertencia.getBytes())
                .build()
                : null);

        log.debug("Obtendo colaborador pelo id ({})...", idColaboradorAlvo);
        ColaboradorEntity colaborador = colaboradorRepositoryImpl.implementaBuscaPorId(idColaboradorAlvo,
                colaboradorLogado.getEmpresa().getId());

        log.debug("Adicionando advertência criada ao objeto colaborador...");
        colaborador.getAdvertencias().add(advertenciaEntity);

        log.debug("Realizando atualização do objeto colaborador com advertência acoplada...");
        colaboradorRepositoryImpl.implementaPersistencia(colaborador);

        log.debug("Persistência realizada com sucesso");

        log.debug("Iniciando acesso ao método de exportação de PDF para gerar a advertência em PDF...");
        advertenciaRelatorioService.exportarPdf(res, colaboradorLogado, colaborador, advertenciaEntity);

        log.info("Requisição finalizada com sucesso");
    }

    private TipoArquivoEnum realizaTratamentoTipoDeArquivoDoContratoAdvertencia(String tipoArquivo) {

        if (tipoArquivo == null) return TipoArquivoEnum.PDF;

        switch (tipoArquivo) {
            case "application/vnd.openxmlformats-officedocument.wordprocessingml.document":
                return TipoArquivoEnum.DOCX;
            case "image/png":
                return TipoArquivoEnum.PNG;
            case "image/jpeg":
                return TipoArquivoEnum.JPG;
            default:
                return TipoArquivoEnum.PDF;
        }
    }

    public AdvertenciaPageResponse obtemAdvertenciasColaborador(ColaboradorEntity colaboradorLogado, Pageable pageable, Long idColaborador) {

        log.debug("Método de serviço obtenção de advertências do colaborador de id {} acessado", idColaborador);

        log.debug("Acessando repositório de busca de advertências");
        Page<AdvertenciaEntity> advertenciaPage = colaboradorRepository
                .buscaAdvertenciasPorIdColaborador(pageable, idColaborador, colaboradorLogado.getEmpresa().getId());

        log.debug("Busca de advertências por paginação realizada com sucesso. Acessando método de conversão dos objetos do tipo " +
                "Entity para objetos do tipo Response...");
        AdvertenciaPageResponse advertenciaPageResponse = converteListaDeEntitiesParaPageResponse(advertenciaPage);

        log.debug("Conversão de tipagem realizada com sucesso");

        log.info("A busca paginada de advertências foi realizada com sucesso");
        return advertenciaPageResponse;
    }

    public AdvertenciaPageResponse converteListaDeEntitiesParaPageResponse(Page<AdvertenciaEntity> advertenciaEntityPage) {
        log.debug("Método de conversão de advertências do tipo Entity para advertências do tipo Response acessado");

        log.debug("Criando lista vazia de objetos do tipo AcaoResponse...");
        List<AdvertenciaEntity> advertenciaEntity = advertenciaEntityPage.getContent();

        log.debug("Iniciando criação de objeto do tipo AdvertenciaPageResponse, que possui todas as informações referentes " +
                "ao conteúdo da página e à paginação...");
        advertenciaEntityPage.getPageable();
        AdvertenciaPageResponse advertenciaPageResponse = AdvertenciaPageResponse.builder()
                .content(advertenciaEntity)
                .empty(advertenciaEntityPage.isEmpty())
                .first(advertenciaEntityPage.isFirst())
                .last(advertenciaEntityPage.isLast())
                .number(advertenciaEntityPage.getNumber())
                .numberOfElements(advertenciaEntityPage.getNumberOfElements())
                .offset(advertenciaEntityPage.getPageable().getOffset())
                .pageNumber(advertenciaEntityPage.getPageable().getPageNumber())
                .pageSize(advertenciaEntityPage.getPageable().getPageSize())
                .paged(advertenciaEntityPage.getPageable().isPaged())
                .unpaged(advertenciaEntityPage.getPageable().isUnpaged())
                .size(advertenciaEntityPage.getSize())
                .totalElements(advertenciaEntityPage.getTotalElements())
                .totalPages(advertenciaEntityPage.getTotalPages())
                .build();

        log.debug("Objeto do tipo AdvertenciaPageResponse criado com sucesso. Retornando objeto...");
        return advertenciaPageResponse;
    }

}
