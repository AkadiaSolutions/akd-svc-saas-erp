package br.akd.svc.akadia.controllers.sistema.clientes;

import br.akd.svc.akadia.config.security.JWTUtil;
import br.akd.svc.akadia.models.dto.sistema.clientes.ClienteDto;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Slf4j
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
    public ResponseEntity<ClienteEntity> criaNovoCliente(HttpServletRequest req, ClienteDto clienteDto) {
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
                                                         ClienteDto clienteDto,
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
    public ResponseEntity<ClienteEntity> removeCliente(HttpServletRequest req,
                                                       @PathVariable Long id) {
        log.info("Método controlador de remoção de cliente acessado");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clienteService.removeCliente(jwtUtil.obtemUsuarioAtivo(req), id));
    }

}
