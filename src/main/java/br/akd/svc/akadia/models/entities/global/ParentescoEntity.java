package br.akd.svc.akadia.models.entities.global;

import br.akd.svc.akadia.models.entities.bckoff.ColaboradorInternoEntity;
import br.akd.svc.akadia.models.enums.global.GrauParentescoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
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

    @ManyToOne(targetEntity = ColaboradorEntity.class)
    @JoinColumn(name = "id_colaborador")
    private ColaboradorEntity colaborador;

    @ManyToOne(targetEntity = ColaboradorInternoEntity.class)
    @JoinColumn(name = "id_colab_interno")
    private ColaboradorInternoEntity colaboradorInternoEntity;

}
