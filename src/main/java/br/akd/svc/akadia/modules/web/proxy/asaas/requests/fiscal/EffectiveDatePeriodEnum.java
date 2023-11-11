package br.akd.svc.akadia.modules.web.proxy.asaas.requests.fiscal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EffectiveDatePeriodEnum {

    ON_PAYMENT_CONFIRMATION(0, "Quando a cobrança for paga"),
    ON_PAYMENT_DUE_DATE(1, "No dia do vencimento de cada cobrança"),
    BEFORE_PAYMENT_DUE_DATE(2, "Dias antes do vencimento da cobrança"),
    ON_DUE_DATE_MONTH(3, "No primeiro mês do vencimento de cada cobrança"),
    ON_NEXT_MONTH(4, "No primeiro dia do mês do vencimento de cada cobrança");

    private final int code;
    private final String desc;

}
