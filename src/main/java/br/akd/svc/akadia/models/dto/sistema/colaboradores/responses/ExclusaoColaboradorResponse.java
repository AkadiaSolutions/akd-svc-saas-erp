package br.akd.svc.akadia.models.dto.sistema.colaboradores.responses;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExclusaoColaboradorResponse {
    private String dataExclusao;
    private String horaExclusao;
    private Boolean excluido;
}
