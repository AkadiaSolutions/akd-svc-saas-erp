package br.akd.svc.akadia.modules.web.models.dto.empresa;

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
