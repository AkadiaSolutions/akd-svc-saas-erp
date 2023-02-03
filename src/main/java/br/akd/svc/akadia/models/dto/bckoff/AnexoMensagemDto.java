package br.akd.svc.akadia.models.dto.bckoff;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AnexoMensagemDto {
    private Long id;
    private byte[] dados;
    private String nome;
    private String tipo;
}