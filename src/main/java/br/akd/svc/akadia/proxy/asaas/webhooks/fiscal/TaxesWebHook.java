package br.akd.svc.akadia.proxy.asaas.webhooks.fiscal;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TaxesWebHook {
    private Boolean retainIss;
    private Integer iss;
    private Integer cofins;
    private Integer csll;
    private Integer inss;
    private Double ir;
    private Double pis;
}
