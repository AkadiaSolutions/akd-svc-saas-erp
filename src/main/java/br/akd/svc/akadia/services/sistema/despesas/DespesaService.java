package br.akd.svc.akadia.services.sistema.despesas;

import br.akd.svc.akadia.models.dto.sistema.despesas.request.DespesaRequest;
import br.akd.svc.akadia.models.dto.sistema.despesas.response.DespesaPageResponse;
import br.akd.svc.akadia.models.dto.sistema.despesas.response.DespesaResponse;
import br.akd.svc.akadia.models.entities.global.ExclusaoEntity;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.ColaboradorEntity;
import br.akd.svc.akadia.models.entities.sistema.despesas.DespesaEntity;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.ModulosEnum;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.TipoAcaoEnum;
import br.akd.svc.akadia.models.enums.sistema.despesas.StatusDespesaEnum;
import br.akd.svc.akadia.models.enums.sistema.despesas.TipoRecorrenciaDespesaEnum;
import br.akd.svc.akadia.repositories.sistema.despesas.DespesaRepository;
import br.akd.svc.akadia.repositories.sistema.despesas.impl.DespesaRepositoryImpl;
import br.akd.svc.akadia.services.exceptions.InvalidRequestException;
import br.akd.svc.akadia.services.sistema.colaboradores.acao.AcaoService;
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
public class DespesaService {

    @Autowired
    AcaoService acaoService;

    @Autowired
    DespesaValidationService despesaValidationService;

    @Autowired
    DespesaTypeConverter despesaTypeConverter;

    @Autowired
    DespesaRepositoryImpl despesaRepositoryImpl;

    @Autowired
    DespesaRepository despesaRepository;

    String AGENDADO = "Agendado";

    String BUSCA_DESPESA_POR_ID = "Iniciando acesso ao método de implementação de busca de despesa por id...";

    public DespesaResponse criaNovaDespesa(ColaboradorEntity colaboradorLogado, DespesaRequest despesaRequest) {

        log.debug("Método de serviço de criação de nova despesa acessado");

        log.debug(Constantes.VERIFICANDO_SE_COLABORADOR_PODE_ALTERAR_DADOS);
        SecurityUtil.verificaSePodeRealizarAlteracoes(colaboradorLogado.getAcessoSistema());

        log.debug("Iniciando criação do objeto DespesaEntity...");
        DespesaEntity despesaEntity = DespesaEntity.builder()
                .dataCadastro(LocalDate.now().toString())
                .horaCadastro(LocalTime.now().toString())
                .dataPagamento(despesaRequest.getDataPagamento() == null
                        ? AGENDADO
                        : despesaRequest.getDataPagamento())
                .dataAgendamento(despesaRequest.getDataAgendamento() == null
                        ? "Pago"
                        : despesaRequest.getDataAgendamento())
                .descricao(despesaRequest.getDescricao())
                .valor(despesaRequest.getValor())
                .observacao(despesaRequest.getQtdRecorrencias() > 0
                        ? "Possui " + despesaRequest.getQtdRecorrencias() + " recorrência(s)"
                        : "Não possui recorrências")
                .tipoRecorrencia(despesaRequest.getQtdRecorrencias() > 0
                        ? TipoRecorrenciaDespesaEnum.PRINCIPAL
                        : TipoRecorrenciaDespesaEnum.SEM_RECORRENCIA)
                .statusDespesa(despesaRequest.getStatusDespesa())
                .tipoDespesa(despesaRequest.getTipoDespesa())
                .exclusao(null)
                .colaboradorResponsavel(colaboradorLogado)
                .empresa(colaboradorLogado.getEmpresa())
                .recorrencias(despesaRequest.getQtdRecorrencias() > 0
                        ? geraRecorrencias(colaboradorLogado, despesaRequest)
                        : new ArrayList<>())
                .build();
        log.debug("Objeto despesaEntity criado com sucesso");

        log.debug("Iniciando acesso ao método de implementação da persistência da despesa...");
        DespesaEntity despesaPersistida = despesaRepositoryImpl.implementaPersistencia(despesaEntity);

        log.debug(Constantes.INICIANDO_SALVAMENTO_HISTORICO_DESPESA);
        acaoService.salvaHistoricoColaborador(colaboradorLogado, despesaPersistida.getId(),
                ModulosEnum.DESPESAS, TipoAcaoEnum.CRIACAO, null);

        log.debug("Despesa persistida com sucesso. Convertendo despesaEntity para despesaResponse...");
        DespesaResponse despesaResponse = despesaTypeConverter.converteDespesaEntityParaDespesaResponse(despesaPersistida);

        log.info("Despesa criada com sucesso");
        return despesaResponse;
    }

