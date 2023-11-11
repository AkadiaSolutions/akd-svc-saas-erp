package br.akd.svc.akadia.modules.erp.colaboradores.acao.models.entity;

import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.enums.ModulosEnum;
import br.akd.svc.akadia.modules.erp.colaboradores.acao.models.enums.TipoAcaoEnum;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_acao")
public class AcaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idObjeto;
    private String dataCriacao;
    private String horaCriacao;
    @Enumerated(EnumType.STRING)
    private ModulosEnum moduloEnum;
    @Enumerated(EnumType.STRING)
    private TipoAcaoEnum tipoAcaoEnum;
    private String observacao;

}
