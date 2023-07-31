package br.akd.svc.akadia.controllers.sistema.colaboradores;

import br.akd.svc.akadia.config.security.JWTUtil;
import br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.acao.AcaoPageResponse;
import br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.acesso.AcessoPageResponse;
import br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.ColaboradorPageResponse;
import br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.ColaboradorResponse;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.ColaboradorEntity;
import br.akd.svc.akadia.repositories.sistema.colaboradores.ColaboradorRepository;
import br.akd.svc.akadia.services.exceptions.InvalidRequestException;
import br.akd.svc.akadia.services.exceptions.ObjectNotFoundException;
import br.akd.svc.akadia.services.sistema.colaboradores.acao.AcaoService;
import br.akd.svc.akadia.services.sistema.colaboradores.acesso.AcessoService;
import br.akd.svc.akadia.services.sistema.colaboradores.colaborador.ColaboradorRelatorioService;
import br.akd.svc.akadia.services.sistema.colaboradores.colaborador.ColaboradorService;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin
@RestController
@Api(value = "Essa API disponibiliza os endpoints de CRUD da entidade Colaborador")
@Produces({MediaType.APPLICATION_JSON, "application/json"})
@Consumes({MediaType.APPLICATION_JSON, "application/json"})
@RequestMapping("/api/sistema/v1/colaborador")
public class ColaboradorController {

    @Autowired
    ColaboradorService colaboradorService;

    @Autowired
    AcaoService acaoService;

    @Autowired
    AcessoService acessoService;

    @Autowired
    ColaboradorRelatorioService relatorioService;

    @Autowired
    ColaboradorRepository colaboradorRepository;

    @Autowired
    JWTUtil jwtUtil;

