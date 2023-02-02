package br.akd.svc.akadia.proxy.asaas;

import br.akd.svc.akadia.proxy.asaas.requests.ClienteSistemaRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(name = "ASAAS", url = "${URL_ASAAS}")
public interface AsaasProxy {

    @PostMapping(value="/customers")
    ResponseEntity<?> cadastraCliente(@RequestBody ClienteSistemaRequest clienteSistemaRequest,
                                      @RequestHeader Map<String, String> headers);

}
