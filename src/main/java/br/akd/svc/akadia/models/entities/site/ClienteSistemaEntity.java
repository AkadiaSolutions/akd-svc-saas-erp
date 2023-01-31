package br.akd.svc.akadia.models.entities.site;

import br.akd.svc.akadia.models.entities.global.EnderecoEntity;
import br.akd.svc.akadia.models.entities.global.TelefoneEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_cli_sistema")
public class ClienteSistemaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigoClienteAsaas;

    private String dataCadastro;
    private String horaCadastro;

    private String email;

    private String nome;

    private String cpf;

    private Double saldo;

    @OneToOne(targetEntity = PlanoEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private PlanoEntity plano;

    @OneToOne(targetEntity = TelefoneEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private TelefoneEntity telefone;

    @OneToOne(targetEntity = EnderecoEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private EnderecoEntity endereco;

    @OneToMany(targetEntity = PagamentoSistemaEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<PagamentoSistemaEntity> pagamentos = new ArrayList<>();

    @OneToMany(targetEntity = CartaoEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<CartaoEntity> cartoes = new ArrayList<>();

    @OneToMany(targetEntity = EmpresaEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<EmpresaEntity> empresas = new ArrayList<>();
}
