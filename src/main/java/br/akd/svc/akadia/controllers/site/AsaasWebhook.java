package br.akd.svc.akadia.controllers.site;

import br.akd.svc.akadia.proxy.asaas.webhooks.AtualizacaoCobrancaWebHook;
import br.akd.svc.akadia.services.site.PagamentoSistemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hook/v1/pagamento")
public class AsaasWebhook {

    @Autowired
    PagamentoSistemaService pagamentoSistemaService;

    //TODO INSERIR ANOTATIONS DA API
    @PostMapping
    public void recebeStatusPagamento(@RequestBody AtualizacaoCobrancaWebHook atualizacaoCobrancaWebHook) {
        pagamentoSistemaService.realizaTratamentoWebhook(atualizacaoCobrancaWebHook);
    }

}
