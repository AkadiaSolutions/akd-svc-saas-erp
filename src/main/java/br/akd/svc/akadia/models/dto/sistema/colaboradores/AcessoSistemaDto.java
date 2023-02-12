package br.akd.svc.akadia.models.dto.sistema.colaboradores;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AcessoSistemaDto {
    private Long id;
    private Boolean acessoSistemaAtivo;
    private String nomeUsuario;
    private String senha;
    private String senhaCriptografada;
}
