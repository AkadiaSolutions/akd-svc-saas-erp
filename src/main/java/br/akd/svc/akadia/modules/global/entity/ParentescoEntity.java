package br.akd.svc.akadia.modules.global.entity;

import br.akd.svc.akadia.modules.global.enums.GrauParentescoEnum;
import lombok.*;

import javax.persistence.*;

@Entity
@ToString
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_parentesco")
public class ParentescoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String dataNascimento;

    private String cpf;

    @Enumerated(EnumType.STRING)
    private GrauParentescoEnum grauParentescoEnum;

    @OneToOne(targetEntity = TelefoneEntity.class, cascade = CascadeType.ALL, orphanRemoval = true)
    private TelefoneEntity telefone;

}
