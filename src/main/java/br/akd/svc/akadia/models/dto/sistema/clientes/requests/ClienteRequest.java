package br.akd.svc.akadia.models.dto.sistema.clientes.requests;

import br.akd.svc.akadia.models.dto.global.EnderecoDto;
import br.akd.svc.akadia.models.dto.global.TelefoneDto;
import br.akd.svc.akadia.models.enums.sistema.clientes.StatusClienteEnum;
import br.akd.svc.akadia.models.enums.sistema.clientes.TipoPessoaEnum;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequest {
    private Long id;
    private String dataNascimento;
    private String nome;
    private String cpfCnpj;
    private String inscricaoEstadual;
    private String email;
    private StatusClienteEnum statusCliente;
    private TipoPessoaEnum tipoPessoa;
    private EnderecoDto endereco;
    private TelefoneDto telefone;
}
