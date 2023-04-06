package br.akd.svc.akadia.models.dto.global;

import br.akd.svc.akadia.models.enums.global.EstadoEnum;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDto {
    private Long id;
    private String logradouro;
    private Integer numero;
    private String bairro;
    private String codigoPostal;
    private String cidade;

    private String complemento;
    private EstadoEnum estado;

}