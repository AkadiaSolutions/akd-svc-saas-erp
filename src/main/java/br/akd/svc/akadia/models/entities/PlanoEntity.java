package br.akd.svc.akadia.models.entities;

import br.akd.svc.akadia.models.enums.FormaPagamentoSistemaEnum;
import br.akd.svc.akadia.models.enums.StatusPlanoEnum;
import br.akd.svc.akadia.models.enums.TipoPlanoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_plano")
public class PlanoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dataContratacao;

    private String horaContratacao;

    private Double valor;

    private Integer diasRestantes;

    private Integer qtdLimiteEmpresasCadastradas;

    private Boolean ativo;

    @Enumerated(EnumType.STRING)
    private TipoPlanoEnum tipoPlanoEnum;

    @Enumerated(EnumType.STRING)
    private StatusPlanoEnum statusPlanoEnum;

    @Enumerated(EnumType.STRING)
    private FormaPagamentoSistemaEnum formaPagamentoSistemaEnum;

    @OneToOne (targetEntity = ClienteSistemaEntity.class)
    @JoinColumn(name = "id_cli_sistema")
    private ClienteSistemaEntity clienteSistema;

}
