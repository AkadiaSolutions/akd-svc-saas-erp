package br.akd.svc.akadia.models.dto.global;

import br.akd.svc.akadia.models.enums.global.GrauParentescoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParentescoDto {
    private Long id;
    private String nome;
    private String dataNascimento;
    private String cpf;
    private GrauParentescoEnum grauParentescoEnum;
    private TelefoneDto telefone;

}
