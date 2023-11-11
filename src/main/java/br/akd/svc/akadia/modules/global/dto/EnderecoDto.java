package br.akd.svc.akadia.modules.global.dto;

import br.akd.svc.akadia.modules.global.enums.EstadoEnum;
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