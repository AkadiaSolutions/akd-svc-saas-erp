package br.akd.svc.akadia.models.entities.sistema.colaboradores;

import lombok.*;

import javax.persistence.*;

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
}
