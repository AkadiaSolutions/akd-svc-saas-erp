package br.akd.svc.akadia.models.entities.sistema.colaboradores;

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
