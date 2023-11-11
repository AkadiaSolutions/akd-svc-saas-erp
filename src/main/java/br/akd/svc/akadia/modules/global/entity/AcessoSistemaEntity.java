package br.akd.svc.akadia.modules.global.entity;


import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.enums.ModulosEnum;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.enums.PermissaoEnum;
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
    private String senha;
    private String senhaCriptografada;
    @Enumerated(EnumType.STRING)
    private PermissaoEnum permissaoEnum;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PERFIS")
    protected Set<ModulosEnum> privilegios = new HashSet<>();
}
