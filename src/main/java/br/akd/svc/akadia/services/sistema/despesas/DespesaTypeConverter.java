package br.akd.svc.akadia.services.sistema.despesas;

import br.akd.svc.akadia.models.dto.sistema.despesas.response.DespesaPageResponse;
import br.akd.svc.akadia.models.dto.sistema.despesas.response.DespesaResponse;
import br.akd.svc.akadia.models.entities.sistema.despesas.DespesaEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DespesaTypeConverter {
    public DespesaPageResponse converteListaDeDespesasEntityParaDespesasResponse(Page<DespesaEntity> despesaEntities) {
        log.debug("Método de conversão de despesas do tipo Entity para despesas do tipo Response acessado");

        log.debug("Criando lista vazia de objetos do tipo DespesaResponse...");
        List<DespesaResponse> despesasResponse = new ArrayList<>();

        log.debug("Iniciando iteração da lista de DespesaEntity obtida na busca para conversão para objetos do tipo " +
                "DespesaResponse...");
        for (DespesaEntity despesa : despesaEntities.getContent()) {
            DespesaResponse despesaResponse = DespesaResponse.builder()
                    .id(despesa.getId())
                    .dataCadastro(despesa.getDataCadastro())
                    .horaCadastro(despesa.getHoraCadastro())
                    .dataPagamento(despesa.getDataPagamento())
                    .dataAgendamento(despesa.getDataAgendamento())
                    .descricao(despesa.getDescricao())
                    .valor(despesa.getValor())
                    .qtdRecorrencias(despesa.getRecorrencias().size())
                    .observacao(despesa.getObservacao())
                    .statusDespesa(despesa.getStatusDespesa())
                    .tipoDespesa(despesa.getTipoDespesa())
                    .tipoRecorrencia(despesa.getTipoRecorrencia())
                    .build();
            despesasResponse.add(despesaResponse);
        }
        log.debug("Iteração finalizada com sucesso. Listagem de objetos do tipo DespesaResponse preenchida");

        log.debug("Iniciando criação de objeto do tipo DespesaPageResponse, que possui todas as informações referentes " +
                "ao conteúdo da página e à paginação...");
        despesaEntities.getPageable();
        DespesaPageResponse despesaPageResponse = DespesaPageResponse.builder()
                .content(despesasResponse)
                .empty(despesaEntities.isEmpty())
                .first(despesaEntities.isFirst())
                .last(despesaEntities.isLast())
                .number(despesaEntities.getNumber())
                .numberOfElements(despesaEntities.getNumberOfElements())
                .pageNumber(despesaEntities.getPageable().getPageNumber())
                .pageSize(despesaEntities.getPageable().getPageSize())
                .paged(despesaEntities.getPageable().isPaged())
                .unpaged(despesaEntities.getPageable().isUnpaged())
                .size(despesaEntities.getSize())
                .totalElements(despesaEntities.getTotalElements())
                .totalPages(despesaEntities.getTotalPages())
                .build();

        log.debug("Objeto do tipo DespesaPageResponse criado com sucesso. Retornando objeto...");
        return despesaPageResponse;
    }

    public DespesaResponse converteDespesaEntityParaDespesaResponse(DespesaEntity despesa) {
        log.debug("Método de conversão de objeto do tipo DespesaEntity para objeto do tipo DespesaResponse acessado");

        log.debug("Iniciando construção do objeto DespesaResponse...");
        DespesaResponse despesaResponse = DespesaResponse.builder()
                .id(despesa.getId())
                .dataCadastro(despesa.getDataCadastro())
                .horaCadastro(despesa.getHoraCadastro())
                .dataPagamento(despesa.getDataPagamento())
                .dataAgendamento(despesa.getDataAgendamento())
                .descricao(despesa.getDescricao())
                .valor(despesa.getValor())
                .qtdRecorrencias(despesa.getRecorrencias().size())
                .observacao(despesa.getObservacao())
                .statusDespesa(despesa.getStatusDespesa())
                .tipoDespesa(despesa.getTipoDespesa())
                .tipoRecorrencia(despesa.getTipoRecorrencia())
                .build();

        log.debug("Objeto DespesaResponse buildado com sucesso. Retornando...");
        return despesaResponse;
    }
}
