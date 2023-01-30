package br.akd.svc.akadia.models.entities;

import br.akd.svc.akadia.models.entities.fiscal.ConfigFiscalEmpresaEntity;
import br.akd.svc.akadia.models.enums.SegmentoEmpresaEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_empresa")
public class EmpresaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dataCadastro;
    private String horaCadastro;

    private String nome;

    private String razaoSocial;

    private String cnpj;

    private String email;

    private String nomeFantasia;

    private String inscricaoEstadual;

    private String inscricaoMunicipal;

    private String nomeResponsavel;

    private String cpfResponsavel;

    private byte[] logo;

    @Enumerated(EnumType.STRING)
    private SegmentoEmpresaEnum segmentoEmpresaEnum;

    @OneToOne(targetEntity = TelefoneEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private TelefoneEntity telefone;

    @ManyToOne(targetEntity = ClienteSistemaEntity.class)
    @JoinColumn(name = "cli_sistema_id")
    private ClienteSistemaEntity clienteSistema;

    @OneToOne(targetEntity = ConfigFiscalEmpresaEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private ConfigFiscalEmpresaEntity configFiscalEmpresa;

    @OneToOne(targetEntity = EnderecoEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private EnderecoEntity endereco;

    private List<ChamadoEntity> chamados;

}
