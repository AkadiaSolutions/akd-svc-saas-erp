package br.akd.svc.akadia.proxy.asaas.webhooks;

import br.akd.svc.akadia.proxy.asaas.webhooks.enums.EventEnum;
import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AtualizacaoCobrancaWebHook {
    private EventEnum event;
    PagamentoWebHook payment;
}
