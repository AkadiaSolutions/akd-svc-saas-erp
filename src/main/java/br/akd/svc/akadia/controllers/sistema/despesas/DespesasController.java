package br.akd.svc.akadia.controllers.sistema.despesas;

import br.akd.svc.akadia.config.security.JWTUtil;
import br.akd.svc.akadia.models.dto.sistema.clientes.responses.ClienteResponse;
import br.akd.svc.akadia.models.dto.sistema.despesas.request.DespesaRequest;
import br.akd.svc.akadia.models.dto.sistema.despesas.response.DespesaPageResponse;
import br.akd.svc.akadia.models.dto.sistema.despesas.response.DespesaResponse;
import br.akd.svc.akadia.services.exceptions.InvalidRequestException;
import br.akd.svc.akadia.services.exceptions.ObjectNotFoundException;
import br.akd.svc.akadia.services.sistema.despesas.DespesaService;
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
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@Api(value = "Essa API disponibiliza os endpoints de CRUD da entidade Despesa")
@Produces({MediaType.APPLICATION_JSON, "application/json"})
@Consumes({MediaType.APPLICATION_JSON, "application/json"})
@RequestMapping("/api/sistema/v1/despesas")
public class DespesasController {

    @Autowired
    DespesaService despesaService;

    @Autowired
    JWTUtil jwtUtil;

    @GetMapping("/{id}")
    @ApiOperation(
            value = "Busca de despesa por id",
            notes = "Esse endpoint tem como objetivo realizar a busca de uma despesa pelo id recebido pelo parâmetro",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "A busca de despesa por id foi realizada com sucesso",
                    response = ClienteResponse.class),
            @ApiResponse(code = 400, message = "Nenhuma despesa foi encontrada com o id informado",
                    response = ObjectNotFoundException.class),
    })
    @PreAuthorize("hasAnyRole('DESPESAS')")
    public ResponseEntity<DespesaResponse> obtemDespesaPorId(
            @PathVariable("id") Long id,
            HttpServletRequest req) {
        log.info("Endpoint de busca de despesa por id acessado. ID recebido: {}", id);
        return ResponseEntity.ok().body(despesaService.realizaBuscaDeDespesaPorId(
                jwtUtil.obtemUsuarioAtivo(req), id));
    }

    @PostMapping
    @ApiOperation(
            value = "Criação de nova despesa",
            notes = "Esse endpoint tem como objetivo realizar a criação de uma nova despesa na base dados da empresa",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 201, message = "Despesa persistida com sucesso", response = DespesaResponse.class),
            @ApiResponse(code = 400, message = "Erro de requisição inválida", response = InvalidRequestException.class)
    })
    @PreAuthorize("hasAnyRole('DESPESAS')")
    public ResponseEntity<DespesaResponse> criaNovaDespesa(HttpServletRequest req,
                                                           @RequestBody DespesaRequest despesaRequest) {
        log.info("Método controlador de criação de nova despesa acessada");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(despesaService.criaNovaDespesa(jwtUtil.obtemUsuarioAtivo(req), despesaRequest));
    }

    @DeleteMapping
    @ApiOperation(
            value = "Remoção de despesa em massa",
            notes = "Esse endpoint tem como objetivo realizar a exclusão de despesas em massa",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Despesas excluídas com sucesso"),
            @ApiResponse(code = 400, message = "Nenhuma despesa foi encontrada com o id informado",
                    response = ObjectNotFoundException.class),
            @ApiResponse(code = 400, message = "A despesa selecionada já foi excluída",
                    response = InvalidRequestException.class),
            @ApiResponse(code = 400, message = "Nenhuma despesa foi encontrada para remoção",
                    response = InvalidRequestException.class),
            @ApiResponse(code = 400, message = "Não é possível remover uma despesa que já foi excluída",
                    response = InvalidRequestException.class)
    })
    @PreAuthorize("hasAnyRole('DESPESAS')")
    public ResponseEntity<?> removeDespesasEmMassa(HttpServletRequest req,
                                                   @RequestBody List<Long> ids) {
        log.info("Método controlador de remoção de despesas em massa acessado");
        despesaService.removeDespesasEmMassa(jwtUtil.obtemUsuarioAtivo(req), ids);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(
            value = "Remoção de despesa",
            notes = "Esse endpoint tem como objetivo realizar a exclusão de uma despesa",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Despesas excluída com sucesso",
                    response = DespesaResponse.class),
            @ApiResponse(code = 400, message = "Nenhuma despesa foi encontrada com o id informado",
                    response = ObjectNotFoundException.class),
            @ApiResponse(code = 400, message = "A despesa selecionada já foi excluída",
                    response = InvalidRequestException.class),
            @ApiResponse(code = 400, message = "Nenhuma despesa foi encontrada para remoção",
                    response = InvalidRequestException.class),
            @ApiResponse(code = 400, message = "Não é possível remover uma despesa que já foi excluída",
                    response = InvalidRequestException.class)
    })
    @PreAuthorize("hasAnyRole('DESPESAS')")
    public ResponseEntity<DespesaResponse> removeDespesa(HttpServletRequest req,
                                                         @RequestParam(value = "removeRecorrencia") Boolean removeRecorrencia,
                                                         @PathVariable Long id) {
        log.info("Método controlador de remoção de despesa acessado");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(despesaService.removeDespesa(jwtUtil.obtemUsuarioAtivo(req), id, removeRecorrencia));
    }

    @PutMapping("/{id}")
    @ApiOperation(
            value = "Atualização de despesa",
            notes = "Esse endpoint tem como objetivo realizar a atualização de uma despesa na base de dados da empresa",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Despesa atualizada com sucesso",
                    response = DespesaResponse.class),
            @ApiResponse(code = 400, message = "Nenhuma despesa foi encontrada com o id informado",
                    response = ObjectNotFoundException.class)
    })
    @PreAuthorize("hasAnyRole('DESPESAS')")
    public ResponseEntity<DespesaResponse> atualizaDespesa(HttpServletRequest req,
                                                           @RequestBody DespesaRequest despesaRequest,
                                                           @PathVariable Long id) {
        log.info("Método controlador de atualização de despesa acessado");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(despesaService.atualizaDespesa(jwtUtil.obtemUsuarioAtivo(req), id, despesaRequest));
    }

    @GetMapping
    @ApiOperation(
            value = "Busca paginada por despesas cadastradas",
            notes = "Esse endpoint tem como objetivo realizar a busca paginada de despesas cadastradas na empresa do " +
                    "usuário que acionou a requisição com os filtros de busca enviados pelo usuário",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "A busca paginada de despesas foi realizada com sucesso",
                    response = DespesaPageResponse.class),
    })
    @PreAuthorize("hasAnyRole('DESPESAS')")
    public ResponseEntity<DespesaPageResponse> obtemDespesasPaginadas(
            @RequestParam(value = "busca", required = false) String busca,
            @RequestParam(value = "mesAno") String mesAno,
            Pageable pageable,
            HttpServletRequest req) {
        log.info("Endpoint de busca paginada por despesas acessado. Filtros de busca: {}",
                busca == null ? "Nulo" : busca);
        return ResponseEntity.ok().body(despesaService.realizaBuscaPaginadaPorDespesas(
                jwtUtil.obtemUsuarioAtivo(req), pageable, mesAno, busca));
    }

}
