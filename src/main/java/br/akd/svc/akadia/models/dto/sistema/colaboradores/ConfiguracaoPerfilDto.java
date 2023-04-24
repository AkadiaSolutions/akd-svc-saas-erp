package br.akd.svc.akadia.models.dto.sistema.colaboradores;

import br.akd.svc.akadia.models.enums.sistema.colaboradores.TemaTelaEnum;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ConfiguracaoPerfilDto {
    private Long id;
    private String dataUltimaAtualizacao;
    private String horaUltimaAtualizacao;
    private TemaTelaEnum temaTelaEnum;
}
