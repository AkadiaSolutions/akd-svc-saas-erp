package br.akd.svc.akadia.modules.web.proxy.asaas.webhooks.cobranca;

import br.akd.svc.akadia.modules.web.proxy.asaas.webhooks.cobranca.enums.EventoCobrancaEnum;
import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AtualizacaoCobrancaWebHook {
    private EventoCobrancaEnum event;
    private PagamentoWebHook payment;
}
