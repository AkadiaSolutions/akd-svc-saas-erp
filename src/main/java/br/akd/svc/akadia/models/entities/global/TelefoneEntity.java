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
}
