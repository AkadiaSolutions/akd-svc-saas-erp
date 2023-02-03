package br.akd.svc.akadia.models.dto.bckoff;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnexoMensagemDto {
    private Long id;
    private byte[] dados;
    private String nome;
    private String tipo;
}