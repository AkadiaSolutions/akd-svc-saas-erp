package br.akd.svc.akadia.models.dto.site;

import br.akd.svc.akadia.models.enums.site.FormaPagamentoSistemaEnum;
import br.akd.svc.akadia.models.enums.site.StatusPlanoEnum;
import br.akd.svc.akadia.models.enums.site.TipoPlanoEnum;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PlanoDto {
    private Long id;
    private String codigoAssinaturaAsaas;
    private String dataContratacao;
    private String horaContratacao;
    private TipoPlanoEnum tipoPlanoEnum;
    private StatusPlanoEnum statusPlanoEnum;
    private FormaPagamentoSistemaEnum formaPagamentoSistemaEnum;

}