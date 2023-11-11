package br.akd.svc.akadia.modules.erp.colaboradores.advertencia.services;

import br.akd.svc.akadia.modules.erp.colaboradores.advertencia.models.dto.response.AdvertenciaPageResponse;
import br.akd.svc.akadia.modules.global.entity.ArquivoEntity;
import br.akd.svc.akadia.modules.erp.colaboradores.advertencia.models.entity.AdvertenciaEntity;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.entity.ColaboradorEntity;
import br.akd.svc.akadia.modules.global.enums.TipoArquivoEnum;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.enums.ModulosEnum;
import br.akd.svc.akadia.modules.erp.colaboradores.advertencia.models.enums.StatusAdvertenciaEnum;
import br.akd.svc.akadia.modules.erp.colaboradores.acao.models.enums.TipoAcaoEnum;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.repository.ColaboradorRepository;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.repository.impl.ColaboradorRepositoryImpl;
import br.akd.svc.akadia.exceptions.ObjectNotFoundException;
import br.akd.svc.akadia.modules.erp.colaboradores.acao.services.AcaoService;
import br.akd.svc.akadia.utils.Constantes;
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
import java.util.Objects;

@Slf4j
@Service
public class AdvertenciaService {

    @Autowired
    ColaboradorRepositoryImpl colaboradorRepositoryImpl;

    @Autowired
    ColaboradorRepository colaboradorRepository;

    @Autowired
    AdvertenciaRelatorioService advertenciaRelatorioService;

    @Autowired
    AcaoService acaoService;

    String NENHUMA_ADVERTENCIA_ENCONTRADA = "Nenhuma advertência foi encontrada no colaborador atual";
    String ATUALIZA_COLABORADOR_COM_ADVERTENCIA = "Realizando atualização do objeto colaborador com advertência acoplada...";
    String ACESSA_METODO_BUSCA_ADVERTENCIA = "Iniciando acesso ao método de busca de advertência por id na lista de advertências do cliente...";

    public ArquivoEntity obtemAnexoAdvertencia(ColaboradorEntity colaboradorLogado,
                                               Long idColaborador,
                                               Long idAdvertencia) {

        log.debug("Método de serviço de obtenção de anexo de advertência acessado");

        log.debug("Obtendo colaborador pelo id: {}...", idColaborador);
        ColaboradorEntity colaborador = colaboradorRepositoryImpl.implementaBuscaPorId(idColaborador,
                colaboradorLogado.getEmpresa().getId());

        log.debug(ACESSA_METODO_BUSCA_ADVERTENCIA);
        AdvertenciaEntity advertenciaEntity = realizaBuscaAdvertenciaPorIdNaListaDeAdvertenciasDoColaborador(
                colaborador.getAdvertencias(), idAdvertencia);

        log.debug(Constantes.INICIANDO_SALVAMENTO_HISTORICO_COLABORADOR);
        acaoService.salvaHistoricoColaborador(colaboradorLogado, colaborador.getId(),
                ModulosEnum.COLABORADORES, TipoAcaoEnum.OUTRO, "Download de anexo de advertência");

        log.info("Obtenção de anexo de advertência de id {} finalizado com sucesso", idAdvertencia);
        return advertenciaEntity.getAdvertenciaAssinada();
    }

    public void removerAdvertencia(ColaboradorEntity colaboradorLogado,
                                   Long idColaborador,
                                   Long idAdvertencia) {

        log.debug("Método de serviço de remoção de advertência acessado");

        log.debug(Constantes.VERIFICANDO_SE_COLABORADOR_PODE_ALTERAR_DADOS);
        SecurityUtil.verificaSePodeRealizarAlteracoes(colaboradorLogado.getAcessoSistema());

        log.debug("Obtendo colaborador pelo id: {}...", idColaborador);
        ColaboradorEntity colaborador = colaboradorRepositoryImpl.implementaBuscaPorId(idColaborador,
                colaboradorLogado.getEmpresa().getId());

        log.debug(ACESSA_METODO_BUSCA_ADVERTENCIA);
        AdvertenciaEntity advertenciaEntity = realizaBuscaAdvertenciaPorIdNaListaDeAdvertenciasDoColaborador(
                colaborador.getAdvertencias(), idAdvertencia);

        log.debug("Removendo advertência...");
        colaborador.removeAdvertencia(advertenciaEntity);

        log.debug(ATUALIZA_COLABORADOR_COM_ADVERTENCIA);
        colaboradorRepositoryImpl.implementaPersistencia(colaborador);

        log.debug(Constantes.INICIANDO_SALVAMENTO_HISTORICO_COLABORADOR);
        acaoService.salvaHistoricoColaborador(colaboradorLogado, colaborador.getId(),
                ModulosEnum.COLABORADORES, TipoAcaoEnum.REMOCAO, "Remoção de advertência realizada");

        log.info("Remoção de advertência de id {} finalizado com sucesso", idAdvertencia);
    }

