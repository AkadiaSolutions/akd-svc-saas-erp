package br.akd.svc.akadia.modules.global.dto;

import br.akd.svc.akadia.modules.global.enums.TipoTelefoneEnum;
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
    private TipoTelefoneEnum tipoTelefone;
}
