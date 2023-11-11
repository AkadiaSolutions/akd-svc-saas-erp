package br.akd.svc.akadia.modules.web.proxy.asaas.responses.assinatura.consulta;

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
