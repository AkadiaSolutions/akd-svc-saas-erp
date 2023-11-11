package br.akd.svc.akadia.modules.web.models.entity;

import br.akd.svc.akadia.modules.web.models.enums.FormaPagamentoSistemaEnum;
import br.akd.svc.akadia.modules.web.models.enums.StatusPagamentoSistemaEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@ToString
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_pag_sistema")
public class PagamentoSistemaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dataCadastro;
    private String horaCadastro;
    private String dataPagamento;
    private String horaPagamento;
    @Column(unique = true)
    private String codigoPagamentoAsaas;
    private Double valor;
    private Double valorLiquido;
    private String descricao;
    private String dataVencimento;

    @Enumerated(EnumType.STRING)
    private FormaPagamentoSistemaEnum formaPagamentoSistemaEnum;

    @Enumerated(EnumType.STRING)
    private StatusPagamentoSistemaEnum statusPagamentoSistemaEnum;

    @ManyToOne(targetEntity = CartaoEntity.class)
    @JoinColumn(name = "id_cartao")
    private CartaoEntity cartao;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne (targetEntity = ClienteSistemaEntity.class)
    @JoinColumn(name = "id_cli_sistema")
    private ClienteSistemaEntity clienteSistema;

}
