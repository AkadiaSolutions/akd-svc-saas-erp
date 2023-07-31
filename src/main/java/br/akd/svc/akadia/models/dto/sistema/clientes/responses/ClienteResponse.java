package br.akd.svc.akadia.models.dto.sistema.clientes.responses;

import br.akd.svc.akadia.models.entities.global.EnderecoEntity;
import br.akd.svc.akadia.models.entities.global.ExclusaoEntity;
import br.akd.svc.akadia.models.entities.global.TelefoneEntity;
import br.akd.svc.akadia.models.enums.sistema.clientes.StatusClienteEnum;
import br.akd.svc.akadia.models.enums.sistema.clientes.TipoPessoaEnum;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponse {
    private Long id;
    private String dataCadastro;
    private String horaCadastro;
    private String dataNascimento;
    private String nome;
    private String cpfCnpj;
    private String inscricaoEstadual;
    private String email;
    private StatusClienteEnum statusCliente;
    private TipoPessoaEnum tipoPessoa;
    private Integer qtdOrdensRealizadas;
    private Double giroTotal;
    private ExclusaoEntity exclusaoEntity;
    private EnderecoEntity endereco;
    private TelefoneEntity telefone;
    private String nomeColaboradorResponsavel;
}
