package br.akd.svc.akadia.models.entities.sistema.colaboradores;

import br.akd.svc.akadia.models.enums.sistema.colaboradores.EscalaEnum;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_expediente")
public class ExpedienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String horaEntrada;
    private String horaSaidaAlmoco;
    private String horaEntradaAlmoco;
    private String horaSaida;
    private String cargaHorariaSemanal;
    private EscalaEnum escalaEnum;
}
