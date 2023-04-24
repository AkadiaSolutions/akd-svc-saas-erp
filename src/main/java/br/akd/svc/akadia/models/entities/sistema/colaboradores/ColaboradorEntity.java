package br.akd.svc.akadia.models.entities.sistema.colaboradores;

import br.akd.svc.akadia.models.entities.global.EnderecoEntity;
import br.akd.svc.akadia.models.entities.global.ParentescoEntity;
import br.akd.svc.akadia.models.entities.global.TelefoneEntity;
import br.akd.svc.akadia.models.entities.site.empresa.EmpresaEntity;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.ModeloContratacaoEnum;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.ModeloTrabalhoEnum;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.StatusColaboradorEnum;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.TipoOcupacaoEnum;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_colaborador")
public class ColaboradorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dataCadastro;

    private String horaCadastro;

    @Column(unique = true)
    private Long matricula;

    @Lob
    private byte[] fotoPerfil;

    private String nome;

    private String dataNascimento;

    private String email;

    private String cpfCnpj;

    private Boolean ativo;

    private Double salario;

    private String entradaEmpresa;

    private String saidaEmpresa;

    @Lob
    private byte[] contratoContratacao;

    private String ocupacao;

    @Enumerated(EnumType.STRING)
    private TipoOcupacaoEnum tipoOcupacaoEnum;

    @Enumerated(EnumType.STRING)
    private ModeloContratacaoEnum modeloContratacaoEnum;

    @Enumerated(EnumType.STRING)
    private ModeloTrabalhoEnum modeloTrabalhoEnum;

    @Enumerated(EnumType.STRING)
    private StatusColaboradorEnum statusColaboradorEnum;

    @OneToOne(targetEntity = ExclusaoColaboradorEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private ExclusaoColaboradorEntity exclusao;

    @OneToOne(targetEntity = AcessoSistemaEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private AcessoSistemaEntity acessoSistema;

    @OneToOne(targetEntity = ConfiguracaoPerfilEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private ConfiguracaoPerfilEntity configuracaoPerfil;

    @OneToOne(targetEntity = EnderecoEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private EnderecoEntity endereco;

    @OneToOne(targetEntity = TelefoneEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private TelefoneEntity telefone;

    @OneToOne(targetEntity = ExpedienteEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private ExpedienteEntity expediente;

    @OneToOne(targetEntity = DispensaEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private DispensaEntity dispensa;

    @OneToMany(targetEntity = PontoEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<PontoEntity> pontos = new ArrayList<>();

    @OneToMany(targetEntity = FeriasEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<FeriasEntity> historicoFerias = new ArrayList<>();

    @OneToMany(targetEntity = AdvertenciaEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<AdvertenciaEntity> advertencias = new ArrayList<>();

    @OneToMany(targetEntity = ParentescoEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<ParentescoEntity> parentescos = new ArrayList<>();

    @ManyToOne(targetEntity = EmpresaEntity.class)
    @JoinColumn(name = "id_empresa")
    private EmpresaEntity empresa;

}
