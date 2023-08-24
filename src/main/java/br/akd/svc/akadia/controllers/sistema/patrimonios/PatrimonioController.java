package br.akd.svc.akadia.controllers.sistema.patrimonios;

import br.akd.svc.akadia.config.security.JWTUtil;
import br.akd.svc.akadia.models.dto.sistema.patrimonios.request.PatrimonioRequest;
import br.akd.svc.akadia.models.dto.sistema.patrimonios.response.PatrimonioPageResponse;
import br.akd.svc.akadia.models.dto.sistema.patrimonios.response.PatrimonioResponse;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.ColaboradorEntity;
import br.akd.svc.akadia.services.exceptions.InvalidRequestException;
import br.akd.svc.akadia.services.exceptions.ObjectNotFoundException;
import br.akd.svc.akadia.services.sistema.patrimonios.PatrimonioRelatorioService;
import br.akd.svc.akadia.services.sistema.patrimonios.PatrimonioService;
import com.lowagie.text.DocumentException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@Api(value = "Essa API disponibiliza os endpoints de CRUD da entidade Patrimonio")
@Produces({MediaType.APPLICATION_JSON, "application/json"})
@Consumes({MediaType.APPLICATION_JSON, "application/json"})
@RequestMapping("/api/sistema/v1/patrimonios")
public class PatrimonioController {

    @Autowired
    PatrimonioService patrimonioService;

    @Autowired
    PatrimonioRelatorioService relatorioService;

    @Autowired
    JWTUtil jwtUtil;

