package br.akd.svc.akadia.proxy.asaas.requests;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssinaturaRequest {
    private String customer;
    private String billingType;
    private String nextDueDate;
    private Double value;
    private String cycle;
    private String description;
    private String creditCardToken;
    private CreditCardRequest creditCard;
    private CreditCardHolderInfoRequest creditCardHolderInfo;
}
