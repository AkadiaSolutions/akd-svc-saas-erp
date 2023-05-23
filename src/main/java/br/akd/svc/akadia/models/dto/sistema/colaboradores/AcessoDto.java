package br.akd.svc.akadia.models.dto.sistema.colaboradores;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AcessoDto {
    private Long id;
    private String dataCadastro;
    private String horaCadastro;
}