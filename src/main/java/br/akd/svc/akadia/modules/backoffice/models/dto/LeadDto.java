package br.akd.svc.akadia.modules.backoffice.models.dto;

import br.akd.svc.akadia.modules.global.dto.TelefoneDto;
import br.akd.svc.akadia.modules.backoffice.models.enums.OrigemLeadEnum;
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
