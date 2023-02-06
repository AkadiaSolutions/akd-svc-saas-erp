package br.akd.svc.akadia.proxy.asaas.requests;

import lombok.*;

@Builder
@ToString
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteSistemaRequest {
    private String name;
    private String email;
    private String phone;
    private String mobilePhone;
    private String cpfCnpj;
    private String postalCode;
    private String address;
    private String addressNumber;
    private String complement;
    private String province;
    private String externalReference;
    private Boolean notificationDisabled;
    private String additionalEmails;
    private String municipalInscription;
    private String stateInscription;
    private String observations;
}
