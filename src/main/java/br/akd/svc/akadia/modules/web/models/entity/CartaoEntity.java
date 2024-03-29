package br.akd.svc.akadia.modules.web.models.entity;

import br.akd.svc.akadia.modules.web.models.enums.BandeiraCartaoEnum;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@ToString
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_cartao")
public class CartaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomePortador;

    private String cpfCnpj;

    private Long numero;

    private Integer ccv;

    private Integer mesExpiracao;

    private Integer anoExpiracao;

    private String tokenCartao;

    private Boolean ativo;

    @Enumerated(EnumType.STRING)
    private BandeiraCartaoEnum bandeiraCartaoEnum;

}
