package br.akd.svc.akadia.models.entities.global;

import br.akd.svc.akadia.models.entities.site.ClienteSistemaEntity;
import br.akd.svc.akadia.models.entities.site.EmpresaEntity;
import br.akd.svc.akadia.models.enums.global.EstadoEnum;
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
@Table(name = "tb_endereco")
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String logradouro;

    private Integer numero;
    private String bairro;
    private String codigoPostal;
    private String cidade;

    @Enumerated(EnumType.STRING)
    private EstadoEnum estadoEnum;

    @OneToOne(targetEntity = ClienteEntity.class)
    @JoinColumn(name = "id_cliente")
    private ClienteEntity cliente;

    @OneToOne(targetEntity = ClienteSistemaEntity.class)
    @JoinColumn(name = "id_cli_sistema")
    private ClienteSistemaEntity clienteSistema;

    @OneToOne(targetEntity = FornecedorEntity.class)
    @JoinColumn(name = "id_fornecedor")
    private FornecedorEntity fornecedor;

    @OneToOne(targetEntity = EmpresaEntity.class)
    @JoinColumn(name = "id_empresa")
    private EmpresaEntity empresa;

}
