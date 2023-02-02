package br.akd.svc.akadia.proxy.asaas.requests;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardRequest {
    private String holderName;
    private String number;
    private String expiryMonth;
    private String expiryYear;
    private String ccv;
}
