package br.akd.svc.akadia.models.entities.site;

import br.akd.svc.akadia.models.enums.site.BandeiraCartaoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_cartao")
public class CartaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpfCnpj;

    private Long numero;

    private Integer cvv;

    private Integer mesExpiracao;

    private Integer anoExpiracao;

    private String tokenCartao;

    private Boolean ativo;

    @Enumerated(EnumType.STRING)
    private BandeiraCartaoEnum bandeiraCartaoEnum;

    @ManyToOne(targetEntity = ClienteSistemaEntity.class)
    @JoinColumn(name = "id_cli_sistema")
    private ClienteSistemaEntity clienteSistema;

    @OneToMany(targetEntity = PagamentoSistemaEntity.class)
    private List<PagamentoSistemaEntity> pagamentos = new ArrayList<>();

}
