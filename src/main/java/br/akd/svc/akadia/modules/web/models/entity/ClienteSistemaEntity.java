package br.akd.svc.akadia.modules.web.models.entity;

import br.akd.svc.akadia.modules.global.entity.EnderecoEntity;
import br.akd.svc.akadia.modules.global.entity.TelefoneEntity;
import br.akd.svc.akadia.modules.web.models.entity.empresa.EmpresaEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@ToString
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_cli_sistema")
public class ClienteSistemaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String codigoClienteAsaas;

    private String dataCadastro;
    private String horaCadastro;
    private String dataNascimento;

    @Column(unique = true)
    private String email;

    private String nome;

    private String senha;

    @Column(unique = true)
    private String cpf;

    private Double saldo;

    @OneToOne(targetEntity = PlanoEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private PlanoEntity plano;

    @OneToOne(targetEntity = TelefoneEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private TelefoneEntity telefone;

    @OneToOne(targetEntity = EnderecoEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private EnderecoEntity endereco;

    @OneToOne(targetEntity = CartaoEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private CartaoEntity cartao;

    @OneToMany(targetEntity = PagamentoSistemaEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<PagamentoSistemaEntity> pagamentos = new ArrayList<>();

    @OneToMany(targetEntity = EmpresaEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<EmpresaEntity> empresas = new ArrayList<>();
}
