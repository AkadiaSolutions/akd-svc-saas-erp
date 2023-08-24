package br.akd.svc.akadia.models.dto.sistema.patrimonios.request;

import br.akd.svc.akadia.models.enums.sistema.patrimonios.TipoPatrimonioEnum;
import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PatrimonioRequest {

    private String dataEntrada;
    private String descricao;
    private Double valor;
    private TipoPatrimonioEnum tipoPatrimonio;

}
