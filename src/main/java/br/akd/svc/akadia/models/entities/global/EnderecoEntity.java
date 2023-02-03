package br.akd.svc.akadia.models.entities.global;

import br.akd.svc.akadia.models.enums.global.EstadoEnum;
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

    @Enumerated(EnumType.STRING)
    private EstadoEnum estadoEnum;

}
