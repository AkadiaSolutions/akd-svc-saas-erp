package br.akd.svc.akadia.modules.erp.colaboradores.advertencia.controllers;

import br.akd.svc.akadia.config.security.JWTUtil;
import br.akd.svc.akadia.modules.erp.colaboradores.advertencia.models.dto.response.AdvertenciaPageResponse;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.dto.response.page.ColaboradorPageResponse;
import br.akd.svc.akadia.modules.erp.colaboradores.advertencia.models.enums.StatusAdvertenciaEnum;
import br.akd.svc.akadia.exceptions.InvalidRequestException;
import br.akd.svc.akadia.modules.erp.colaboradores.advertencia.services.AdvertenciaService;
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

    @GetMapping("obtem-anexo/{idColaborador}/{idAdvertencia}")
    @ApiOperation(
            value = "Obtem anexo de advertência",
            notes = "Esse endpoint tem como objetivo realizar a obtenção de anexo de uma advertência"
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Anexo obtido com sucesso", response = String.class),
            @ApiResponse(code = 400, message = "Erro de requisição inválida", response = InvalidRequestException.class)
    })
    @PreAuthorize("hasAnyRole('COLABORADORES')")
    public ResponseEntity<byte[]> obtemAnexoAdvertencia(HttpServletRequest req,
                                                        @PathVariable(value = "idColaborador") Long idColaborador,
                                                        @PathVariable(value = "idAdvertencia") Long idAdvertencia) {
        log.info("Método controlador de obtenção de anexo de advertência acessado");
        return ResponseEntity.ok().body(advertenciaService.obtemAnexoAdvertencia(
                jwtUtil.obtemUsuarioAtivo(req),
                idColaborador,
                idAdvertencia).getArquivo());
    }

    @DeleteMapping("/{idColaborador}/{idAdvertencia}")
    @ApiOperation(
            value = "Remove uma advertência",
            notes = "Esse endpoint tem como objetivo realizar a remoção de uma advertência"
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Advertência removida com sucesso", response = String.class),
            @ApiResponse(code = 400, message = "Erro de requisição inválida", response = InvalidRequestException.class)
    })
    @PreAuthorize("hasAnyRole('COLABORADORES')")
    public ResponseEntity<?> removeAdvertencia(HttpServletRequest req,
                                               @PathVariable(value = "idColaborador") Long idColaborador,
                                               @PathVariable(value = "idAdvertencia") Long idAdvertencia) {
        log.info("Método controlador de exclusão da advertência acessado");
        advertenciaService.removerAdvertencia(
                jwtUtil.obtemUsuarioAtivo(req),
                idColaborador,
                idAdvertencia);
        return ResponseEntity.ok().build();
    }

    @PutMapping("altera-status/{idColaborador}/{idAdvertencia}")
    @ApiOperation(
            value = "Alteração de status da advertência",
            notes = "Esse endpoint tem como objetivo realizar a alteração do status da advertência"
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Status da advertência alterado com sucesso", response = String.class),
            @ApiResponse(code = 400, message = "Erro de requisição inválida", response = InvalidRequestException.class)
    })
    @PreAuthorize("hasAnyRole('COLABORADORES')")
    public ResponseEntity<?> alteraStatusAdvertencia(HttpServletRequest req,
                                                     @RequestBody String statusAdvertenciaEnum,
                                                     @PathVariable(value = "idColaborador") Long idColaborador,
                                                     @PathVariable(value = "idAdvertencia") Long idAdvertencia) {
        log.info("Método controlador de alteração de status da advertência acessado");
        advertenciaService.alteraStatusAdvertencia(
                jwtUtil.obtemUsuarioAtivo(req),
                StatusAdvertenciaEnum.valueOf(statusAdvertenciaEnum),
                idColaborador,
                idAdvertencia);
        return ResponseEntity.ok().build();
    }

    @PutMapping("anexa-documento/{idColaborador}/{idAdvertencia}")
    @ApiOperation(
            value = "Anexação de arquivo na advertência",
            notes = "Esse endpoint tem como objetivo realizar a chamada à lógica de anexação de arquivo em uma advertência"
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Arquivo anexado com sucesso", response = String.class),
            @ApiResponse(code = 400, message = "Erro de requisição inválida", response = InvalidRequestException.class)
    })
    @PreAuthorize("hasAnyRole('COLABORADORES')")
    public ResponseEntity<?> anexaArquivoAdvertencia(HttpServletRequest req,
                                                     @RequestParam(value = "anexo", required = false) MultipartFile anexo,
                                                     @PathVariable(value = "idColaborador") Long idColaborador,
                                                     @PathVariable(value = "idAdvertencia") Long idAdvertencia) throws IOException {
        log.info("Método controlador de anexação de arquivo em advertência acessado");
        advertenciaService.anexaArquivoAdvertencia(
                jwtUtil.obtemUsuarioAtivo(req),
                anexo,
                idColaborador,
                idAdvertencia);
        return ResponseEntity.ok().build();
    }

    @GetMapping("pdf-padrao/{idColaborador}/{idAdvertencia}")
    @ApiOperation(
            value = "Obtenção de PDF padrão",
            notes = "Esse endpoint tem como objetivo realizar a obtenção do PDF padrão da advertência recebida"
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "PDF obtido com sucesso", response = String.class),
            @ApiResponse(code = 400, message = "Erro de requisição inválida", response = InvalidRequestException.class)
    })
    @PreAuthorize("hasAnyRole('COLABORADORES')")
    public ResponseEntity<?> geraPdfPadraoAdvertencia(HttpServletRequest req,
                                                      HttpServletResponse res,
                                                      @PathVariable(value = "idColaborador") Long idColaborador,
                                                      @PathVariable(value = "idAdvertencia") Long idAdvertencia) throws IOException {
        log.info("Método controlador de obtenção de PDF padrão da advertência acessado");
        advertenciaService.geraPdfPadraoAdvertencia(
                jwtUtil.obtemUsuarioAtivo(req),
                res,
                idColaborador,
                idAdvertencia);
        return ResponseEntity.ok().build();
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
    public ResponseEntity<?> criaNovaAdvertencia(HttpServletRequest req,
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
        return ResponseEntity.ok().build();
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
