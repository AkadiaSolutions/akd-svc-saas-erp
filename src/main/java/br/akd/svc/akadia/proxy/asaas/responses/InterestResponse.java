package br.akd.svc.akadia.proxy.asaas.responses;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InterestResponse {
    private Double value;
    private String type;
}
