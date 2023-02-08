package br.akd.svc.akadia.proxy.asaas.responses.assinatura;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaSplitResponse {
    private String walletId;
    private Double fixedValue;
    private Integer percentualValue;
    private String status;
    private String refusalReason;

}
