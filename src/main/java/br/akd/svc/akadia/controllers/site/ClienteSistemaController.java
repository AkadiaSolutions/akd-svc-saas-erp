package br.akd.svc.akadia.controllers.site;

import br.akd.svc.akadia.models.dto.site.ClienteSistemaDto;
import br.akd.svc.akadia.models.dto.site.EmpresaDto;
import br.akd.svc.akadia.models.entities.site.ClienteSistemaEntity;
import br.akd.svc.akadia.services.global.exceptions.InvalidRequestException;
import br.akd.svc.akadia.services.site.ClienteSistemaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RestController
@Api(value = "Essa API disponibiliza os endpoints de CRUD da entidade ClienteSistema")
@Produces({MediaType.APPLICATION_JSON, "application/json"})
@Consumes({MediaType.APPLICATION_JSON, "application/json"})
@RequestMapping("/api/v1/cliente-sistema")
public class ClienteSistemaController {

    @Autowired
    ClienteSistemaService clienteSistemaService;

    @ApiOperation(
            value = "Cadastro de novo cliente (1° Etapa)",
            notes = "Essa requisição tem como objetivo realizar o cadastro das informações básicas do cliente no banco " +
                    "de dados para formação de leads",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 201, message = "Dados captados com sucesso", response = EmpresaDto.class),
            @ApiResponse(code = 400, message = "Falha na criação do cliente", response = InvalidRequestException.class),
            @ApiResponse(code = 401, message = "Falha de autenticação"),
    })
    @PostMapping
    public ResponseEntity<ClienteSistemaEntity> primeiraEtapaCadastro(@RequestBody ClienteSistemaDto clienteSistemaDto) {
        return ResponseEntity.ok().body(clienteSistemaService.primeiraEtapaCadastroCliente(clienteSistemaDto));
    }









}
