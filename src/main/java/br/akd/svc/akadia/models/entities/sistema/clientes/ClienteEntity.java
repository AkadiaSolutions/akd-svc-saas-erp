package br.akd.svc.akadia.models.entities.sistema.clientes;

import br.akd.svc.akadia.models.entities.global.EnderecoEntity;
import br.akd.svc.akadia.models.entities.global.TelefoneEntity;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.ColaboradorEntity;
import br.akd.svc.akadia.models.entities.site.empresa.EmpresaEntity;
import br.akd.svc.akadia.models.enums.sistema.clientes.StatusClienteEnum;
import br.akd.svc.akadia.models.enums.sistema.clientes.TipoPessoaEnum;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_cliente")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private String dataCadastro;

    @Column(nullable = false, updatable = false)
    private String horaCadastro;
    private String dataNascimento;
    @Column(nullable = false)
    private String nome;
    private String cpfCnpj;
    private String inscricaoEstadual;
    private String email;
    @Enumerated(EnumType.STRING)
    private StatusClienteEnum statusCliente;
    @Enumerated(EnumType.STRING)
    private TipoPessoaEnum tipoPessoa;
    private Integer qtdOrdensRealizadas;
    private Double giroTotal;
    @OneToOne(targetEntity = ExclusaoClienteEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private ExclusaoClienteEntity exclusaoCliente;
    @OneToOne(targetEntity = EnderecoEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private EnderecoEntity endereco;
    @OneToOne(targetEntity = TelefoneEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private TelefoneEntity telefone;
    @ManyToOne(targetEntity = ColaboradorEntity.class)
    @JoinColumn(name = "id_colab_responsavel")
    private ColaboradorEntity colaboradorResponsavel;
    @ManyToOne(targetEntity = EmpresaEntity.class)
    @JoinColumn(name = "id_empresa")
    private EmpresaEntity empresa;

}
