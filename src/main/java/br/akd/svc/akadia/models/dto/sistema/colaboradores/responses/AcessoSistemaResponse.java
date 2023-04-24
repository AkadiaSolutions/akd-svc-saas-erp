package br.akd.svc.akadia.models.dto.sistema.colaboradores.responses;

import br.akd.svc.akadia.models.entities.sistema.colaboradores.ModulosEnum;
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
    private Set<ModulosEnum> privilegios = new HashSet<>();
}
