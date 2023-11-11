package br.akd.svc.akadia.modules.web.models.dto;

import br.akd.svc.akadia.modules.web.models.enums.FormaPagamentoSistemaEnum;
import br.akd.svc.akadia.modules.web.models.enums.StatusPlanoEnum;
import br.akd.svc.akadia.modules.web.models.enums.TipoPlanoEnum;
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
    private String dataVencimento;
    private TipoPlanoEnum tipoPlanoEnum;
    private StatusPlanoEnum statusPlanoEnum;
    private FormaPagamentoSistemaEnum formaPagamentoSistemaEnum;

}