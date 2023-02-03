package br.akd.svc.akadia.models.dto.site.fiscal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NfceConfigDto {
    private Long id;
    private Long proximoNumeroProducao;
    private Long proximoNumeroHomologacao;
    private Integer serieProducao;
    private Integer serieHomologacao;
    private String cscProducao;
    private String cscHomologacao;
    private Long idTokenProducao;
    private Long idTokenHomologacao;
}
