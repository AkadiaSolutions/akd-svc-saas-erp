package br.akd.svc.akadia.modules.web.proxy.asaas.webhooks.cobranca;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FineWebHook {
    private Double value;
    private String type;
}
