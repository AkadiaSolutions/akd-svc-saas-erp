package br.akd.svc.akadia.modules.web.controller;

import br.akd.svc.akadia.modules.web.models.dto.ClienteSistemaDto;
import br.akd.svc.akadia.modules.backoffice.models.entity.LeadEntity;
import br.akd.svc.akadia.modules.web.models.entity.ClienteSistemaEntity;
import br.akd.svc.akadia.modules.web.repository.impl.ClienteSistemaRepositoryImpl;
import br.akd.svc.akadia.modules.backoffice.services.LeadService;
import br.akd.svc.akadia.exceptions.FeignConnectionException;
import br.akd.svc.akadia.exceptions.InvalidRequestException;
import br.akd.svc.akadia.exceptions.ObjectNotFoundException;
import br.akd.svc.akadia.modules.web.services.AssinaturaService;
import br.akd.svc.akadia.modules.web.services.ClienteSistemaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Slf4j
@RestController
@Api(value = "Essa API disponibiliza os endpoints de CRUD da entidade ClienteSistema")
@Produces({MediaType.APPLICATION_JSON, "application/json"})
@Consumes({MediaType.APPLICATION_JSON, "application/json"})
@RequestMapping("/api/site/v1/cliente-sistema")
public class ClienteSistemaController {

    @Autowired
    ClienteSistemaService clienteSistemaService;

    @Autowired
    AssinaturaService assinaturaService;

    @Autowired
    ClienteSistemaRepositoryImpl clienteSistemaRepositoryImpl;

    @Autowired
    LeadService leadService;

