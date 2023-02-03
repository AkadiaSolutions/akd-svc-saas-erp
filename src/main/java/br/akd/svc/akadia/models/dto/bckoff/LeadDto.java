package br.akd.svc.akadia.models.dto.bckoff;

import br.akd.svc.akadia.models.dto.global.TelefoneDto;
import br.akd.svc.akadia.models.enums.bckoff.OrigemLeadEnum;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LeadDto {
    private Long id;
    private String nome;
    private String email;
    private OrigemLeadEnum origemLeadEnum;
    private TelefoneDto telefone;
}
