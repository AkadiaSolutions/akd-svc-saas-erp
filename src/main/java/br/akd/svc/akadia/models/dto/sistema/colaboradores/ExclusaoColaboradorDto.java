package br.akd.svc.akadia.models.dto.sistema.colaboradores;

import br.akd.svc.akadia.models.entities.sistema.colaboradores.ColaboradorEntity;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ExclusaoColaboradorDto {
    private Long id;
    private String dataExclusao;
    private String horaExclusao;
    private Boolean excluido;
    private ColaboradorEntity responsavelExclusao;
}
