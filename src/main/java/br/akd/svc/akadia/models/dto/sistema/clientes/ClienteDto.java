package br.akd.svc.akadia.models.dto.sistema.clientes;

import br.akd.svc.akadia.models.dto.global.EnderecoDto;
import br.akd.svc.akadia.models.dto.global.TelefoneDto;
import br.akd.svc.akadia.models.dto.sistema.colaboradores.ColaboradorDto;
import br.akd.svc.akadia.models.dto.site.empresa.EmpresaDto;
import br.akd.svc.akadia.models.enums.sistema.clientes.StatusClienteEnum;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto {

    private Long id;
    private String dataCadastro;
    private String horaCadastro;
    private String dataNascimento;
    private String nome;
    private String cpfCnpj;
    private String inscricaoEstadual;
    private String email;
    private StatusClienteEnum statusCliente;
    private Integer qtdOrdensRealizadas;
    private Double giroTotal;
    private ExclusaoClienteDto exclusaoCliente;
    private EnderecoDto endereco;
    private TelefoneDto telefone;
    private ColaboradorDto colaboradorResponsavel;
    private EmpresaDto empresa;
}