package br.akd.svc.akadia.models.entities.site.empresa;

import br.akd.svc.akadia.models.entities.sistema.colaboradores.ColaboradorEntity;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CriaEmpresaResponse {
    private Long idClienteEmpresa;
    private ColaboradorEntity colaboradorCriado;
}
