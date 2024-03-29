package br.akd.svc.akadia.modules.web.hook.asaas;

import br.akd.svc.akadia.modules.web.proxy.asaas.webhooks.cobranca.AtualizacaoCobrancaWebHook;
import br.akd.svc.akadia.modules.web.proxy.asaas.webhooks.fiscal.AtualizacaoFiscalWebHook;
import br.akd.svc.akadia.exceptions.FeignConnectionException;
import br.akd.svc.akadia.exceptions.ObjectNotFoundException;
import br.akd.svc.akadia.modules.web.services.PagamentoSistemaService;
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

@Slf4j
@RestController
@RequestMapping("/hook/v1")
@Api(value = "Essa API disponibiliza os endpoints de recebimento de webhooks por parte da integradora de pagamentos")
@Produces({MediaType.APPLICATION_JSON, "application/json"})
@Consumes({MediaType.APPLICATION_JSON, "application/json"})
public class AsaasWebhook {

    @Autowired
    PagamentoSistemaService pagamentoSistemaService;

    @ApiOperation(
            value = "Recebimento de status de pagamento",
            notes = "Esse endpoint tem como objetivo receber atualizações no status dos pagamentos das assinaturas por " +
                    "parte da integradora ASAAS",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição finalizada com sucesso", response = HttpStatus.class),
            @ApiResponse(code = 400, message = "Nenhum cliente foi encontrado com o id informado",
                    response = ObjectNotFoundException.class),
            @ApiResponse(code = 500, message = "Ocorreu uma falha na comunicação com a integradora de pagamentos",
                    response = FeignConnectionException.class),
    })
    @PostMapping(value = "/pagamento")
    public ResponseEntity<HttpStatus> recebeStatusPagamento(@RequestBody AtualizacaoCobrancaWebHook atualizacaoCobrancaWebHook,
                                                            @RequestHeader(value = "asaas-access-token") String token) {
        log.info("Webhook ASAAS de atualização do status de cobrança recebido: {}", atualizacaoCobrancaWebHook);
        pagamentoSistemaService.realizaValidacaoToken(token);
        pagamentoSistemaService.realizaTratamentoWebhookCobranca(atualizacaoCobrancaWebHook);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping(value = "/fiscal")
    public ResponseEntity<HttpStatus> recebeStatusFiscal(@RequestBody AtualizacaoFiscalWebHook atualizacaoFiscalWebHook,
                                                         @RequestHeader(value = "asaas-access-token") String token) {
        log.info("Webhook ASAAS de atualização do status fiscal de uma cobrança recebido: {}", atualizacaoFiscalWebHook);
        pagamentoSistemaService.realizaValidacaoToken(token);
        pagamentoSistemaService.realizaTratamentoWebhookFiscal(atualizacaoFiscalWebHook);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}