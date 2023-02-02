package br.akd.svc.akadia.proxy.asaas.responses;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FineResponse {
    private Double value;
    private String type;
}
