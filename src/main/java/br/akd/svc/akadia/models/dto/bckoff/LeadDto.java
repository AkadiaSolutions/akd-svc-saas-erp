package br.akd.svc.akadia.models.dto.bckoff;

import br.akd.svc.akadia.models.dto.global.TelefoneDto;
import br.akd.svc.akadia.models.enums.bckoff.OrigemLeadEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LeadDto {
    private Long id;
    private String nome;
    private String email;
    private OrigemLeadEnum origemLeadEnum;
    private TelefoneDto telefone;
}
