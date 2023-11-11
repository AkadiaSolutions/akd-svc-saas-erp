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
@Table(name = "tb_ponto")
public class PontoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String horaEntrada;
    private String horaSaidaAlmoco;
    private String horaEntradaAlmoco;
    private String horaSaida;
}
