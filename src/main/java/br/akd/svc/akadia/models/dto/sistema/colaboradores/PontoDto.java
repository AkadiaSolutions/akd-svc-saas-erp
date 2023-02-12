package br.akd.svc.akadia.models.dto.sistema.colaboradores;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PontoDto {
    private Long id;
    private String horaEntrada;
    private String horaSaidaAlmoco;
    private String horaEntradaAlmoco;
    private String horaSaida;
}
