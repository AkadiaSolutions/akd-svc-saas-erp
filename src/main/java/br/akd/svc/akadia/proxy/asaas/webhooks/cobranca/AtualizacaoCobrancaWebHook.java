package br.akd.svc.akadia.proxy.asaas.webhooks.cobranca;

import br.akd.svc.akadia.proxy.asaas.webhooks.cobranca.enums.EventoCobrancaEnum;
import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AtualizacaoCobrancaWebHook {
    private EventoCobrancaEnum event;
    PagamentoWebHook payment;
}
