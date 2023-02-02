package br.akd.svc.akadia.proxy.asaas.responses;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssinaturaResponse {
    private String object;
    private String id;
    private String dateCreated;
    private String customer;
    private String paymentLink;
    private Double value;
    private String nextDueDate;
    private String cycle;
    private String description;
    private String billingType;
    private Boolean deleted;
    private String status;
    private String externalReference;
    private Boolean sendPaymentByPostalService;
    private CreditCardResponse creditCard;
}
