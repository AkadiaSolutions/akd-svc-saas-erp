package br.akd.svc.akadia.modules.erp.produtos.services;

import br.akd.svc.akadia.modules.erp.produtos.models.dto.request.ProdutoRequest;
import br.akd.svc.akadia.modules.erp.produtos.models.dto.response.page.ProdutoPageResponse;
import br.akd.svc.akadia.modules.erp.produtos.models.dto.response.ProdutoResponse;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.entity.ColaboradorEntity;
import br.akd.svc.akadia.modules.erp.precos.models.entity.PrecoEntity;
import br.akd.svc.akadia.modules.erp.produtos.models.entity.ProdutoEntity;
import br.akd.svc.akadia.modules.erp.produtos.models.entity.id.ProdutoId;
import br.akd.svc.akadia.modules.erp.produtos.repository.ProdutoRepository;
import br.akd.svc.akadia.modules.erp.produtos.repository.impl.ProdutoRepositoryImpl;
import br.akd.svc.akadia.modules.erp.colaboradores.acao.services.AcaoService;
import br.akd.svc.akadia.utils.Constantes;
import br.akd.svc.akadia.utils.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

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

        log.debug("Validando se código interno informado já existe...");


        log.debug("Iniciando criação do objeto ProdutoEntity...");
        ProdutoEntity produtoEntity = new ProdutoEntity().buildFromRequest(produtoRequest, colaboradorLogado);
        produtoEntity.setPrecos(
                new PrecoEntity().realizaValidacaoCriacaoListaPrecos(produtoRequest.getPrecos(), colaboradorLogado));
        log.debug("Objeto produtoEntity criado com sucesso");

        log.debug("Iniciando acesso ao método de implementação da persistência do produto...");
        ProdutoEntity produtoPersistido =
                produtoRepositoryImpl.implementaPersistencia(produtoEntity);

        //TODO AJUSTAR SALVAMENTO DE HISTÓRICO - DEPENDE DE TUDO O QUE SALVA HISTÓRICO TER SUA CHAVE ALTERADA PARA UUID
//        log.debug(Constantes.INICIANDO_SALVAMENTO_HISTORICO_PRODUTO);
//        acaoService.salvaHistoricoColaborador(colaboradorLogado, produtoPersistido.getId(),
//                ModulosEnum.ESTOQUE, TipoAcaoEnum.CRIACAO, null);

        log.debug("Produto persistido com sucesso. Convertendo produtoEntity para produtoResponse...");
        ProdutoResponse produtoResponse = produtoTypeConverter
                .converteEntityParaResponse(produtoPersistido);

        log.info("Produto criado com sucesso");
        return produtoResponse;
    }

    public ProdutoResponse realizaBuscaPorId(ColaboradorEntity colaboradorLogado, UUID id) {
        log.debug("Método de serviço de obtenção de produto por id. ID recebido: {}", id);

        log.debug("Acessando repositório de busca de produto por ID...");
        ProdutoEntity produto = produtoRepositoryImpl
                .implementaBuscaPorId(new ProdutoId(id, colaboradorLogado.getEmpresa().getId()));

        log.debug("Busca de produtos por id realizada com sucesso. Acessando método de conversão dos objeto do tipo " +
                "Entity para objeto do tipo Response...");
        ProdutoResponse produtoResponse = produtoTypeConverter
                .converteEntityParaResponse(produto);
        log.debug("Conversão de tipagem realizada com sucesso");

        log.info("A busca de produto por id foi realizada com sucesso");
        return produtoResponse;
    }

    public ProdutoPageResponse realizaBuscaPaginada(ColaboradorEntity colaboradorLogado,
                                                    Pageable pageable,
                                                    String campoBusca) {
        log.debug("Método de serviço de obtenção paginada de produtos acessado. Campo de busca: {}",
                campoBusca != null ? campoBusca : "Nulo");

        log.debug("Acessando repositório de busca de produtos");
        Page<ProdutoEntity> produtoPage = campoBusca != null && !campoBusca.isEmpty()
                ? produtoRepository.buscaPaginadaTypeAhead(pageable, campoBusca, colaboradorLogado.getEmpresa().getId())
                : produtoRepository.buscaPaginada(pageable, colaboradorLogado.getEmpresa().getId());

        log.debug("Busca de produtos por paginação realizada com sucesso. Acessando método de conversão dos objetos do tipo " +
                "Entity para objetos do tipo Response...");
        ProdutoPageResponse produtoPageResponse =
                produtoTypeConverter.converteListaEntityListaResponse(produtoPage);
        log.debug("Conversão de tipagem realizada com sucesso");

        log.info("A busca paginada de produtos foi realizada com sucesso");
        return produtoPageResponse;
    }
}
