package br.akd.svc.akadia.models.dto.global;

import br.akd.svc.akadia.models.enums.global.TipoTelefoneEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TelefoneDto {
    private Long id;
    private Integer prefixo;
    private Long numero;
    private TipoTelefoneEnum tipoTelefoneEnum;
}
