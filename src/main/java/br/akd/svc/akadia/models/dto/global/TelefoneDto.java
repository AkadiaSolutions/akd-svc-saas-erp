package br.akd.svc.akadia.models.dto.global;

import br.akd.svc.akadia.models.enums.global.TipoTelefoneEnum;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TelefoneDto {
    private Long id;
    private Integer prefixo;
    private Long numero;
    private TipoTelefoneEnum tipoTelefoneEnum;
}
