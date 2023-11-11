package br.akd.svc.akadia.modules.web.proxy.asaas.requests.assinatura;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InterestRequest {
    private Double value;
}
