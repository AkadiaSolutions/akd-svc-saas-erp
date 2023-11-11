package br.akd.svc.akadia.modules.web.proxy.asaas.requests.assinatura;

import br.akd.svc.akadia.modules.web.proxy.asaas.requests.CreditCardHolderInfoRequest;
import br.akd.svc.akadia.modules.web.proxy.asaas.requests.CreditCardRequest;
import lombok.*;

@Builder
@Getter
@Setter
@ToString
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
