package br.akd.svc.akadia.models.entities.sistema.colaboradores;

import br.akd.svc.akadia.models.enums.sistema.colaboradores.TemaTelaEnum;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_config_perfil")
public class ConfiguracaoPerfilEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dataUltimaAtualizacao;
    private String horaUltimaAtualizacao;

    @Enumerated(EnumType.STRING)
    private TemaTelaEnum temaTelaEnum;
}
