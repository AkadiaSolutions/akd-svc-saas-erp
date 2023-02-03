package br.akd.svc.akadia.models.entities.bckoff;

import br.akd.svc.akadia.models.entities.global.ParentescoEntity;
import br.akd.svc.akadia.models.entities.global.TelefoneEntity;
import br.akd.svc.akadia.models.enums.bckoff.CargoInternoEnum;
import br.akd.svc.akadia.models.enums.bckoff.StatusAtividadeEnum;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_colab_interno")
public class ColaboradorInternoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dataCadastro;

    private String horaCadastro;

    private String nome;

    private String email;

    private String cpf;

    private Boolean acessoSistemaLiberado;

    private String dataNascimento;

    private Double remuneracao;

    private Integer tempoFerias;

    private String entradaEmpresa;

    private String saidaEmpresa;

    @Enumerated(EnumType.STRING)
    private CargoInternoEnum cargoEnum;

    @Enumerated(EnumType.STRING)
    private StatusAtividadeEnum statusAtividadeEnum;

    @OneToOne(targetEntity = TelefoneEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private TelefoneEntity telefone;

    @OneToMany(targetEntity = ParentescoEntity.class, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<ParentescoEntity> parentescos = new ArrayList<>();

    @OneToMany(targetEntity = ChamadoEntity.class, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<ChamadoEntity> chamados = new ArrayList<>();
}
