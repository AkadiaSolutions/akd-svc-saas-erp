package br.akd.svc.akadia.modules.erp.produtos.services;

import br.akd.svc.akadia.modules.erp.produtos.models.dto.response.page.ProdutoPageResponse;
import br.akd.svc.akadia.modules.erp.produtos.models.dto.response.ProdutoResponse;
import br.akd.svc.akadia.modules.erp.produtos.models.entity.ProdutoEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProdutoTypeConverter {
    public ProdutoPageResponse converteListaEntityListaResponse(Page<ProdutoEntity> produtoEntities) {
        log.debug("Método de conversão de itens do tipo Entity para itens do tipo Response acessado");

        log.debug("Criando lista vazia de objetos do tipo response...");
        List<ProdutoResponse> produtoResponses = new ArrayList<>();

        log.debug("Iniciando iteração da lista de entities obtida na busca para conversão para objetos do tipo " +
                "response...");
        for (ProdutoEntity produto : produtoEntities.getContent()) {
            ProdutoResponse produtoResponse = ProdutoResponse.builder()
                    .id(produto.getId())
                    .dataCadastro(produto.getDataCadastro())
                    .horaCadastro(produto.getHoraCadastro())
                    .codigoInterno(produto.getCodigoInterno())
                    .sigla(produto.getSigla())
                    .marca(produto.getMarca())
                    .descricao(produto.getDescricao())
                    .categoria(produto.getCategoria())
                    .quantidadeMinima(produto.getQuantidadeMinima())
                    .quantidadeAtacado(produto.getQuantidadeAtacado())
                    .quantidade(produto.getQuantidade())
                    .codigoNcm(produto.getCodigoNcm())
                    .pesoUnitario(produto.getPesoUnitario())
                    .tipoProduto(produto.getTipoProduto().getDesc())
                    .unidadeComercial(produto.getUnidadeComercial().getDesc())
                    .tipoPeso(produto.getTipoPeso().getDesc())
                    .nomeColaboradorResponsavel(produto.getColaboradorResponsavel().getNome())
                    .build();
            produtoResponses.add(produtoResponse);
        }
        log.debug("Iteração finalizada com sucesso. Listagem de objetos do tipo response preenchida");

        log.debug("Iniciando criação de objeto do tipo PageResponse, que possui todas as informações referentes " +
                "ao conteúdo da página e à paginação...");
        produtoEntities.getPageable();
        ProdutoPageResponse produtoPageResponse = ProdutoPageResponse.builder()
                .content(produtoResponses)
                .empty(produtoEntities.isEmpty())
                .first(produtoEntities.isFirst())
                .last(produtoEntities.isLast())
                .number(produtoEntities.getNumber())
                .numberOfElements(produtoEntities.getNumberOfElements())
                .pageNumber(produtoEntities.getPageable().getPageNumber())
                .pageSize(produtoEntities.getPageable().getPageSize())
                .paged(produtoEntities.getPageable().isPaged())
                .unpaged(produtoEntities.getPageable().isUnpaged())
                .size(produtoEntities.getSize())
                .totalElements(produtoEntities.getTotalElements())
                .totalPages(produtoEntities.getTotalPages())
                .build();

        log.debug("Objeto do tipo ProdutoPageResponse criado com sucesso. Retornando objeto...");
        return produtoPageResponse;
    }

    public ProdutoResponse converteEntityParaResponse(ProdutoEntity produto) {
        log.debug("Método de conversão de objeto do tipo ProdutoEntity para objeto do tipo ProdutoResponse acessado");

        log.debug("Iniciando construção do objeto ProdutoResponse...");
        ProdutoResponse produtoResponse = ProdutoResponse.builder()
                .id(produto.getId())
                .dataCadastro(produto.getDataCadastro())
                .horaCadastro(produto.getHoraCadastro())
                .codigoInterno(produto.getCodigoInterno())
                .sigla(produto.getSigla())
                .marca(produto.getMarca())
                .descricao(produto.getDescricao())
                .categoria(produto.getCategoria())
                .quantidadeMinima(produto.getQuantidadeMinima())
                .quantidadeAtacado(produto.getQuantidadeAtacado())
                .quantidade(produto.getQuantidade())
                .codigoNcm(produto.getCodigoNcm())
                .pesoUnitario(produto.getPesoUnitario())
                .tipoProduto(produto.getTipoProduto().getDesc())
                .unidadeComercial(produto.getUnidadeComercial().getDesc())
                .tipoPeso(produto.getTipoPeso().getDesc())
                .nomeColaboradorResponsavel(produto.getColaboradorResponsavel().getNome())
                .build();

        log.debug("Objeto ProdutoResponse buildado com sucesso. Retornando...");
        return produtoResponse;
    }
}
