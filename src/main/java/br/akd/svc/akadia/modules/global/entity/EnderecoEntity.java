package br.akd.svc.akadia.modules.global.entity;

import br.akd.svc.akadia.modules.global.enums.EstadoEnum;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@ToString
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_endereco")
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String logradouro;

    private Integer numero;
    private String bairro;
    private String codigoPostal;
    private String cidade;

    private String complemento;

    @Enumerated(EnumType.STRING)
    private EstadoEnum estado;

}
