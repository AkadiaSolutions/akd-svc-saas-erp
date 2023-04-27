package br.akd.svc.akadia.models.dto.sistema.colaboradores.responses;

import br.akd.svc.akadia.models.enums.sistema.colaboradores.ModulosEnum;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.PermissaoEnum;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AcessoSistemaResponse {
    private Boolean acessoSistemaAtivo;
    private String nomeUsuario;
    private PermissaoEnum permissaoEnum;
    private Set<ModulosEnum> privilegios = new HashSet<>();
}
