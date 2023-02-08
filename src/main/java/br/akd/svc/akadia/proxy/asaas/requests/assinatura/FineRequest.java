package br.akd.svc.akadia.proxy.asaas.requests.assinatura;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FineRequest {
    private Double value;
}
