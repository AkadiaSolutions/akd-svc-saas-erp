package br.akd.svc.akadia.controllers.sistema.colaboradores;

import br.akd.svc.akadia.models.entities.sistema.colaboradores.ColaboradorEntity;
import br.akd.svc.akadia.repositories.sistema.colaboradores.impl.ColaboradorRepositoryImpl;
import io.swagger.annotations.Api;
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

@RestController
@Api(value = "Essa API disponibiliza os endpoints de CRUD da entidade Colaborador")
@Produces({MediaType.APPLICATION_JSON, "application/json"})
@Consumes({MediaType.APPLICATION_JSON, "application/json"})
@RequestMapping("/api/sistema/v1/colaborador")
public class ColaboradorController {

    @Autowired
    ColaboradorRepositoryImpl colaboradorRepositoryImpl;

    //TODO ADICIONAR ANNOTATIONS
    @GetMapping
    public ResponseEntity<List<ColaboradorEntity>> listaTodosColaboradores() {
        return ResponseEntity.status(HttpStatus.OK).body(colaboradorRepositoryImpl.buscaTodosOsColaboradores());
    }

}
