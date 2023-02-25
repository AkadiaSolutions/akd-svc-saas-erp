package br.akd.svc.akadia.controllers.sistema.colaboradores;

import br.akd.svc.akadia.models.entities.sistema.colaboradores.ColaboradorEntity;
import br.akd.svc.akadia.repositories.sistema.colaboradores.impl.ColaboradorRepositoryImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Slf4j
@RestController
@Api(value = "Essa API disponibiliza os endpoints de CRUD da entidade Colaborador")
@Produces({MediaType.APPLICATION_JSON, "application/json"})
@Consumes({MediaType.APPLICATION_JSON, "application/json"})
@RequestMapping("/api/sistema/v1/colaborador")
public class ColaboradorController {

    @Autowired
    ColaboradorRepositoryImpl colaboradorRepositoryImpl;

    @ApiOperation(
            value = "Busca de todos os colaboradores cadastrados",
            notes = "Esse endpoint tem como objetivo realizar a busca de todos os colaboradores cadastrados no banco de dados",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso", response = ColaboradorEntity.class),
    })
    @GetMapping
    public ResponseEntity<List<ColaboradorEntity>> listaTodosColaboradores() {
        log.info("Método controlador de listagem de todos os colaboradores (GET) acessado");
        return ResponseEntity.status(HttpStatus.OK).body(colaboradorRepositoryImpl.buscaTodosOsColaboradores());
    }

}
