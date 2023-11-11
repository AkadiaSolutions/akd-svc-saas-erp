package br.akd.svc.akadia.modules.web.proxy.asaas.responses.assinatura;

import br.akd.svc.akadia.modules.web.proxy.asaas.responses.CreditCardResponse;
import lombok.*;

@Getter
@Setter
@ToString
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
    private FineResponse fine;
    private InterestResponse interest;
    private String split;
}
