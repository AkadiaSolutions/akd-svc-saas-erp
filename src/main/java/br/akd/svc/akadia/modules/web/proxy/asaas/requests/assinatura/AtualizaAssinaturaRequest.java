package br.akd.svc.akadia.modules.web.proxy.asaas.requests.assinatura;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AtualizaAssinaturaRequest {
    private String billingType;
    private Double value;
    private String nextDueDate;
    private DiscountRequest discount;
    private InterestRequest interest;
    private FineRequest fine;
    private String cycle;
    private String description;
    private String updatePendingPayments;
    private String externalReference;
}
