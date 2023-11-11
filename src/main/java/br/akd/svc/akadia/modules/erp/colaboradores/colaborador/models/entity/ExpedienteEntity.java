package br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.entity;

import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.enums.EscalaEnum;
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
