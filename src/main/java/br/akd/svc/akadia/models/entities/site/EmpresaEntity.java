package br.akd.svc.akadia.models.entities.site;

import br.akd.svc.akadia.models.entities.bckoff.ChamadoEntity;
import br.akd.svc.akadia.models.entities.global.EnderecoEntity;
import br.akd.svc.akadia.models.entities.global.TelefoneEntity;
import br.akd.svc.akadia.models.entities.site.fiscal.ConfigFiscalEmpresaEntity;
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

    private String razaoSocial;

    private String cnpj;
    private String endpoint;

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

    @OneToOne(targetEntity = ConfigFiscalEmpresaEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private ConfigFiscalEmpresaEntity configFiscalEmpresa;

    @OneToOne(targetEntity = EnderecoEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private EnderecoEntity endereco;

    @OneToMany(targetEntity = ChamadoEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<ChamadoEntity> chamados = new ArrayList<>();

}
