package br.akd.svc.akadia.controllers.site;

import br.akd.svc.akadia.models.dto.site.EmpresaDto;
import br.akd.svc.akadia.models.entities.site.ClienteSistemaEntity;
import br.akd.svc.akadia.models.entities.site.EmpresaEntity;
import br.akd.svc.akadia.repositories.site.impl.EmpresaRepositoryImpl;
import br.akd.svc.akadia.services.exceptions.InvalidRequestException;
import br.akd.svc.akadia.services.site.EmpresaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
@Api(value = "Essa API disponibiliza os endpoints de CRUD da entidade Empresa")
@Produces({MediaType.APPLICATION_JSON, "application/json"})
@Consumes({MediaType.APPLICATION_JSON, "application/json"})
@RequestMapping("/api/v1/empresa")
public class EmpresaController {

    @Autowired
    EmpresaService empresaService;

    @Autowired
    EmpresaRepositoryImpl empresaRepositoryImpl;

    @GetMapping
    public ResponseEntity<List<EmpresaEntity>> listaTodasEmpresas() {
        return ResponseEntity.status(HttpStatus.OK).body(empresaRepositoryImpl.buscaTodasEmpresas());
    }

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
    @PostMapping(value = "{idCliente}")
    public ResponseEntity<ClienteSistemaEntity> criaEmpresa(@PathVariable Long idCliente,
                                                            @RequestBody EmpresaDto empresaDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaService.criaNovaEmpresa(idCliente, empresaDto));
    }

    @ApiOperation(
            value = "Criação",
            notes = "Essa requisição tem como objetivo realizar a persistência de uma nova empresa no banco de dados",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 201, message = "Empresa atualizada com sucesso", response = EmpresaDto.class),
            @ApiResponse(code = 400, message = "Falha na atualização da empresa", response = InvalidRequestException.class),
            @ApiResponse(code = 401, message = "Falha de autenticação"),
    })
    @PutMapping(value = "{idEmpresa}")
    public ResponseEntity<EmpresaEntity> atualizaEmpresa(@PathVariable Long idEmpresa,
                                                         @RequestBody EmpresaDto empresaDto) {
        return ResponseEntity.ok().body(empresaService.atualizaEmpresa(idEmpresa, empresaDto));
    }

    @DeleteMapping(value = "{idEmpresa}")
    public ResponseEntity<EmpresaEntity> removeEmpresa(@PathVariable Long idEmpresa) {
        return ResponseEntity.ok().body(empresaService.removeEmpresa(idEmpresa));
    }

}
