package br.akd.svc.akadia.models.dto.bckoff;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AvaliacaoDto {
    private Long id;
    private Integer nota;
    private String descricao;
}
