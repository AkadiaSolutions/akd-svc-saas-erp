package br.akd.svc.akadia.proxy.asaas.responses;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardResponse {

    private String creditCardNumber;
    private String creditCardBrand;
    private String creditCardToken;

}
