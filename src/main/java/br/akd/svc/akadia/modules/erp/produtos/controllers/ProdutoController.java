package br.akd.svc.akadia.modules.erp.produtos.controllers;

import br.akd.svc.akadia.config.security.JWTUtil;
import br.akd.svc.akadia.modules.erp.clientes.models.dto.response.ClienteResponse;
import br.akd.svc.akadia.modules.erp.produtos.models.dto.request.ProdutoRequest;
import br.akd.svc.akadia.modules.erp.produtos.models.dto.response.page.ProdutoPageResponse;
import br.akd.svc.akadia.modules.erp.produtos.models.dto.response.ProdutoResponse;
import br.akd.svc.akadia.exceptions.InvalidRequestException;
import br.akd.svc.akadia.exceptions.ObjectNotFoundException;
import br.akd.svc.akadia.modules.erp.produtos.services.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

@Slf4j
@CrossOrigin
@RestController
@Api(value = "Esta API disponibiliza os endpoints de CRUD da entidade Produto")
@Produces({MediaType.APPLICATION_JSON, "application/json"})
@Consumes({MediaType.APPLICATION_JSON, "application/json"})
@RequestMapping("api/sistema/v1/estoque")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @Autowired
    JWTUtil jwtUtil;

    @PostMapping
    @ApiOperation(
            value = "Criação de novo produto",
            notes = "Esse endpoint tem como objetivo realizar a criação de um novo produto na base dados da empresa",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 201, message = "Produto persistido com sucesso", response = ClienteResponse.class),
            @ApiResponse(code = 400, message = "O produto informado já existe", response = InvalidRequestException.class),
            @ApiResponse(code = 400, message = "Erro de requisição inválida", response = InvalidRequestException.class)
    })
    @PreAuthorize("hasAnyRole('ESTOQUE')")
    public ResponseEntity<ProdutoResponse> criaNovoProduto(HttpServletRequest req,
                                                           @Valid @RequestBody ProdutoRequest produtoRequest) {
        log.info("Método controlador de criação de novo produto acessado");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(produtoService.criaNovoProduto(jwtUtil.obtemUsuarioAtivo(req), produtoRequest));
    }

    @GetMapping("/{id}")
    @ApiOperation(
            value = "Busca de produto por id",
            notes = "Esse endpoint tem como objetivo realizar a busca de um produto pelo id recebido pelo parâmetro",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "A busca de produto por id foi realizada com sucesso",
                    response = ProdutoResponse.class),
            @ApiResponse(code = 400, message = "Nenhum produto foi encontrado com o id informado",
                    response = ObjectNotFoundException.class),
    })
    @PreAuthorize("hasAnyRole('ESTOQUE')")
    public ResponseEntity<ProdutoResponse> obtemObjetoPorId(
            @PathVariable("id") UUID id,
            HttpServletRequest req) {
        log.info("Endpoint de busca de produto por id acessado. ID recebido: {}", id);
        return ResponseEntity.ok().body(produtoService.realizaBuscaPorId(
                jwtUtil.obtemUsuarioAtivo(req), id));
    }

    @GetMapping
    @ApiOperation(
            value = "Busca paginada por produtos cadastrados",
            notes = "Esse endpoint tem como objetivo realizar a busca paginada de produtos cadastrados na empresa do " +
                    "usuário que acionou a requisição com os filtros de busca enviados pelo usuário",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "A busca paginada de produtos foi realizada com sucesso",
                    response = ProdutoPageResponse.class),
    })
    @PreAuthorize("hasAnyRole('ESTOQUE')")
    public ResponseEntity<ProdutoPageResponse> obtemProdutosPaginados(
            @RequestParam(value = "busca", required = false) String busca,
            Pageable pageable,
            HttpServletRequest req) {
        log.info("Endpoint de busca paginada por produtos acessado. Filtros de busca: {}",
                busca == null ? "Nulo" : busca);
        return ResponseEntity.ok().body(produtoService.realizaBuscaPaginada(
                jwtUtil.obtemUsuarioAtivo(req), pageable, busca));
    }

}
