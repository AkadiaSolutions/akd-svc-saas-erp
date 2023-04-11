package br.akd.svc.akadia.controllers.sistema.clientes;

import br.akd.svc.akadia.config.security.JWTUtil;
import br.akd.svc.akadia.models.dto.sistema.clientes.ClienteDto;
import br.akd.svc.akadia.models.dto.sistema.clientes.responses.ClientePageResponse;
import br.akd.svc.akadia.models.dto.sistema.clientes.responses.ClienteResponse;
import br.akd.svc.akadia.models.entities.sistema.clientes.ClienteEntity;
import br.akd.svc.akadia.services.exceptions.InvalidRequestException;
import br.akd.svc.akadia.services.exceptions.ObjectNotFoundException;
import br.akd.svc.akadia.services.sistema.clientes.ClienteService;
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

@Slf4j
@CrossOrigin
@RestController
@Api(value = "Esta API disponibiliza os endpoints de CRUD da entidade Cliente")
@Produces({MediaType.APPLICATION_JSON, "application/json"})
@Consumes({MediaType.APPLICATION_JSON, "application/json"})
@RequestMapping("api/sistema/v1/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @Autowired
    JWTUtil jwtUtil;

    @PostMapping("/verifica-ie")
    @ApiOperation(
            value = "Validação de duplicidade na entrada da inscrição estadual",
            notes = "Esse endpoint tem como objetivo realizar a validação se a inscrição estadual digitada pelo " +
                    "cliente já existe na base de dados da empresa.",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição finalizada com sucesso",
                    response = ClienteEntity.class),
    })
    @PreAuthorize("hasAnyRole('CLIENTES')")
    public ResponseEntity<?> verificaDuplicidadeInscricaoEstadual(HttpServletRequest req,
                                                                  @RequestBody String inscricaoEstadual) {
        log.info("Endpoint de validação de duplicidade de inscrição estadual acessado. IE: " + inscricaoEstadual);
        clienteService.validaSeInscricaoEstadualJaExiste(inscricaoEstadual, jwtUtil.obtemUsuarioAtivo(req).getEmpresa().getId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/verifica-cpfCnpj")
    @ApiOperation(
            value = "Validação de duplicidade na entrada do CPF ou CNPJ",
            notes = "Esse endpoint tem como objetivo realizar a validação se o CPF ou CNPJ digitado pelo cliente já " +
                    "existe na base de dados da empresa.",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição finalizada com sucesso",
                    response = ClienteEntity.class),
    })
    @Produces({MediaType.APPLICATION_JSON, "application/json"})
    @Consumes({MediaType.APPLICATION_JSON, "application/json"})
    @PreAuthorize("hasAnyRole('CLIENTES')")
    public ResponseEntity<?> verificaDuplicidadeCpfCnpj(HttpServletRequest req,
                                                        @RequestBody String cpfCnpj) {
        log.info("Endpoint de validação de duplicidade de CPF/CNPJ acessado. CPF/CNPJ: " + cpfCnpj);
        clienteService.validaSeCpfCnpjJaExiste(cpfCnpj, jwtUtil.obtemUsuarioAtivo(req).getEmpresa().getId());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @ApiOperation(
            value = "Busca paginada por clientes cadastrados",
            notes = "Esse endpoint tem como objetivo realizar a busca paginada de clientes cadastrados na empresa do " +
                    "usuário que acionou a requisição com os filtros de busca enviados pelo usuário",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição finalizada com sucesso",
                    response = ClienteEntity.class),
    })
    @PreAuthorize("hasAnyRole('CLIENTES')")
    public ResponseEntity<ClientePageResponse> obtemClientesPaginados(
            @RequestParam(value = "busca", required = false) String busca,
            Pageable pageable,
            HttpServletRequest req) {
        log.info("Endpoint de busca paginada por clientes acessado. Filtros de busca: {}",
                busca == null ? "Nulo" : busca);
        return ResponseEntity.ok().body(clienteService.realizaBuscaPaginadaPorClientes(
                jwtUtil.obtemUsuarioAtivo(req), pageable, busca));
    }

    @PostMapping
    @ApiOperation(
            value = "Criação de novo cliente",
            notes = "Esse endpoint tem como objetivo realizar a criação de um novo cliente na base dados da empresa",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cliente criado com sucesso", response = ClienteEntity.class),
            @ApiResponse(code = 400, message = "Erro de requisição inválida", response = InvalidRequestException.class)
    })
    @PreAuthorize("hasAnyRole('CLIENTES')")
    public ResponseEntity<ClienteEntity> criaNovoCliente(HttpServletRequest req,
                                                         @RequestBody ClienteDto clienteDto) {
        log.info("Método controlador de criação de novo cliente acessado");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clienteService.criaNovoCliente(jwtUtil.obtemUsuarioAtivo(req), clienteDto));
    }

    @PutMapping("/{id}")
    @ApiOperation(
            value = "Atualização de cliente",
            notes = "Esse endpoint tem como objetivo realizar a atualização de um cliente na base de dados da empresa",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cliente atualizado com sucesso", response = ClienteEntity.class),
            @ApiResponse(code = 404, message = "Objeto cliente não encontrado pelo id informado", response = ObjectNotFoundException.class)
    })
    @PreAuthorize("hasAnyRole('CLIENTES')")
    public ResponseEntity<ClienteEntity> atualizaCliente(HttpServletRequest req,
                                                         @RequestBody ClienteDto clienteDto,
                                                         @PathVariable Long id) {
        log.info("Método controlador de atualização de cliente acessado");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clienteService.atualizaCliente(jwtUtil.obtemUsuarioAtivo(req), id, clienteDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(
            value = "Remoção de cliente",
            notes = "Esse endpoint tem como objetivo realizar a exclusão de um cliente",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cliente excluído com sucesso", response = ClienteEntity.class),
            @ApiResponse(code = 404, message = "Objeto cliente não encontrado pelo id informado", response = ObjectNotFoundException.class),
            @ApiResponse(code = 400, message = "Não é possível remover um cliente que já foi excluído", response = InvalidRequestException.class)
    })
    @PreAuthorize("hasAnyRole('CLIENTES')")
    public ResponseEntity<ClienteResponse> removeCliente(HttpServletRequest req,
                                                         @PathVariable Long id) {
        log.info("Método controlador de remoção de cliente acessado");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clienteService.removeCliente(jwtUtil.obtemUsuarioAtivo(req), id));
    }

}
