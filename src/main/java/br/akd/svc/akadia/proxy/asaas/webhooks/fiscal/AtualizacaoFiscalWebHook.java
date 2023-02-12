package br.akd.svc.akadia.proxy.asaas.webhooks.fiscal;

import br.akd.svc.akadia.proxy.asaas.webhooks.fiscal.enums.EventoFiscalEnum;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class AtualizacaoFiscalWebHook {

    private EventoFiscalEnum event;

    private InvoiceWebHook invoice;

}