package br.akd.svc.akadia.models.dto.sistema.colaboradores;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AdvertenciaDto {
    private Long id;
    private String dataCadastro;
    private String horaCadastro;
    private String motivo;
    private String descricao;
    private byte[] advertenciaAssinada;
}
