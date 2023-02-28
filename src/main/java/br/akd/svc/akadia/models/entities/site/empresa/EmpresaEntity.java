package br.akd.svc.akadia.models.entities.site.empresa;

import br.akd.svc.akadia.models.entities.bckoff.ChamadoEntity;
import br.akd.svc.akadia.models.entities.global.EnderecoEntity;
import br.akd.svc.akadia.models.entities.global.TelefoneEntity;
import br.akd.svc.akadia.models.entities.site.empresa.fiscal.ConfigFiscalEmpresaEntity;
import br.akd.svc.akadia.models.enums.site.SegmentoEmpresaEnum;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Builder
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

    @Column(unique = true)
    private String razaoSocial;

    @Column(unique = true)
    private String cnpj;

    @Column(unique = true)
    private String endpoint;

    private String email;

    private String nomeFantasia;

    @Column(unique = true)
    private String inscricaoEstadual;

    @Column(unique = true)
    private String inscricaoMunicipal;

    private String nomeResponsavel;

    private String cpfResponsavel;

    private byte[] logo;

    private Boolean deletada;

    @OneToOne(targetEntity = DadosEmpresaDeletadaEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private DadosEmpresaDeletadaEntity dadosEmpresaDeletada;

    @Enumerated(EnumType.STRING)
    private SegmentoEmpresaEnum segmentoEmpresaEnum;

    @OneToOne(targetEntity = TelefoneEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private TelefoneEntity telefone;

    @OneToOne(targetEntity = ConfigFiscalEmpresaEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private ConfigFiscalEmpresaEntity configFiscalEmpresa;

    @OneToOne(targetEntity = EnderecoEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private EnderecoEntity endereco;

    @OneToMany(targetEntity = ChamadoEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<ChamadoEntity> chamados = new ArrayList<>();

    //TODO Verificar necessidade de lista de colaboradores para facilitar persistência de empresa e colaborador responsável

}
