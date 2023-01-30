package br.akd.svc.akadia.models.entities;

import br.akd.svc.akadia.models.enums.TipoTelefoneEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
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
    private TipoTelefoneEnum tioTelefoneEnum;

    @OneToOne (targetEntity = ClienteEntity.class)
    @JoinColumn(name = "id_cliente")
    private ClienteEntity clienteEntity;

    @OneToOne (targetEntity = ClienteSistemaEntity.class)
    @JoinColumn(name = "id_cli_sistema")
    private ClienteSistemaEntity clienteSistema;

    private FornecedorEntity fornecedor;

    @OneToOne (targetEntity = EmpresaEntity.class)
    @JoinColumn(name = "id_empresa")
    private EmpresaEntity empresaEntity;
}
