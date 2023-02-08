package br.akd.svc.akadia.proxy.asaas.responses.assinatura;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AtualizaAssinaturaDiscountResponse {
    private Double value;
    private Integer dueDateLimitDays;
    private String type;
}
