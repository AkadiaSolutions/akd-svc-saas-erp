package br.akd.svc.akadia.controllers.sistema.clientes;

import br.akd.svc.akadia.models.dto.sistema.clientes.ClienteDto;
import br.akd.svc.akadia.models.entities.sistema.clientes.ClienteEntity;
import br.akd.svc.akadia.services.exceptions.InvalidRequestException;
import br.akd.svc.akadia.services.sistema.clientes.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<ClienteEntity> criaNovoCliente(ClienteDto clienteDto) {
        log.info("Método controlador de criação de novo cliente acessado");
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.criaNovoCliente(clienteDto));
    }

}
