package br.akd.svc.akadia.modules.web.proxy.asaas.requests;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardHolderInfoRequest {
    private String name;
    private String email;
    private String cpfCnpj;
    private String postalCode;
    private String addressNumber;
    private String addressComplement;
    private String phone;
    private String mobilePhone;
}
