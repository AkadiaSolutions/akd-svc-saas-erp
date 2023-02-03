package br.akd.svc.akadia.models.dto.global;

import br.akd.svc.akadia.models.enums.global.GrauParentescoEnum;
import lombok.*;

@Getter
@Setter
@ToString
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
