package br.akd.svc.akadia.modules.web.models.dto.empresa.fiscal;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
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