    private List<DespesaEntity> geraRecorrencias(ColaboradorEntity colaboradorLogado, DespesaRequest despesaRequest) {

        List<DespesaEntity> despesas = new ArrayList<>();
        for (int i = 1; i <= despesaRequest.getQtdRecorrencias(); i++) {

            LocalDate dataDespesa;
            if (despesaRequest.getStatusDespesa().equals(StatusDespesaEnum.PAGO)) {
                dataDespesa = LocalDate.parse(despesaRequest.getDataPagamento()).plusMonths(i);
            } else {
                dataDespesa = LocalDate.parse(despesaRequest.getDataAgendamento()).plusMonths(i);
            }

            DespesaEntity despesa = DespesaEntity.builder()
                    .dataCadastro(LocalDate.now().toString())
                    .horaCadastro(LocalTime.now().toString())
                    .dataPagamento(AGENDADO)
                    .dataAgendamento(dataDespesa.toString())
                    .descricao(despesaRequest.getDescricao())
                    .valor(despesaRequest.getValor())
                    .observacao("Recorrência " + i + " de " + despesaRequest.getQtdRecorrencias())
                    .tipoRecorrencia(TipoRecorrenciaDespesaEnum.HERDEIRA)
                    .statusDespesa(StatusDespesaEnum.PENDENTE)
                    .tipoDespesa(despesaRequest.getTipoDespesa())
                    .exclusao(null)
                    .colaboradorResponsavel(colaboradorLogado)
                    .empresa(colaboradorLogado.getEmpresa())
                    .recorrencias(new ArrayList<>())
                    .build();

            despesas.add(despesa);

        }

        return despesas;
    }

    public DespesaResponse removeDespesa(ColaboradorEntity colaboradorLogado, Long id, Boolean removeRecorrencia) {
        log.debug("Método de serviço de remoção de despesa acessado");

        log.debug(BUSCA_DESPESA_POR_ID);
        DespesaEntity despesaEncontrada = despesaRepositoryImpl
                .implementaBuscaPorId(id, colaboradorLogado.getEmpresa().getId());

        log.debug("Iniciando acesso ao método de validação de exclusão de despesa que já foi excluído...");
        despesaValidationService.validaSeDespesaEstaExcluida(despesaEncontrada,
                Constantes.DESPESA_JA_EXCLUIDA);

        log.debug(Constantes.ATUALIZANDO_EXCLUSAO_DESPESA);
        ExclusaoEntity exclusaoEntity = ExclusaoEntity.builder()
                .dataExclusao(LocalDate.now().toString())
                .horaExclusao(LocalTime.now().toString())
                .responsavelExclusao(colaboradorLogado)
                .build();

        despesaEncontrada.setExclusao(exclusaoEntity);
        log.debug(Constantes.OBJETO_EXCLUSAO_DESPESA_SETADO_COM_SUCESSO, id);

        log.debug(Constantes.PERSISTINDO_DESPESA_EXCLUIDA);
        DespesaEntity despesaExcluida = despesaRepositoryImpl.implementaPersistencia(despesaEncontrada);

        if (Boolean.TRUE.equals(removeRecorrencia))
            removeRecorrenciasEmMassa(colaboradorLogado, despesaExcluida.getRecorrencias());

        log.debug(Constantes.INICIANDO_SALVAMENTO_HISTORICO_DESPESA);
        acaoService.salvaHistoricoColaborador(colaboradorLogado, null,
                ModulosEnum.DESPESAS, TipoAcaoEnum.REMOCAO, null);

        log.info("Despesa excluída com sucesso");
        return despesaTypeConverter.converteDespesaEntityParaDespesaResponse(despesaExcluida);
    }

