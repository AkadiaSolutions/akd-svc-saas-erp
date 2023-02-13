package br.akd.svc.akadia.models.dto.site.empresa;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DadosEmpresaDeletadaDto {
    private Long id;
    private String dataRemocao;
    private String horaRemocao;
    private String cnpj;
    private String razaoSocial;
    private String inscricaoMunicipal;
    private String inscricaoEstadual;
}
