package br.akd.svc.akadia.modules.web.proxy.asaas.webhooks.fiscal;

import lombok.*;

@Getter
@Setter
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
