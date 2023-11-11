package br.akd.svc.akadia.modules.erp.clientes.models.dto.response;

import br.akd.svc.akadia.modules.global.entity.EnderecoEntity;
import br.akd.svc.akadia.modules.global.entity.ExclusaoEntity;
import br.akd.svc.akadia.modules.global.entity.TelefoneEntity;
import br.akd.svc.akadia.modules.erp.clientes.models.enums.StatusClienteEnum;
import br.akd.svc.akadia.modules.erp.clientes.models.enums.TipoPessoaEnum;
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
