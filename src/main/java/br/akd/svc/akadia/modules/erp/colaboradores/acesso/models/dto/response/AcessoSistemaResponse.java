package br.akd.svc.akadia.modules.erp.colaboradores.acesso.models.dto.response;

import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.enums.ModulosEnum;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.enums.PermissaoEnum;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AcessoSistemaResponse {
    private Boolean acessoSistemaAtivo;
    private PermissaoEnum permissaoEnum;

    @ToString.Exclude
    @Builder.Default
    private Set<ModulosEnum> privilegios = new HashSet<>();
}
