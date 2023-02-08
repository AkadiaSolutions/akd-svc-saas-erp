package br.akd.svc.akadia.proxy.asaas.webhooks;

import lombok.*;

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardWebHook {
    private String creditCardNumber;
    private String creditCardBrand;
    private String creditCardToken;
}
