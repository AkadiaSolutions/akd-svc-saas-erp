package br.akd.svc.akadia.modules.backoffice.models.dto;

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