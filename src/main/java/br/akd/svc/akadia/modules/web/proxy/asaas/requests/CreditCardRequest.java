package br.akd.svc.akadia.modules.web.proxy.asaas.requests;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardRequest {
    private String holderName;
    private String number;
    private String expiryMonth;
    private String expiryYear;
    private String ccv;
}
