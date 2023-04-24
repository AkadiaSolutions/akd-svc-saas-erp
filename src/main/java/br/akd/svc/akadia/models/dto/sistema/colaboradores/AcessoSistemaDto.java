package br.akd.svc.akadia.models.dto.sistema.colaboradores;

import br.akd.svc.akadia.models.entities.sistema.colaboradores.ModulosEnum;
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
    private String nomeUsuario;
    private String senha;
    private String senhaCriptografada;
    private List<ModulosEnum> privilegios = new ArrayList<>();
}
