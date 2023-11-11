package br.akd.svc.akadia.modules.erp.colaboradores.advertencia.models.entity;

import br.akd.svc.akadia.modules.global.entity.ArquivoEntity;
import br.akd.svc.akadia.modules.erp.colaboradores.advertencia.models.enums.StatusAdvertenciaEnum;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_advertencia")
public class AdvertenciaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dataCadastro;
    private String horaCadastro;
    private String motivo;

    @Column(length = 600)
    private String descricao;

    @Enumerated(EnumType.STRING)
    private StatusAdvertenciaEnum statusAdvertenciaEnum;

    @OneToOne(targetEntity = ArquivoEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private ArquivoEntity advertenciaAssinada;
}
