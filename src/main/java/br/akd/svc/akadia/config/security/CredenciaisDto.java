package br.akd.svc.akadia.config.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CredenciaisDto {

    private String nomeUsuario;
    private String senha;

}
