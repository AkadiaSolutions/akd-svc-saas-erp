package br.akd.svc.akadia.modules.web.models.entity.empresa;

import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.entity.ColaboradorEntity;
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