    @GetMapping("imagem-perfil/{id}")
    @ApiOperation(
            value = "Obtenção de imagem de perfil do colaborador",
            notes = "Esse endpoint tem como objetivo realizar a obtenção da imagem de perfil de um colaborador",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Imagem de perfil do colaborador obtida com sucesso", response = ColaboradorResponse.class),
            @ApiResponse(code = 400, message = "Nenhum colaborador foi encontrado com o id informado", response = ObjectNotFoundException.class)
    })
    @PreAuthorize("hasAnyRole('COLABORADORES')")
    public ResponseEntity<byte[]> obtemImagemDePerfilDoColaborador(HttpServletRequest req,
                                                                                @PathVariable("id") Long id) {
        log.info("Método controlador de obtenção de imagem de perfil de colaborador acessado");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(colaboradorService.obtemImagemPerfilColaborador(jwtUtil.obtemUsuarioAtivo(req), id));
    }

    @PutMapping("imagem-perfil/{id}")
    @ApiOperation(
            value = "Atualização de imagem de perfil do colaborador",
            notes = "Esse endpoint tem como objetivo realizar a atualização da imagem de perfil de um colaborador",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Colaborador atualizado com sucesso", response = ColaboradorResponse.class),
            @ApiResponse(code = 400, message = "Nenhum colaborador foi encontrado com o id informado", response = ObjectNotFoundException.class)
    })
    @PreAuthorize("hasAnyRole('COLABORADORES')")
    public ResponseEntity<ColaboradorResponse> atualizaImagemPerfilColaborador(HttpServletRequest req,
                                                                               @RequestParam(value = "imagemPerfil", required = false) MultipartFile imagemPerfil,
                                                                               @PathVariable("id") Long id) throws IOException {
        log.info("Método controlador de atualização de imagem de perfil de colaborador acessado");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(colaboradorService.atualizaImagemPerfilColaborador(
                        jwtUtil.obtemUsuarioAtivo(req),
                        id,
                        imagemPerfil));
    }

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

    @GetMapping("/{id}/acessos")
    @ApiOperation(
            value = "Busca paginada por acessos do colaborador",
            notes = "Esse endpoint tem como objetivo realizar a busca paginada de acessos do colaborador",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "A busca paginada de advertências do colaborador foi realizada com sucesso",
                    response = AcessoPageResponse.class),
    })
    @PreAuthorize("hasAnyRole('COLABORADORES')")
    public ResponseEntity<AcessoPageResponse> obtemAcessosColaboradorPaginada(
            Pageable pageable,
            HttpServletRequest req,
            @PathVariable("id") Long id) {
        log.info("Endpoint de busca paginada de acessos do colaborador acessada");
        return ResponseEntity.ok().body(acessoService.obtemAcessosColaborador(
                jwtUtil.obtemUsuarioAtivo(req), pageable, id));
    }

    @GetMapping("/{id}/acoes")
    @ApiOperation(
            value = "Busca paginada por ações de colaborador",
            notes = "Esse endpoint tem como objetivo realizar a busca paginada de ações de colaborador",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "A busca paginada de ações do colaborador foi realizada com sucesso",
                    response = AcaoPageResponse.class),
    })
    @PreAuthorize("hasAnyRole('COLABORADORES')")
    public ResponseEntity<AcaoPageResponse> obtemAcoesColaboradorPaginada(
            Pageable pageable,
            HttpServletRequest req,
            @PathVariable("id") Long id) {
        log.info("Endpoint de busca paginada de ações do colaborador acessada");
        return ResponseEntity.ok().body(acaoService.obtemAcoesColaborador(
                jwtUtil.obtemUsuarioAtivo(req), pageable, id));
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
            notes = "Esse endpoint tem como objetivo realizar a criação de um novo colaborador na base dados da empresa"
    )
    @ApiResponses({
            @ApiResponse(code = 201, message = "Colaborador persistido com sucesso", response = Long.class),
            @ApiResponse(code = 400, message = "A inscrição estadual informada já existe", response = InvalidRequestException.class),
            @ApiResponse(code = 400, message = "O CPF/CNPJ informado já existe", response = InvalidRequestException.class),
            @ApiResponse(code = 400, message = "Erro de requisição inválida", response = InvalidRequestException.class)
    })
    @PreAuthorize("hasAnyRole('COLABORADORES')")
    public ResponseEntity<String> criaNovoColaborador(HttpServletRequest req,
                                                      @RequestParam(value = "contratoColaborador", required = false) MultipartFile contratoColaborador,
                                                      @RequestParam("colaborador") String colaborador) throws IOException {
        log.info("Método controlador de criação de novo colaborador acessado");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(colaboradorService.criaNovoColaborador(jwtUtil.obtemUsuarioAtivo(req), contratoColaborador, colaborador));
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
    public ResponseEntity<String> atualizaColaborador(HttpServletRequest req,
                                                      @RequestParam(value = "contratoColaborador", required = false) MultipartFile contratoColaborador,
                                                      @RequestParam("colaborador") String colaborador,
                                                      @RequestParam("id") Long id) throws IOException {
        log.info("Método controlador de atualização de colaborador acessado");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(colaboradorService.atualizaColaborador(
                        jwtUtil.obtemUsuarioAtivo(req),
                        id,
                        contratoColaborador,
                        colaborador).getMatricula());
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
                          @RequestBody List<Long> ids) throws DocumentException, IOException {
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

        relatorioService.exportarPdf(res, usuarioAtivo, ids);
    }

    @GetMapping("/ocupacoes")
    @ApiOperation(
            value = "Obtém todas as ocupações da empresa",
            notes = "Esse endpoint tem como objetivo retornar uma lista com todas as ocupações já cadastradas da empresa " +
                    "atual",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok")
    })
    @PreAuthorize("hasAnyRole('COLABORADORES')")
    public ResponseEntity<List<String>> obtemOcupacoes(HttpServletRequest req) {
        ColaboradorEntity usuarioAtivo = jwtUtil.obtemUsuarioAtivo(req);
        return ResponseEntity.ok()
                .body(new LinkedHashSet<>(
                        colaboradorRepository.buscaTodasOcupacoesDaEmpresa(usuarioAtivo.getEmpresa().getId()))
                        .stream().sorted().collect(Collectors.toList()));
    }

}
