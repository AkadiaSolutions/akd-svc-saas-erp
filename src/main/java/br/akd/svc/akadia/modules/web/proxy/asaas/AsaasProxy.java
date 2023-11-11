package br.akd.svc.akadia.modules.web.proxy.asaas;

import br.akd.svc.akadia.modules.web.proxy.asaas.requests.assinatura.AssinaturaRequest;
import br.akd.svc.akadia.modules.web.proxy.asaas.requests.assinatura.AtualizaAssinaturaRequest;
import br.akd.svc.akadia.modules.web.proxy.asaas.requests.ClienteSistemaRequest;
import br.akd.svc.akadia.modules.web.proxy.asaas.requests.fiscal.CriaConfigFiscalRequest;
import br.akd.svc.akadia.modules.web.proxy.asaas.responses.assinatura.AssinaturaResponse;
import br.akd.svc.akadia.modules.web.proxy.asaas.responses.ClienteSistemaResponse;
import br.akd.svc.akadia.modules.web.proxy.asaas.responses.assinatura.atualiza.AtualizaAssinaturaResponse;
import br.akd.svc.akadia.modules.web.proxy.asaas.responses.assinatura.cancela.CancelamentoAssinaturaResponse;
import br.akd.svc.akadia.modules.web.proxy.asaas.responses.assinatura.consulta.ConsultaAssinaturaResponse;
import br.akd.svc.akadia.modules.web.proxy.asaas.responses.fiscal.CriaConfigFiscalResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ASAAS", url = "${URL_ASAAS}")
public interface AsaasProxy {

    @PostMapping(value = "/customers")
    ResponseEntity<ClienteSistemaResponse> cadastraNovoCliente(@RequestBody ClienteSistemaRequest clienteSistemaRequest,
                                          @RequestHeader(value = "access_token") String accessToken);

    @PostMapping(value = "/customers/{idCliente}")
    ResponseEntity<ClienteSistemaResponse> atualizaDadosCliente(@PathVariable String idCliente,
                                                @RequestBody ClienteSistemaRequest clienteSistemaRequest,
                                                @RequestHeader(value = "access_token") String accessToken);

    @PostMapping(value = "/subscriptions")
    ResponseEntity<AssinaturaResponse> cadastraNovaAssinatura(@RequestBody AssinaturaRequest assinaturaRequest,
                                                              @RequestHeader(value = "access_token") String accessToken);

    @GetMapping(value = "/subscriptions/{idAssinatura}")
    ResponseEntity<ConsultaAssinaturaResponse> consultaAssinatura(@PathVariable String idAssinatura,
                                                                  @RequestHeader(value = "access_token") String accessToken);

    @PostMapping(value = "/subscriptions/{idAssinatura}")
    ResponseEntity<AtualizaAssinaturaResponse> atualizaAssinatura(@PathVariable String idAssinatura,
                                                                  @RequestBody AtualizaAssinaturaRequest atualizaAssinaturaRequest,
                                                                  @RequestHeader(value = "access_token") String accessToken);

    @DeleteMapping(value = "/subscriptions/{idAssinatura}")
    ResponseEntity<CancelamentoAssinaturaResponse> cancelaAssinatura(@PathVariable String idAssinatura,
                                                                     @RequestHeader(value = "access_token") String accessToken);

    @PostMapping(value = "/subscriptions/{idAssinatura}/invoiceSettings")
    ResponseEntity<CriaConfigFiscalResponse> criaConfiguracaoFiscalDaAssinatura(@PathVariable String idAssinatura,
                                                                                @RequestBody CriaConfigFiscalRequest criaConfigFiscalRequest,
                                                                                @RequestHeader(value = "access_token") String accessToken);
}
