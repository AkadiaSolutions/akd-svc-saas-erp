package br.akd.svc.akadia.modules.backoffice.models.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@ToString
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
}
