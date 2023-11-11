package br.akd.svc.akadia.modules.web.models.entity;

import br.akd.svc.akadia.modules.web.models.enums.FormaPagamentoSistemaEnum;
import br.akd.svc.akadia.modules.web.models.enums.StatusPlanoEnum;
import br.akd.svc.akadia.modules.web.models.enums.TipoPlanoEnum;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@ToString
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_plano")
public class PlanoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String codigoAssinaturaAsaas;

    private String dataContratacao;

    private String horaContratacao;

    private String dataVencimento;

    @Enumerated(EnumType.STRING)
    private TipoPlanoEnum tipoPlanoEnum;

    @Enumerated(EnumType.STRING)
    private StatusPlanoEnum statusPlanoEnum;

    @Enumerated(EnumType.STRING)
    private FormaPagamentoSistemaEnum formaPagamentoSistemaEnum;

}
