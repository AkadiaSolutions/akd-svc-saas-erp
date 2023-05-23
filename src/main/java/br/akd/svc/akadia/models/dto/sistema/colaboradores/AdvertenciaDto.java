package br.akd.svc.akadia.models.dto.sistema.colaboradores;

import br.akd.svc.akadia.models.entities.global.ArquivoEntity;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.StatusAdvertenciaEnum;
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
    private StatusAdvertenciaEnum statusAdvertenciaEnum;
    private ArquivoEntity advertenciaAssinada;
}
