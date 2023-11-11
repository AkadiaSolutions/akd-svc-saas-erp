package br.akd.svc.akadia.modules.backoffice.models.entity;

import br.akd.svc.akadia.modules.backoffice.models.enums.CaminhoMensagemEnum;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
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
    @ToString.Exclude
    private List<AnexoMensagemEntity> anexos = new ArrayList<>();
}
