package br.akd.svc.akadia.modules.web.proxy.asaas.webhooks.fiscal.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EventoFiscalEnum {
    INVOICE_CREATED(0, "Geração de nova nota fiscal"),
    INVOICE_UPDATED(1, "Alteração de nota fiscal"),
    INVOICE_SYNCHRONIZED(2, "Nota fiscal enviada para a prefeitura"),
    INVOICE_AUTHORIZED(3, "Nota fiscal emitida"),
    INVOICE_PROCESSING_CANCELLATION(4, "Nota fiscal processando cancelamento"),
    INVOICE_CANCELED(5, "Invoice canceled"),
    INVOICE_CANCELLATION_DENIED(6, "Recusado o cancelamento da nota fiscal"),
    INVOICE_ERROR(7, "Nota fiscal com erro");

    private final int code;
    private final String desc;
}
