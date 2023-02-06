package br.akd.svc.akadia.proxy.asaas;

import br.akd.svc.akadia.proxy.asaas.requests.AssinaturaRequest;
import br.akd.svc.akadia.proxy.asaas.requests.ClienteSistemaRequest;
import br.akd.svc.akadia.proxy.asaas.responses.AssinaturaResponse;
import br.akd.svc.akadia.proxy.asaas.responses.ClienteSistemaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "ASAAS", url = "${URL_ASAAS}")
public interface AsaasProxy {

    @PostMapping(value = "/customers")
    ResponseEntity<ClienteSistemaResponse> cadastraNovoCliente(@RequestBody ClienteSistemaRequest clienteSistemaRequest,
                                          @RequestHeader(value = "access_token") String accessToken);

    @PostMapping(value = "/subscriptions")
    ResponseEntity<AssinaturaResponse> cadastraNovoPlano(@RequestBody AssinaturaRequest assinaturaRequest,
                                                         @RequestHeader(value = "access_token") String accessToken);

    @PostMapping(value = "/customers/{idCliente}")
    ResponseEntity<ClienteSistemaResponse> atualizaDadosCliente(@PathVariable String idCliente,
                                                @RequestBody ClienteSistemaRequest clienteSistemaRequest,
                                                @RequestHeader(value = "access_token") String accessToken);
}
