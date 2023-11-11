package br.akd.svc.akadia.modules.web.proxy.asaas.responses.assinatura.cancela;

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