    public void alteraStatusAdvertencia(ColaboradorEntity colaboradorLogado,
                                        StatusAdvertenciaEnum statusAdvertenciaEnum,
                                        Long idColaborador,
                                        Long idAdvertencia) {

        log.debug("Método de serviço de alteração de status da advertência acessado");

        log.debug(Constantes.VERIFICANDO_SE_COLABORADOR_PODE_ALTERAR_DADOS);
        SecurityUtil.verificaSePodeRealizarAlteracoes(colaboradorLogado.getAcessoSistema());

        log.debug("Obtendo colaborador pelo id {}...", idColaborador);
        ColaboradorEntity colaborador = colaboradorRepositoryImpl.implementaBuscaPorId(idColaborador,
                colaboradorLogado.getEmpresa().getId());

        log.debug(ACESSA_METODO_BUSCA_ADVERTENCIA);
        AdvertenciaEntity advertenciaEntity = realizaBuscaAdvertenciaPorIdNaListaDeAdvertenciasDoColaborador(
                colaborador.getAdvertencias(), idAdvertencia);

        log.debug("Setando status atualizado da advertência...");
        advertenciaEntity.setStatusAdvertenciaEnum(statusAdvertenciaEnum);

        log.debug(ATUALIZA_COLABORADOR_COM_ADVERTENCIA);
        colaboradorRepositoryImpl.implementaPersistencia(colaborador);

        log.debug(Constantes.INICIANDO_SALVAMENTO_HISTORICO_COLABORADOR);
        acaoService.salvaHistoricoColaborador(colaboradorLogado, colaborador.getId(),
                ModulosEnum.COLABORADORES, TipoAcaoEnum.ALTERACAO,
                "Status da advertência de motivo [" + advertenciaEntity.getMotivo()
                        + "] alterado para " + statusAdvertenciaEnum.getDesc());

        log.info("Atualização de status da advertência de id {} finalizado com sucesso", idAdvertencia);
    }

    public void anexaArquivoAdvertencia(ColaboradorEntity colaboradorLogado,
                                        MultipartFile anexo,
                                        Long idColaborador,
                                        Long idAdvertencia) throws IOException {

        log.debug("Método de serviço de anexação de PDF padrão na advertência acessado");

        log.debug(Constantes.VERIFICANDO_SE_COLABORADOR_PODE_ALTERAR_DADOS);
        SecurityUtil.verificaSePodeRealizarAlteracoes(colaboradorLogado.getAcessoSistema());

        log.debug("Obtendo colaborador pelo id {}...", idColaborador);
        ColaboradorEntity colaborador = colaboradorRepositoryImpl.implementaBuscaPorId(idColaborador,
                colaboradorLogado.getEmpresa().getId());

        log.debug(ACESSA_METODO_BUSCA_ADVERTENCIA);
        AdvertenciaEntity advertenciaEntity = realizaBuscaAdvertenciaPorIdNaListaDeAdvertenciasDoColaborador(
                colaborador.getAdvertencias(), idAdvertencia);

        log.debug("Setando contrato na advertência do colaborador...");
        advertenciaEntity.setAdvertenciaAssinada(anexo != null
                ? ArquivoEntity.builder()
                .nome(anexo.getOriginalFilename())
                .tipo(realizaTratamentoTipoDeArquivoDoContratoAdvertencia(anexo.getContentType()))
                .tamanho(anexo.getSize())
                .arquivo(anexo.getBytes())
                .build()
                : null);

        log.debug(ATUALIZA_COLABORADOR_COM_ADVERTENCIA);
        colaboradorRepositoryImpl.implementaPersistencia(colaborador);

        log.debug(Constantes.INICIANDO_SALVAMENTO_HISTORICO_COLABORADOR);
        acaoService.salvaHistoricoColaborador(colaboradorLogado, colaborador.getId(),
                ModulosEnum.COLABORADORES, TipoAcaoEnum.ALTERACAO, "Anexo adicionado à advertência");

        log.info("Atualização de anexo da advertência de id {} finalizado com sucesso", idAdvertencia);
    }

