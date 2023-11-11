package br.akd.svc.akadia.modules.web.proxy.asaas.global;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Taxes {
    private Boolean retainIss;
    private Integer iss;
    private Integer cofins;
    private Integer csll;
    private Integer inss;
    private Double ir;
    private Double pis;
}
