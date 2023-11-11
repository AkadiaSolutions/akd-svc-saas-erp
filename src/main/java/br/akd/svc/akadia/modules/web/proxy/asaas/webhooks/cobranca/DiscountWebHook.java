package br.akd.svc.akadia.modules.web.proxy.asaas.webhooks.cobranca;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DiscountWebHook {

    private Double value;
    private Integer dueDateLimitDays;
    private String type;
}
