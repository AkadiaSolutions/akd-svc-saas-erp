package br.akd.svc.akadia.modules.web.controller;

import br.akd.svc.akadia.modules.web.models.dto.empresa.EmpresaDto;
import br.akd.svc.akadia.modules.web.models.entity.empresa.CriaEmpresaResponse;
import br.akd.svc.akadia.modules.web.models.entity.empresa.EmpresaEntity;
import br.akd.svc.akadia.modules.web.repository.impl.EmpresaRepositoryImpl;
import br.akd.svc.akadia.exceptions.InvalidRequestException;
import br.akd.svc.akadia.modules.web.services.EmpresaService;
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
@Api(value = "Essa API disponibiliza os endpoints de CRUD da entidade Empresa")
@Produces({MediaType.APPLICATION_JSON, "application/json"})
@Consumes({MediaType.APPLICATION_JSON, "application/json"})
@RequestMapping("/api/site/v1/empresa")
public class EmpresaController {

    @Autowired
    EmpresaService empresaService;

    @Autowired
    EmpresaRepositoryImpl empresaRepositoryImpl;

    @ApiOperation(
            value = "Busca de todas as empresas cadastradas",
            notes = "Esse endpoint tem como objetivo realizar a busca de todas as empresas cadastradas no banco de dados",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso", response = EmpresaEntity.class),
    })
    @GetMapping
    public ResponseEntity<List<EmpresaEntity>> listaTodasEmpresas() {
        log.info("Método controlador de listagem de todas as empresas cadastradas acessado");
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
    public ResponseEntity<CriaEmpresaResponse> criaEmpresa(@PathVariable Long idCliente,
                                                           @RequestBody EmpresaDto empresaDto) {
        log.info("Método controlador de criação de nova empresa acessado");
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaService.criaNovaEmpresa(idCliente, empresaDto));
    }

    @ApiOperation(
            value = "Atualização",
            notes = "Essa requisição tem como objetivo atualizar os dados de uma empresa já existente no banco de dados",
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
        log.info("Método controlador de atualização de empresa acessado");
        return ResponseEntity.ok().body(empresaService.atualizaEmpresa(idEmpresa, empresaDto));
    }

    @DeleteMapping(value = "{idEmpresa}")
    public ResponseEntity<EmpresaEntity> removeEmpresa(@PathVariable Long idEmpresa) {
        log.info("Método controlador de remoção de empresa acessado");
        return ResponseEntity.ok().body(empresaService.removeEmpresa(idEmpresa));
    }

}
