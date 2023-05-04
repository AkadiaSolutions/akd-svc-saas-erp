package br.akd.svc.akadia.models.dto.sistema.colaboradores;

import br.akd.svc.akadia.models.enums.sistema.colaboradores.ModulosEnum;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.PermissaoEnum;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AcessoSistemaDto {
    private Long id;
    private Boolean acessoSistemaAtivo;
    private String senha;
    private String senhaCriptografada;
    private PermissaoEnum permissaoEnum;
    private List<ModulosEnum> privilegios = new ArrayList<>();
}
