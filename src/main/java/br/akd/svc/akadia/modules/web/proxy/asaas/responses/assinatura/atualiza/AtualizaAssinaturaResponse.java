package br.akd.svc.akadia.modules.web.proxy.asaas.responses.assinatura.atualiza;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AtualizaAssinaturaResponse {
    private String id;
    private String dateCreated;
    private String customer;
    private String paymentLink;
    private String billingType;
    private Double value;
    private String nextDueDate;
    private AtualizaAssinaturaDiscountResponse discount;
    private AtualizaAssinaturaInterestResponse interest;
    private AtualizaAssinaturaFineResponse fine;
    private String cycle;
    private String description;
    private String endDate;
    private String maxPayments;
    private String status;
    private String externalReference;
    private AtualizaAssinaturaSplitResponse split;
}