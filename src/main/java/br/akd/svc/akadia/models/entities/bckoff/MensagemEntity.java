package br.akd.svc.akadia.models.entities.bckoff;

import br.akd.svc.akadia.models.enums.bckoff.CaminhoMensagemEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_mensagem")
public class MensagemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dataEnvio;
    private String horaEnvio;

    private Boolean visualizada;

    private Boolean respondida;

    private String conteudo;

    @Enumerated(EnumType.STRING)
    private CaminhoMensagemEnum caminhoMensagemEnum;

    @OneToMany(targetEntity = AnexoMensagemEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<AnexoMensagemEntity> anexos = new ArrayList<>();

    @ManyToOne(targetEntity = ChamadoEntity.class)
    @JoinColumn(name = "id_chamado")
    private ChamadoEntity chamado;
}
