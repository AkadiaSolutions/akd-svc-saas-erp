package br.akd.svc.akadia.proxy.asaas.webhooks.cobranca;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChargeBackWebHook {
    private String status;
    private String reason;
}