    @ApiOperation(
            value = "Busca de todos os clientes cadastrados",
            notes = "Esse endpoint tem como objetivo realizar a busca de todos os clientes cadastrados no banco de dados",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso", response = ClienteSistemaEntity.class),
    })
    @GetMapping
    public ResponseEntity<List<ClienteSistemaEntity>> listaTodosClientes() {
        log.info("Método controlador de listagem de todos os clientes do Akadion acessado");
        return ResponseEntity.status(HttpStatus.OK).body(clienteSistemaRepositoryImpl.buscaTodosClientes());
    }

    @ApiOperation(
            value = "Captação de lead da criação de novo cliente",
            notes = "Esse endpoint tem como objetivo realizar o cadastro das informações básicas do cliente no banco " +
                    "de dados para formação de leads, além de verificar se o e-mail informado já existe",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 201, message = "Dados persistidos com sucesso", response = LeadEntity.class),
            @ApiResponse(code = 400, message = "O e-mail informado já existe", response = InvalidRequestException.class),
    })
    @PostMapping("cadastro/verifica-email")
    public ResponseEntity<LeadEntity> captaLeadsPreCadastro(@RequestBody ClienteSistemaDto clienteSistemaDto) {
        log.info("Método controlador de captação de leads pré-cadastro acessado");
        return ResponseEntity.status(HttpStatus.CREATED).body(leadService.encaminhaLeadParaPersistencia(clienteSistemaDto));
    }

    @ApiOperation(
            value = "Validação de CPF da criação de novo cliente",
            notes = "Esse endpoint tem como objetivo realizar a verificação de já existência do CPF enviado",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "O cpf informado ainda não existe", response = ClienteSistemaEntity.class),
            @ApiResponse(code = 400, message = "O cpf informado já existe", response = InvalidRequestException.class),
    })
    @PostMapping("cadastro/verifica-cpf")
    public ResponseEntity<ClienteSistemaEntity> verificaSeCpfJaExiste(@RequestBody ClienteSistemaDto clienteSistemaDto) {
        log.info("Método controlador de validação se cpf acessado");
        clienteSistemaService.validaSeCpfJaExiste(clienteSistemaDto);
        log.info("Validação de cpf realizada com sucesso. O cpf informado está disponível");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ApiOperation(
            value = "Cadastro de novo cliente",
            notes = "Esse endpoint tem como objetivo realizar o cadastro de um novo cliente no banco de dados do projeto " +
                    "e na integradora de pagamentos ASAAS",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cliente salvo com sucesso", response = ClienteSistemaEntity.class),
            @ApiResponse(code = 400, message = "Ocorreu um erro no processo de criação do cliente", response = InvalidRequestException.class),
            @ApiResponse(code = 400, message = "Ocorreu um erro no processo de criação da assinatura", response = InvalidRequestException.class),
            @ApiResponse(code = 500, message = "Ocorreu uma falha na conexão com o feign", response = FeignConnectionException.class),
    })
    @PostMapping("cadastro/cria-cliente")
    public ResponseEntity<ClienteSistemaEntity> criaNovoCliente(@RequestBody ClienteSistemaDto clienteSistemaDto) {
        log.info("Método controlador de criação de novo cliente acessado");
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSistemaService.cadastraNovoCliente(clienteSistemaDto));
    }

    @ApiOperation(
            value = "Atualização de dados do cliente",
            notes = "Esse endpoint tem como objetivo realizar a atualização dos dados do cliente no banco de dados do projeto " +
                    "e na integradora de pagamentos ASAAS",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cliente atualizado com sucesso", response = ClienteSistemaEntity.class),
            @ApiResponse(code = 400, message = "Ocorreu um erro no processo de atualização do cliente", response = InvalidRequestException.class),
            @ApiResponse(code = 400, message = "Nenhum cliente foi encontrado com o id informado", response = ObjectNotFoundException.class),
            @ApiResponse(code = 500, message = "Ocorreu uma falha na conexão com o feign", response = FeignConnectionException.class),
    })
    @PutMapping("atualiza-cliente/{idCliente}")
    public ResponseEntity<ClienteSistemaEntity> atualizaDadosCliente(@PathVariable Long idCliente,
                                                                     @RequestBody ClienteSistemaDto clienteSistemaDto) {
        log.info("Método controlador de atualização de dados do cliente de id {} acessado", idCliente);
        return ResponseEntity.status(HttpStatus.OK).body(clienteSistemaService.atualizaDadosCliente(idCliente, clienteSistemaDto));
    }

    @ApiOperation(
            value = "Atualização de dados da assinatura do cliente",
            notes = "Esse endpoint tem como objetivo realizar a atualização dos dados da assinatura do cliente no " +
                    "banco de dados do projeto e na integradora de pagamentos ASAAS",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Assinatura do cliente atualizada com sucesso",
                    response = ClienteSistemaEntity.class),
            @ApiResponse(code = 400, message = "Ocorreu um erro no processo de atualização da assinatura do cliente",
                    response = InvalidRequestException.class),
            @ApiResponse(code = 400, message = "Nenhum cliente foi encontrado com o id informado",
                    response = ObjectNotFoundException.class),
            @ApiResponse(code = 500, message = "Ocorreu uma falha na conexão com o feign",
                    response = FeignConnectionException.class),
    })
    @PutMapping("atualiza-assinatura/{idCliente}")
    public ResponseEntity<ClienteSistemaEntity> atualizaDadosAssinaturaCliente(@PathVariable Long idCliente,
                                                                               @RequestBody ClienteSistemaDto clienteSistemaDto) {
        log.info("Método controlador de atualização de dados da assinatura do cliente de id {} acessado", idCliente);
        return ResponseEntity.status(HttpStatus.OK).body(assinaturaService.atualizaDadosAssinatura(idCliente, clienteSistemaDto));
    }

    @ApiOperation(
            value = "Cancelamento da assinatura do cliente",
            notes = "Esse endpoint tem como objetivo realizar o cancelammento da assinatura do cliente no " +
                    "banco de dados do projeto e na integradora de pagamentos ASAAS",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Assinatura do cliente cancelada com sucesso",
                    response = ClienteSistemaEntity.class),
            @ApiResponse(code = 400, message = "Ocorreu um erro no processo de cancelamento da assinatura do cliente",
                    response = InvalidRequestException.class),
            @ApiResponse(code = 400, message = "Nenhum cliente foi encontrado com o id informado",
                    response = ObjectNotFoundException.class),
            @ApiResponse(code = 500, message = "Ocorreu uma falha na conexão com o feign",
                    response = FeignConnectionException.class),
    })
    @DeleteMapping("cancela-assinatura/{idCliente}")
    public ResponseEntity<ClienteSistemaEntity> cancelaAssinaturaCliente(@PathVariable Long idCliente) {
        log.info("Método controlador de cancelamento de assinatura do cliente acessado");
        return ResponseEntity.status(HttpStatus.OK).body(assinaturaService.cancelaAssinatura(idCliente));
    }

}
