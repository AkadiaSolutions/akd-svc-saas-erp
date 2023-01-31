package br.akd.svc.akadia.models.dto.bckoff;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AvaliacaoDto {
    private Long id;
    private Integer nota;
    private String descricao;
    private ChamadoDto chamado;
}