    @PostMapping
    @ApiOperation(
            value = "Criação de novo patrimônio",
            notes = "Esse endpoint tem como objetivo realizar a criação de um novo patrimônio na base dados da empresa",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 201, message = "Patrimônio persistido com sucesso", response = PatrimonioResponse.class),
            @ApiResponse(code = 400, message = "Erro de requisição inválida", response = InvalidRequestException.class)
    })
    @PreAuthorize("hasAnyRole('PATRIMONIOS')")
    public ResponseEntity<PatrimonioResponse> criaNovoPatrimonio(HttpServletRequest req,
                                                                 @RequestBody PatrimonioRequest patrimonioRequest) {
        log.info("Método controlador de criação de novo patrimônio acessado");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(patrimonioService.criaNovoPatrimonio(jwtUtil.obtemUsuarioAtivo(req), patrimonioRequest));
    }

    @GetMapping("/{id}")
    @ApiOperation(
            value = "Busca de patrimônio por id",
            notes = "Esse endpoint tem como objetivo realizar a busca de um patrimônio pelo id recebido pelo parâmetro",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "A busca de patrimônio por id foi realizada com sucesso",
                    response = PatrimonioResponse.class),
            @ApiResponse(code = 400, message = "Nenhum patrimônio foi encontrado com o id informado",
                    response = ObjectNotFoundException.class),
    })
    @PreAuthorize("hasAnyRole('PATRIMONIOS')")
    public ResponseEntity<PatrimonioResponse> obtemObjetoPorId(
            @PathVariable("id") Long id,
            HttpServletRequest req) {
        log.info("Endpoint de busca de patrimônio por id acessado. ID recebido: {}", id);
        return ResponseEntity.ok().body(patrimonioService.realizaBuscaPorId(
                jwtUtil.obtemUsuarioAtivo(req), id));
    }

    @GetMapping
    @ApiOperation(
            value = "Busca paginada por patrimônios cadastrados",
            notes = "Esse endpoint tem como objetivo realizar a busca paginada de patrimônios cadastrados na empresa do " +
                    "usuário que acionou a requisição com os filtros de busca enviados pelo usuário",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "A busca paginada de patrimônios foi realizada com sucesso",
                    response = PatrimonioPageResponse.class),
    })
    @PreAuthorize("hasAnyRole('PATRIMONIOS')")
    public ResponseEntity<PatrimonioPageResponse> obtemPatrimoniosPaginados(
            @RequestParam(value = "busca", required = false) String busca,
            Pageable pageable,
            HttpServletRequest req) {
        log.info("Endpoint de busca paginada por patrimônioss acessado. Filtros de busca: {}",
                busca == null ? "Nulo" : busca);
        return ResponseEntity.ok().body(patrimonioService.realizaBuscaPaginada(
                jwtUtil.obtemUsuarioAtivo(req), pageable, busca));
    }

    @PutMapping("/{id}")
    @ApiOperation(
            value = "Atualização de patrimônio",
            notes = "Esse endpoint tem como objetivo realizar a atualização de um patrimônio na base de dados da empresa",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Patrimônio atualizado com sucesso",
                    response = PatrimonioResponse.class),
            @ApiResponse(code = 400, message = "Nenhum patrimônio foi encontrado com o id informado",
                    response = ObjectNotFoundException.class)
    })
    @PreAuthorize("hasAnyRole('PATRIMONIOS')")
    public ResponseEntity<PatrimonioResponse> atualizaPatrimonio(HttpServletRequest req,
                                                                 @RequestBody PatrimonioRequest patrimonioRequest,
                                                                 @PathVariable Long id) {
        log.info("Método controlador de atualização de patrimônio acessado");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(patrimonioService.atualizaObjeto(jwtUtil.obtemUsuarioAtivo(req), id, patrimonioRequest));
    }

    @DeleteMapping
    @ApiOperation(
            value = "Remoção de patrimônio em massa",
            notes = "Esse endpoint tem como objetivo realizar a exclusão de patrimônios em massa",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Patrimonios excluídos com sucesso"),
            @ApiResponse(code = 400, message = "Nenhum patrimônio foi encontrado com o id informado",
                    response = ObjectNotFoundException.class),
            @ApiResponse(code = 400, message = "O Patrimônio selecionado já foi excluído",
                    response = InvalidRequestException.class),
            @ApiResponse(code = 400, message = "Nenhum patrimônio foi encontrado para remoção",
                    response = InvalidRequestException.class),
            @ApiResponse(code = 400, message = "Não é possível remover um patrimônio que já foi excluído",
                    response = InvalidRequestException.class)
    })
    @PreAuthorize("hasAnyRole('PATRIMONIOS')")
    public ResponseEntity<?> removePatrimoniosEmMassa(HttpServletRequest req,
                                                      @RequestBody List<Long> ids) {
        log.info("Método controlador de remoção de patrimônioss em massa acessado");
        patrimonioService.removeEmMassa(jwtUtil.obtemUsuarioAtivo(req), ids);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(
            value = "Remoção de patrimônio",
            notes = "Esse endpoint tem como objetivo realizar a exclusão de um patrimônio",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Patrimonios excluídos com sucesso",
                    response = PatrimonioResponse.class),
            @ApiResponse(code = 400, message = "Nenhum patrimônio foi encontrado com o id informado",
                    response = ObjectNotFoundException.class),
            @ApiResponse(code = 400, message = "O patrimônio selecionado já foi excluído",
                    response = InvalidRequestException.class),
            @ApiResponse(code = 400, message = "Nenhum patrimônio foi encontrado para remoção",
                    response = InvalidRequestException.class),
            @ApiResponse(code = 400, message = "Não é possível remover um patrimônio que já foi excluído",
                    response = InvalidRequestException.class)
    })
    @PreAuthorize("hasAnyRole('PATRIMONIOS')")
    public ResponseEntity<PatrimonioResponse> removePatrimonio(HttpServletRequest req,
                                                               @PathVariable Long id) {
        log.info("Método controlador de remoção de patrimônio acessado");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(patrimonioService.removeObjeto(jwtUtil.obtemUsuarioAtivo(req), id));
    }

    @PostMapping("/relatorio")
    @ApiOperation(
            value = "Gerar relatório em PDF",
            notes = "Esse endpoint tem como objetivo realizar a criação de um relatório em PDF com a listagem de patrimônios " +
                    "solicitada",
            produces = MediaType.APPLICATION_OCTET_STREAM,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "PDF gerado com sucesso"),
            @ApiResponse(code = 400, message = "Ocorreu um erro na criação do PDF", response = Exception.class),
    })
    @PreAuthorize("hasAnyRole('PATRIMONIOS')")
    public void relatorio(HttpServletResponse res,
                          HttpServletRequest req,
                          @RequestBody List<Long> ids) throws DocumentException, IOException {
        log.info("Método controlador de obtenção de relatório de patrimônios em PDF acessado. IDs: {}", ids);

        ColaboradorEntity usuarioAtivo = jwtUtil.obtemUsuarioAtivo(req);

        res.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachement; filename=akadion_"
                + usuarioAtivo.getEmpresa().getNome().replace(" ", "-").toLowerCase()
                + "_patrimonios_"
                + new SimpleDateFormat("dd.MM.yyyy_HHmmss").format(new Date())
                + ".pdf";
        res.setHeader(headerKey, headerValue);

        relatorioService.exportarPdf(res, usuarioAtivo, ids);
    }

}
