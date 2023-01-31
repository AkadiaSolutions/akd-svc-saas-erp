package br.akd.svc.akadia.models.entities.bckoff;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_anexo_msg")
public class AnexoMensagemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[] dados;

    private String nome;

    private String tipo;

    @ManyToOne(targetEntity = MensagemEntity.class)
    @JoinColumn(name = "id_mensagem")
    private MensagemEntity mensagem;

}
