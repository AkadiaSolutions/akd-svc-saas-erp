package br.akd.svc.akadia.models.entities.global;

import br.akd.svc.akadia.models.entities.bckoff.LeadEntity;
import br.akd.svc.akadia.models.entities.site.ClienteSistemaEntity;
import br.akd.svc.akadia.models.entities.site.EmpresaEntity;
import br.akd.svc.akadia.models.enums.global.TipoTelefoneEnum;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_empresa")
public class TelefoneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer prefixo;

    private Long numero;

    @Enumerated(EnumType.STRING)
    private TipoTelefoneEnum tipoTelefoneEnum;

    @OneToOne(targetEntity = ClienteSistemaEntity.class)
    @JoinColumn(name = "id_cli_sistema")
    private ClienteSistemaEntity clienteSistema;

    @OneToOne(targetEntity = ParentescoEntity.class)
    @JoinColumn(name = "id_parentesco")
    private ParentescoEntity parentesco;

//TODO HABILITAR FORNECEDOR NO SERVIÇO DO SISTEMA
//    @OneToOne (targetEntity = FornecedorEntity.class)
//    @JoinColumn(name = "id_fornecedor")
//    private FornecedorEntity fornecedor;

//TODO HABILITAR CLIENTE NO SERVIÇO DO SISTEMA
//    @OneToOne (targetEntity = ClienteEntity.class)
//    @JoinColumn(name = "id_cliente")
//    private ClienteEntity cliente;

    @OneToOne(targetEntity = EmpresaEntity.class)
    @JoinColumn(name = "id_empresa")
    private EmpresaEntity empresa;

    @OneToOne(targetEntity = LeadEntity.class)
    @JoinColumn(name = "id_lead")
    private LeadEntity lead;
}
