package br.akd.svc.akadia.proxy.asaas.responses;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardResponse {

    private String creditCardNumber;
    private String creditCardBrand;
    private String creditCardToken;

}
