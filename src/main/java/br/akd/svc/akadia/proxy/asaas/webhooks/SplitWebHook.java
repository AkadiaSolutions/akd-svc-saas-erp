package br.akd.svc.akadia.proxy.asaas.webhooks;

import lombok.*;

@Builder
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
