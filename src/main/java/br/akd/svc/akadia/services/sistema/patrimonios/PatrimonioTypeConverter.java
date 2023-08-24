package br.akd.svc.akadia.services.sistema.patrimonios;

import br.akd.svc.akadia.models.dto.sistema.patrimonios.response.PatrimonioPageResponse;
import br.akd.svc.akadia.models.dto.sistema.patrimonios.response.PatrimonioResponse;
import br.akd.svc.akadia.models.entities.sistema.patrimonios.PatrimonioEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class PatrimonioTypeConverter {

    public PatrimonioPageResponse converteListaEntityListaResponse(Page<PatrimonioEntity> patrimonioEntities) {
        log.debug("Método de conversão de itens do tipo Entity para itens do tipo Response acessado");

        log.debug("Criando lista vazia de objetos do tipo response...");
        List<PatrimonioResponse> patrimonioResponses = new ArrayList<>();

        log.debug("Iniciando iteração da lista de entities obtida na busca para conversão para objetos do tipo " +
                "response...");
        for (PatrimonioEntity patrimonio : patrimonioEntities.getContent()) {
            PatrimonioResponse patrimonioResponse = PatrimonioResponse.builder()
                    .id(patrimonio.getId())
                    .dataCadastro(patrimonio.getDataCadastro())
                    .horaCadastro(patrimonio.getHoraCadastro())
                    .dataEntrada(patrimonio.getDataEntrada())
                    .descricao(patrimonio.getDescricao())
                    .valor(patrimonio.getValor())
                    .tipoPatrimonio(patrimonio.getTipoPatrimonio())
                    .build();
            patrimonioResponses.add(patrimonioResponse);
        }
        log.debug("Iteração finalizada com sucesso. Listagem de objetos do tipo response preenchida");

        log.debug("Iniciando criação de objeto do tipo PageResponse, que possui todas as informações referentes " +
                "ao conteúdo da página e à paginação...");
        patrimonioEntities.getPageable();
        PatrimonioPageResponse patrimonioPageResponse = PatrimonioPageResponse.builder()
                .content(patrimonioResponses)
                .empty(patrimonioEntities.isEmpty())
                .first(patrimonioEntities.isFirst())
                .last(patrimonioEntities.isLast())
                .number(patrimonioEntities.getNumber())
                .numberOfElements(patrimonioEntities.getNumberOfElements())
                .pageNumber(patrimonioEntities.getPageable().getPageNumber())
                .pageSize(patrimonioEntities.getPageable().getPageSize())
                .paged(patrimonioEntities.getPageable().isPaged())
                .unpaged(patrimonioEntities.getPageable().isUnpaged())
                .size(patrimonioEntities.getSize())
                .totalElements(patrimonioEntities.getTotalElements())
                .totalPages(patrimonioEntities.getTotalPages())
                .build();

        log.debug("Objeto do tipo PatrimonioPageResponse criado com sucesso. Retornando objeto...");
        return patrimonioPageResponse;
    }

    public PatrimonioResponse converteEntityParaResponse(PatrimonioEntity patrimonioEntity) {
        log.debug("Método de conversão de objeto do tipo PatrimonioEntity para objeto do tipo PatrimonioResponse acessado");

        log.debug("Iniciando construção do objeto PatrimonioResponse...");
        PatrimonioResponse patrimonioResponse = PatrimonioResponse.builder()
                .id(patrimonioEntity.getId())
                .dataCadastro(patrimonioEntity.getDataCadastro())
                .horaCadastro(patrimonioEntity.getHoraCadastro())
                .dataEntrada(patrimonioEntity.getDataEntrada())
                .descricao(patrimonioEntity.getDescricao())
                .valor(patrimonioEntity.getValor())
                .tipoPatrimonio(patrimonioEntity.getTipoPatrimonio())
                .build();

        log.debug("Objeto PatrimonioResponse buildado com sucesso. Retornando...");
        return patrimonioResponse;
    }
}
