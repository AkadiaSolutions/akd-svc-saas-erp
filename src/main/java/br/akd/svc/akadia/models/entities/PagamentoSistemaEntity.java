package br.akd.svc.akadia.models.entities;

import br.akd.svc.akadia.models.enums.FormaPagamentoSistemaEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
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

    private Long codigoTransacao;

    private Double valor;

    private String vencimento;

    @Enumerated(EnumType.STRING)
    private FormaPagamentoSistemaEnum formaPagamentoSistemaEnum;

    private CartaoEntity cartao;

    @OneToOne (targetEntity = ClienteSistemaEntity.class)
    @JoinColumn(name = "id_cli_sistema")
    private ClienteSistemaEntity clienteSistema;

}
