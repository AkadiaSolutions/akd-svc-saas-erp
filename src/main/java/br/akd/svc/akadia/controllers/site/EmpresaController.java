package br.akd.svc.akadia.controllers.site;

import br.akd.svc.akadia.models.dto.site.EmpresaDto;
import br.akd.svc.akadia.models.entities.site.EmpresaEntity;
import br.akd.svc.akadia.services.exceptions.InvalidRequestException;
import br.akd.svc.akadia.services.site.EmpresaService;
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
@Api(value = "Essa API disponibiliza os endpoints de CRUD da entidade Empresa")
@Produces({MediaType.APPLICATION_JSON, "application/json"})
@Consumes({MediaType.APPLICATION_JSON, "application/json"})
@RequestMapping("/api/v1/empresa")
public class EmpresaController {

    @Autowired
    EmpresaService empresaService;

    @ApiOperation(
            value = "Criação",
            notes = "Essa requisição tem como objetivo realizar a persistência de uma nova empresa no banco de dados",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 201, message = "Empresa criada com sucesso", response = EmpresaDto.class),
            @ApiResponse(code = 400, message = "Falha na criação da empresa", response = InvalidRequestException.class),
            @ApiResponse(code = 401, message = "Falha de autenticação"),
    })
    @PostMapping
    public ResponseEntity<EmpresaEntity> criaEmpresa(@RequestBody EmpresaDto empresaDto) {

        return ResponseEntity.ok().body(empresaService.criaNovaEmpresa(empresaDto));
    }

}
