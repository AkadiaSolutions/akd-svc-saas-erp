package br.akd.svc.akadia.controllers.sistema.colaboradores;

import br.akd.svc.akadia.config.security.JWTUtil;
import br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.AdvertenciaPageResponse;
import br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.ColaboradorPageResponse;
import br.akd.svc.akadia.services.exceptions.InvalidRequestException;
import br.akd.svc.akadia.services.sistema.colaboradores.AdvertenciaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Slf4j
@CrossOrigin
@RestController
@Api(value = "Essa API disponibiliza os endpoints de CRUD da entidade Advertencia")
@Produces({MediaType.APPLICATION_JSON, "application/json"})
@Consumes({MediaType.APPLICATION_JSON, "application/json"})
@RequestMapping("/api/sistema/v1/advertencia")
public class AdvertenciaController {

    @Autowired
    AdvertenciaService advertenciaService;

    @Autowired
    JWTUtil jwtUtil;

    @GetMapping("pdf-padrao/{idColaborador}/{idAdvertencia}")
    @ApiOperation(
            value = "Obtenção de PDF padrão",
            notes = "Esse endpoint tem como objetivo realizar a obtenção do PDF padrão da advertência recebida"
    )
    @ApiResponses({
            @ApiResponse(code = 201, message = "PDF obtido com sucesso", response = String.class),
            @ApiResponse(code = 400, message = "Erro de requisição inválida", response = InvalidRequestException.class)
    })
    @PreAuthorize("hasAnyRole('COLABORADORES')")
    public void geraPdfPadraoAdvertencia(HttpServletRequest req,
                                         HttpServletResponse res,
                                         @PathVariable(value = "idColaborador") Long idColaborador,
                                         @PathVariable(value = "idAdvertencia") Long idAdvertencia) throws IOException {
        log.info("Método controlador de obtenção de PDF padrão da advertência acessado");
        advertenciaService.geraPdfPadraoAdvertencia(
                jwtUtil.obtemUsuarioAtivo(req),
                res,
                idColaborador,
                idAdvertencia);
    }

    @PostMapping("/{idColaborador}")
    @ApiOperation(
            value = "Criação de nova advertência",
            notes = "Esse endpoint tem como objetivo realizar a criação de uma advertência e atribuí-la " +
                    "a um colaborador específico"
    )
    @ApiResponses({
            @ApiResponse(code = 201, message = "Advertência persistida com sucesso", response = String.class),
            @ApiResponse(code = 400, message = "Erro de requisição inválida", response = InvalidRequestException.class)
    })
    @PreAuthorize("hasAnyRole('COLABORADORES')")
    public void criaNovaAdvertencia(HttpServletRequest req,
                                    HttpServletResponse res,
                                    @PathVariable(value = "idColaborador") Long idColaborador,
                                    @RequestParam(value = "arquivoAdvertencia", required = false) MultipartFile arquivoAdvertencia,
                                    @RequestParam("advertencia") String advertencia) throws IOException {
        log.info("Método controlador de criação de nova advertência acessado");
        advertenciaService.geraAdvertenciaColaborador(
                jwtUtil.obtemUsuarioAtivo(req),
                res,
                idColaborador,
                arquivoAdvertencia,
                advertencia);
    }

    @GetMapping("/{id}")
    @ApiOperation(
            value = "Busca paginada por advertências do colaborador",
            notes = "Esse endpoint tem como objetivo realizar a busca paginada de advertências do colaborador",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "A busca paginada de advertências do colaborador foi realizada com sucesso",
                    response = ColaboradorPageResponse.class),
    })
    @PreAuthorize("hasAnyRole('COLABORADORES')")
    public ResponseEntity<AdvertenciaPageResponse> obtemAdvertenciasColaboradorPaginada(
            Pageable pageable,
            HttpServletRequest req,
            @PathVariable("id") Long id) {
        log.info("Endpoint de busca paginada de advertências do colaborador acessada");
        return ResponseEntity.ok().body(advertenciaService.obtemAdvertenciasColaborador(
                jwtUtil.obtemUsuarioAtivo(req), pageable, id));
    }

}
