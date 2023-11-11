package br.akd.svc.akadia.modules.web.proxy.asaas.responses.assinatura.atualiza;

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
