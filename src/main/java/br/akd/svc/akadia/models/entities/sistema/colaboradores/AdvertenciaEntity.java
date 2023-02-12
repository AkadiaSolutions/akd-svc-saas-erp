package br.akd.svc.akadia.models.entities.sistema.colaboradores;

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

    private String descricao;

    @Lob
    private byte[] advertenciaAssinada;

}
