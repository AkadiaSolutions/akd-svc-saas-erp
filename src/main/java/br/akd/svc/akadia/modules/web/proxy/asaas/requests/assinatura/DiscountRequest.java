package br.akd.svc.akadia.modules.web.proxy.asaas.requests.assinatura;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DiscountRequest {
    private Double value;
    private Integer dueDateLimitDays;
}
