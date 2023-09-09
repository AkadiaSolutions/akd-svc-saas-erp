package br.akd.svc.akadia.services.sistema.produtos;

import br.akd.svc.akadia.models.dto.sistema.produtos.request.ProdutoRequest;
import br.akd.svc.akadia.models.dto.sistema.produtos.response.ProdutoResponse;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.ColaboradorEntity;
import br.akd.svc.akadia.models.entities.sistema.produto.CategoriaProdutoEntity;
import br.akd.svc.akadia.models.entities.sistema.produto.ProdutoEntity;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.ModulosEnum;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.TipoAcaoEnum;
import br.akd.svc.akadia.repositories.sistema.produtos.impl.ProdutoRepositoryImpl;
import br.akd.svc.akadia.services.sistema.colaboradores.acao.AcaoService;
import br.akd.svc.akadia.utils.Constantes;
import br.akd.svc.akadia.utils.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

@Slf4j
@Service
public class ProdutoService {

    @Autowired
    ProdutoRepositoryImpl produtoRepositoryImpl;

    @Autowired
    ProdutoTypeConverter produtoTypeConverter;

    @Autowired
    AcaoService acaoService;

    public ProdutoResponse criaNovoProduto(ColaboradorEntity colaboradorLogado, ProdutoRequest produtoRequest) {

        log.debug("Método de serviço de criação de novo produto acessado");

        log.debug(Constantes.VERIFICANDO_SE_COLABORADOR_PODE_ALTERAR_DADOS);
        SecurityUtil.verificaSePodeRealizarAlteracoes(colaboradorLogado.getAcessoSistema());

        log.debug("Iniciando criação do objeto ProdutoEntity...");
        ProdutoEntity produtoEntity = ProdutoEntity.builder()
                .dataCadastro(LocalDate.now().toString())
                .horaCadastro(LocalTime.now().toString())
                .codigoInterno(produtoRequest.getCodigoInterno())
                .sigla(produtoRequest.getSigla())
                .marca(produtoRequest.getMarca())
                .descricao(produtoRequest.getDescricao())
                .quantidadeMinima(produtoRequest.getQuantidadeMinima())
                .quantidadeAtacado(produtoRequest.getQuantidadeAtacado())
                .quantidade(0)
                .codigoNcm(produtoRequest.getCodigoNcm())
                .pesoUnitario(produtoRequest.getPesoUnitario())
                .tipoProduto(produtoRequest.getTipoProduto())
                .unidadeComercial(produtoRequest.getUnidadeComercial())
                .categoriaProduto(CategoriaProdutoEntity.builder()
                        .nome(produtoRequest.getCategoriaProduto().getNome())
                        .descricao(produtoRequest.getCategoriaProduto().getDescricao())
                        .colaboradorResponsavel(colaboradorLogado)
                        .empresa(colaboradorLogado.getEmpresa())
                        .build())
                .precos(produtoRequest.getPrecos())
                .historicoCompras(new ArrayList<>())
                .colaboradorResponsavel(colaboradorLogado)
                .empresa(colaboradorLogado.getEmpresa())
                .build();
        log.debug("Objeto produtoEntity criado com sucesso");

        log.debug("Iniciando acesso ao método de implementação da persistência do produto...");
        ProdutoEntity produtoPersistido =
                produtoRepositoryImpl.implementaPersistencia(produtoEntity);

        log.debug(Constantes.INICIANDO_SALVAMENTO_HISTORICO_PRODUTO);
        acaoService.salvaHistoricoColaborador(colaboradorLogado, produtoPersistido.getId(),
                ModulosEnum.ESTOQUE, TipoAcaoEnum.CRIACAO, null);

        log.debug("Produto persistido com sucesso. Convertendo produtoEntity para produtoResponse...");
        ProdutoResponse produtoResponse = produtoTypeConverter
                .converteEntityParaResponse(produtoPersistido);

        log.info("Produto criado com sucesso");
        return produtoResponse;
    }

}
