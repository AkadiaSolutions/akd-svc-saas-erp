package br.akd.svc.akadia.proxy.asaas.responses.assinatura.cancela;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CancelamentoAssinaturaResponse {
    private Boolean deleted;
    private String id;
}