    public void geraPdfPadraoAdvertencia(ColaboradorEntity colaboradorLogado,
                                         HttpServletResponse res,
                                         Long idColaborador,
                                         Long idAdvertencia) throws IOException {

        log.debug("Método de serviço de obtenção de PDF padrão da advertência acessado");

        log.debug("Obtendo colaborador pelo id ({})...", idColaborador);
        ColaboradorEntity colaborador = colaboradorRepositoryImpl.implementaBuscaPorId(idColaborador,
                colaboradorLogado.getEmpresa().getId());

        log.debug(ACESSA_METODO_BUSCA_ADVERTENCIA);
        AdvertenciaEntity advertenciaEntity = realizaBuscaAdvertenciaPorIdNaListaDeAdvertenciasDoColaborador(
                colaborador.getAdvertencias(), idAdvertencia);

        log.debug("Iniciando exportação do PDF padrão");
        advertenciaRelatorioService.exportarPdf(res, colaboradorLogado, colaborador, advertenciaEntity);

        log.debug(Constantes.INICIANDO_SALVAMENTO_HISTORICO_COLABORADOR);
        acaoService.salvaHistoricoColaborador(colaboradorLogado, colaborador.getId(),
                ModulosEnum.COLABORADORES, TipoAcaoEnum.RELATORIO, "Arquivo padrão de advertência gerado");

        log.info("Exportação do PDF padrão da advertência de id {} realizado com sucesso", idAdvertencia);
    }

    public AdvertenciaEntity realizaBuscaAdvertenciaPorIdNaListaDeAdvertenciasDoColaborador(
            List<AdvertenciaEntity> advertenciaList, Long idAdvertenciaBuscada) {

        log.debug("Método de busca de advertência por id na lista de advertências do colaborador acessado");

        AdvertenciaEntity advertenciaEntity = null;

        log.debug("Iniciando iteração por todas as advertências do colaborador...");
        for (AdvertenciaEntity advertencia : advertenciaList) {
            log.debug("Verificando se advertência de id {} possui o mesmo id recebido por parâmetro: ({})",
                    advertencia.getId(), idAdvertenciaBuscada);
            if (Objects.equals(advertencia.getId(), idAdvertenciaBuscada)) {
                log.debug("A advertência possui o mesmo id");
                advertenciaEntity = advertencia;
            }
        }

        if (advertenciaEntity == null) {
            log.error(NENHUMA_ADVERTENCIA_ENCONTRADA);
            throw new ObjectNotFoundException(NENHUMA_ADVERTENCIA_ENCONTRADA);
        }

        return advertenciaEntity;
    }

    public void geraAdvertenciaColaborador(ColaboradorEntity colaboradorLogado,
                                           HttpServletResponse res,
                                           Long idColaboradorAlvo,
                                           MultipartFile contratoAdvertencia,
                                           String advertenciaEmJson) throws IOException {

        log.debug("Método de serviço de criação de nova advertência acessado");

        log.debug(Constantes.VERIFICANDO_SE_COLABORADOR_PODE_ALTERAR_DADOS);
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

        log.debug(ATUALIZA_COLABORADOR_COM_ADVERTENCIA);
        colaboradorRepositoryImpl.implementaPersistencia(colaborador);

        log.debug("Persistência realizada com sucesso");

        log.debug("Iniciando acesso ao método de exportação de PDF para gerar a advertência em PDF...");
        advertenciaRelatorioService.exportarPdf(res, colaboradorLogado, colaborador, advertenciaEntity);

        log.debug(Constantes.INICIANDO_SALVAMENTO_HISTORICO_COLABORADOR);
        acaoService.salvaHistoricoColaborador(colaboradorLogado, colaborador.getId(),
                ModulosEnum.COLABORADORES, TipoAcaoEnum.ALTERACAO, "Advertência criada");

        log.info("Requisição finalizada com sucesso");
    }

    public TipoArquivoEnum realizaTratamentoTipoDeArquivoDoContratoAdvertencia(String tipoArquivo) {

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
