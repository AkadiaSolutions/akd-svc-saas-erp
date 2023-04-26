package br.akd.svc.akadia.controllers.sistema.colaboradores;

import br.akd.svc.akadia.config.security.JWTUtil;
import br.akd.svc.akadia.models.dto.sistema.colaboradores.ColaboradorDto;
import br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.ColaboradorPageResponse;
import br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.ColaboradorResponse;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.ColaboradorEntity;
import br.akd.svc.akadia.services.exceptions.InvalidRequestException;
import br.akd.svc.akadia.services.exceptions.ObjectNotFoundException;
import br.akd.svc.akadia.services.sistema.colaboradores.ColaboradorService;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@Api(value = "Essa API disponibiliza os endpoints de CRUD da entidade Colaborador")
@Produces({MediaType.APPLICATION_JSON, "application/json"})
@Consumes({MediaType.APPLICATION_JSON, "application/json"})
@RequestMapping("/api/sistema/v1/colaborador")
public class ColaboradorController {

    @Autowired
    ColaboradorService colaboradorService;

    @Autowired
    JWTUtil jwtUtil;

    @GetMapping("/{id}")
    @ApiOperation(
            value = "Busca de colaborador por id",
            notes = "Esse endpoint tem como objetivo realizar a busca de um colaborador pelo id recebido pelo parâmetro",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "A busca de colaborador por id foi realizada com sucesso",
                    response = ColaboradorResponse.class),
            @ApiResponse(code = 400, message = "Nenhum colaborador foi encontrado com o id informado",
                    response = ObjectNotFoundException.class),
    })
    @PreAuthorize("hasAnyRole('COLABORADORES')")
    public ResponseEntity<ColaboradorResponse> obtemColaboradorPorId(
            @PathVariable("id") Long id,
            HttpServletRequest req) {
        log.info("Endpoint de busca de colaborador por id acessado. ID recebido: {}", id);
        return ResponseEntity.ok().body(colaboradorService.realizaBuscaDeColaboradorPorId(
                jwtUtil.obtemUsuarioAtivo(req), id));
    }

    @GetMapping
    @ApiOperation(
            value = "Busca paginada por colaboradores cadastrados",
            notes = "Esse endpoint tem como objetivo realizar a busca paginada de colaboradores cadastrados na empresa do " +
                    "usuário que acionou a requisição com os filtros de busca enviados pelo usuário",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "A busca paginada de colaboradores foi realizada com sucesso",
                    response = ColaboradorPageResponse.class),
    })
    @PreAuthorize("hasAnyRole('COLABORADORES')")
    public ResponseEntity<ColaboradorPageResponse> obtemColaboradoresPaginados(
            @RequestParam(value = "busca", required = false) String busca,
            Pageable pageable,
            HttpServletRequest req) {
        log.info("Endpoint de busca paginada por colaboradores acessado. Filtros de busca: {}",
                busca == null ? "Nulo" : busca);
        return ResponseEntity.ok().body(colaboradorService.realizaBuscaPaginadaPorColaboradores(
                jwtUtil.obtemUsuarioAtivo(req), pageable, busca));
    }

    @PostMapping
    @ApiOperation(
            value = "Criação de novo colaborador",
            notes = "Esse endpoint tem como objetivo realizar a criação de um novo colaborador na base dados da empresa",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 201, message = "Colaborador persistido com sucesso", response = ColaboradorResponse.class),
            @ApiResponse(code = 400, message = "A inscrição estadual informada já existe", response = InvalidRequestException.class),
            @ApiResponse(code = 400, message = "O CPF/CNPJ informado já existe", response = InvalidRequestException.class),
            @ApiResponse(code = 400, message = "Erro de requisição inválida", response = InvalidRequestException.class)
    })
    @PreAuthorize("hasAnyRole('COLABORADORES')")
    public ResponseEntity<ColaboradorResponse> criaNovoColaborador(HttpServletRequest req,
                                                                   @RequestBody ColaboradorDto colaboradorDto) {
        log.info("Método controlador de criação de novo colaborador acessado");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(colaboradorService.criaNovoColaborador(jwtUtil.obtemUsuarioAtivo(req), colaboradorDto));
    }

    @PutMapping("/{id}")
    @ApiOperation(
            value = "Atualização de colaborador",
            notes = "Esse endpoint tem como objetivo realizar a atualização de um colaborador na base de dados da empresa",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Colaborador atualizado com sucesso", response = ColaboradorResponse.class),
            @ApiResponse(code = 400, message = "A inscrição estadual informada já existe", response = InvalidRequestException.class),
            @ApiResponse(code = 400, message = "O CPF/CNPJ informado já existe", response = InvalidRequestException.class),
            @ApiResponse(code = 400, message = "Nenhum colaborador foi encontrado com o id informado", response = ObjectNotFoundException.class)
    })
    @PreAuthorize("hasAnyRole('COLABORADORES')")
    public ResponseEntity<ColaboradorResponse> atualizaColaborador(HttpServletRequest req,
                                                                   @RequestBody ColaboradorDto colaboradorDto,
                                                                   @PathVariable Long id) {
        log.info("Método controlador de atualização de colaborador acessado");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(colaboradorService.atualizaColaborador(jwtUtil.obtemUsuarioAtivo(req), id, colaboradorDto));
    }

    @DeleteMapping
    @ApiOperation(
            value = "Remoção de colaborador em massa",
            notes = "Esse endpoint tem como objetivo realizar a exclusão de colaboradores em massa",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Colaboradores excluídos com sucesso"),
            @ApiResponse(code = 400, message = "Nenhum colaborador foi encontrado com o id informado", response = ObjectNotFoundException.class),
            @ApiResponse(code = 400, message = "O colaborador selecionado já foi excluído", response = InvalidRequestException.class),
            @ApiResponse(code = 400, message = "Nenhum colaborador foi encontrado para remoção", response = InvalidRequestException.class),
            @ApiResponse(code = 400, message = "Não é possível remover um colaborador que já foi excluído", response = InvalidRequestException.class)
    })
    @PreAuthorize("hasAnyRole('COLABORADORES')")
    public ResponseEntity<?> removeColaboradoresEmMassa(HttpServletRequest req,
                                                        @RequestBody List<Long> ids) {
        log.info("Método controlador de remoção de colaboradores em massa acessado");
        colaboradorService.removeColaboradoresEmMassa(jwtUtil.obtemUsuarioAtivo(req), ids);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(
            value = "Remoção de colaborador",
            notes = "Esse endpoint tem como objetivo realizar a exclusão de um colaborador",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Colaboradores excluídos com sucesso", response = ColaboradorResponse.class),
            @ApiResponse(code = 400, message = "Nenhum colaborador foi encontrado com o id informado", response = ObjectNotFoundException.class),
            @ApiResponse(code = 400, message = "O colaborador selecionado já foi excluído", response = InvalidRequestException.class),
            @ApiResponse(code = 400, message = "Nenhum colaborador foi encontrado para remoção", response = InvalidRequestException.class),
            @ApiResponse(code = 400, message = "Não é possível remover um colaborador que já foi excluído", response = InvalidRequestException.class)
    })
    @PreAuthorize("hasAnyRole('COLABORADORES')")
    public ResponseEntity<ColaboradorResponse> removeColaborador(HttpServletRequest req,
                                                                 @PathVariable Long id) {
        log.info("Método controlador de remoção de colaborador acessado");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(colaboradorService.removeColaborador(jwtUtil.obtemUsuarioAtivo(req), id));
    }

    @PostMapping("/relatorio")
    @ApiOperation(
            value = "Gerar relatório em PDF",
            notes = "Esse endpoint tem como objetivo realizar a criação de um relatório em PDF com a listagem de colaboradores " +
                    "solicitada",
            produces = MediaType.APPLICATION_OCTET_STREAM,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "PDF gerado com sucesso"),
            @ApiResponse(code = 400, message = "Ocorreu um erro na criação do PDF", response = Exception.class),
    })
    @PreAuthorize("hasAnyRole('COLABORADORES')")
    public void relatorio(HttpServletResponse res,
                          HttpServletRequest req,
                          @RequestBody List<Long> ids) throws DocumentException {
        log.info("Método controlador de obtenção de relatório de colaboradores em PDF acessado. IDs: {}", ids);

        ColaboradorEntity usuarioAtivo = jwtUtil.obtemUsuarioAtivo(req);

        res.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachement; filename=akadion_"
                + usuarioAtivo.getEmpresa().getNome().replace(" ", "-").toLowerCase()
                + "_colaboradores_"
                + new SimpleDateFormat("dd.MM.yyyy_HHmmss").format(new Date())
                + ".pdf";
        res.setHeader(headerKey, headerValue);

        //relatorioService.exportarPdf(res, usuarioAtivo, ids);
    }

}
