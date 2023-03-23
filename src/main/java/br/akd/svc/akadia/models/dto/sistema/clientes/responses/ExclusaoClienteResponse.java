package br.akd.svc.akadia.models.dto.sistema.clientes.responses;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ExclusaoClienteResponse {
    private String dataExclusao;
    private String horaExclusao;
    private Boolean excluido;
}
