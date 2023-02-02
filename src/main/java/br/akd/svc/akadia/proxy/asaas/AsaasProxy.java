package br.akd.svc.akadia.proxy.asaas;

import br.akd.svc.akadia.proxy.asaas.requests.AssinaturaRequest;
import br.akd.svc.akadia.proxy.asaas.requests.ClienteSistemaRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "ASAAS", url = "${URL_ASAAS}")
public interface AsaasProxy {

    @PostMapping(value="/customers")
    ResponseEntity<?> cadastraNovoCliente(@RequestBody ClienteSistemaRequest clienteSistemaRequest,
                                          @RequestHeader(value = "access_token") String access_token);

    @PostMapping(value="/subscriptions")
    ResponseEntity<?> cadastraNovoPlano(@RequestBody AssinaturaRequest assinaturaRequest,
                                        @RequestHeader(value = "access_token") String access_token);

}
