package br.akd.svc.akadia.modules.erp.colaboradores.acesso.models.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_acesso")
public class AcessoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dataCadastro;
    private String horaCadastro;
}
