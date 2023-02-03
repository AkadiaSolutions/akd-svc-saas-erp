package br.akd.svc.akadia.models.entities.site;

import br.akd.svc.akadia.models.enums.site.FormaPagamentoSistemaEnum;
import br.akd.svc.akadia.models.enums.site.StatusPlanoEnum;
import br.akd.svc.akadia.models.enums.site.TipoPlanoEnum;
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

    private String codigoAssinaturaAsaas;

    private String dataContratacao;

    private String horaContratacao;

    @Enumerated(EnumType.STRING)
    private TipoPlanoEnum tipoPlanoEnum;

    @Enumerated(EnumType.STRING)
    private StatusPlanoEnum statusPlanoEnum;

    @Enumerated(EnumType.STRING)
    private FormaPagamentoSistemaEnum formaPagamentoSistemaEnum;

}
