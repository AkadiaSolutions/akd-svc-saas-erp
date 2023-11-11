package br.akd.svc.akadia.modules.backoffice.models.entity;

import br.akd.svc.akadia.modules.global.entity.TelefoneEntity;
import br.akd.svc.akadia.modules.backoffice.models.enums.OrigemLeadEnum;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@ToString
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_lead")
public class LeadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    @Enumerated(EnumType.STRING)
    private OrigemLeadEnum origemLeadEnum;

    @OneToOne(targetEntity = TelefoneEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private TelefoneEntity telefone;

}
