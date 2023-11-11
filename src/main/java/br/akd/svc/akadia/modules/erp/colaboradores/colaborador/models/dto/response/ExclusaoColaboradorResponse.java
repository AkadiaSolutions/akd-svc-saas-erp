package br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ExclusaoColaboradorResponse {
    private String dataExclusao;
    private String horaExclusao;
    private Boolean excluido;
}
