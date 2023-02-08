package br.akd.svc.akadia.proxy.asaas.responses.assinatura;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AtualizaAssinaturaSplitResponse {
    private String walletId;
    private Double fixedValue;
    private Double percentualValue;
    private String status;
    private String refusalReason;
}
