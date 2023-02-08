package br.akd.svc.akadia.proxy.asaas.webhooks;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InterestWebHook {
    private Double value;
    private String type;
}
