package br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_ferias")
public class FeriasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dataCadastro;
    private String horaCadastro;
    private Integer totalDias;
    private String dataInicio;
    private String dataFim;
}
