package br.akd.svc.akadia.controllers.sistema.produtos;

import br.akd.svc.akadia.config.security.JWTUtil;
import br.akd.svc.akadia.models.dto.sistema.clientes.responses.ClienteResponse;
import br.akd.svc.akadia.models.dto.sistema.produtos.request.ProdutoRequest;
import br.akd.svc.akadia.models.dto.sistema.produtos.response.ProdutoResponse;
import br.akd.svc.akadia.services.exceptions.InvalidRequestException;
import br.akd.svc.akadia.services.sistema.produtos.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
    @PreAuthorize("hasAnyRole('CLIENTES')")
    public ResponseEntity<ProdutoResponse> criaNovoProduto(HttpServletRequest req,
                                                           @RequestBody ProdutoRequest produtoRequest) {
        log.info("Método controlador de criação de novo produto acessado");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(produtoService.criaNovoProduto(jwtUtil.obtemUsuarioAtivo(req), produtoRequest));
    }

}