    public void removeRecorrenciasEmMassa(ColaboradorEntity colaboradorLogado, List<DespesaEntity> despesas) {
        log.debug("Método de serviço de remoção de recorrências em massa acessado");

        log.debug("Iniciando acesso ao método de validação de exclusão de despesa que já foi excluída...");
        for (DespesaEntity despesa : despesas) {
            log.debug(Constantes.ATUALIZANDO_EXCLUSAO_DESPESA);
            ExclusaoEntity exclusao = ExclusaoEntity.builder()
                    .dataExclusao(LocalDate.now().toString())
                    .horaExclusao(LocalTime.now().toString())
                    .responsavelExclusao(colaboradorLogado)
                    .build();

            despesa.setExclusao(exclusao);
            log.debug(Constantes.OBJETO_EXCLUSAO_DESPESA_SETADO_COM_SUCESSO, despesa.getId());
        }

        log.debug("Verificando se listagem de despesas encontradas está preenchida...");
        if (!despesas.isEmpty()) {
            log.debug(Constantes.PERSISTINDO_DESPESA_EXCLUIDA);
            despesaRepositoryImpl.implementaPersistenciaEmMassa(despesas);
        } else throw new InvalidRequestException("Nenhuma despesa foi encontrada para remoção");

        log.info("Despesas excluídas com sucesso");
    }

    public void removeDespesasEmMassa(ColaboradorEntity colaboradorLogado, List<Long> ids) {
        log.debug("Método de serviço de remoção de despesas em massa acessado");

        List<DespesaEntity> despesasEncontradas = new ArrayList<>();

        for (Long id : ids) {
            log.debug(BUSCA_DESPESA_POR_ID);
            DespesaEntity despesaEncontrada = despesaRepositoryImpl
                    .implementaBuscaPorId(id, colaboradorLogado.getEmpresa().getId());
            despesasEncontradas.add(despesaEncontrada);
        }

        log.debug("Iniciando acesso ao método de validação de exclusão de despesa que já foi excluída...");
        for (DespesaEntity despesa : despesasEncontradas) {
            despesaValidationService.validaSeDespesaEstaExcluida(despesa,
                    Constantes.DESPESA_JA_EXCLUIDA);
            log.debug(Constantes.ATUALIZANDO_EXCLUSAO_DESPESA);
            ExclusaoEntity exclusao = ExclusaoEntity.builder()
                    .dataExclusao(LocalDate.now().toString())
                    .horaExclusao(LocalTime.now().toString())
                    .responsavelExclusao(colaboradorLogado)
                    .build();

            despesa.setExclusao(exclusao);
            log.debug(Constantes.OBJETO_EXCLUSAO_DESPESA_SETADO_COM_SUCESSO, despesa.getId());
        }

        log.debug("Verificando se listagem de despesas encontradas está preenchida...");
        if (!despesasEncontradas.isEmpty()) {
            log.debug(Constantes.PERSISTINDO_DESPESA_EXCLUIDA);
            despesaRepositoryImpl.implementaPersistenciaEmMassa(despesasEncontradas);

            log.debug(Constantes.INICIANDO_SALVAMENTO_HISTORICO_DESPESA);
            acaoService.salvaHistoricoColaborador(colaboradorLogado, null,
                    ModulosEnum.DESPESAS, TipoAcaoEnum.REMOCAO_EM_MASSA, despesasEncontradas.size() + " Itens removidos");
        } else throw new InvalidRequestException("Nenhuma despesa foi encontrada para remoção");

        log.info("Despesas excluídas com sucesso");
    }

