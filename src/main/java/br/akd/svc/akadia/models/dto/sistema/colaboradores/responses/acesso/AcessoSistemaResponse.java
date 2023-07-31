package br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.acesso;

import br.akd.svc.akadia.models.enums.sistema.colaboradores.ModulosEnum;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.PermissaoEnum;
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
