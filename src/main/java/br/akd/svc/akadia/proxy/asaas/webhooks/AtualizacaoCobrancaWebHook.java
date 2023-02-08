package br.akd.svc.akadia.proxy.asaas.webhooks;

import lombok.*;

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AtualizacaoCobrancaWebHook {
    private EventEnum event;
    PagamentoWebHook payment;
}