    public DespesaResponse atualizaDespesa(ColaboradorEntity colaboradorLogado, Long id, DespesaRequest despesaRequest) {
        log.debug("Método de serviço de atualização de despesa acessado");

        log.debug(Constantes.VERIFICANDO_SE_COLABORADOR_PODE_ALTERAR_DADOS);
        SecurityUtil.verificaSePodeRealizarAlteracoes(colaboradorLogado.getAcessoSistema());

        log.debug(BUSCA_DESPESA_POR_ID);
        DespesaEntity despesaEncontrada = despesaRepositoryImpl.implementaBuscaPorId(id, colaboradorLogado.getEmpresa().getId());

        log.debug("Iniciando acesso ao método de validação de alteração de dados de despesa excluída...");
        despesaValidationService.validaSeDespesaEstaExcluida(despesaEncontrada,
                "Não é possível atualizar um despesa excluída");

        log.debug("Iniciando criação do objeto DespesaEntity...");
        DespesaEntity novaDespesaAtualizada = DespesaEntity.builder()
                .id(despesaEncontrada.getId())
                .dataCadastro(despesaEncontrada.getDataCadastro())
                .horaCadastro(despesaEncontrada.getHoraCadastro())
                .dataPagamento(despesaRequest.getDataPagamento() == null
                        ? AGENDADO
                        : despesaRequest.getDataPagamento())
                .dataAgendamento(despesaRequest.getDataAgendamento() == null
                        ? "Pago"
                        : despesaRequest.getDataAgendamento())
                .descricao(despesaRequest.getDescricao())
                .valor(despesaRequest.getValor())
                .observacao(despesaEncontrada.getObservacao())
                .tipoRecorrencia(despesaEncontrada.getTipoRecorrencia())
                .statusDespesa(despesaRequest.getStatusDespesa())
                .tipoDespesa(despesaRequest.getTipoDespesa())
                .exclusao(null)
                .colaboradorResponsavel(despesaEncontrada.getColaboradorResponsavel())
                .empresa(despesaEncontrada.getEmpresa())
                .recorrencias(despesaEncontrada.getRecorrencias())
                .build();
        log.debug("Objeto despesa construído com sucesso");

        log.debug("Iniciando acesso ao método de implementação da persistência da despesa...");
        DespesaEntity despesaPersistida = despesaRepositoryImpl.implementaPersistencia(novaDespesaAtualizada);

        log.debug(Constantes.INICIANDO_SALVAMENTO_HISTORICO_COLABORADOR);
        acaoService.salvaHistoricoColaborador(colaboradorLogado, despesaPersistida.getId(),
                ModulosEnum.DESPESAS, TipoAcaoEnum.ALTERACAO, null);

        log.debug("Despesa persistida com sucesso. Convertendo DespesaEntity para DespesaResponse...");
        DespesaResponse despesaResponse = despesaTypeConverter.converteDespesaEntityParaDespesaResponse(despesaPersistida);

        log.info("Despesa atualizada com sucesso");
        return despesaResponse;
    }

    public DespesaResponse realizaBuscaDeDespesaPorId(ColaboradorEntity colaboradorLogado, Long id) {
        log.debug("Método de serviço de obtenção de despesa por id. ID recebido: {}", id);

        log.debug("Acessando repositório de busca de despesa por ID...");
        DespesaEntity despesa = despesaRepositoryImpl.implementaBuscaPorId(id, colaboradorLogado.getEmpresa().getId());

        log.debug("Busca de despesas por id realizada com sucesso. Acessando método de conversão dos objeto do tipo " +
                "Entity para objeto do tipo Response...");
        DespesaResponse despesaResponse = despesaTypeConverter.converteDespesaEntityParaDespesaResponse(despesa);
        log.debug("Conversão de tipagem realizada com sucesso");

        log.info("A busca de despesa por id foi realizada com sucesso");
        return despesaResponse;
    }

    public DespesaPageResponse realizaBuscaPaginadaPorDespesas(ColaboradorEntity colaboradorLogado,
                                                               Pageable pageable,
                                                               String mesAno,
                                                               String campoBusca) {
        log.debug("Método de serviço de obtenção paginada de despesas acessado. Campo de busca: {}",
                campoBusca != null ? campoBusca : "Nulo");

        String dataInicio = mesAno + "-01";
        String dataFim = mesAno + "-31";

        log.debug("Acessando repositório de busca de despesas");
        Page<DespesaEntity> despesaPage = campoBusca != null && !campoBusca.isEmpty()
                ? despesaRepository
                .buscaPorDespesasTypeAhead(pageable, dataInicio, dataFim, campoBusca, colaboradorLogado.getEmpresa().getId())
                : despesaRepository
                .buscaPorDespesas(pageable, dataInicio, dataFim, colaboradorLogado.getEmpresa().getId());

        log.debug("Busca de despesas por paginação realizada com sucesso. Acessando método de conversão dos objetos do tipo " +
                "Entity para objetos do tipo Response...");
        DespesaPageResponse despesaPageResponse = despesaTypeConverter
                .converteListaDeDespesasEntityParaDespesasResponse(despesaPage);
        log.debug("Conversão de tipagem realizada com sucesso");

        log.info("A busca paginada de despesas foi realizada com sucesso");
        return despesaPageResponse;
    }

}
