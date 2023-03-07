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
    private String prefixo;
    private String numero;
    private TipoTelefoneEnum tipoTelefoneEnum;
}
