package br.akd.svc.akadia.modules.erp.patrimonios.services;

import br.akd.svc.akadia.modules.erp.patrimonios.models.dto.request.PatrimonioRequest;
import br.akd.svc.akadia.modules.erp.patrimonios.models.dto.response.page.PatrimonioPageResponse;
import br.akd.svc.akadia.modules.erp.patrimonios.models.dto.response.PatrimonioResponse;
import br.akd.svc.akadia.modules.global.entity.ExclusaoEntity;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.entity.ColaboradorEntity;
import br.akd.svc.akadia.modules.erp.patrimonios.models.entity.PatrimonioEntity;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.enums.ModulosEnum;
import br.akd.svc.akadia.modules.erp.colaboradores.acao.models.enums.TipoAcaoEnum;
import br.akd.svc.akadia.modules.erp.patrimonios.repository.PatrimonioRepository;
import br.akd.svc.akadia.modules.erp.patrimonios.repository.impl.PatrimonioRepositoryImpl;
import br.akd.svc.akadia.exceptions.InvalidRequestException;
import br.akd.svc.akadia.modules.erp.colaboradores.acao.services.AcaoService;
import br.akd.svc.akadia.utils.Constantes;
import br.akd.svc.akadia.utils.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class PatrimonioService {

    @Autowired
    PatrimonioRepository patrimonioRepository;

    @Autowired
    PatrimonioRepositoryImpl patrimonioRepositoryImpl;

    @Autowired
    PatrimonioValidationService patrimonioValidationService;

    @Autowired
    PatrimonioTypeConverter patrimonioTypeConverter;

    @Autowired
    AcaoService acaoService;

    String INICIA_BUSCA_POR_ID = "Iniciando acesso ao método de implementação de busca de patrimônio por id...";

    public PatrimonioResponse criaNovoPatrimonio(ColaboradorEntity colaboradorLogado, PatrimonioRequest patrimonioRequest) {

        log.debug("Método de serviço de criação de novo patrimônio acessado");

        log.debug(Constantes.VERIFICANDO_SE_COLABORADOR_PODE_ALTERAR_DADOS);
        SecurityUtil.verificaSePodeRealizarAlteracoes(colaboradorLogado.getAcessoSistema());

        log.debug("Iniciando criação do objeto PatrimonioEntity...");
        PatrimonioEntity patrimonioEntity = PatrimonioEntity.builder()
                .dataCadastro(LocalDate.now().toString())
                .horaCadastro(LocalTime.now().toString())
                .dataEntrada(patrimonioRequest.getDataEntrada())
                .descricao(patrimonioRequest.getDescricao())
                .valor(patrimonioRequest.getValor())
                .tipoPatrimonio(patrimonioRequest.getTipoPatrimonio())
                .exclusao(null)
                .colaboradorResponsavel(colaboradorLogado)
                .empresa(colaboradorLogado.getEmpresa())
                .build();
        log.debug("Objeto patrimonioEntity criado com sucesso");

        log.debug("Iniciando acesso ao método de implementação da persistência do patrimônio...");
        PatrimonioEntity patrimonioPersistido = patrimonioRepositoryImpl.implementaPersistencia(patrimonioEntity);

        log.debug(Constantes.INICIANDO_SALVAMENTO_HISTORICO_PATRIMONIO);
        acaoService.salvaHistoricoColaborador(colaboradorLogado, patrimonioPersistido.getId(),
                ModulosEnum.PATRIMONIOS, TipoAcaoEnum.CRIACAO, null);

        log.debug("Patrimônio persistido com sucesso. Convertendo patrimonioEntity para patrimonioResponse...");
        PatrimonioResponse patrimonioResponse = patrimonioTypeConverter
                .converteEntityParaResponse(patrimonioPersistido);

        log.info("Patrimônio criado com sucesso");
        return patrimonioResponse;
    }

    public PatrimonioResponse atualizaObjeto(ColaboradorEntity colaboradorLogado,
                                             Long id,
                                             PatrimonioRequest patrimonioRequest) {
        log.debug("Método de serviço de atualização de patrimônio acessado");

        log.debug(Constantes.VERIFICANDO_SE_COLABORADOR_PODE_ALTERAR_DADOS);
        SecurityUtil.verificaSePodeRealizarAlteracoes(colaboradorLogado.getAcessoSistema());

        log.debug(INICIA_BUSCA_POR_ID);
        PatrimonioEntity patrimonioEncontrado = patrimonioRepositoryImpl
                .implementaBuscaPorId(id, colaboradorLogado.getEmpresa().getId());

        log.debug("Iniciando acesso ao método de validação de alteração de dados de patrimônio excluído...");
        patrimonioValidationService
                .validaSeObjetoEstaExcluido(patrimonioEncontrado, "Não é possível atualizar um patrimônio excluído");

        log.debug("Iniciando criação do objeto PatrimonioEntity...");
        PatrimonioEntity patrimonioAtualizado = PatrimonioEntity.builder()
                .id(patrimonioEncontrado.getId())
                .dataCadastro(patrimonioEncontrado.getDataCadastro())
                .horaCadastro(patrimonioEncontrado.getHoraCadastro())
                .dataEntrada(patrimonioRequest.getDataEntrada())
                .descricao(patrimonioRequest.getDescricao())
                .valor(patrimonioRequest.getValor())
                .tipoPatrimonio(patrimonioRequest.getTipoPatrimonio())
                .exclusao(patrimonioEncontrado.getExclusao())
                .colaboradorResponsavel(patrimonioEncontrado.getColaboradorResponsavel())
                .empresa(patrimonioEncontrado.getEmpresa())
                .build();
        log.debug("Objeto patrimônio construído com sucesso");

        log.debug("Iniciando acesso ao método de implementação da persistência do patrimônio...");
        PatrimonioEntity patrimonioPersistido = patrimonioRepositoryImpl
                .implementaPersistencia(patrimonioAtualizado);

        log.debug(Constantes.INICIANDO_SALVAMENTO_HISTORICO_COLABORADOR);
        acaoService.salvaHistoricoColaborador(colaboradorLogado, patrimonioPersistido.getId(),
                ModulosEnum.PATRIMONIOS, TipoAcaoEnum.ALTERACAO, null);

        log.debug("Patrimônio persistido com sucesso. Convertendo Entity para Response...");
        PatrimonioResponse patrimonioResponse = patrimonioTypeConverter
                .converteEntityParaResponse(patrimonioPersistido);

        log.info("Patrimônio criado com sucesso");
        return patrimonioResponse;
    }

    public PatrimonioResponse removeObjeto(ColaboradorEntity colaboradorLogado, Long id) {
        log.debug("Método de serviço de remoção de patrimônio acessado");

        log.debug(INICIA_BUSCA_POR_ID);
        PatrimonioEntity patrimonioEncontrado = patrimonioRepositoryImpl
                .implementaBuscaPorId(id, colaboradorLogado.getEmpresa().getId());

        log.debug("Iniciando acesso ao método de validação de exclusão de patrimônio que já foi excluído...");
        patrimonioValidationService
                .validaSeObjetoEstaExcluido(patrimonioEncontrado,
                        "O patrimônio selecionado já foi excluído");

        log.debug("Atualizando objeto Exclusao do patrimônio com dados referentes à sua exclusão...");
        ExclusaoEntity exclusaoEntity = ExclusaoEntity.builder()
                .dataExclusao(LocalDate.now().toString())
                .horaExclusao(LocalTime.now().toString())
                .responsavelExclusao(colaboradorLogado)
                .build();

        patrimonioEncontrado.setExclusao(exclusaoEntity);
        log.debug("Objeto Exclusao do patrimônio de id {} setado com sucesso", id);

        log.debug("Persistindo patrimônio excluído no banco de dados...");
        PatrimonioEntity patrimonioExcluido = patrimonioRepositoryImpl
                .implementaPersistencia(patrimonioEncontrado);

        log.debug(Constantes.INICIANDO_SALVAMENTO_HISTORICO_COLABORADOR);
        acaoService.salvaHistoricoColaborador(colaboradorLogado, null,
                ModulosEnum.PATRIMONIOS, TipoAcaoEnum.REMOCAO, null);

        log.info("Patrimônio excluído com sucesso");
        return patrimonioTypeConverter.converteEntityParaResponse(patrimonioExcluido);
    }

    public void removeEmMassa(ColaboradorEntity colaboradorLogado, List<Long> ids) {
        log.debug("Método de serviço de remoção de patrimônios em massa acessado");

        List<PatrimonioEntity> patrimoniosEncontrados = new ArrayList<>();

        for (Long id : ids) {
            log.debug(INICIA_BUSCA_POR_ID);
            PatrimonioEntity patrimonioEncontrado = patrimonioRepositoryImpl
                    .implementaBuscaPorId(id, colaboradorLogado.getEmpresa().getId());
            patrimoniosEncontrados.add(patrimonioEncontrado);
        }

        log.debug("Iniciando acesso ao método de validação de exclusão de patrimônio que já foi excluído...");
        for (PatrimonioEntity patrimonio : patrimoniosEncontrados) {
            patrimonioValidationService.validaSeObjetoEstaExcluido(patrimonio,
                    "O patrimonio selecionado já foi excluído");
            log.debug("Atualizando objeto Exclusao do patrimonio com dados referentes à sua exclusão...");
            ExclusaoEntity exclusao = ExclusaoEntity.builder()
                    .dataExclusao(LocalDate.now().toString())
                    .horaExclusao(LocalTime.now().toString())
                    .responsavelExclusao(colaboradorLogado)
                    .build();

            patrimonio.setExclusao(exclusao);
            log.debug("Objeto Exclusao do patrimonio de id {} setado com sucesso", patrimonio.getId());
        }

        log.debug("Verificando se listagem de patrimônios encontrados está preenchida...");
        if (!patrimoniosEncontrados.isEmpty()) {
            log.debug("Persistindo patrimônio excluído no banco de dados...");
            patrimonioRepositoryImpl.implementaPersistenciaEmMassa(patrimoniosEncontrados);

            log.debug(Constantes.INICIANDO_SALVAMENTO_HISTORICO_COLABORADOR);
            acaoService.salvaHistoricoColaborador(colaboradorLogado, null,
                    ModulosEnum.PATRIMONIOS, TipoAcaoEnum.REMOCAO_EM_MASSA,
                    patrimoniosEncontrados.size() + " Itens removidos");
        } else throw new InvalidRequestException("Nenhum patrimônio foi encontrado para remoção");

        log.info("Patrimônios excluídos com sucesso");
    }

    public PatrimonioResponse realizaBuscaPorId(ColaboradorEntity colaboradorLogado, Long id) {
        log.debug("Método de serviço de obtenção de patrimônio por id. ID recebido: {}", id);

        log.debug("Acessando repositório de busca de patrimônio por ID...");
        PatrimonioEntity patrimonio = patrimonioRepositoryImpl
                .implementaBuscaPorId(id, colaboradorLogado.getEmpresa().getId());

        log.debug("Busca de patrimônios por id realizada com sucesso. Acessando método de conversão dos objeto do tipo " +
                "Entity para objeto do tipo Response...");
        PatrimonioResponse patrimonioResponse = patrimonioTypeConverter
                .converteEntityParaResponse(patrimonio);
        log.debug("Conversão de tipagem realizada com sucesso");

        log.info("A busca de patrimonio por id foi realizada com sucesso");
        return patrimonioResponse;
    }

    public PatrimonioPageResponse realizaBuscaPaginada(ColaboradorEntity colaboradorLogado,
                                                       Pageable pageable,
                                                       String campoBusca) {
        log.debug("Método de serviço de obtenção paginada de patrimônios acessado. Campo de busca: {}",
                campoBusca != null ? campoBusca : "Nulo");

        log.debug("Acessando repositório de busca de patrimonios");
        Page<PatrimonioEntity> patrimonioPage = campoBusca != null && !campoBusca.isEmpty()
                ? patrimonioRepository.buscaPaginadaTypeAhead(pageable, campoBusca, colaboradorLogado.getEmpresa().getId())
                : patrimonioRepository.buscaPaginada(pageable, colaboradorLogado.getEmpresa().getId());

        log.debug("Busca de patrimônios por paginação realizada com sucesso. Acessando método de conversão dos objetos do tipo " +
                "Entity para objetos do tipo Response...");
        PatrimonioPageResponse patrimonioPageResponse =
                patrimonioTypeConverter.converteListaEntityListaResponse(patrimonioPage);
        log.debug("Conversão de tipagem realizada com sucesso");

        log.info("A busca paginada de patrimônios foi realizada com sucesso");
        return patrimonioPageResponse;
    }
}
