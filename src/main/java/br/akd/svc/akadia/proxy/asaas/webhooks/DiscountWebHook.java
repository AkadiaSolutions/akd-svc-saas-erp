package br.akd.svc.akadia.proxy.asaas.webhooks;

import lombok.*;

@Builder
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
