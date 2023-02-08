package br.akd.svc.akadia.proxy.asaas.webhooks;

import lombok.*;

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FineWebHook {
    private Double value;
    private String type;
}
