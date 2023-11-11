package br.akd.svc.akadia.modules.web.proxy.asaas.webhooks.fiscal;

import br.akd.svc.akadia.modules.web.proxy.asaas.webhooks.fiscal.enums.EventoFiscalEnum;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class AtualizacaoFiscalWebHook {

    private EventoFiscalEnum event;

    private InvoiceWebHook invoice;

}
