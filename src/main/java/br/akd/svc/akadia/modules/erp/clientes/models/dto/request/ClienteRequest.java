package br.akd.svc.akadia.modules.erp.clientes.models.dto.request;

import br.akd.svc.akadia.modules.global.dto.EnderecoDto;
import br.akd.svc.akadia.modules.global.dto.TelefoneDto;
import br.akd.svc.akadia.modules.erp.clientes.models.enums.StatusClienteEnum;
import br.akd.svc.akadia.modules.erp.clientes.models.enums.TipoPessoaEnum;
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
