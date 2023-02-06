package br.akd.svc.akadia.proxy.asaas.responses;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FineResponse {
    private Double value;
    private String type;
}
