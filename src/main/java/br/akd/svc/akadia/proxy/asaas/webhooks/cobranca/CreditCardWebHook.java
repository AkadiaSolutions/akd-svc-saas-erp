package br.akd.svc.akadia.proxy.asaas.webhooks.cobranca;

import lombok.*;

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
