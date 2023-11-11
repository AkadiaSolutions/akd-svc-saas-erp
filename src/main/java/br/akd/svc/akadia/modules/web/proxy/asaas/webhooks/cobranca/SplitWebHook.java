package br.akd.svc.akadia.modules.web.proxy.asaas.webhooks.cobranca;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SplitWebHook {
    private String walletId;
    private Double fixedValue;
    private String status;
    private String refusalReason;
}
