package br.akd.svc.akadia.models.entities.sistema.colaboradores;


import br.akd.svc.akadia.models.enums.sistema.colaboradores.ModulosEnum;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_acesso_sistema")
public class AcessoSistemaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean acessoSistemaAtivo;
    private String nomeUsuario;
    private String senha;
    private String senhaCriptografada;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PERFIS")
    protected Set<ModulosEnum> privilegios = new HashSet<>();
}
