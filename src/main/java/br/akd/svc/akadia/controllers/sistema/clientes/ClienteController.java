package br.akd.svc.akadia.controllers.sistema.clientes;

import br.akd.svc.akadia.config.security.JWTUtil;
import br.akd.svc.akadia.models.dto.sistema.clientes.ClienteDto;
import br.akd.svc.akadia.models.entities.sistema.clientes.ClienteEntity;
import br.akd.svc.akadia.repositories.sistema.clientes.ClienteRepository;
import br.akd.svc.akadia.services.exceptions.InvalidRequestException;
import br.akd.svc.akadia.services.exceptions.ObjectNotFoundException;
import br.akd.svc.akadia.services.sistema.clientes.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

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


    //TODO PARA TESTE DO ANGULAR. REMOVER
    @GetMapping
    public ResponseEntity<List<ClienteEntity>> buscaTodosClientes(
            @RequestParam(value = "busca", required = false) List<String> busca,
            @PageableDefault(size = 20,
                    page = 0,
                    sort = {"horaCadastro", "dataCadastro"},
                    direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("Endpoint de busca de todos os clientes acessado");
        return ResponseEntity.ok().body(
                clienteService.obtemClientesFiltrados(pageable, busca == null ? new ArrayList<String>() : busca));
    }

    @GetMapping("/mes-ano")
    @ApiOperation(
            value = "Busca de clientes cadastrados por mês/ano",
            notes = "Esse endpoint tem como objetivo realizar a busca de clientes cadastrados em um mês específico de um ano específico",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição finalizada com sucesso",
                    response = ClienteEntity.class),
            @ApiResponse(code = 400, message = "Nenhum cliente foi encontrado no mês e ano indicado",
                    response = ObjectNotFoundException.class)
    })
    @PreAuthorize("hasAnyRole('CLIENTES')")
    public ResponseEntity<List<ClienteEntity>> buscaClientesPorMesAno(HttpServletRequest req,
                                                                      @RequestParam("mes") String mes,
                                                                      @RequestParam("ano") String ano) {
        log.info("Método controlador de busca de clientes por mês e ano acessado");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clienteService.obtemClientesPorMes(jwtUtil.obtemUsuarioAtivo(req), mes, ano));
    }

    @GetMapping("/periodo")
    @ApiOperation(
            value = "Busca de clientes cadastrados em um range de data",
            notes = "Esse endpoint tem como objetivo realizar a busca de clientes cadastrados em um período de data",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição finalizada com sucesso",
                    response = ClienteEntity.class),
            @ApiResponse(code = 400, message = "Nenhum cliente foi encontrado na data indicada",
                    response = ObjectNotFoundException.class)
    })
    @PreAuthorize("hasAnyRole('CLIENTES')")
    public ResponseEntity<List<ClienteEntity>> buscaClientesPorRangeDeData(HttpServletRequest req,
                                                                           @RequestParam("inicio") String inicio,
                                                                           @RequestParam("fim") String fim) {
        log.info("Método controlador de busca de clientes por período de data acessado");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clienteService.obtemClientesPorRangeDeData(jwtUtil.obtemUsuarioAtivo(req), inicio, fim));
    }

    @GetMapping("/telefone")
    @ApiOperation(
            value = "Busca de clientes por telefone",
            notes = "Esse endpoint tem como objetivo realizar a busca de clientes pelo telefone informado",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição finalizada com sucesso",
                    response = ClienteEntity.class),
            @ApiResponse(code = 400, message = "Nenhum cliente foi encontrado com o telefone informado",
                    response = ObjectNotFoundException.class)
    })
    @PreAuthorize("hasAnyRole('CLIENTES')")
    public ResponseEntity<List<ClienteEntity>> buscaClientesPeloTelefone(HttpServletRequest req,
                                                                         @RequestParam("busca") String busca) {
        log.info("Método controlador de busca de clientes pelo telefone acessado");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clienteService.obtemClientesPeloTelefone(jwtUtil.obtemUsuarioAtivo(req), busca));
    }

    @GetMapping("/inscricao-estadual")
    @ApiOperation(
            value = "Busca de clientes por inscrição estadual",
            notes = "Esse endpoint tem como objetivo realizar a busca de clientes pela inscrição estadual informada",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição finalizada com sucesso",
                    response = ClienteEntity.class),
            @ApiResponse(code = 400, message = "Nenhum cliente foi encontrado com a inscrição estadual informada",
                    response = ObjectNotFoundException.class)
    })
    @PreAuthorize("hasAnyRole('CLIENTES')")
    public ResponseEntity<List<ClienteEntity>> buscaClientesPelaInscricaoEstadual(HttpServletRequest req,
                                                                                  @RequestParam("busca") String busca) {
        log.info("Método controlador de busca de clientes pela inscrição estadual acessado");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clienteService.obtemClientesPelaInscricaoEstadual(jwtUtil.obtemUsuarioAtivo(req), busca));
    }

    @GetMapping("/cpf-cnpj")
    @ApiOperation(
            value = "Busca de clientes por cpf ou cnpj",
            notes = "Esse endpoint tem como objetivo realizar a busca de clientes pelo cpf ou cnpj informado",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição finalizada com sucesso", response = ClienteEntity.class),
            @ApiResponse(code = 400, message = "Nenhum cliente foi encontrado com o cpf ou cnpj informado", response = ObjectNotFoundException.class)
    })
    @PreAuthorize("hasAnyRole('CLIENTES')")
    public ResponseEntity<List<ClienteEntity>> buscaClientesPeloCpfOuCnpj(HttpServletRequest req,
                                                                          @RequestParam("busca") String busca) {
        log.info("Método controlador de busca de clientes pelo cpf ou cnpj acessado");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clienteService.obtemClientesPeloCpfCnpj(jwtUtil.obtemUsuarioAtivo(req), busca));
    }

    @GetMapping("/nome")
    @ApiOperation(
            value = "Busca de clientes por nome",
            notes = "Esse endpoint tem como objetivo realizar a busca de clientes pelo nome informado",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição finalizada com sucesso", response = ClienteEntity.class),
            @ApiResponse(code = 400, message = "Nenhum cliente foi encontrado com o nome informado", response = ObjectNotFoundException.class)
    })
    @PreAuthorize("hasAnyRole('CLIENTES')")
    public ResponseEntity<List<ClienteEntity>> buscaClientesPeloNome(HttpServletRequest req,
                                                                     @RequestParam("busca") String busca) {
        log.info("Método controlador de busca de clientes pelo nome acessado");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clienteService.obtemClientesPeloNome(jwtUtil.obtemUsuarioAtivo(req), busca));
    }

    @GetMapping("/email")
    @ApiOperation(
            value = "Busca de clientes por e-mail",
            notes = "Esse endpoint tem como objetivo realizar a busca de clientes pelo e-mail informado",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição finalizada com sucesso", response = ClienteEntity.class),
            @ApiResponse(code = 400, message = "Nenhum cliente foi encontrado com o e-mail informado", response = ObjectNotFoundException.class)
    })
    @PreAuthorize("hasAnyRole('CLIENTES')")
    public ResponseEntity<List<ClienteEntity>> buscaClientesPeloEmail(HttpServletRequest req,
                                                                      @RequestParam("busca") String busca) {
        log.info("Método controlador de busca de clientes pelo nome acessado");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clienteService.obtemClientesPeloEmail(jwtUtil.obtemUsuarioAtivo(req), busca));
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
    public ResponseEntity<ClienteEntity> removeCliente(HttpServletRequest req,
                                                       @PathVariable Long id) {
        log.info("Método controlador de remoção de cliente acessado");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clienteService.removeCliente(jwtUtil.obtemUsuarioAtivo(req), id));
    }

}
